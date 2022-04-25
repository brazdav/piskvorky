package LAN;

import Rozhrani.FirstTurn;
import Rozhrani.Music;
import Spousteni_hry.Start;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Třída Server dědí z třídy Start
 * Obsahuje konstruktor, který nám spouští server
 * Také obsahuje metodu end, která ukončuje stream serveru a metodu sendEnd, která posílá clientovi informace potřebné pro ukončení
 * @author Vojtěch Brázda
 * @version 1.0.0
 */
public class Server extends Start implements Music {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private BufferedReader input = null;
    private String line = "";
    private String send;
    private Thread thread;
    private Thread thread2;
    private int port = 6669;
    private int indexTlacoPredchozi;
    private int odchozi;
    private int prichozi;
    private Server obj;
    private Piskvorky piskvorky;
    private boolean vypnuto = false;

    /**
     * Konstruktor třídy server, po vytvoření instance třídy spustí server na určeném portu.
     * Po připojení clienta k serveru pomocí IP adresy se provede metoda startLan, která načte hrací pole.
     * Následovně se otevřou všechny ptřebné streamy pro posílání packetů mezi serverem a clientem.
     * V konstruktoru jsou vytvořeny dvě vlákna, aby server mohl posílat a zároveň přijímat packety.
     * Ve vláknech je cyklus, který se ukončí po výhry jednoho z hráčů
     * @param piskvorky objekt, přes který spouštíme metodu componentsOff a componentsOn
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public Server(Piskvorky piskvorky) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(piskvorky,"server");
        this.piskvorky = piskvorky;
        // starts server and waits for a connection
        this.obj = this;
        try {
            if (piskvorky.turn_music)
                piskvorky.waiting.loop(Clip.LOOP_CONTINUOUSLY);
            server = new ServerSocket(port);
            System.out.println("LAN.Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            if (piskvorky.turn_music) {
                piskvorky.waiting.stop();
            }
            System.out.println("LAN.Client accepted");
            startLan(this.obj);
            getServer(obj);
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            input = new BufferedReader(new InputStreamReader(System.in));

            try {
                out.writeUTF(String.valueOf(piskvorky.player1_turn.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }


            // reads message from client until "Over" is sent
            thread = new Thread(() -> {
                while (!line.equals("Over")){
                    getIndexTlaco();
                    if (indexTlacoPredchozi != indexTlaco && prichozi != indexTlaco) {
                        try {
                            out.writeUTF(String.valueOf(indexTlaco));
                            odchozi = indexTlaco;
                            System.out.println(odchozi);
                            for (int i = 0; i < buttons.size(); i++) {
                                piskvorky.componentsOff((JButton) buttons.get(i));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        indexTlacoPredchozi = indexTlaco;
                    }
                }
            });

            thread2 = new Thread(() -> {
                while (!line.equals("Over")) {
                    try {
                        line = in.readUTF();
                        if (!line.equals("Over")) {
                            if (line.equals("true") || line.equals("false")){
                                FirstTurn.server_turn.set(Boolean.parseBoolean(line));
                                if (!FirstTurn.server_turn.get()){
                                    for (Object button2 : buttons) {
                                        piskvorky.componentsOff((JButton) button2);
                                    }
                                }
                                if ((player1_turn.get() && server_turn.get()) || (!player1_turn.get() && !server_turn.get()))
                                    server_znak = "X";
                                else
                                    server_znak = "O";
                            }
                            else if (Integer.parseInt(line) != odchozi) {
                                int index = Integer.parseInt(line);
                                prichozi = index;
                                JButton button = (JButton) buttons.get(index);
                                if (buttons.get(0) != null) {
                                    for (Object button2 : buttons) {
                                        piskvorky.componentsOn((JButton) button2);
                                    }
                                }
                                button.doClick();
                            }
                        }
                        else {
                            System.out.println("dostali jsme over");
                            end();
                            break;
                        }
                    } catch (IOException i) {
                        System.out.println(i);
                        try {
                            if (!vypnuto) {
                                JOptionPane.showMessageDialog(frame, "Client se odpojil");
                                frame.dispose();
                                SettingUpServer obj = new SettingUpServer(piskvorky);
                                end();
                            }
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        break;
                    }catch (UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread2.start();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    /**
     * Metoda end ukončuje všechny zapnuté streamy, které jsou potřebné ke komunikaci serveru s clientem
     * @throws IOException
     */
    public void end() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if ((server_znak.equals("X") && piskvorky.winO < piskvorky.winX) || ((server_znak.equals("O") && piskvorky.winO > piskvorky.winX)))
            win();
        else
            lose();
        vypnuto = true;
        buttons.removeAll(buttons);
        piskvorky.button_panel.removeAll();
        server.close();
        input.close();
        out.close();
        socket.close();
        in.close();
    }


}
