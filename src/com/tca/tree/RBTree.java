package com.tca.tree;

import com.tca.list.ArrayStack;
import com.tca.list.LinkedQueue;

public class RBTree<T extends Comparable<T>> {
	
	/**
	 * 节点颜色
	 * @author zhoua
	 *
	 */
	static enum NodeColor {
		RED, BLACK;
	}
	
	/**
	 * 节点
	 * @author zhoua
	 *
	 * @param <T>
	 */
	static class Node<T> {
		T value; // 存储的值
		NodeColor color; // 当前节点颜色
		Node<T> left; // 左儿子节点
		Node<T> right; // 右儿子节点
		Node<T> father; // 父节点
		
		/**
		 * 构造器
		 */
		public Node() {
			this.color = NodeColor.RED;
		}
		
		/**
		 * 构造器
		 * @param value
		 */
		public Node(T value) {
			this();
			if (value == null) {
				throw new RuntimeException("value is null");
			}
			this.value = value;
		}
		
		/**
		 * 构造器
		 * @param value
		 * @param father
		 */
		public Node(T value, Node<T> father) {
			this(value);
			this.father = father;
		}
		
		/**
		 * 构造器
		 * @param value
		 * @param color
		 * @param father
		 */
		public Node(T value, NodeColor color, Node<T> father) {
			if (value == null) {
				throw new RuntimeException("value is null");
			}
			this.value = value;
			this.color = color;
			this.father = father;
		}
		
		/**
		 * 更改当前节点颜色
		 */
		public void changeColor() {
			if (this.color == null) {
				throw new RuntimeException("the node color is null");
			}
			this.color = this.color == NodeColor.RED? NodeColor.BLACK: NodeColor.RED;
		}
		
		/**
		 * 获取当前节点的兄弟节点(当前节点不为根节点)
		 * @return
		 */
		public Node<T> getBrother() {
			if (this == this.father.left) {// 如果当前节点为父节点的左儿子
				return this.father.right;
			}
			return this.father.left;
		}
	}
	
	private Node<T> root; // 根节点
	
	/**
	 * 构造器
	 */
	public RBTree () {}
	
	/**
	 * 获取最大值
	 * @return
	 */
	public T getMax() {
		return getMax(root);
	}
	
	/**
	 * 获取以node为根节点的红黑树的最大值 —— 非递归方式
	 * @param rootNode
	 * @return
	 */
	private T getMax(Node<T> rootNode) {
		if (rootNode == null) {
			throw new RuntimeException("root is null");
		}
		
		Node<T> node = rootNode;
		while (node.right != null) {
			node = node.right;
		}
		return node.value;
	}
	
	/**
	 * 获取最小值
	 * @return
	 */
	public T getMin() {
		return getMin(root);
	}
	
	/**
	 * 获取以rootNode为根节点的树的最小值
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
	 * 先序遍历
	 * 	1.先打印当前节点
	 * 	2.再访问左子树
	 * 	3.再访问右子树
	 */
	public void preOrder() {
		preOrder(root);
	}
	
	/**
	 * 采用递归方式先序遍历以rootNode为根节点的树
	 * @param rootNode
	 */
	private void preOrder(Node<T> rootNode) {
		if (rootNode == null) {
			return;
		}
		System.out.println(rootNode.value + ":" + rootNode.color);// 先打印当前节点
		
		if (rootNode.left != null) {// 遍历左子树
			preOrder(rootNode.left);
		}
		
		if (rootNode.right != null) {// 遍历右子树
			preOrder(rootNode.right);
		}
	}
	
	/**
	 * 中序遍历
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
		System.out.println(rootNode.value + ":" + rootNode.color);
		
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
			System.out.println(node.value + ":" + node.color);// 输出队头节点
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
	 * 判断树是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * 以rootNode节点为旋转中心，左旋
	 * @param node
	 */
	private void leftRotation(Node<T> rootNode) {
		if (rootNode == null) {
			return;
		}
		Node<T> newRootNode = rootNode.right;
		//step1 将rootNode的右儿子指向newRootNode的左儿子
		if (newRootNode.left != null) {
			newRootNode.left.father = rootNode;
		}
		rootNode.right = newRootNode.left;
		
		//step2 rootNode的父亲的儿子指向newRootNode
		if (rootNode.father != null) {
			if (rootNode == rootNode.father.left) {
				rootNode.father.left = newRootNode;
			} else {
				rootNode.father.right = newRootNode;
			}
		} else {
			root = newRootNode;
		}
		
		//step3 将rootNode的父亲指向newRootNode, 将newRootNode的左儿子指向rootNode
		newRootNode.father = rootNode.father;
		rootNode.father = newRootNode;
		newRootNode.left = rootNode;
	}
	
