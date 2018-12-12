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
		maxHeap.add(6);
		
		System.out.println(maxHeap);
		
		System.out.println(maxHeap.remove());
		System.out.println(maxHeap);
		System.out.println(maxHeap.remove());
		System.out.println(maxHeap);
		System.out.println(maxHeap.remove());
		System.out.println(maxHeap);
		System.out.println(maxHeap.remove());
		System.out.println(maxHeap);
		System.out.println(maxHeap.remove());
		System.out.println(maxHeap);
	}
	
	
	@Test
	public void test02() {
		System.out.println("Hello");
	}
	
	@Test
	public void test03() {
		Integer[] elements = new Integer[] {1, 2, 3, 4, 5, 6};
		MaxHeap<Integer> maxHeap = new MaxHeap<>(elements); 
		System.out.println(maxHeap);
	}
	
	
}
