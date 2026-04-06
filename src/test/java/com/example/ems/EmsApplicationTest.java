package com.example.ems;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class EmsApplicationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("✅ Database is connected! Test query returned: " + result);
            assertThat(result).isEqualTo(1);
        } catch (Exception e) {
            System.err.println("❌ Cannot connect to the database!");
            e.printStackTrace();
            throw e; // fail the test
        }
    }
}