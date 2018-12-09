package com.tca.list.test;

import org.junit.Test;

import com.tca.list.ArrayStack;

public class ArrayStackTest {
	@Test
	public void test01() {
		ArrayStack<String> stack = new ArrayStack<>();
		
		// push
		stack.push("A");
		stack.push("B");
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
		ArrayStack<String> stack = new ArrayStack<>();
		stack.push("A");
		stack.push("B");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
