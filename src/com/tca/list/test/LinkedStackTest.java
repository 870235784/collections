package com.tca.list.test;

import org.junit.Test;

import com.tca.list.LinkedStack;

public class LinkedStackTest {
	@Test
	public void test01() {
		LinkedStack<String> stack = new LinkedStack<>();
		
		// push
		stack.push("A");
		System.out.println(stack);
		stack.push("B");
		System.out.println(stack);
		stack.push("C");
		System.out.println(stack);
		
		// isEmpty
		System.out.println(stack.isEmpty());
		
		// top
		System.out.println(stack.top());
		System.out.println(stack);
		
		// pop
		System.out.println(stack.pop());
		System.out.println(stack);
	}
	
	@Test
	public void test02() {
		LinkedStack<String> stack = new LinkedStack<>();
		stack.push("A");
		stack.push("B");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
