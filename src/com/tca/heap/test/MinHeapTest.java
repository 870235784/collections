package com.tca.heap.test;

import org.junit.Test;

import com.tca.heap.MinHeap;


public class MinHeapTest {
	
	@Test
	public void test01() {
		MinHeap<Integer> minHeap = new MinHeap<>();
		
		minHeap.add(6);
		minHeap.add(5);
		minHeap.add(4);
		minHeap.add(3);
		minHeap.add(2);
		minHeap.add(1);
		System.out.println(minHeap);
		
		minHeap.add(0);
		
		System.out.println(minHeap);
		
		
	}
}
