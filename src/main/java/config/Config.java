package config;

import database.DBManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class Config {
    @Bean("myDb")
    public DBManager getDBManager() {
        return new DBManager();
    }
}