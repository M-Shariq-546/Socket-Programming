import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String clientMessage = in.readLine();
                    if (clientMessage != null) {
                        System.out.println("Client: " + clientMessage);

                        // Process the client's message and prepare a response
                        String response = "Walikum Salam " + clientMessage.substring(clientMessage.lastIndexOf("is") + 3);

                        // Send the response to the client
                        out.println(response);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
