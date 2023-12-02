package br.edu.infnet;

import br.edu.infnet.models.Universidade;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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

                JSONArray universitiesArray = new JSONArray(responseStringBuilder.toString());

                List<Universidade> universidades = new ArrayList<>();
                for (int i = 0; i < universitiesArray.length(); i++) {
                    JSONObject universityJson = universitiesArray.getJSONObject(i);
                    String nome = universityJson.getString("name");
                    JSONArray webPagesArray = universityJson.getJSONArray("web_pages");
                    String url = webPagesArray.length() > 0 ? webPagesArray.getString(0) : ""; // Use the first URL if available

                    // Create Universidade object and add to the list
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
