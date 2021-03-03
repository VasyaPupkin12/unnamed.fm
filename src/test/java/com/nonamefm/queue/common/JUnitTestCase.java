package com.nonamefm.queue.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public abstract class JUnitTestCase {
}
