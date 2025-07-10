package com.example.linebot;

import com.example.linebot.model.UserPreference;
import com.example.linebot.repository.UserPreferenceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LineWebhookController {

    private final UserPreferenceRepository userPreferenceRepository;
    private final LineNotifyService lineNotifyService;

    public LineWebhookController(UserPreferenceRepository repo, LineNotifyService notifyService) {
        this.userPreferenceRepository = repo;
        this.lineNotifyService = notifyService;
    }

    @PostMapping("/webhook")
    public void receiveWebhook(@RequestBody Map<String, Object> payload) {
        try {
            List<Map<String, Object>> events = (List<Map<String, Object>>) payload.get("events");
            for (Map<String, Object> event : events) {
                Map<String, Object> source = (Map<String, Object>) event.get("source");
                String userId = (String) source.get("userId");

                Map<String, Object> message = (Map<String, Object>) event.get("message");
                String text = (String) message.get("text");

                System.out.println("受信メッセージ: " + text);

                if (text.equals("スタート")) {
                    lineNotifyService.sendLeagueSelector(userId);
                }

                if (text.equals("パリーグ")) {
                    lineNotifyService.sendPacificTeamSelector(userId);
                }

                List<String> pacificTeams = List.of("楽天", "ロッテ", "ソフトバンク", "西武", "オリックス", "日本ハム");

                if (pacificTeams.contains(text)) {
                    userPreferenceRepository.save(new UserPreference(userId, text));
                    lineNotifyService.sendMessage("あなたの応援チーム「" + text + "」を登録しました！");
                }

                // あとで：セリーグ対応も追加予定
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
