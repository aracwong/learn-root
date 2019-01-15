package com.kotall.learn;

import com.kotall.learn.spring.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private BlogService blogService;

    @Test
    public void post() {
        this.blogService.post("20190107");
    }
}
