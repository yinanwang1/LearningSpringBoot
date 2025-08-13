package com.yn.customer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class CustomerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        final List mock = Mockito.mock(List.class);
        log.info("mock.add result => " + mock.add("first"));
        log.info("mock.size result => " + mock.size());

        Mockito.when(mock.get(0)).thenReturn("second");
        Mockito.doReturn(66).when(mock).size();

        log.info("mock.get(0) result => " + mock.get(0));
        log.info("mock.size() result => " + mock.size());

        Mockito.verify(mock).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.times(2)).size();


        assertEquals("second", mock.get(0));
        assertEquals(66, mock.size());
    }

    @Test
    void testMyAnnotation() {

    }

}
