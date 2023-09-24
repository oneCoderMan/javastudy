package desingpattern;

import com.java.study.designpattern.somtech.OptStrategyContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 21:31
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=BasicApplicationTest.class)
@Slf4j
public class IfReplaceTest {
    @Autowired
    private OptStrategyContext optStrategyContext;
    @Test
    public void testIf() {
        int devideOpt = optStrategyContext.apply("devideOpt", 4, 2);
        log.info("{}", devideOpt);
    }
}
