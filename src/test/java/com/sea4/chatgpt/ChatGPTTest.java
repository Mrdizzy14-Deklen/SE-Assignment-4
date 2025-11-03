package com.sea4.chatgpt;
import com.sea4.Stack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChatGPTTest {

    @Test
    @DisplayName("New stack is empty, not full, size 0")
    void newStackBasics() {
        Stack s = new Stack(3);
        assertTrue(s.isEmpty());
        assertFalse(s.isFull());
        assertEquals(0, s.size());
    }

    @Test
    @DisplayName("Push then peek returns last value without mutating size")
    void pushThenPeek() {
        Stack s = new Stack(3);
        s.push(7);
        assertEquals(7, s.peek());
        assertEquals(1, s.size());
        s.push(9);
        assertEquals(9, s.peek());
        assertEquals(2, s.size());
    }

    @Test
    @DisplayName("Push then pop behaves LIFO and mutates size")
    void pushThenPopLifo() {
        Stack s = new Stack(5);
        s.push(1);
        s.push(2);
        s.push(3);
        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    @Test
    @DisplayName("Popping empty stack throws IllegalStateException('Stack is empty')")
    void popEmptyThrows() {
        Stack s = new Stack(2);
        IllegalStateException ex = assertThrows(IllegalStateException.class, s::pop);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    @DisplayName("Peeking empty stack throws IllegalStateException('Stack is empty')")
    void peekEmptyThrows() {
        Stack s = new Stack(2);
        IllegalStateException ex = assertThrows(IllegalStateException.class, s::peek);
        assertEquals("Stack is empty", ex.getMessage());
    }

    @Test
    @DisplayName("Filling to capacity sets isFull true; pushing past capacity throws exact message")
    void pushPastCapacityThrows() {
        Stack s = new Stack(2);
        s.push(10);
        s.push(20);
        assertTrue(s.isFull());
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> s.push(30));
        assertEquals("Stack is full", ex.getMessage());
        assertEquals(2, s.size());
        assertEquals(20, s.peek());
    }

    @ParameterizedTest(name = "Push single value {0} then pop returns same value")
    @ValueSource(ints = { -100, -1, 0, 1, 42, Integer.MAX_VALUE, Integer.MIN_VALUE + 1 })
    void pushPopRoundTripValues(int v) {
        Stack s = new Stack(1);
        s.push(v);
        assertEquals(v, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    @DisplayName("Interleaved push/pop keeps invariants consistent")
    void interleavedOperations() {
        Stack s = new Stack(3);
        s.push(5);      // [5]
        s.push(6);      // [5,6]
        assertEquals(6, s.pop()); // [5]
        s.push(7);      // [5,7]
        assertEquals(7, s.peek());
        assertFalse(s.isEmpty());
        assertFalse(s.isFull());
        assertEquals(2, s.size());
        assertEquals(7, s.pop()); // [5]
        assertEquals(5, s.pop()); // []
        assertTrue(s.isEmpty());
    }
}