	/**
	 * 以rootNode节点为旋转中心，右旋
	 * @param node
	 */
	private void rightRotation(Node<T> rootNode) {
		if (rootNode == null) {
			return;
		}
		Node<T> newRootNode = rootNode.left;
		//step1 
		if (newRootNode.right != null) {
			newRootNode.right.father = rootNode;
		}
		rootNode.left = newRootNode.right;
		
		//step2
		if (rootNode.father != null) {
			if (rootNode == rootNode.father.left) {
				rootNode.father.left = newRootNode;
			} else {
				rootNode.father.right = newRootNode;
			}
		} else {
			root = newRootNode;
		}
		
		//step3
		newRootNode.father = rootNode.father;
		rootNode.father = newRootNode;
		newRootNode.right = rootNode;
	}
	
	/**
	 * 添加元素
	 * @param value
	 */
	public void insert(T value) {
		// step1: 创建新增节点
		Node<T> newNode = new Node<>(value);
		
		// step2: 插入当前元素(不能插入相同元素)
		insert(newNode);
		
		// step3: 调整结构
		alterAfterInsert(newNode);
	}
	
	/**
	 * 调整新插入节点插入后的树结构
	 * @param newNode
	 */
	private void alterAfterInsert(Node<T> newNode) {
		// 1.如果新增节点为根节点
		if (root == newNode) {
			newNode.changeColor(); // 改变根节点的颜色为黑色
			return;
		}
		
		Node<T> father = newNode.father;
		// 2.新增节点不是根节点, 父节点为黑色
		if (father.color == NodeColor.BLACK) {
			return; // 不需要做任何调整
		}
		
		// 3.新增节点不是根节点,父节点为红色(当前节点一定存在祖父节点, 因为根节点为黑色)
		Node<T> uncle = father.getBrother();
		Node<T> grandFather = father.father;
		
		// 3.1 如果叔节点为红色
		if (uncle != null && uncle.color == NodeColor.RED) {
			// 改变叔节点，父节点，祖父节点的颜色，并递归调整祖父节点
			uncle.changeColor();
			father.changeColor();
			grandFather.changeColor();
			alterAfterInsert(grandFather);
		} else {
			// 3.2 叔节点为黑色

			// 3.2.1 新增节点是祖父节点的左儿子的左儿子
			if (grandFather.left == father && father.left == newNode) {
				rightRotation(grandFather);// 右旋转祖父节点
				father.changeColor(); // 更改父节点颜色
				grandFather.changeColor(); // 更改祖父节点颜色
			}
			
			// 3.2.2 新增节点是祖父节点的左儿子的右儿子
			if (grandFather.left == father && father.right == newNode) {
				leftRotation(father);// 左旋转父节点
				rightRotation(grandFather); // 右旋转祖父节点
				newNode.changeColor(); // 更改新节点颜色
				grandFather.changeColor(); // 更改祖父节点颜色
			}
			
			// 3.2.3 新增节点是祖父节点的右儿子的右儿子
			if (grandFather.right == father && father.right == newNode) {
				leftRotation(grandFather);// 左旋转祖父节点
				father.changeColor(); // 更改父节点颜色
				grandFather.changeColor(); // 更改祖父节点颜色
			}
			
			// 3.2.4 新增节点是祖父节点的右儿子的左儿子
			if (grandFather.right == father && father.left == newNode) {
				rightRotation(father);// 右旋转父节点
				leftRotation(grandFather); // 左旋转祖父节点
				newNode.changeColor(); // 更改新节点颜色
				grandFather.changeColor(); // 更改祖父节点颜色
			}
		}
		
	}
	
	/**
	 * 增加节点
	 * @param newNode
	 */
	private void insert(Node<T> newNode) {
		// 如果树为空, 则新增节点为根节点
		if (isEmpty()) {
			root = newNode;
			return;
		}
		insert(root, newNode);
	}
	
	/**
	 * 将newNode新增到根节点为rootNode的树中
	 * @param rootNode
	 * @param newNode
	 */
	private void insert(Node<T> rootNode, Node<T> newNode) {
		T rootValue = rootNode.value;
		T newValue = newNode.value;
		// 如果新节点的值等于根节点
		int result = newValue.compareTo(rootValue);
		if (result == 0) {
			throw new RuntimeException("value " + newValue + " has already existed!");
		}
		
		// 如果新节点的值大于根节点
		if (result > 0) {
			if (rootNode.right == null) { //根节点没有右儿子
				rootNode.right = newNode;
				newNode.father = rootNode;
			} else { // 根节点有右儿子
				insert(rootNode.right, newNode);
			}
		}
		// 如果新节点的值小于根节点
		if (result < 0) { 
			if (rootNode.left == null) { // 根节点没有左儿子
				rootNode.left = newNode;
				newNode.father = rootNode;
			} else { // 根节点有左儿子
				insert(rootNode.left, newNode);
			}
		}
	}
	
