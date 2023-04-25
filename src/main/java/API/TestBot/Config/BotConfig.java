package API.TestBot.Config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @org.springframework.beans.factory.annotation.Value("${telegram.bot.name}")
    String botName;
    @org.springframework.beans.factory.annotation.Value("${telegram.bot.token}")
    String token;
    @org.springframework.beans.factory.annotation.Value("${telegram.bot.ownerUserName}")
    String ownerUserName;
}
