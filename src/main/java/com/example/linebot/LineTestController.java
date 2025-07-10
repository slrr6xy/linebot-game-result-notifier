package com.example.linebot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineTestController {
    private final LineNotifyService lineNotifyService;

    public LineTestController(LineNotifyService lineNotifyService) {
        this.lineNotifyService = lineNotifyService;
    }

    @GetMapping("/test")
    public String sendTestMessage() {
        lineNotifyService.sendMessage("こんにちは！test");
        return "LINEに送信しました";
    }

    @GetMapping("/select")
    public String sendSelector() {
        lineNotifyService.sendLeagueSelector();
        return "リーグ選択を送信しました";
    }
}
