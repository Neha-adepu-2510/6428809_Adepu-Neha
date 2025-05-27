import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to server.");

            // Input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Chat loop
            String msg;
            while (true) {
                System.out.print("Client: ");
                msg = keyboard.readLine();
                out.println(msg); // to server
                if (msg.equalsIgnoreCase("exit")) break;

                msg = in.readLine(); // from server
                System.out.println("Server: " + msg);
                if (msg.equalsIgnoreCase("exit")) break;
            }

            System.out.println("Chat ended.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
