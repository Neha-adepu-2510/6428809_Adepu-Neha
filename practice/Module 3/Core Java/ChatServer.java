import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            // Chat loop
            String msg;
            while (true) {
                msg = in.readLine(); // from client
                if (msg.equalsIgnoreCase("exit")) break;
                System.out.println("Client: " + msg);

                System.out.print("Server: ");
                msg = keyboard.readLine();
                out.println(msg); // to client
                if (msg.equalsIgnoreCase("exit")) break;
            }

            socket.close();
            System.out.println("Chat ended.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