	/**
	 * 删除节点值为value的节点
	 * @param value
	 */
	public boolean remove(T value) {
		// step1: 获取当前值对应的节点
		Node<T> node = get(value);
		if (node == null) {
			return false;
		}
		
		// step2: 如果被删除节点左右儿子都不为空，则需要找出后继节点替换删除节点
		if (node.left != null && node.right != null) {
			Node<T> rightMinNode = get(getMin(node.right)); // 找到后继节点
			node.value = rightMinNode.value;// 将后继节点的值赋给原先需要被删除的节点
			node = rightMinNode; // 此时后继节点变成了需要被删除的真正节点
		}
		
		// 如果待删除节点是根节点
		if (root == node) {
			root = null;
			return true;
		}
		
		//========================================================
		// 删除的节点不是根节点
		/*
		 * 此时,被删除节点只可能有两种情况:
		 * 	1.被删除节点没有子孩子
		 *  2.被删除节点有一个子孩子
		 */
		
		// 1.被删除的节点没有子孩子
		if (node.left == null && node.right == null) {
			// 1.1 如果被删除节点是红色，可以直接删除
			if (node.color == NodeColor.RED) {
				if (node.father.left == node) {
					node.father.left = null;
				} else {
					node.father.right = null;
				}
				node.father = null;
			} else {
			// 1.2 被删除的节点是黑色, 需要先进行调整，再删除
				// 调整
				alterAfterRemove(root);
				// 删除
				if (node.father.left == node) {
					node.father.left = null;
				} else {
					node.father.right = null;
				}
				node.father = null;
			}
			return true;
		}
		
		// 2.被删除节点有一个子孩子 (则被删除的节点一定是黑色!!!)
		// step1: 找到被删除的节点的唯一子孩子
		Node<T> son = node.left == null? node.right: node.left;
		
		// step2: 删除被删除节点
		if (node.father.left == node) {
			node.father.left = son;
		} else {
			node.father.right = son;
		}
		
		son.father = node.father;
		node.left = node.right = node.father = null;//删除节点
	
		// step3: 调整子孩子(因为黑色父节点已经被删除，所以必然失衡)
		alterAfterRemove(son);
		
		return true;
	}
	
	/**
	 * 调整rootNode节点
	 * @param rootNode
	 */
	private void alterAfterRemove(Node<T> rootNode) {
		while (rootNode != root && rootNode.color == NodeColor.BLACK) {
			// 调整节点是左儿子
			if (rootNode == rootNode.father.left) {
				Node<T> brother = rootNode.getBrother();
				
				if(getColor(brother) == NodeColor.RED) {
					brother.color = NodeColor.BLACK;
					rootNode.father.color = NodeColor.RED;
					leftRotation(rootNode.father);
					brother = rootNode.father.right;
				}
				
				if (getColor(brother.left) == NodeColor.BLACK && 
						getColor(brother.right) == NodeColor.BLACK) {
					brother.color = NodeColor.RED;
					rootNode = rootNode.father;
				} else {
					if (getColor(brother.right) == NodeColor.BLACK) {
						if (brother.left != null) {
							brother.left.color = NodeColor.BLACK;
						}
						brother.color = NodeColor.RED;
						rightRotation(brother);
						brother = rootNode.father.right;
					}
					brother.color = rootNode.father.color; 
					rootNode.father.color = NodeColor.BLACK;
					if (brother.right != null) {
						brother.right.color = NodeColor.BLACK;
					}
					leftRotation(rootNode.father);
					rootNode = root;
				}
			} else {
			// 调整节点是右儿子	
				Node<T> brother = rootNode.getBrother();
				if (getColor(brother) == NodeColor.RED) {
					brother.color = NodeColor.BLACK;
					rootNode.father.color = NodeColor.RED;
					rightRotation(rootNode.father);
					brother = rootNode.father.left;
				}
				
				if (getColor(brother.right) == NodeColor.BLACK &&
						getColor(brother.left) == NodeColor.BLACK) {
					brother.color = NodeColor.RED;
					rootNode = rootNode.father;
				} else {
					if (getColor(brother.left) == NodeColor.BLACK) {
						if (brother.right != null) {
							brother.right.color = NodeColor.BLACK;
						}
						brother.color = NodeColor.RED;
						leftRotation(brother);
						brother = rootNode.father.left;
					}
					brother.color = rootNode.father.color;
					rootNode.father.color = NodeColor.BLACK;
					if (brother.left != null) {
						brother.left.color = NodeColor.BLACK;
					}
					rightRotation(rootNode.father);
					rootNode = root;
				}
			}
		}
		
		rootNode.color = NodeColor.BLACK;
	}
	
	/**
	 * 获取节点颜色 —— 节点为空时，为黑色
	 * @param node
	 * @return
	 */
	private NodeColor getColor(Node<T> node) {
		return node == null? NodeColor.BLACK: node.color;
	}

	/**
	 * 根据value获取对应节点 —— 非递归的方式
	 * @param value
	 * @return
	 */
	private Node<T> get(T value) {
		if (value == null) {
			return null;
		}
		
		Node<T> rootNode = root;
		
		while (rootNode != null) {
			T rootNodeValue = rootNode.value;
			int result = value.compareTo(rootNodeValue);
			if (result == 0) {
				return rootNode;
			} else if (result < 0) {
				rootNode = rootNode.left;
			} else {
				rootNode = rootNode.right;
			}
		}
		
		return null;
	}
	
}
