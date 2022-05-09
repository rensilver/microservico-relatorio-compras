package com.rensilver.msrelatoriocompras.service.validation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ExternalAPIConnectionValidation {

    public static boolean verificarConexaoAPIExterna(String urlExterna) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlExterna);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
