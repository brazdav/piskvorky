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
    private int port = 6669;
    private int indexTlacoPredchozi;
    private int odchozi;
    private int prichozi;
    private Server obj;
    private Piskvorky piskvorky;
    private boolean vypnuto = false;
    private boolean comunication = true;

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
            server_turned_on = true;
            getServer(obj);
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            input = new BufferedReader(new InputStreamReader(System.in));

            sendData();


            thread = new Thread(() -> {
                while (comunication) {
                    try {
                        line = in.readUTF();
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
                    } catch (IOException i) {
                        System.out.println(i);
                        try {
                            if (!vypnuto) {
                                JOptionPane.showMessageDialog(frame, "Client se odpojil");
                                frame.dispose();
                                end();
                            }
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            });
            thread.start();

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
        else if ((server_znak.equals("X") && piskvorky.winO > piskvorky.winX) || ((server_znak.equals("O") && piskvorky.winO < piskvorky.winX)))
            lose();
        restart();
        vypnuto = true;
        piskvorky.button_panel.removeAll();
        comunication = false;
        server.close();
        input.close();
        out.close();
        socket.close();
        in.close();
    }

    public void sendData(){
        try {
            out.writeUTF(String.valueOf(piskvorky.player1_turn.get()));
            out.writeUTF("Kola:" + piskvorky.kolaLan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendIndex(int indexTlaco){
        try {
            if (indexTlaco != prichozi) {
                out.writeUTF(String.valueOf(indexTlaco));
                System.out.println("Odchozí od serveru: " + indexTlaco);
                odchozi = indexTlaco;
                if (buttons.get(0) != null) {
                    for (Object button : buttons) {
                        piskvorky.componentsOff((JButton) button);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
