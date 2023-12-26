package org.springframework.boot;

import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.boot.logging.DeferredLogs;

/**
 * @Author： yijun
 * @DATE: 2023/12/26 22:38
 * @Description
 */
public class EnvStep5Test {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication();
        ApplicationEnvironment env = new ApplicationEnvironment();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> 增强前");
        env.getPropertySources().forEach(System.out::println);

        ConfigDataEnvironmentPostProcessor processor1 = new ConfigDataEnvironmentPostProcessor(
                new DeferredLogs(), new DefaultBootstrapContext()
        );
        processor1.postProcessEnvironment(env, app);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> 增强后");
        env.getPropertySources().forEach(System.out::println);
        System.out.println(env.getProperty("author.name"));

        RandomValuePropertySourceEnvironmentPostProcessor processor2 =
                new RandomValuePropertySourceEnvironmentPostProcessor(new DeferredLog());
        processor2.postProcessEnvironment(env, app);
    }
}
