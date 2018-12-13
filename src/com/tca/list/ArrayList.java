package com.tca.list;

import java.util.Iterator;

/**
 *  数组表
 * 	1.构造数组表
 * 	2.数组表中实际存储元素个数
 * 	3.判断数组表是否为空
 * 	4.判断数组表中是否包含某元素
 * 	5.判断数组表中某个元素的第一次出现的位置
 * 	6.判断数组表中某个元素的最后一次出现的位置
 * 	7.添加元素
 * 	8.删除元素
 * 	9.修改元素
 * 	10.清空数组表
 * @author zhoua
 *
 * @param <T>
 */
public class ArrayList<T>{
	
	private transient Object[] elementData;// 用于存放实际数据
	
	private int size;// 用于记录实际存放的数据量
	
	/**
	 * 无参构造器
	 */
	public ArrayList() {
		this(10); // 默认容量为10
	}
	
	/**
	 * 含参构造器
	 * @param size
	 */
	public ArrayList(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		this.elementData = new Object[size];
	}
	
	/**
	 * 添加元素(默认在最后位置添加)
	 * @param e
	 * @return
	 */
	public boolean add(T e) {
		// 判断当前数组是否已满, 如果已满, 则进行扩容(每次扩容为之前的1.5倍)
		checkAndExpansion();
		
		// 将元素添加到最后一位
		this.elementData[size++] = e;
		
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
	 * 返回列表已使用容量
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
			for (int i = 0; i < size; i++) {
				if (this.elementData[i] == null) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.elementData[i])) {
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
			for (int i = 0; i < size; i++) {
				if (this.elementData[i] == null) {
					System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
					this.elementData[--size] = null;
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.elementData[i])) {
					System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
					this.elementData[--size] = null;
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
		for (int i = 0; i < this.size; i++) {
			this.elementData[i] = null;
		}
		this.size = 0;
	}
	
	/**
	 * 根据索引获取元素
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		// 判断索引是否越界
		checkIndex(index);
		return (T) this.elementData[index];
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
	@SuppressWarnings("unchecked")
	public T set(int index, T element) {
		// 判断索引是否越界
		checkIndex(index);
		T oldValue = (T) this.elementData[index];
		this.elementData[index] = element;
		return oldValue;
	}
	
	/**
	 * 在index位置插入元素element
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) {
		// 判断索引是否越界
		if (index < 0 || index > size) {
			throw new RuntimeException("index out of bounds: size = " + size + " , index = " + index);
		}
		
		// 判断是否需要扩容
		checkAndExpansion();
		
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		this.elementData[index] = element;
		size++;
	}
	
	/**
	 * 根据索引移除元素, 返回移除的元素
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		T oldValue = get(index);
		System.arraycopy(elementData, index + 1, elementData, index, size - index -1);
		size--;
		return oldValue;
	}
	
	/**
	 * 元素第一次出现的索引
	 * @param o
	 * @return
	 */
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (this.elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(this.elementData[i])) {
					return i;
				}
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
		if (o == null) {
			for (int i = size-1; i >= 0; i--) {
				if (this.elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size-1; i >= 0; i--) {
				if (o.equals(this.elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			
			private int consor;

			@Override
			public boolean hasNext() {
				return consor != size;
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				if (consor >= size || consor >= elementData.length) {
					throw new RuntimeException("no such element");
				}
				return (T) elementData[consor++];
			}
			
		};
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (size > 0) {
			for (int i = 0; i < size - 1; i++) {
				sb.append(elementData[i].toString());
				sb.append(", ");
			}
			sb.append(elementData[size - 1]);
		}
		sb.append("]");
		return sb.toString();
	}

	
}
