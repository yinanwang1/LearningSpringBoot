package com.yn.learningspringboot;

import com.yn.learningspringboot.controller.HelloController;
import com.yn.learningspringboot.schedule.AsyncTasks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
class LearningSpringBootApplicationTests {

    private static MockMvc mvc;

    /**
     * 测试前置条件
     */
    @DisplayName("测试前置条件")
    @Test
    void testAssumptions() throws Exception {
        Assumptions.assumeTrue(true, "结果不是true");

        System.out.println("111111");
    }


    @BeforeAll
    public static void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello?name=132").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Autowired
    private AsyncTasks asyncTasks;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = asyncTasks.doTaskOne("1");
        CompletableFuture<String> task2 = asyncTasks.doTaskOne("2");
        CompletableFuture<String> task3 = asyncTasks.doTaskOne("3");

        CompletableFuture<String> task4 = asyncTasks.doTaskTwo("4");
        CompletableFuture<String> task5 = asyncTasks.doTaskTwo("5");
        CompletableFuture<String> task6 = asyncTasks.doTaskTwo("6");

        CompletableFuture.allOf(task1, task2, task3).join();
        long end = System.currentTimeMillis();

        log.info("任务全部完成，总耗时： " + (end - start) + "毫秒");

    }

    /// 测试下数据库的连接

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        long aLong = jdbcTemplate.queryForObject("select count(*) from a_account", Long.class);
        log.info("记录总数为：{}", aLong);
    }

    /// 测试下数据库的连接 __END__
}
