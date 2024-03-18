package com.yn.learningspringboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Junit5测试")
public class Junit5Test {

    @DisplayName("测试display name注解")
    @Test
    void testDisplayName() throws Exception {
        System.out.println(1);
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试就要开始了...");
    }

    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertions() throws Exception {
        int result = calculate(2, 3);
        Assertions.assertEquals(5, result, "业务逻辑计算失败：！！！");
    }

    int calculate(int i, int j) {
        return  i + j;
    }

}
