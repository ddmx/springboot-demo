package com.dmx.demo;

import com.dmx.demo.config.Test1DataSourceConfig;
import com.dmx.demo.config.Test2DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Test1DataSourceConfig.class, Test2DataSourceConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
