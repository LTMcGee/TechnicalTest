package org.lmcgee.technicaltest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TechnicalTestApplicationTests {

    @Test
    public void contextLoads() {
        TechnicalTestApplication.main(new String[] {""});
        assertTrue(true, "No exceptions thrown running application");
    }
}
