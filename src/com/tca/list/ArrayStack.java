package com.tca.list;

/**
 * 数组栈 -- 无限栈
 * 	1.构建数组栈
 * 	2.判断数组栈是否为空
 * 	3.入栈
 * 	4.出栈
 * 	5.返回栈顶元素
 * @author zhoua
 *
 * @param <T>
 */
public class ArrayStack<T> {
	
	private transient Object[] elementData; // 实际存储元素的数组(可扩容)
	
	private int size; // 实际元素个数
	
	private T top; // 栈顶元素
	
	/**
	 * 默认无参构造器
	 */
	public ArrayStack() {
		this(10);
	}
	
	/**
	 * 含参构造器
	 * @param size
	 */
	public ArrayStack(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		elementData = new Object[size];
	}
	
	/**
	 * 判断栈是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 栈的实际使用大小
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断栈是否已满(因为为可扩容数组，所以不会满)
	 * @return
	 */
	public boolean isFull() {
		return false;
	}
	
	/**
	 * 将元素压入栈顶
	 * @param element
	 * @return
	 */
	public boolean push(T e) {
		// 判断当前数组是否已满, 如果已满, 则进行扩容(每次扩容为之前的1.5倍)
		checkAndExpansion();
		
		// 将元素添加到最后一位
		this.elementData[size++] = e;
		
		// 将top指向栈顶元素
		this.top = e;
		
		return true;
	}
	
	/**
	 * 检验当前数组是否已满，如果已满，则进行扩容，每次扩容为之前的1.5倍+1
	 * 	+1 是为了避免原数组长度为0
	 */
	private void checkAndExpansion() {
		int length = elementData.length; // 表示数组容量已满
		if (length == size) {
			Object[] newElementData = new Object[length + length >> 1 + 1];
			System.arraycopy(elementData, 0, newElementData, 0, length);
			this.elementData = newElementData;
		}
	}
	
	/**
	 * 获取栈顶元素, 但不会弹栈
	 * @return
	 */
	public T top() {
		return top;
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
		T value = top;
		this.elementData[--size] = null; // 消除过期的对象，便于垃圾回收
		top = size == 0? null: (T) this.elementData[size - 1];
		return value;
	}
	
	/**
	 * 第一个位置为栈顶
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (size > 0) {
			for (int i = size - 1; i > 0; i--) {
				sb.append(elementData[i].toString());
				sb.append(", ");
			}
			sb.append(elementData[0]);
		}
		sb.append("]");
		return sb.toString();
	}
}
