import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 1234;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String clientMessage = "Hello My name is John"; // You can replace "John" with your name
            out.println(clientMessage);

            String serverResponse = in.readLine();
            System.out.println("Server: " + serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
