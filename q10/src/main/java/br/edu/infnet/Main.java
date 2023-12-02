package br.edu.infnet;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try {
            URL endpointUrl = new URL("https://jsonplaceholder.typicode.com/posts");

            String payload = "{"
                    + "\"userId\": " + 1 + ","
                    + "\"id\": " + 1 + ","
                    + "\"title\": \"" + "delectus aut autem" + "\","
                    + "\"completed\": " + false
                    + "}";

            HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] requestData = payload.getBytes(StandardCharsets.UTF_8);
                outputStream.write(requestData, 0, requestData.length);
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
