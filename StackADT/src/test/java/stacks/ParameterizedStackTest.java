package stacks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StackInterface Conformance Tests")
public class ParameterizedStackTest {

    /* ---------- Providers ---------- */

    // Providers for ANY stack implementation (bounded or unbounded)
    static Iterable<Arguments> stackProviders() {
        return List.of(
                Arguments.of("ArrayBoundedStack", (Supplier<StackInterface<Integer>>) () -> new ArrayBoundedStack<>(10)),
                Arguments.of("ArraylistStack", (Supplier<StackInterface<Integer>>) ArrayListStack::new)
        );
    }


    /* ---------- Tests for all stacks ---------- */

    @Nested
    @DisplayName("Common behavior (all implementations)")
    class CommonBehavior {

        @ParameterizedTest(name = "{0} — create empty")
        @MethodSource("stacks.ParameterizedStackTest#stackProviders")
        void test_create(String name, Supplier<StackInterface<Integer>> factory) {
            StackInterface<Integer> stack = factory.get();
            assertTrue(stack.isEmpty());
        }

        @ParameterizedTest(name = "{0} — push/top")
        @MethodSource("stacks.ParameterizedStackTest#stackProviders")
        void test_push(String name, Supplier<StackInterface<Integer>> factory) {
            StackInterface<Integer> stack = factory.get();
            stack.push(1);
            assertFalse(stack.isEmpty());
            assertEquals(1, stack.top());
        }

        @ParameterizedTest(name = "{0} — pop")
        @MethodSource("stacks.ParameterizedStackTest#stackProviders")
        void test_pop(String name, Supplier<StackInterface<Integer>> factory) {
            StackInterface<Integer> stack = factory.get();
            stack.push(1);
            Integer removed = stack.pop();
            assertEquals(1, removed);
            assertTrue(stack.isEmpty());
        }

        @ParameterizedTest(name = "{0} — underflow on empty pop/top")
        @MethodSource("stacks.ParameterizedStackTest#stackProviders")
        void test_underflow(String name, Supplier<StackInterface<Integer>> factory) {
            StackInterface<Integer> stack = factory.get();
            assertThrows(StackUnderflowException.class, stack::pop);
            assertThrows(StackUnderflowException.class, stack::top);
        }

    }
}


