package com.tca.heap;

/**
 * 最大堆 -- 用数组的方式实现
 * 	1.添加元素
 * 	2.删除最大元素
 * 	3.将一个普通数组(元素可排序)转化为最大堆
 * @author zhoua
 *
 */
public class MaxHeap<T extends Comparable<T>> {
	
	private transient Object[] elementData; // 用于存放实际存储的数据
	
	private int size; // 堆中实际存储的数据量
	
	@SuppressWarnings("unchecked")
	public MaxHeap(int size) {
		if (size < 0) {
			throw new RuntimeException("size: " + size + " is illegal");
		}
		this.elementData = new Object[size];
	}
	
	public MaxHeap() {
		this(16);
	}
	
	/**
	 * 添加元素
	 * @param element
	 */
	public void add(T element) {
		if (element == null) {
			throw new RuntimeException("the element is null");
		}
		
		// 判断当前数组是否已满, 如果已满, 则进行扩容(每次扩容为之前的1.5倍)
		checkAndExpansion();

		this.elementData[size++] = element;
		
		for (int i = size - 1, j = (i - 1) / 2; ((T)this.elementData[j]).compareTo((T) this.elementData[i]) < 0 && i > 0; i = j, j = (i - 1) / 2) {
			T temp = (T) this.elementData[i];
			this.elementData[i] = this.elementData[j]; 
			this.elementData[j] = temp; 
		}
		
	}
	
	/**
	 * 取出最大元素
	 * @return
	 */
	public T remove() {
		if (isEmpty()) {
			throw new RuntimeException("the heap is empty");
		}
		
		T max = (T) this.elementData[0];
		
		if (size > 1) {
			// 将最后一个元素放到根位置，再做调整
			this.elementData[0] = this.elementData[size - 1];
			this.elementData[size - 1] = null;
			
			int i = 0;
			alterRoot(0);
		}
		
		size--;
		
		return max;
	}
	
	/**
	 * 根节点i中根节点，左儿子，右儿子中最大元素的索引
	 * @param i
	 * @return
	 */
	private int max(int i) {
		int leftson = 2 * i + 1;
		int rightson = 2 * i + 2;
		
		T root = (T) this.elementData[i];
		T leftsonEle =  (T) this.elementData[leftson];
		T rightsonEle = (T) this.elementData[rightson];
		if (leftsonEle == null) {
			return i;
		}
		
		int max = root.compareTo(leftsonEle) < 0? leftson: i;
		if (rightsonEle == null) {
			return max;
		}
		
		if (((Comparable<T>) this.elementData[max]).compareTo(rightsonEle) < 0) {
			max = rightson;
		}
		return max;
	}
	
	/**
	 * 调整根节点
	 * @param i
	 */
	private void alterRoot(int i) {
		int maxIndex = max(i);
		if (maxIndex == i) {
			return;
		}
		T temp = (T) this.elementData[i];
		this.elementData[i] = this.elementData[maxIndex];
		this.elementData[maxIndex] = temp;
		
		alterRoot(maxIndex);
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
			this.elementData = (T[]) newElementData;
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
