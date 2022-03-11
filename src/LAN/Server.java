package LAN;

import Spousteni_hry.Start;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Start {
    //initialize socket and input stream
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
    // constructor with port
    public Server(Piskvorky piskvorky) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // starts server and waits for a connection
        this.obj = this;
        try {
            server = new ServerSocket(port);
            System.out.println("LAN.Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("LAN.Client accepted");
            startLan(piskvorky);
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
                            /*for (Object button : buttons) {
                                piskvorky.buttonOff((JButton) button);
                            }*/
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
                        if (Integer.parseInt(line) != odchozi){
                                int index = Integer.parseInt(line);
                                prichozi = index;
                                JButton button = (JButton) buttons.get(index);
                            if (buttons.get(0) != null) {
                                for (Object button2 : buttons) {
                                    piskvorky.buttonOn((JButton) button2);
                                }
                            }
                                button.doClick();
                        }
                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }
                try {
                    end();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread2.start();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void sendEnd() throws IOException {
        out.writeUTF("Over");
    }

    private void end() throws IOException {
        System.out.println("LAN.Server se vyplnul");
        // close connection
        thread.stop();
        thread2.stop();
        input.close();
        out.close();
        socket.close();
        in.close();

    }


}
