package com.tca.tree;

import com.tca.heap.MinHeap;

/**
 * 哈夫曼树(最优二叉树)
 * 	1.构造哈夫曼树(所有的原始节点都在叶子节点上)
 * 	2.计算树的WPL(带权路径长度)
 * @author zhoua
 *
 */
public class HuffmanTree {
	
	private Node root; // 根
	
	static class Node implements Comparable<Node>{
		int value; // 节点存储的值
		Node left; // 左儿子节点
		Node right; // 右儿子节点
		int depth; // 该节点的深度
		
		/**
		 * 构造器
		 * @param value
		 */
		Node(int value) {
			this.value = value;
		}
		
		/**
		 * 构造器
		 * @param value
		 * @param left
		 * @param right
		 */
		Node(int value, Node left, Node right, int depth) {
			this(value);
			this.left = left;
			this.right =right;
			this.depth = depth;
			depth(this);
		}
		
		/**
		 *  增加该节点每一个子节点的depth
		 * @param node
		 */
		private void depth(Node node) {
			if (node == null || node.left == null || node.right == null) {
				return;
			}
			node.left.depth ++;
			node.right.depth ++;
			depth(node.left);
			depth(node.right);
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(value, node.value);
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", left=" + left + ", right=" + right + ", depth=" + depth + "]";
		}
		
	}
	
	public HuffmanTree() {}
	
	/**
	 * 将可排序数组转成哈夫曼树(最终元素都在叶子节点上)
	 * @param elements
	 */
	public HuffmanTree (int[] elements) {
		this();
		if (elements == null || elements.length == 0) {
			return;
		}
		
		int length = elements.length;
		if (length == 1) {
			root = new Node(elements[0], null, null, 0);
			return;
		}
		
		// 将可排序元素转成最小堆
		Node[] nodes = new Node[length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(elements[i]);
		}
		MinHeap<Node> minHeap = new MinHeap<>(nodes); 
		
		/**
		 * 取出最小的两个元素, 合并成一个新的树, 放入最小堆中
		 */
		while (minHeap.size() > 1) {
			Node first = minHeap.remove();
			Node second = minHeap.remove();
			minHeap.add(new Node(first.value + second.value, first, second, 0));
		}
		
		// 最小堆中的最后一个元素即为根节点
		this.root = minHeap.remove();
	}
	
	public int WPL() {
		return WPL(root);
	}
	
	/**
	 * 计算某一个节点为根的WPL
	 * @param node
	 * @return
	 */
	private int WPL(Node node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null || node.right == null) {
			return node.value * node.depth;
		}
		return WPL(node.left) + WPL(node.right);
	}
}
