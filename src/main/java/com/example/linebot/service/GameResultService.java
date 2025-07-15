package com.example.linebot.service;

import com.example.linebot.model.GameResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GameResultService {

    public Optional<GameResult> findTodayGame(String teamName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("mock/games.json");

            if (is == null) {
                System.out.println("games.jsonが見つかりませんでした");
                return Optional.empty();
            }

            List<GameResult> games = mapper.readValue(is, new TypeReference<List<GameResult>>() {});

            return games.stream()
                    .filter(game -> {
                        String data = game.getDate().equals("today") ? LocalDate.now().toString() : game.getDate();
                        return data.equals(LocalDate.now().toString());
                    })
                    .filter(game -> game.getHomeTeam().equals(teamName) || game.getAwayTeam().equals(teamName))
                    .findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public String getResultMessage(GameResult game, String teamName) {
        Integer myScore = teamName.equals(game.getHomeTeam()) ? game.getHomeScore() : game.getAwayScore();
        Integer oppScore = teamName.equals(game.getHomeTeam()) ? game.getAwayScore() : game.getHomeScore();

        if (myScore == null || oppScore == null) {
            return String.format("今日の試合結果:%s vs %s\n結果: 試合情報がまだ登録されていません。",
                    game.getHomeTeam(), game.getAwayTeam());
        }

        String result = myScore > oppScore ? "勝ち" : (myScore < oppScore ? "負け" : "引き分け");
        return String.format("今日の試合結果:%s vs %s\nスコア: %d - %d\n結果: %s",
                game.getHomeTeam(), game.getAwayTeam(), game.getHomeScore(), game.getAwayScore(), result);
    }
}
