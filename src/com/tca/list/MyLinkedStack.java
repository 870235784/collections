package com.tca.list;

/**
 * 底层是单向链表的栈, 只能以头节点作为top, 有限栈
 * @author zhoua
 *
 * @param <T>
 */
public class MyLinkedStack<T> {
	
	private Node<T> top; // 栈顶元素
	
	private int maxSize; // 栈的最大使用空间
	
	private int size; // 栈的实际使用大小
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
		
		public Node(T value) {
			this.value = value;
		}
		
		public Node(T value, Node<T> node) {
			this.value = value;
			this.next = node;
		}
		
		public void free() {
			this.value = null;
			this.next = null;
		}
	}
	
	/**
	 * 构造器, 传入的参数为栈的最大容量
	 * @param size
	 */
	public MyLinkedStack(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		this.maxSize = size;
	}
	
	/**
	 * 默认容量为32
	 */
	public MyLinkedStack() {
		this(32);
	}
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 判断栈是否已满(因为为可扩容数组，所以不会满)
	 * @return
	 */
	public boolean isFull() {
		return size >= maxSize;
	}
	
	/**
	 * 栈的实际使用大小
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 将元素压入栈顶
	 * @param element
	 * @return
	 */
	public boolean push(T e) {
		// 判断当前栈是否已满
		if (isFull()) {
			throw new RuntimeException("the stack is full");
		}
		
		// 将元素添加到栈顶
		Node<T> newNode = top == null? new Node(e): new Node(e, top);
		top = newNode;
		size++;
		
		return true;
	}
	
	/**
	 * 获取栈顶元素, 但不会弹栈
	 * @return
	 */
	public T top() {
		return top.value;
	}
	
	/**
	 * 弹出栈顶元素
	 * @return
	 */
	public T pop() {
		// 如果栈为空, 则抛出异常
		if (isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		// 使用新节点指向原先的top, 获取值后, 再释放, 便于垃圾回收
		Node<T> node = top;
		T value = node.value;
		top = top.next;
		node.free();
		size--;
		return value;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node<T> node = top;
		sb.append("[");
		if (size > 0) {
			for (int i = 0; i < size - 1; i++) {
				sb.append(node.value.toString());
				sb.append(", ");
				node = node.next;
			}
			sb.append(node.value.toString());
		}
		sb.append("]");
		return sb.toString();
	}
}
