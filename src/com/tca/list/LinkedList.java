package com.tca.list;

import java.util.Iterator;

/**
 *  双向链表
 * 	1.构造双向链表
 * 	2.双向链表中实际存储元素个数
 * 	3.判断双向链表是否为空
 * 	4.判断双向链表中是否包含某元素
 * 	5.判断双向链表中某个元素的第一次出现的位置
 * 	6.判断双向链表中某个元素的最后一次出现的位置
 * 	7.添加元素
 * 	8.删除元素
 * 	9.修改元素
 * 	10.清空双向链表表
 * @author zhoua
 *
 * @param <T>
 */
public class LinkedList<T> {
	
	// 存储头节点
	private Node<T> first;
	
	// 存储尾节点
	private Node<T> last;
	
	// 节点数
	private int size;
	
	// 构造器
	public LinkedList () {
		first = last = null;
	}
	
	// 节点类
	private static class Node<T> {
		private Node<T> pre;
		T value;
		private Node<T> next;
		
		Node(T value) {
			this.value = value;
		}
		
		Node(Node<T> pre,T value, Node<T> next) {
			this.pre = pre;
			this.value = value;
			this.next = next;
		}
		
		/**
		 * 释放当前节点
		 */
		void free() {
			this.pre = this.next = null;
			this.value = null;
		}
	}
	
