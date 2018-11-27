package com.tca.tree.test;

import org.junit.Test;

import com.tca.tree.AVLTree;

public class AVLTreeTest {

	@Test
	public void test01() {
		int[] arr= {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
		AVLTree<Integer> tree = new AVLTree<>();
		
		for (int i : arr) {
			tree.add(i);
			System.out.println("height = " + tree.height());
			tree.levelOrder();
			System.out.println("================");
		}
	}
	
	@Test
	public void test02() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.add(3);
		tree.add(2);
	}
}
