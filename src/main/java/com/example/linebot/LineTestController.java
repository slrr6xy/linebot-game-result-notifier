package com.example.linebot;

import com.example.linebot.model.UserPreference;
import com.example.linebot.repository.UserPreferenceRepository;

import com.example.linebot.service.GameNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineTestController {

    private final LineNotifyService lineNotifyService;
    private final UserPreferenceRepository userPreferenceRepository;
    private final GameNotificationService gameNotificationService;

    public LineTestController(LineNotifyService lineNotifyService, UserPreferenceRepository repo, GameNotificationService gameNotificationService) {
        this.lineNotifyService = lineNotifyService;
        this.userPreferenceRepository = repo;
        this.gameNotificationService = gameNotificationService;
    }

    @GetMapping("/test")
    public String sendTestMessage() {
        lineNotifyService.sendMessage("こんにちは！test");
        return "LINEに送信しました";
    }

    @GetMapping("/select")
    public String sendSelector(@RequestParam String userId) {
        lineNotifyService.sendLeagueSelector(userId);
        return "リーグ選択を送信しました（宛先：" + userId + "）";
    }

    @GetMapping("/user-preference")
    public String getUserPreference(@RequestParam String userId) {
        return userPreferenceRepository.findByUserId(userId)
                .map(pref -> "現在の登録チーム：" + pref.getTeamName())
                .orElse("チームはまだ登録されていません");
    }

    @GetMapping("/test-notify")
    public String testNotify() {
        gameNotificationService.notifyUser();
        return "通知テストを実行しました";
    }
}
