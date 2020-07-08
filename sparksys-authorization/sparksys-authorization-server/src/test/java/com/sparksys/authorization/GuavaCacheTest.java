package com.sparksys.authorization;

import com.sparksys.commons.core.cache.CacheProviderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("guavaCache")
    private CacheProviderService cacheProviderService;
    @Test
    public void guavaCacheTest() {
        Long data = cacheProviderService.increment("hahha",3);
        log.info("data is {}", data);
        Long datav = cacheProviderService.increment("hahha",3);
        log.info("datav is {}", datav);
        Long datav1 = cacheProviderService.decrement("hahha",1);
        log.info("datav1 is {}", datav1);
    }
}
