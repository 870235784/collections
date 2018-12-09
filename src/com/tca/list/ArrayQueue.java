package com.tca.list;

/**
 *   循环数组队列 -- 有限队列
 * @author zhoua
 *
 * @param <T>
 */
public class ArrayQueue<T> {
	
	private int front; // 队列头位置(出队列)
	
	private int rear; // 队列尾位置(入队列)
	
	private Object[] elementData; // 用于存储实际数据
	
	/**
	 * 无参构造器
	 */
	public ArrayQueue() {
		this(10);
	}
	
	/**
	 * 用于表示队列的实际大小
	 * @param size
	 */
	public ArrayQueue(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		this.elementData = new Object[size + 1];// 空一格位置用来区分队列已满和队列为空的状态
	}
	
	/**
	 * 判断队列是否为空 -- 当front和rear重合时表示为空
	 * @return
	 */
	public boolean isEmpty() {
		return front == rear;
	}
	
	/**
	 * 返回队列中的元素个数
	 * @return
	 */
	public int size() {
		return rear - front;
	}
	
	/**
	 * 判断队列已满
	 * @return
	 */
	public boolean isFull() {
		return rear - front == this.elementData.length -1;
	}
	
	/**
	 * 入队
	 * @param e
	 */
	public void enqueue(T e) {
		if (isFull()) {
			throw new RuntimeException("queue is full");
		}
		rear++;
		this.elementData[rear % this.elementData.length] = e;
	}
	
	/**
	 * 出队
	 */
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("queue is empty");
		}
		T value = (T) this.elementData[(front + 1) % this.elementData.length];
		this.elementData[(front + 1) % this.elementData.length] = null;
		front++;
		return value;
	}
	
	/**
	 * 返回队列的容量
	 * @return
	 */
	public int capacity() {
		return this.elementData.length -1;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (!isEmpty()) {
			for (int i = front + 1; i < rear ; i++) {
				sb.append(elementData[i % elementData.length].toString());
				sb.append(", ");
			}
			sb.append(elementData[rear % elementData.length]);
		}
		sb.append("]");
		return sb.toString();
	}
}
