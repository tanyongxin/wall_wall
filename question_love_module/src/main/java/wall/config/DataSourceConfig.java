package wall.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {


    @Bean
    @Primary
    public HikariDataSource getHikariDataSource(@Qualifier("getHikariConfig") HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig getHikariConfig(){
        return new HikariConfig();
    }

}
