package junit_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AssumptionsDemo {
    @BeforeEach
    void checkEnv() {
        System.out.println("Current ENV: " + System.getenv("ENV"));
    }
    private final Calculator calculator = new Calculator();

    @Nested
    @DisplayName("ENV=CI")
    class CiEnv {
        @Test
        void testOnlyOnCiServer() {
            assumeTrue("CI".equals(System.getenv("ENV")));
            System.out.println("Running test on CI server");
        }

        @Test
        void testInAllEnvironments() {
            // CI-specific assertions (only run when ENV=CI)
            assumingThat("CI".equals(System.getenv("ENV")), () -> {
                System.out.println("Running CI-specific assertions");
                assertEquals(2, calculator.divide(4, 2));
            });

            // Runs in all environments
            assertEquals(42, calculator.multiply(6, 7));
        }
    }

    @Nested
    @DisplayName("ENV=DEV")
    class DevEnv {
        @Test
        void testOnlyOnDeveloperWorkstation() {
            assumeTrue("DEV".equals(System.getenv("ENV")),
                    () -> "Aborting test: not on developer workstation");
            System.out.println("Running test on developer workstation");
        }

        @Test
        void testInAllEnvironments() {
            // DEV-specific assertions (only run when ENV=DEV)
            assumingThat("DEV".equals(System.getenv("ENV")), () -> {
                System.out.println("Running DEV-specific assertions");
                assertEquals(2, calculator.divide(4, 2));
            });

            // Runs in all environments
            assertEquals(42, calculator.multiply(6, 7));
        }
    }
}
