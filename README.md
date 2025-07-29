# プロ野球試合結果通知 LINE Bot

## 📌 概要

このアプリは、ユーザーが応援するプロ野球チームを選択し、毎日21時に当日の試合結果を LINE へ通知するシステムです。

デモデータを用いて構築されていますが、実際の通知機能やユーザー登録フローが動作するポートフォリオ用アプリです。

## 🛠️ 使用技術

- Java 17
- Spring Boot
- LINE Messaging API（Bot）
- OkHttp
- Jackson
- H2 Database
- Git / GitHub

## ⚙️ 機能一覧

- 「スタート」と送るとリーグ選択メッセージが届く
- リーグを選択するとチーム選択メッセージが届く
- チームを選択すると DB に登録 or 更新
- 毎日21:00にチームの試合結果を LINE へ自動通知
- 試合がない日は「本日は試合がありません」と通知

## 🗂️ デモデータについて

このアプリは `src/main/resources/mock/games.json` にある試合情報を元に通知を送信します。

※実際の試合結果ではないことに注意してください。（デモデータです）

日付は `"2025-07-17"` の形式で記述してください。

```json
[
  {
    "date": "2025-07-17",
    "homeTeam": "楽天",
    "awayTeam": "ロッテ",
    "homeScore": 3,
    "awayScore": 2
  }
]
```

## 💬 LINE Bot 使用方法

1. LINE Developers コンソールでチャネル作成

2. アクセストークンとユーザーIDを .env ファイルに記述
```ini
LINE_CHANNEL_ACCESS_TOKEN=***
LINE_USER_ID=***
```
3. ngrok またはリモートサーバで Spring Boot を公開

4. Webhook URL を LINE Developers に登録

    例: `ngrok http 8080` を起動し、表示された URL に `/webhook` をつけて登録
   
    例: `https://*****.ngrok-free.app/webhook`

5. LINE で Bot に「スタート」と送信して開始

## 🧪 テスト方法

通知の送信時間をテストするには、`GameNotificationService.java` の `@Scheduled` アノテーションを以下のように変更します。

```java
@Scheduled(cron = "0 * * * * *") // 毎分実行
```
## 🗒️ 補足

- 実際のプロ野球試合情報 API は使っていません

- デモ用途・ポートフォリオ目的で構築しています

## ⚙️ 実際の動作

- 試合がある日
  
![試合あり](https://github.com/user-attachments/assets/52a159ec-91f6-4034-9c3e-9b653b2b361a)

- 試合がない日

![試合なし](https://github.com/user-attachments/assets/e170864a-a3e6-4c1e-afc7-75710d537498)

- 試合情報がない場合(スコアの部分が "null" の場合)

![スコアなし](https://github.com/user-attachments/assets/6fdae2c8-f091-4725-aa23-fb91f7446c9c)