	/**
	 * 在链表的尾节点处添加新的节点
	 * @param e
	 * @return
	 */
	public boolean add(T e) {
		// 判断当前链表是否为空
		if (isEmpty()) {
			first = last = new Node<T>(e);
			size ++;
			return true;
		}
		
		// 新建一个节点(该节点的前一个节点指向last节点)
		Node<T> node = new Node<T>(last, e, null);
		
		// 将节点放在最后位置
		last.next = node;
		last = node;
		size ++;
		return true;
	}
	
	
	/**
	 * 返回链表节点数
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 判断列表是否为空(已使用容量为0)
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 判断集合中是否包含元素o
	 * 	1. o为null时, 直接判断
	 * 	2. o不为null时, 根据equals方法判断
	 */
	public boolean contains(Object o) {
		if (o == null) {
			for (Node<T> node = first; node != null; node = node.next) {
				if (node.value == null) {
					return true;
				}
			}
		} else {
			for (Node<T> node = first; node != null; node = node.next) {
				if (o.equals(node.value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 删除某个元素
	 * 	true: 删除成功
	 * 	false: 删除失败
	 */
	public boolean remove(Object o) {
		if (o == null) {
			for (Node<T> node = first; node != null; node = node.next) {
				if (node.value == null) {
					// 判断是否是头节点
					if (node == first) {// 是:直接让first指向该节点的next节点
						first = node.next;
					} else { // 否:让该节点的前一个节点的next指向该节点的next节点
						node.pre.next = node.next;
					}
					// 判断是否是尾节点
					if (node == last) { // 是:直接让last指向该节点的pre节点
						last = node.pre;
					} else { // 否:让该节点的下一个节点的pre指向该节点的pre节点
						node.next.pre = node.pre;
					}
					// 释放该节点
					node.free();
					size--;
					return true;
				}
			}
		} else {
			for (Node<T> node = first; node != null; node = node.next) {
				if (o.equals(node.value)) {
					// 判断是否是头节点
					if (node == first) {// 是:直接让first指向该节点的next节点
						first = node.next;
					} else { // 否:让该节点的前一个节点的next指向该节点的next节点
						node.pre.next = node.next;
					}
					// 判断是否是尾节点
					if (node == last) { // 是:直接让last指向该节点的pre节点
						last = node.pre;
					} else { // 否:让该节点的下一个节点的pre指向该节点的pre节点
						node.next.pre = node.pre;
					}
					// 释放该节点
					node.free();
					size--;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 清空实际存储数据
	 */
	public void clear() {
		for (Node<T> node = first; node != null; node = node.next) {
			node.free();
		}
		first = last = null;
		this.size = 0;
	}
	
	/**
	 * 根据索引获取元素
	 * @param index
	 * @return
	 */
	public T get(int index) {
		checkIndex(index);
		boolean flag = index < size/2;
		if (flag) {
			Node<T> node = first;
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
			return node.value;
		} 
		Node<T> node = last;
		for (int i = size - 1; i > index; i--) {
			node = node.pre;
		}
		return node.value;
	}
	
	/**
	 * 判断索引是否越界
	 * @param index
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("index out of bounds: size = " + size + " , index = " + index);
		}
	}
	
	/**
	 * 修改某个索引位置的值为element, 并返回原来的值
	 * @param index
	 * @param element
	 * @return
	 */
	public T set(int index, T element) {
		// 判断索引是否越界
		checkIndex(index);
		
		// 查找到index位置的Node节点
		Node<T> node;
		boolean flag = index < size/2;
		if (flag) {
			node = first;
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.pre;
			}
		}
		
		// 更新value并返回原value
		T oldValue = node.value;
		node.value = element;
		return oldValue;
	}
	
	/**
	 * 在index位置插入元素element
	 * @param index
	 * @param element
	 */
	@SuppressWarnings("unused")
	public void add(int index, T element) {
		// 判断索引是否越界
		if (index < 0 || index > size) {
			throw new RuntimeException("index out of bounds: size = " + size + " , index = " + index);
		}
		
		// 判断当前链表是否为空
		if (isEmpty()) {
			first = last = new Node<T>(element);
			size ++;
			return;
		}
		
		// 查找到对应位置的元素
		boolean flag = index < size/2;
		Node<T> node;
		if (flag) {
			node = first;
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.pre;
			}
		}
		
		// 在该元素的前位置插入元素
		Node<T> newNode = new Node<T>(node.pre, element, node);
		if (node.pre == null) {// 如果插入头位置
			first = newNode;
		} else {
			node.pre.next = newNode;
		}
		
		if (node == null) {// 插入的是尾部位置
			last = newNode;
		} else {
			node.pre = newNode;
		}
		size++;
	}
	
	/**
	 * 根据索引移除元素, 返回移除的元素
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		// 判断索引是否越界
		checkIndex(index);
		
		// 如果只有一个元素
		if (size == 1) {
			first.free();
			last.free();
			size--;
		}
		
		// 查找到index位置的Node节点
		Node<T> node;
		boolean flag = index < size/2;
		if (flag) {
			node = first;
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.pre;
			}
		}
		
		T oldValue = node.value;
		
		// 移除元素
		if (node.pre == null) {// 如果移除的是first
			first = node.next;
		} else {
			node.pre.next = node.next;
		}
		
		if (node.next == null) {// 如果移除的是last
			last = last.pre;
		} else {
			node.next.pre = node.pre;
		}
		node.free();
		size--;

		return oldValue;
	}
	
	/**
	 * 元素第一次出现的索引
	 * @param o
	 * @return
	 */
	public int indexOf(Object o) {
		Node<T> node = first;
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (node.value == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(node.value)) {
					return i;
				}
				node = node.next;
			}
		}
		return -1;
	}
	
	/**
	 * 元素最后一次出现的位置
	 * @param o
	 * @return
	 */
	public int lastIndexOf(Object o) {
		Node<T> node = last;
		if (o == null) {
			for (int i = size-1; i >= 0; i--) {
				if (node.value == null) {
					return i;
				}
				node = node.pre;
			}
		} else {
			for (int i = size-1; i >= 0; i--) {
				if (o.equals(node.value)) {
					return i;
				}
				node = node.pre;
			}
		}
		return -1;
	}
	
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private Node<T> consor = first;

			@Override
			public boolean hasNext() {
				return consor != null;
			}

			@Override
			public T next() {
				if (consor != last && consor.next == null) {
					throw new RuntimeException("no such element");
				}
				T value = consor.value;
				consor = consor.next;
				return value;
			}
			
		};
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node<T> node = first;
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
