package com.tca.heap;

/**
 * 最小堆 -- 用数组的方式实现
 * 	1.添加元素
 * 	2.删除最小元素
 * 	3.将一个普通数组(元素可排序)转化为最小堆
 * @author zhoua
 *
 */
public class MinHeap<T extends Comparable<T>> {
	
	private transient Object[] elementData; // 用于存放实际存储的数据
	
	private int size; // 堆中实际存储的数据量
	
	public MinHeap(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		this.elementData = new Object[size];
	}
	
	public MinHeap() {
		this(16);
	}
	
	/**
	 * 添加元素
	 * @param element
	 */
	@SuppressWarnings("unchecked")
	public void add(T element) {
		if (element == null) {
			throw new RuntimeException("the element is null");
		}
		
		// 判断当前数组是否已满, 如果已满, 则进行扩容(每次扩容为之前的1.5倍)
		checkAndExpansion();

		this.elementData[size++] = element;
		
		for (int i = size - 1, j = (i - 1) / 2; ((T)this.elementData[j]).compareTo((T) this.elementData[i]) > 0 && i > 0; i = j, j = (i - 1) / 2) {
			T temp = (T) this.elementData[i];
			this.elementData[i] = this.elementData[j]; 
			this.elementData[j] = temp; 
		}
	}
	
	/**
	 * 取出最大元素
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T remove() {
		if (isEmpty()) {
			throw new RuntimeException("the heap is empty");
		}
		
		T min = (T) this.elementData[0];
		
		if (size > 1) {
			// 将最后一个元素放到根位置，再做调整
			this.elementData[0] = this.elementData[size - 1];
			this.elementData[size - 1] = null;
			// 调整树结构
			alterRoot(0);
		}
		
		size--;
		
		return min;
	}
	
	/**
	 * 根节点i中根节点，左儿子，右儿子中最小元素的索引
	 * @param i
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int min(int i) {
		int leftson = 2 * i + 1;
		int rightson = 2 * i + 2;
		
		T root = (T) this.elementData[i];
		if (root == null) {
			throw new RuntimeException("the root element is null");
		}
		
		T leftsonEle =  (T) this.elementData[leftson];
		T rightsonEle = (T) this.elementData[rightson];
		if (leftsonEle == null) {
			return i;
		}
		
		int min = root.compareTo(leftsonEle) > 0? leftson: i;
		if (rightsonEle == null) {
			return min;
		}
		
		if (((Comparable<T>) this.elementData[min]).compareTo(rightsonEle) > 0) {
			min = rightson;
		}
		return min;
	}
	
	/**
	 * 调整根节点
	 * @param i
	 */
	@SuppressWarnings("unchecked")
	private void alterRoot(int i) {
		while (min(i) != i) {
			int minIndex = min(i);
			// 交换元素
			T temp = (T) this.elementData[i];
			this.elementData[i] = this.elementData[minIndex];
			this.elementData[minIndex] = temp;
			
			i = minIndex;
		}
		
	}
	
	/**
	 * 检验当前数组是否已满，如果已满，则进行扩容，每次扩容为之前的1.5倍+1
	 * 	+1 是为了避免原数组长度为0
	 */
	@SuppressWarnings("unchecked")
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
