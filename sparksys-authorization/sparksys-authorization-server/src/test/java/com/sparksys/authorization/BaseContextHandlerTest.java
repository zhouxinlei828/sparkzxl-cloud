package com.sparksys.authorization;

import com.sparksys.commons.mybatis.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class BaseContextHandlerTest {

    @Before
    public void initBaseContextHandler(){
        BaseContextHandler.setUserId(2222L);
    }

    @Test
    public void getBaseContextHandler() {
        BaseContextHandler.getAccount();
        log.info("BaseContextHandler get userId is {}",BaseContextHandler.getUserId());
    }
}
