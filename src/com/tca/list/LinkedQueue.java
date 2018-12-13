package com.tca.list;

/**
 * 队列的单向链表表示 -- 无限队列
 * 	1.构建单向链表队列
 * 	2.判断队列是否为空
 * 	3.返回队列存储元素个数
 * 	4.入队
 * 	5.出队
 * @author zhoua
 *
 * @param <T>
 */
public class LinkedQueue<T> {
	
	private Node<T> front; // 队头
	
	private Node<T> rear; // 队尾
	
	private int size; // 队列中实际元素个数
	
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
	 * 构造器
	 */
	public LinkedQueue() {}
	
	/**
	 * 队列中元素个数
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断队列是否已满
	 * @return
	 */
	public boolean isFull() {
		return false;
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return front == null || rear == null;
	}
	
	/**
	 * 入队
	 * @param e
	 */
	public void enqueue(T e) {
		Node<T> node = new Node(e);
		if (isEmpty()) {// 如果队列为空
			front = rear = node;
			size++;
			return;
		}
		rear.next = node;
		rear = node;
		size++;
	}
	
	/**
	 * 出队
	 * @return
	 */
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("queue is empty");
		}
		Node<T> oldFront = front;
		T value = oldFront.value;
		front = front.next;
		oldFront.free();
		size--;
		return value;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node<T> node = front;
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
