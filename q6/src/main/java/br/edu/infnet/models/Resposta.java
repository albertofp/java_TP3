package br.edu.infnet.models;

import org.json.JSONObject;

public class Resposta {
    String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
