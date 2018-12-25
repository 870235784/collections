package com.tca.tree.test;

import org.junit.Test;

import com.tca.tree.RBTree;

public class RBTreeTest {
	/*@Test
	public void test01() {
		RBTree<Integer> tree = new RBTree<>();
		//isEmpty
		System.out.println(tree.isEmpty());
		
		//add
		tree.insert(6);
		tree.insert(3);
		tree.insert(8);
		tree.insert(1);
		tree.insert(9);
		tree.insert(4);
		tree.insert(6);
		tree.insert(316);
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
		tree.levelOrder();
	}*/
	
	@Test
	public void test02() {
		RBTree<Integer> tree = new RBTree<>();
		
		tree.insert(316);
		tree.insert(599);
		tree.insert(627);
		tree.insert(142);
		tree.insert(315);
		tree.insert(529);
		tree.insert(374);
		tree.insert(79);
		tree.insert(221);
		tree.insert(185);
		tree.insert(185);
		/*
		*/
		
		
		//getMax getMin
		System.out.println("max = " + tree.getMax());
		System.out.println("min = " + tree.getMin());
		
		//preOrder
		/*tree.preOrder();
		System.out.println("================");
		tree.inOrder();*/
		System.out.println("================");
		tree.levelOrder();
	}
}
