package com.example.linebot.service;

import com.example.linebot.LineNotifyService;
import com.example.linebot.model.GameResult;
import com.example.linebot.model.UserPreference;
import com.example.linebot.repository.UserPreferenceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameNotificationService {

    private final UserPreferenceRepository userPreferenceRepository;
    private final GameResultService gameResultService;
    private final LineNotifyService lineNotifyService;

    public GameNotificationService(UserPreferenceRepository userPreferenceRepository,
                                   GameResultService gameResultService,
                                   LineNotifyService lineNotifyService) {
        this.userPreferenceRepository = userPreferenceRepository;
        this.gameResultService = gameResultService;
        this.lineNotifyService = lineNotifyService;
    }

    @Scheduled(cron = "0 0 21 * * *", zone = "Asia/Tokyo") // 毎日21:00に実行
    public void notifyUser() {
        System.out.println("通知処理を実行します");

        List<UserPreference> users = userPreferenceRepository.findAll();

        for (UserPreference user : users) {
            Optional<GameResult> game = gameResultService.findTodayGame(user.getTeamName());

            if (game.isPresent()) {
                String msg = gameResultService.getResultMessage(game.get(), user.getTeamName());
                lineNotifyService.sendMessage(msg);
            }
        }
    }
}
