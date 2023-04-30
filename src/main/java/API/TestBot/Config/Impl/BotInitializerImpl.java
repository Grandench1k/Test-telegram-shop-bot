package API.TestBot.Config.Impl;

import API.TestBot.Config.BotInitializer;
import API.TestBot.Core.BotCore;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializerImpl implements BotInitializer {
    @Autowired
    BotCore botCore;

    public BotInitializerImpl(BotCore botCore) {
        this.botCore = botCore;
    }

    @SneakyThrows
    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(botCore);
    }
}

