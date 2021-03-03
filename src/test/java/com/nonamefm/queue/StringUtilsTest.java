package com.nonamefm.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StringUtilsTest {

    @Test
    public void stringTest() {
        String str = "Hello this beautiful world";
        String[] array = str.split(" ");
        String result = "";
        for(String s: array) {
            result += s;
        }
        System.out.println(result);

    }

}
