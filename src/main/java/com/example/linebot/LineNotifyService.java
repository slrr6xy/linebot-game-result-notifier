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

        String json = "{ \"to\": \"" + USER_ID + "\", \"messages\": [ { \"type\": \"text\", \"text\": \"" + message.replace("\n", "\\n") + "\" } ] }";

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

    public void sendLeagueSelector(String userId) {
        OkHttpClient client = new OkHttpClient();

        String json = "{"
                + "\"to\":\"" + userId + "\","
                + "\"messages\":["
                + "  {"
                + "    \"type\":\"flex\","
                + "    \"altText\":\"リーグを選んでください\","
                + "    \"contents\":{"
                + "      \"type\":\"bubble\","
                + "      \"body\":{"
                + "        \"type\":\"box\","
                + "        \"layout\":\"vertical\","
                + "        \"contents\":["
                + "          {"
                + "            \"type\":\"text\","
                + "            \"text\":\"リーグを選んでください\","
                + "            \"weight\":\"bold\","
                + "            \"size\":\"md\""
                + "          },"
                + "          {"
                + "            \"type\":\"box\","
                + "            \"layout\":\"horizontal\","
                + "            \"spacing\":\"md\","
                + "            \"contents\":["
                + "              {"
                + "                \"type\":\"button\","
                + "                \"action\":{"
                + "                  \"type\":\"message\","
                + "                  \"label\":\"パ・リーグ\","
                + "                  \"text\":\"パリーグ\""
                + "                }"
                + "              },"
                + "              {"
                + "                \"type\":\"button\","
                + "                \"action\":{"
                + "                  \"type\":\"message\","
                + "                  \"label\":\"セ・リーグ\","
                + "                  \"text\":\"セリーグ\""
                + "                }"
                + "              }"
                + "            ]"
                + "          }"
                + "        ]"
                + "      }"
                + "    }"
                + "  }"
                + "]"
                + "}";

        Request request = new Request.Builder()
                .url(LINE_API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("送信: " + response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPacificTeamSelector(String userId) {
        OkHttpClient client = new OkHttpClient();

        String json = "{"
                + "\"to\":\"" + userId + "\","
                + "\"messages\":["
                + "  {"
                + "    \"type\":\"flex\","
                + "    \"altText\":\"パ・リーグのチームを選んでください\","
                + "    \"contents\":{"
                + "      \"type\":\"bubble\","
                + "      \"body\":{"
                + "        \"type\":\"box\","
                + "        \"layout\":\"vertical\","
                + "        \"contents\":["
                + "          {\"type\":\"text\",\"text\":\"パ・リーグのチームを選んでください\",\"weight\":\"bold\",\"size\":\"md\"},"
                + "          {\"type\":\"box\",\"layout\":\"vertical\",\"spacing\":\"sm\",\"contents\":["
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"楽天\",\"text\":\"楽天\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"ロッテ\",\"text\":\"ロッテ\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"ソフトバンク\",\"text\":\"ソフトバンク\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"西武\",\"text\":\"西武\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"オリックス\",\"text\":\"オリックス\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"日本ハム\",\"text\":\"日本ハム\"}}"
                + "          ]}"
                + "        ]"
                + "      }"
                + "    }"
                + "  }"
                + "]"
                + "}";

        Request request = new Request.Builder()
                .url(LINE_API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("パリーグチーム選択送信: " + response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCentralTeamSelector(String userId) {
        OkHttpClient client = new OkHttpClient();

        String json = "{"
                + "\"to\":\"" + userId + "\","
                + "\"messages\":["
                + "  {"
                + "    \"type\":\"flex\","
                + "    \"altText\":\"セ・リーグのチームを選んでください\","
                + "    \"contents\":{"
                + "      \"type\":\"bubble\","
                + "      \"body\":{"
                + "        \"type\":\"box\","
                + "        \"layout\":\"vertical\","
                + "        \"contents\":["
                + "          {\"type\":\"text\",\"text\":\"セ・リーグのチームを選んでください\",\"weight\":\"bold\",\"size\":\"md\"},"
                + "          {\"type\":\"box\",\"layout\":\"vertical\",\"spacing\":\"sm\",\"contents\":["
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"巨人\",\"text\":\"巨人\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"阪神\",\"text\":\"阪神\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"中日\",\"text\":\"中日\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"ヤクルト\",\"text\":\"ヤクルト\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"広島\",\"text\":\"広島\"}},"
                + "            {\"type\":\"button\",\"action\":{\"type\":\"message\",\"label\":\"DeNA\",\"text\":\"DeNA\"}}"
                + "          ]}"
                + "        ]"
                + "      }"
                + "    }"
                + "  }"
                + "]"
                + "}";

        Request request = new Request.Builder()
                .url(LINE_API_URL)
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + CHANNEL_ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("送信: " + response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
