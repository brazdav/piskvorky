import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server extends Piskvorky{
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
    // constructor with port
    public Server() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // starts server and waits for a connection
        try {
            Vykresleni obj = new Vykresleni();
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            input = new BufferedReader(new InputStreamReader(System.in));


            // reads message from client until "Over" is sent
            thread = new Thread(() -> {
                while (!line.equals("Over")){
                    try {
                        send = input.readLine();
                        out.writeUTF(send);
                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }
                System.out.println("thred is dead");
            });

            thread2 = new Thread(() -> {
                while (!line.equals("Over")) {
                    try {
                        line = in.readUTF();
                        System.out.println(line);
                        int index = Integer.parseInt(line);
                        obj.vykresleniLan((JButton) buttons.get(index));
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