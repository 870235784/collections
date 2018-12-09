package com.tca.list.test;

import org.junit.Test;

import com.tca.list.LinkedQueue;

public class LinkedQueueTest {
	@Test
	public void test01() {
		LinkedQueue<String> queue = new LinkedQueue<>();
		
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
		System.out.println(queue);
		
		System.out.println("============================");
		
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i + "");
		}
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
		System.out.println(queue);
		
		System.out.println("===============================");
		
		System.out.println(queue.dequeue());
		System.out.println(queue);
		
		System.out.println("================================");
		for (int i = 0; i < 5; i++) {
			System.out.println(queue.dequeue());
		}
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
	}
}
