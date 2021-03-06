package LAN;
import Rozhrani.FirstTurn;
import Spousteni_hry.Start;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Třída Client dědí z třídy Start a implementuje rozhraní FirstTurn
 * Obsahuje konstruktor, který otevírá socket na adrese serveru a portu, na kterém server běží
 * Také obsahuje metodu end, která ukončuje stream serveru a metodu sendEnd, která posílá clientovi informace potřebné pro ukončení
 * @author Vojtěch Brázda
 * @version 1.0.0
 */
public class Client extends Start implements FirstTurn {

    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String line = "";
    private int indexTlacoPredchozi;
    private Thread thread;
    private int odchozi;
    private int prichozi;
    private Piskvorky obj;
    private Client obj2;
    private boolean vypnuto;
    private boolean comunication = true;

    /**
     * Konstruktor třídy client se připojuje k serveru na jeho IP adrese a portu, na kterém server běží
     * Otevírá všechny potřebné streamy pro přenos infomrací mezi serverem a clientem
     * V konstruktoru jsou vytvořeny dvě vlákna, aby client mohl posílat a zároveň přijímat packety.
     * Ve vláknech je cyklus, který se ukončí po výhry jednoho z hráčů
     * @param address řetězec, který se přenáší z formuláře při spuštění clienta, určuje, ke teré IP adrese se bude připojovat
     * @param port číslo, které je předem definováno a určuje na kterém portu bude client přijímat a odeslíat
     * @param piskvorky objekt, přes který spouštíme metodu componentsOff a componentsOn
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    public Client(String address, int port, Piskvorky piskvorky) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(piskvorky, "client");
        // establish a connection
        this.obj2 = this;
        this.obj = piskvorky;
        server_turned_on = true;
        FirstTurn.firstTurnLan();
        System.out.println("Server znak: "+server_znak);

        thread = new Thread(() -> {
            while (comunication) {
                try {
                    line = in.readUTF();
                        System.out.println(line);
                        if (line.equals("true") || line.equals("false")) {
                            FirstTurn.player1_turn.set(Boolean.parseBoolean(line));
                            out.writeUTF(String.valueOf(FirstTurn.server_turn.get()));
                            System.out.println(server_turn.get());
                            if ((player1_turn.get() && server_turn.get()) || (!player1_turn.get() && !server_turn.get()))
                                server_znak = "X";
                            else
                                server_znak = "O";
                            if (server_turn.get()){
                                for (Object button2 : buttons) {
                                    piskvorky.componentsOff((JButton) button2);
                                }
                            }
                        }
                        else if (line.contains("Kola")){
                            String[] arr = line.split(":");
                            piskvorky.kolaLan = Integer.parseInt(arr[1]);
                        }
                        else if (Integer.parseInt(line) != odchozi) {
                            int index = Integer.parseInt(line);
                            prichozi = index;
                            JButton button = (JButton) buttons.get(index);
                            for (Object button2 : buttons) {
                                piskvorky.componentsOn((JButton) button2);
                            }
                            button.doClick();
                            System.out.println("kliknutí na: "+line);
                        }
                } catch (IOException i) {
                    System.out.println(i);
                    try {
                        if (!vypnuto) {
                            JOptionPane.showMessageDialog(frame, "Server se odpojil");
                            frame.dispose();
                            end();
                        }
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            startLan(this.obj2);
            // takes input from terminal
            input = new BufferedReader(new InputStreamReader(System.in));
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            thread.start();
        } catch (IOException u) {
            System.out.println(u);
            SettingUpClient obj = new SettingUpClient(piskvorky);
            JOptionPane.showMessageDialog(obj.dialogy, "Na této adrese není zapnutý žádný server");
        }
    }
    public void sendIndex(int indexTlaco){
        try {
            if (indexTlaco != prichozi) {
                out.writeUTF(String.valueOf(indexTlaco));
                System.out.println("Odchozí od klienta: " + indexTlaco);
                odchozi = indexTlaco;
                if (buttons.get(0) != null) {
                    for (Object button : buttons) {
                        obj.componentsOff((JButton) button);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda end ukončuje komunikaci se serverem
     * @throws IOException
     */
    public void end() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        vypnuto = true;
        if ((server_znak.equals("X") && obj.winO > obj.winX) || ((server_znak.equals("O") && obj.winO < obj.winX)))
            win();
        else if ((server_znak.equals("X") && obj.winO < obj.winX) || ((server_znak.equals("O") && obj.winO > obj.winX)))
            lose();
        restart();
        obj.button_panel.removeAll();
        comunication = false;
        input.close();
        out.close();
        socket.close();
        in.close();
    }
}