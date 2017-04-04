package ru.vladislav.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/ru.vladislav/db.properties")
@ComponentScan("ru.vladislav")
/*@EnableJpaRepositories("ru.vladislav.repo")
@EnableTransactionManagement*/
public class SpringConfig {



}
