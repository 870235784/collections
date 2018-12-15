package com.tca.tree;

import com.tca.list.ArrayStack;
import com.tca.list.LinkedQueue;

/**
 *  二叉搜索树
 * 	1.构造树
 * 	2.判断树是否为空
 * 	3.增加元素
 * 	4.删除元素
 * 	5.获取最大值
 * 	6.获取最小值
 * 	7.遍历: 先序遍历, 中序遍历, 后序遍历, 层序遍历
 * @author zhoua
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
	
	private Node<T> root; // 根节点
	
	static class Node<T> {
		T value; // 节点存储的值
		Node<T> left; // 左儿子节点
		Node<T> right; // 右儿子节点
		Node<T> father; // 父节点
		
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
		Node(T value, Node<T> left, Node<T> right, Node<T> father) {
			this(value);
			this.left = left;
			this.right = right;
			this.father = father;
		}
		
		/**
		 * 释放节点
		 */
		public void free() {
			this.value = null;
			this.left = null;
			this.right = null;
			this.father = null;
		}
		
	}
	
	/**
	 *  构造器
	 */
	public BinarySearchTree() {}
	
	/**
	 * 判断树是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * 插入元素 -- 不允许插入相同元素
	 * @param value
	 * @return
	 */
	public boolean add(T value) {
		if (value == null) {
			throw new RuntimeException("the element is null");
		}
		
		Node<T> newNode = new Node<>(value);
		
		// 如果树为空, 则新增节点为根节点
		if (isEmpty()) {
			root = newNode;
			return true;
		}
		return add(root, newNode);
	}
	
	/**
	 *  插入元素 -- 递归算法
	 * @param rootNode 根节点
	 * @param newNode 新插入节点
	 * @return
	 */
	private boolean add(Node<T> rootNode, Node<T> newNode) {
		T rootValue = rootNode.value;
		T newValue = newNode.value;
		// 如果新节点的值等于根节点
		int result = newValue.compareTo(rootValue);
		if (result == 0) {
			return false;
		}
		
		// 如果新节点的值大于根节点
		if (result > 0) {
			if (rootNode.right == null) { //根节点没有右儿子
				rootNode.right = newNode;
				newNode.father = rootNode;
				return true;
			} else { // 根节点有右儿子
				add(rootNode.right, newNode);
			}
		}
		// 如果新节点的值小于根节点
		if (result < 0) { 
			if (rootNode.left == null) { // 根节点没有左儿子
				rootNode.left = newNode;
				newNode.father = rootNode;
				return true;
			} else { // 根节点有左儿子
				add(rootNode.left, newNode);
			}
		}
		return true;
	}
	

	/**
	 * 查找值为value的节点Node
	 * @param value
	 * @return
	 */
	public Node<T> get(T value) {
		Node<T> node = new Node<>(value);
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
		queue.enqueue(rootNode);
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
	
	/**
	 * 删除元素
	 * @param value
	 * @return
	 */
	public boolean delete(T value) {
		// 先查找到对应的节点
		Node<T> node = get(value);
		if (node == null) {
			return false;
		}
		
		Node<T> right = node.right;
		Node<T> left = node.left;
		Node<T> father = node.father;
		// 如果节点是叶子节点
		if (right == null && left == null) {
			node.free();
			if (father != null) {
				if (father.value.compareTo(value) > 0) {
					father.left = null;
				}
				if (father.value.compareTo(value) < 0) {
					father.right = null;
				}
			} 
			return true;
		}
		
		// 如果有两个儿子 -- 用右子树的最小元素或左子树的最大元素替代
		// 本方法采用右子树的最小元素替代
		if (right != null && left != null) {
			// 找出右子树的最小元素
			T min = getMin(right); // 最小元素
			delete(min); // 删除该节点
			node.value = min; // 更新替换元素的值
			if (father == null) { // 如果父节点为空，则删除节点为父节点
				root = node;
			}
			return true;
		}
		
		
		// 如果有一个儿子
		node.free();
		if (left != null) {
			if (father != null) {
				if (father.value.compareTo(value) > 0) {// 如果父节点大于当前节点
					father.left = left;
				} else if (father.value.compareTo(value) < 0) {
					father.right = left;
				}
			} else {// 如果父节点为空, 则左儿子为根
				root = left;
			}
			left.father = father; 
			return true;
		}
		
		if (right != null) {
			if (father != null) {
				if (father.value.compareTo(value) > 0) {// 如果父节点大于当前节点
					father.left = right;
				} else if (father.value.compareTo(value) < 0) {
					father.right = right;
				}
			} else {// 如果父节点为空, 则右儿子为根
				root = right;
			}
			right.father = father; 
		}
		return true;
	}
	
}
