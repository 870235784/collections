package com.tca.list.test;

import java.util.Iterator;

import org.junit.Test;

import com.tca.list.MyArrayList;

public class MyArrayListTest {
	@Test
	public void test01() {
		MyArrayList<String> list = new MyArrayList<>();
		//add
		list.add("A");
		list.add("B");
		list.add("C");
		System.out.println(list);
		
		//add(index)
		//list.add(4, "D");
		list.add(1, "b");
		System.out.println(list);
		
		//contains
		System.out.println(list.contains("b"));
		
		//size
		System.out.println(list.size());
		
		//isEmpty
		System.out.println(list.isEmpty());
		
		//get
		System.out.println(list.get(2));
		
		//indexOf
		System.out.println(list.indexOf("C"));
		
		//lastIndexOf
		System.out.println(list.lastIndexOf("A"));
		
		//remove
		list.remove("C");
		System.out.println(list);
		
		list.remove(0);
		System.out.println(list);
		
		//set
		list.set(1, "a");
		System.out.println(list);
		
		//foreach
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			System.out.println(element);
		}
	}
}
