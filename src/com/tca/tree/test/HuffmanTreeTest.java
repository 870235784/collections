package com.tca.tree.test;

import org.junit.Test;

import com.tca.tree.HuffmanTree;

public class HuffmanTreeTest {
	
	@Test
	public void test01() {
		HuffmanTree huffmanTree = new HuffmanTree(new int[]{1, 2, 3, 4});
		System.out.println(huffmanTree.WPL());
	}
}
