package com.sea4;
public class Stack {
    private int[] stack;
    private int top;
    private int capacity;

    // Constructor
    public Stack(int size) {
        capacity = size;
        stack = new int[size];
        top = -1;
    }

    // Push an element
    public void push(int value) {
        if (top < capacity - 1) {
            stack[++top] = value;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    // Pop an element
    public int pop() {
        if (top >= 0) {
            return stack[top--];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    // Peek top element
    public int peek() {
        if (top >= 0) {
            return stack[top];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Return current size
    public int size() {
        return top + 1;
    }
}
