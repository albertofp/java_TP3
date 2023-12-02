package br.edu.infnet;

import br.edu.infnet.models.Universidade;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            URL apiUrl = new URL("http://universities.hipolabs.com/search?country=Brazil");
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseStringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseStringBuilder.append(line);
                }

                reader.close();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode universitiesNode = objectMapper.readTree(responseStringBuilder.toString());

                List<Universidade> universidades = new ArrayList<>();
                for (JsonNode universityNode : universitiesNode) {
                    String nome = universityNode.get("name").asText();
                    JsonNode webPagesNode = universityNode.get("web_pages");
                    String url = webPagesNode.isArray() && !webPagesNode.isEmpty() ? webPagesNode.get(0).asText() : "";

                    Universidade universidade = new Universidade(nome, url);
                    universidades.add(universidade);
                }

                System.out.println("Universidades:");
                for (Universidade universidade : universidades) {
                    System.out.println(universidade.toString());
                }
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
