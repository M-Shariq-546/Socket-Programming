import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    String inputString = (String) in.readObject();
                    String result = countMaxCharacters(inputString);
                    out.writeObject(result);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String countMaxCharacters(String inputString) {
        Map<Character, Integer> characterCount = new HashMap<>();
        int maxCount = 0;

        for (char c : inputString.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                int count = characterCount.getOrDefault(c, 0) + 1;
                characterCount.put(c, count);

                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                result.append(entry.getKey()).append(":").append(entry.getValue()).append(" , ");
            }
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 3); // Remove the trailing " , "
        }

        return result.toString();
    }
}
