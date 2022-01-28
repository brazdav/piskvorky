import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Start implements FirstTurn{
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
    private boolean exit = true;
    private int port = 6669;
    private int indexTlacoPredchozi;
    // constructor with port
    public Server() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            starLan();
            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            input = new BufferedReader(new InputStreamReader(System.in));

            try {
                out.writeUTF(String.valueOf(player1_turn.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }


            // reads message from client until "Over" is sent
            thread = new Thread(() -> {
                while (!line.equals("Over")){
                    getIndexTlaco();
                    if (indexTlacoPredchozi != indexTlaco) {
                        try {
                            out.writeUTF(String.valueOf(indexTlaco));
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
                        //System.out.println(line);
                        if (!line.equals("")){
                                int index = Integer.parseInt(line);
                                JButton button = (JButton) buttons.get(index);
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
    private void end() throws IOException {
        System.out.println("Closing connection");
        // close connection
        input.close();
        out.close();
        socket.close();
        in.close();
        exit = false;
    }


}
