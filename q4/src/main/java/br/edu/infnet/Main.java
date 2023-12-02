package br.edu.infnet;

import br.edu.infnet.models.Resposta;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.publicapis.org/entries");
            HttpURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                Resposta resposta = new Resposta();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder responseStringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseStringBuilder.append(line);
                }

                reader.close();

                String payload = responseStringBuilder.toString();
                resposta.setPayload(payload);
                System.out.println("Recebido: " + resposta.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}