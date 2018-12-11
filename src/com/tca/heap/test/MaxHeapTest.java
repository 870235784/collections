package com.tca.heap.test;

import org.junit.Test;

import com.tca.heap.MaxHeap;

public class MaxHeapTest {
	
	@Test
	public void test01() {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();
		
		System.out.println(maxHeap.isEmpty());
		
		maxHeap.add(1);
		maxHeap.add(2);
		maxHeap.add(3);
		maxHeap.add(4);
		maxHeap.add(5);
		
		System.out.println(maxHeap);
	}
}
