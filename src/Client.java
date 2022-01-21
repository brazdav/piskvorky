import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client extends Start implements FirstTurn{
    // initialize socket and input output streams
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String line = "";
    private String send;
    private int indexTlacoPredchozi;
    // constructor to put ip address and port
    public Client(String address, int port) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // establish a connection
        Start obj = new Start();
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            obj.start();
            // takes input from terminal
            input = new BufferedReader(new InputStreamReader(System.in));

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input

        // keep reading until "Over" is input
        Thread thread = new Thread(() -> {
            while (!line.equals("Over")){
                try {
                    if (indexTlacoPredchozi != indexTlaco) {
                        sentIndexTlaco();
                        indexTlacoPredchozi = indexTlaco;
                        lan_turn.set(false);
                    }
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
        });
        thread.start();

        while (!line.equals("Over")) {
            try {
                line = in.readUTF();
                System.out.println(line);
                if (line == "true" || line == "false"){
                    lan_turn.set(Boolean.parseBoolean(line));
                }
                else if (lan_turn.get() == false) {
                    int index = Integer.parseInt(line);
                    JButton button = (JButton) obj.buttons.get(index);
                    button.doClick();
                    lan_turn.set(true);
                }
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection

    }
    private void end() throws IOException {
        System.out.println("Closing connection");

        // close connection
        input.close();
        out.close();
        socket.close();
        in.close();
    }
    public void sentIndexTlaco() throws IOException {
        out.writeUTF(String.valueOf(indexTlaco));
    }

}