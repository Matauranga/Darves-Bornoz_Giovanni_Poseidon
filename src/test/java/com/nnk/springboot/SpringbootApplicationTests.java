package com.nnk.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        SpringbootApplication.main(new String[]{});
        Assertions.assertTrue(true);
    }

}
