package com.sparksys.authority;


import com.sparksys.cache.template.CacheTemplate;
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
@SpringBootTest(classes = AuthorityApplication.class)
@Slf4j
public class GuavaCacheTest {


    @Autowired
    private CacheTemplate cacheTemplate;
    @Test
    public void guavaCacheTest() {
        Long data = cacheTemplate.increment("hahha",3);
        log.info("data is {}", data);
        Long datav = cacheTemplate.increment("hahha",3);
        log.info("datav is {}", datav);
        Long datav1 = cacheTemplate.decrement("hahha",1);
        log.info("datav1 is {}", datav1);
    }
}
