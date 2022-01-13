import java.net.*;
import java.io.*;

public class Client {
    // initialize socket and input output streams
    private Socket socket = null;
    private BufferedReader input = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String line = "";
    private String send;
    // constructor to put ip address and port
    public Client(String address, int port) {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

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
                    send = input.readLine();
                    out.writeUTF(send);
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

}