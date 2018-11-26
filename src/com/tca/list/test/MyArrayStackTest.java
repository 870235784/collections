package com.tca.list.test;

import org.junit.Test;

import com.tca.list.MyArrayStack;

public class MyArrayStackTest {
	@Test
	public void test01() {
		MyArrayStack<String> stack = new MyArrayStack<>();
		
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
		MyArrayStack<String> stack = new MyArrayStack<>();
		stack.push("A");
		stack.push("B");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
