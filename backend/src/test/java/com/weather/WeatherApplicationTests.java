package com.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "api.key=test-key",
        "api.base-url=https://api.openweathermap.org/data/2.5"
})
class WeatherApplicationTests {

    @Test
    void contextLoads() {
        
    }
}
