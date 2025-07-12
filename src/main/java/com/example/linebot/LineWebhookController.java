package com.example.linebot;

import com.example.linebot.model.UserPreference;
import com.example.linebot.repository.UserPreferenceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                    return;
                }

                if (text.equals("パリーグ")) {
                    lineNotifyService.sendPacificTeamSelector(userId);
                    return;
                }

                if (text.equals("セリーグ")) {
                    lineNotifyService.sendCentralTeamSelector(userId);
                    return;
                }

                List<String> pacificTeams = List.of("楽天", "ロッテ", "ソフトバンク", "西武", "オリックス", "日本ハム");
                List<String> centraTeams = List.of("巨人", "阪神", "中日", "ヤクルト", "広島", "DeNA");

                if (pacificTeams.contains(text) || centraTeams.contains(text)) {
                    Optional<UserPreference> existing = userPreferenceRepository.findByUserId(userId);
                    if (existing.isPresent()) {
                        UserPreference pref = existing.get();
                        pref.setTeamName(text);
                        userPreferenceRepository.save(pref);
                        lineNotifyService.sendMessage("あなたの応援チームを「" + text + "」に更新しました！");
                    } else {
                        userPreferenceRepository.save(new UserPreference(userId, text));
                        lineNotifyService.sendMessage("あなたの応援チーム「" + text + "」を登録しました！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
