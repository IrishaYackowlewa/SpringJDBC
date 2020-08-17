package recipesPack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@ComponentScan("recipesPack")
public class Config {
    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");
    }
}
