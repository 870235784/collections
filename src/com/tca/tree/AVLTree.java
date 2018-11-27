package com.tca.tree;

public class AVLTree<T extends Comparable<T>> {
	
	private Node<T> root; // 根节点
	
	static class Node<T> {
		T value; // 节点存储的值
		Node<T> left; // 左儿子节点
		Node<T> right; // 右儿子节点
		int height; // 高度
		
		/**
		 * 构造器
		 * @param value
		 */
		Node(T value) {
			if (value == null) {
				throw new RuntimeException("value is null");
			}
			this.value = value;
		}
		
		/**
		 * 构造器
		 * @param value
		 * @param left
		 * @param right
		 * @param father
		 */
		Node(T value, Node<T> left, Node<T> right) {
			this(value);
			this.left = left;
			this.right = right;
		}
		
		/**
		 * 释放节点
		 */
		public void free() {
			this.value = null;
			this.left = null;
			this.right = null;
		}
		
	}
	
	/**
	 *  构造器
	 */
	public AVLTree() {}
	
	/**
	 * 判断树是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	public int height() {
		return height(root);
	}
	
	/**
	 * 计算以某个节点为根的树的高度
	 * 	1.节点为空, height = -1
	 * 	2.节点不为空, height = max(左子树height, 右子树height) + 1
	 * @param node
	 * @return
	 */
	private int height(Node<?> node) {
		if (node == null) {
			return -1;
		}
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	/**
	 * 计算某个节点的平衡因子
	 * 	左子树height - 右子树height
	 * 	用平衡因子判断当前树节点是否需要旋转
	 * @param node
	 * @return
	 */
	private int getBalanceFactor(Node<?> node) {
		return height(node.left) - height(node.right);
	}
	
	/**
	 * LL旋转:
	 * 	概念: 破坏者在发现者左子树的左子树上
	 * 	rootNode: 发现者节点
	 *  return: 旋转后的树的根节点 -- 为发现者的左子树的根节点
	 * @param rootNode
	 * @return 
	 */
	private Node<T> leftLeftRotation(Node<T> rootNode) {
		Node<T> newRoot;
		newRoot = rootNode.left; // 新的根节点为发现者的左子树的根节点
		rootNode.left = newRoot.right; // 发现者的左子树变为新的根节点的右子树
		newRoot.right = rootNode; // 新的根节点的右子树变为发现者
		
		// 重新计算发现者和新的根节点的高度
		rootNode.height = height(rootNode);
		newRoot.height = height(newRoot);
		return newRoot;
	}
	
	/**
	 * RR旋转:
	 * 	概念: 破坏者在发现者右子树的右子树上
	 * 	rootNode: 发现者节点
	 *  return: 旋转后的树的根节点 -- 为发现者的右子树的根节点
	 * @param rootNode
	 * @return
	 */
	private Node<T> rightRightRotation(Node<T> rootNode) {
		Node<T> newRoot;
		newRoot = rootNode.right; // 新的根节点为发现者的右子树的根节点
		rootNode.right = newRoot.left; // 发现者的右子树变为新的根节点的左子树
		newRoot.left = rootNode; // 新的根节点的左子树变为发现者
		
		// 重新计算发现者和新的根节点的高度
		rootNode.height = height(rootNode);
		newRoot.height = height(newRoot);
		return newRoot;
	}
	
	/**
	 * LR旋转:
	 * 	概念: 破坏者在发现者左子树的右子树上
	 * 	rootNode: 发现者节点
	 *  return: 旋转后的树的根节点 
	 *  step:
	 *  	1.以破坏者左子树的根节点为破坏者作RR单旋转
	 *  	2.以破坏者作LL单旋转
	 * @param rootNode
	 * @return
	 */
	private Node<T> leftRightRotation(Node<T> rootNode) {
		rootNode.left = rightRightRotation(rootNode.left); // 以破坏者左子树的根节点为破坏者作RR单旋转
		return leftLeftRotation(rootNode); // 以破坏者作LL单旋转
	}
	
	/**
	 * LR旋转:
	 * 	概念: 破坏者在发现者右子树的左子树上
	 * 	rootNode: 发现者节点
	 *  return: 旋转后的树的根节点 
	 *  step:
	 *  	1.以破坏者左右子树的根节点为破坏者作LL单旋转
	 *  	2.以破坏者作RR单旋转
	 * @param rootNode
	 * @return
	 */
	private Node<T> rightLeftRotation(Node<T> rootNode) {
		rootNode.right = leftLeftRotation(rootNode.right); // 以破坏者右子树的根节点为破坏者作LL单旋转
		return rightRightRotation(rootNode); // 以破坏者作RR单旋转
	}
	
	/**
	 * 插入节点
	 * @param value
	 */
	public void insert(T value) {
		root = insert(root, value);
	}
	
	/**
	 *  插入操作:
	 *  rootNode : 插入的树的根节点
	 *  value : 插入的值
	 *  return : 返回插入节点后的树的根节点
	 * @param rootNode
	 * @param value
	 * @return
	 */
	private Node<T> insert(Node<T> rootNode, T value) {
		// 如果树为空, 则直接新建根节点, 并返回
		if (isEmpty()) {
			return new Node(value);
		}
		
		// 如果树不为空
		int result = rootNode.value.compareTo(value);
		
		if (result > 0) {// 插入右子树
			rootNode.right = insert(rootNode.right, value);
			// 插入后,判断是否需要旋转
			if (getBalanceFactor(rootNode) == -2) {// 需要旋转
				if (value.compareTo(rootNode.right.value) > 0) { // 插在右子树的右子树上 -- RR旋转
					rootNode = rightRightRotation(rootNode);
				} else if (value.compareTo(rootNode.right.value) < 0) { // 插在右子树的左子树上 -- RL旋转
					rootNode = rightLeftRotation(rootNode);
				}
			}
		} else if (result < 0) {// 插入左子树
			rootNode.left = insert(rootNode.left, value);
			if (getBalanceFactor(rootNode) == 2) {
				if (value.compareTo(rootNode.right.value) > 0) { // 插在左子树的右子树上 -- LR旋转
					rootNode = leftRightRotation(rootNode);
				} else if (value.compareTo(rootNode.right.value) < 0) { // 插在左子树的左子树上 -- LL旋转
					rootNode = leftLeftRotation(rootNode);
				}
			}
		}
		
		// 重新计算树高度
		rootNode.height = height(rootNode);
		
		return rootNode;
	}
	
}
