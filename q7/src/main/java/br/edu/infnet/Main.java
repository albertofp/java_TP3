package br.edu.infnet;

import br.edu.infnet.models.Resposta;
import br.edu.infnet.models.Pessoa;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        BufferedReader inputreader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite o nome a pesquisar: ");

        try {
            String query = inputreader.readLine();
            URL url = new URL("https://api.agify.io/?name=" + query);
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

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseStringBuilder.toString());

                String nome = jsonNode.get("name").asText();
                int idade = jsonNode.get("age").asInt();

                Pessoa pessoa = new Pessoa(nome, idade);

                resposta.setPayload(responseStringBuilder.toString());
                System.out.println("Recebido: " + resposta.toString());
                conn.disconnect();
            }
            inputreader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}