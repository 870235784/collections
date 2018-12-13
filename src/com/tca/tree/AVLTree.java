package com.tca.tree;

import com.tca.list.ArrayStack;
import com.tca.list.LinkedQueue;

/**
 * AVL树:
 * 	1.构造AVL树
 * 	2.判断AVL树是否为空
 * 	3.获取AVL树的高度
 * 	4.添加元素
 * 	5.删除元素
 * 	6.获取最大元素
 * 	7.获取最小元素
 * 	8.遍历: 先序遍历, 中序遍历, 后序遍历, 层序遍历
 * @author zhoua
 *
 * @param <T>
 */
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
		
		// 重新计算发现者和新的根节点的高度 -- 子树改变时需要重新计算高度
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
		
		// 重新计算发现者和新的根节点的高度 -- 子树改变时需要重新计算高度
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
	public void add(T value) {
		root = add(root, value);
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
	private Node<T> add(Node<T> rootNode, T value) {
		if (value == null) {
			throw new RuntimeException("the element is null");
		}
		
		// 如果树为空, 则直接新建根节点, 并返回
		if (rootNode == null) {
			return new Node(value);
		}
		
		// 如果树不为空
		int result = rootNode.value.compareTo(value);
		
		if (result < 0) {// 插入右子树
			rootNode.right = add(rootNode.right, value);
			// 插入后,判断是否需要旋转
			if (getBalanceFactor(rootNode) == -2) {// 需要旋转
				if (value.compareTo(rootNode.right.value) > 0) { // 插在右子树的右子树上 -- RR旋转
					rootNode = rightRightRotation(rootNode);
				} else if (value.compareTo(rootNode.right.value) < 0) { // 插在右子树的左子树上 -- RL旋转
					rootNode = rightLeftRotation(rootNode);
				}
			}
		} else if (result > 0) {// 插入左子树
			rootNode.left = add(rootNode.left, value);
			if (getBalanceFactor(rootNode) == 2) {
				if (value.compareTo(rootNode.left.value) > 0) { // 插在左子树的右子树上 -- LR旋转
					rootNode = leftRightRotation(rootNode);
				} else if (value.compareTo(rootNode.left.value) < 0) { // 插在左子树的左子树上 -- LL旋转
					rootNode = leftLeftRotation(rootNode);
				}
			}
		}
		
		// 重新计算树高度
		rootNode.height = height(rootNode);
		
		return rootNode;
	}
	
	/**
	 * 删除
	 * @param data
	 */
	public void remove(T data) {
		this.root = remove(root, data);
	}
	
	/**
	 *  删除操作:
	 *  rootNode : 删除的树的根节点
	 *  value : 删除的值
	 *  return : 返回删除节点后的树的根节点
	 * @param rootNode
	 * @param data
	 * @return
	 */
	private Node<T> remove(Node<T> rootNode, T data) {
		if (rootNode == null) {
			throw new RuntimeException("rootNode is null");
		}
		if (data == null) {
			throw new RuntimeException("data is null");
		}
		
		T value = rootNode.value;
		int result = data.compareTo(value);
		
		if (result > 0) {// 如果删除的节点在根节点右边
			rootNode.right = remove(rootNode.right, data);
			// 子节点有变化, 要判断当前树是否需要旋转
			if (getBalanceFactor(rootNode) == 2) {
				if(height(rootNode.left.left) > height(rootNode.left.right)) {
					// LL旋转
					rootNode = leftLeftRotation(rootNode);
				} else {
					// LR旋转
					rootNode = leftRightRotation(rootNode); 
				}
			}
		} else if (result < 0) {// 如果删除节点在根节点的左边
			rootNode.left = remove(rootNode.left, data);
			// 子节点有变化，要判断当前树是否需要旋转
			if (getBalanceFactor(rootNode) == -2) {
				if (height(rootNode.right.left) > height(rootNode.right.right)) {
					//RL旋转
					rootNode = rightLeftRotation(rootNode);
				} else {
					//RR旋转
					rootNode = rightRightRotation(rootNode);
				}
			}
		} else {// 删除的节点为当前节点
			if (rootNode.right != null && rootNode.left != null) {
				rootNode.value = getMin(rootNode.right);// 用右子树最小值代替当前节点
				rootNode.right = remove(rootNode.right, rootNode.value);
			} else {
				rootNode = rootNode.left == null? rootNode.right: rootNode.left;
			}
			
		}
		
		// 更新树的高度
		if (rootNode != null) {
			rootNode.height = height(rootNode);
		}
		
		return rootNode;
	}

	/**
	 * 查找值为value的节点Node
	 * @param value
	 * @return
	 */
	public Node<T> get(T value) {
		Node<T> node = new Node(value);
		if (root == null) {
			return null;
		}
		return get(root, node);
	}
	
	/**
	 * 查找
	 * @param rootNode
	 * @param node
	 * @return
	 */
	private Node<T> get(Node<T> rootNode, Node<T> node) {
		T rootValue = rootNode.value;
		T value = node.value;
		
		int result = value.compareTo(rootValue);
		if (result == 0) {
			return rootNode;
		}
		
		if (result > 0) {
			return get(rootNode.right, node);
		} 
		
		if (result < 0) {
			return get(rootNode.left, node);
		}
		return null;
	}
	
	/**
	 * 获取最大值
	 * @return
	 */
	public T getMax() {
		return getMax(root);
	}
	
	/**
	 * 获取最大值
	 * @param rootNode
	 */
	private T getMax(Node<T> rootNode) {
		if (rootNode == null) {
			throw new RuntimeException("root is null");
		}
		if (rootNode.right == null) {
			return rootNode.value;
		}
		return getMax(rootNode.right);
	}
	
	/**
	 * 获取最小值
	 * @return
	 */
	public T getMin() {
		return getMin(root);
	}
	
	/**
	 * 获取最小值
	 * @param rootNode
	 * @return
	 */
	private T getMin(Node<T> rootNode) {
		if (rootNode == null) {
			throw new RuntimeException("root is null");
		}
		if (rootNode.left == null) {
			return rootNode.value;
		}
		return getMin(rootNode.left);
	}
	
	/**
	 * 采用递归先序遍历
	 * 	1.先打印当前节点
	 * 	2.再访问左子树
	 * 	3.再访问右子树
	 */
	public void preOrder() {
		preOrder(root);
	}
	
	/**
	 * 采用递归方式先序遍历
	 *  1.先打印当前节点
	 *  2.再访问左子树
	 *  3.再访问右子树
	 * @param rootNode
	 */
	private void preOrder(Node<T> rootNode) {
		if (rootNode == null) {
			return;
		}
		// 输出当前节点
		System.out.println(rootNode.value);
		// 遍历左子树
		if (rootNode.left != null) {
			preOrder(rootNode.left);
		}
		// 遍历右子树
		if (rootNode.right != null) {
			preOrder(rootNode.right);
		}
	}
	
	/**
	 * 采用递归方式中序遍历
	 *  1.先访问左子树
	 * 	2.打印当前节点
	 * 	3.访问右子树
	 */
	public void inOrder() {
		inOrder(root);
	}
	
	/**
	 * 采用递归方式中序遍历
	 * @param rootNode
	 */
	private void inOrder(Node<T> rootNode) {
		if (rootNode == null) {
			return;
		}
		// 遍历左子树
		if (rootNode.left != null) {
			inOrder(rootNode.left);
		}
		// 输出当前节点
		System.out.println(rootNode.value);
		// 遍历右子树
		if (rootNode.right != null) {
			inOrder(rootNode.right);
		}
	}
	
	/**
	 * 后序遍历 -- 采用栈结构
	 */
	public void postOrder() {
		ArrayStack<T> stack = new ArrayStack<T>();
		postOrder(root, stack);
	}
	
	/**
	 * 后序遍历
	 * @param rootNode
	 */
	private void postOrder(Node<T> rootNode, ArrayStack<T> stack) {
		if (rootNode == null) {
			return;
		}
		stack.push(rootNode.value);
		if (rootNode.left != null) {
			postOrder(rootNode.left, stack);
		}
		if (rootNode.right != null) {
			postOrder(rootNode.right, stack);
		}
		System.out.println(stack.pop());
	}
	
	/**
	 * 层序遍历 -- 采用队列的方式
	 */
	public void levelOrder() {
		LinkedQueue<Node<T>> queue = new LinkedQueue<>();
		levelOrder(root, queue);
	}
	/**
	 * 层序遍历
	 * @param rootNode
	 * @param myLinkedQueue
	 */
	private void levelOrder(Node<T> rootNode, LinkedQueue<Node<T>> queue) {
		if (rootNode != null) {
			queue.enqueue(rootNode);
		}
		while (!queue.isEmpty()) {
			Node<T> node = queue.dequeue();
			System.out.println(node.value);// 输出队头节点
			// 将左儿子和右儿子加入队列
			if (node.left != null) {
				queue.enqueue(node.left);
			}
			if (node.right != null) {
				queue.enqueue(node.right);
			}
		}
	}
	
}
