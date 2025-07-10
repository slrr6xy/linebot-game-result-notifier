package com.example.linebot;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LineNotifyService {

    private final Dotenv dotenv = Dotenv.load();

    private final String CHANNEL_ACCESS_TOKEN = dotenv.get("LINE_CHANNEL_ACCESS_TOKEN");
    private final String USER_ID = dotenv.get("LINE_USER_ID");
    private static final String LINE_API_URL = "https://api.line.me/v2/bot/message/push";

    public void sendMessage(String message) {
        OkHttpClient client = new OkHttpClient();

        String json = "{ \"to\": \"" + USER_ID + "\", \"messages\": [ { \"type\": \"text\", \"text\": \"" + message + "\" } ] }";

        Request request = new Request.Builder()
                .url(LINE_API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("送信成功: " + response.body().string());
        } catch (IOException e) {
            System.out.println("送信エラー");
            e.printStackTrace();
        }
    }
}
