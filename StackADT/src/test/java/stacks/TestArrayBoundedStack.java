package stacks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Stack Implementation: ArrayBoundedStack")
public class TestArrayBoundedStack {
    @Test
    @DisplayName("Test creation of an empty stack")
    public void test_create() {
        ArrayBoundedStack<Integer> stack = new ArrayBoundedStack<>(10);
        assert(stack.isEmpty());
    }
    @Test
    @DisplayName("Test pushing an element onto the stack")
    public void test_push() {
        ArrayBoundedStack<Integer> stack = new ArrayBoundedStack<>(10);
        stack.push(1);
        assert(!stack.isEmpty());
        assert(stack.top() == 1);
    }
    @Test
    @DisplayName("Test popping an element from the stack")
    public void test_pop() {
        ArrayBoundedStack<Integer> stack = new ArrayBoundedStack<>(10);
        stack.push(1);
        stack.pop();
        assert(stack.isEmpty());
    }
    @Test
    @DisplayName("Test stack overflow exception")
    public void test_overflow() {
        ArrayBoundedStack<Integer> stack = new ArrayBoundedStack<>(2);
        stack.push(1);
        stack.push(2);
        assertThrows(StackOverflowException.class, () -> {
            stack.push(3);
        });
    }
    @Test
    @DisplayName("Test stack underflow exception")
    public void test_underflow() {
        ArrayBoundedStack<Integer> stack = new ArrayBoundedStack<>(2);
        assertThrows(StackUnderflowException.class, () -> {
            stack.pop();
        });
        assertThrows(StackUnderflowException.class, () -> {
            stack.top();
        });
    }
}
