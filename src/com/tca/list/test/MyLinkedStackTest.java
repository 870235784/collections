package com.tca.list.test;

import org.junit.Test;

import com.tca.list.MyArrayStack;
import com.tca.list.MyLinkedStack;

public class MyLinkedStackTest {
	@Test
	public void test01() {
		MyLinkedStack<String> stack = new MyLinkedStack<>();
		
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
		MyLinkedStack<String> stack = new MyLinkedStack<>();
		stack.push("A");
		stack.push("B");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
