package LAN;

import Rozhrani.FirstTurn;
import Spousteni_hry.Start;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Start implements FirstTurn {
    // initialize socket and input output streams
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String line = "";
    private String send;
    private int indexTlacoPredchozi;
    private Thread thread;
    private Thread thread2;
    private int odchozi;
    private int prichozi;
    // constructor to put ip address and port
    public Client() throws UnsupportedAudioFileException, LineUnavailableException, IOException{
        sendEnd();
    }
    public Client(String address, int port, Piskvorky piskvorky) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // establish a connection
        Start start = new Start(this, "client");
        thread = new Thread(() -> {
            while (!line.equals("Over")) {
                getIndexTlaco();
                if (indexTlacoPredchozi != indexTlaco && prichozi != indexTlaco) {
                    try {
                        out.writeUTF(String.valueOf(indexTlaco));
                        odchozi = indexTlaco;
                        for (Object button : buttons) {
                            piskvorky.buttonOff((JButton) button);
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
                    System.out.println(line);
                    if (line.equals("true") || line.equals("false")) {
                        FirstTurn.player1_turn.set(Boolean.parseBoolean(line));
                    } else if (Integer.parseInt(line) != odchozi) {
                        int index = Integer.parseInt(line);
                        prichozi = index;
                        JButton button = (JButton) buttons.get(index);
                        for (Object button2 : buttons) {
                            piskvorky.buttonOn((JButton) button2);
                        }
                        button.doClick();
                    }
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            try {
                out.writeUTF("Over");
                end();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
            try {
                socket = new Socket(address, port);
                System.out.println("Connected");
                startLan(piskvorky);
                // takes input from terminal
                input = new BufferedReader(new InputStreamReader(System.in));

                // sends output to the socket
                out = new DataOutputStream(socket.getOutputStream());

                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                thread.start();
                thread2.start();
            } catch (UnknownHostException u) {
                System.out.println(u);
            } catch (IOException i) {
                System.out.println(i);
                SettingUpClient obj = new SettingUpClient(piskvorky);
                JOptionPane.showMessageDialog(obj.dialogy, "Na této adrese není zapnutý žádný server");
            }

            // string to read message from input

            // keep reading until "Over" is input



    }
    private void sendEnd()  throws IOException {
        out.writeUTF("Over");
    }
    private void end() throws IOException {
        System.out.println("LAN.Client se vypnul");

        // close connection
        input.close();
        out.close();
        socket.close();
        in.close();
        thread.stop();
        thread2.stop();
    }

}