package junit_demo;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void initAll() {
        System.out.println("In initAll - Starting tests in this container...");
    }

    @BeforeEach
    void init() {
        System.out.println("-------- In init - Starting a test...-------");
    }

    @Test
    void succeedingTest() {
        System.out.println("In succeedingTest()");
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("This test is disabled for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
        System.out.println("------------ In tearDown() - completed a test.-----");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("In tearDownAll() - completed all tests in this container.");
    }

}
