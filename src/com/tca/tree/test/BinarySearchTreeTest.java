package com.tca.tree.test;

import org.junit.Test;

import com.tca.tree.BinarySearchTree;

public class BinarySearchTreeTest {

	@Test
	public void test01() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		//isEmpty
		System.out.println(tree.isEmpty());
		
		//add
		tree.add(6);
		tree.add(3);
		tree.add(8);
		tree.add(1);
		tree.add(9);
		tree.add(4);
		//isEmpty
		System.out.println(tree.isEmpty());
		
		//getMax getMin
		System.out.println(tree.getMax());
		System.out.println(tree.getMin());
		
		//preOrder
		tree.preOrder();
		System.out.println("================");
		tree.inOrder();
		System.out.println("================");
		tree.postOrder();
		System.out.println("================");
		tree.levelOrder();
	}
	
	@Test
	public void test02() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		//add
		tree.add(6);
		tree.add(3);
		tree.add(8);
		tree.add(1);
		tree.add(9);
		tree.add(4);
		boolean add = tree.add(2);
		System.out.println(add);
		tree.preOrder();
		boolean delete = tree.delete(3);
		System.out.println(delete);
		tree.preOrder();
	}
}
