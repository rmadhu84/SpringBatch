/**
 * 
 */
package com.madhu.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Madhu
 *
 */
public class Unit1Exercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Person> list = Arrays.asList(new Person("Charles", "Dickens", 60), new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyl", 51), new Person("Charollotte", "Brants", 45),
				new Person("Matthew", "Arnold", 39));

		

		// Java 7 way
//		list.sort(new Comparator<Person>() {
//
//			@Override
//			public int compare(Person o1, Person o2) {
//				// TODO Auto-generated method stub
//				return o1.getLastName().compareTo(o2.getLastName());
//			}
//		});

		
		
		
		list.sort((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
		// printList.print(list);

//		list.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
		// printList.print(list);
		
		
		System.out.println("Printing Person with Last Name starting with C");
		printLambda(list, p -> p.getLastName().startsWith("C"));
		
		System.out.println("Printing Person with First Name starting with C");
		printLambda(list, p -> p.getFirstName().startsWith("C"));
		
		System.out.println("Printing all persons");
		printLambda(list, p->true);
	}
	public static void printLambda(List<Person> list, Condition condition) {
		
		list.forEach(p->{
			if(condition.test(p))
				System.out.println(p.toString());
		});

	}

}
