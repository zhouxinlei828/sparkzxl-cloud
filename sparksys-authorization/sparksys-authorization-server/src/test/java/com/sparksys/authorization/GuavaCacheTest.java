package com.sparksys.authorization;


import com.sparksys.core.repository.CacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/7 5:20 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthorizationApplication.class)
@Slf4j
public class GuavaCacheTest {


    @Autowired
    private CacheRepository cacheRepository;
    @Test
    public void guavaCacheTest() {
        Long data = cacheRepository.increment("hahha",3);
        log.info("data is {}", data);
        Long datav = cacheRepository.increment("hahha",3);
        log.info("datav is {}", datav);
        Long datav1 = cacheRepository.decrement("hahha",1);
        log.info("datav1 is {}", datav1);
    }
}
