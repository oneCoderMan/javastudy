package desingpattern;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 22:01
 * @Description
 */
@Configuration
@ComponentScan("com.java.study")
@EntityScan("com.java.study")
public class BasicApplicationTest {
}
