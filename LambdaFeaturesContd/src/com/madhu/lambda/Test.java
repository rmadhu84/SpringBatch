package com.madhu.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		
		String out = list.stream().collect(Collectors.joining(","));
		System.out.println(out);
		System.out.println(String.join(",", list));
		
		StringJoiner result = new StringJoiner(",") ;
		System.out.println(result == null);
		System.out.println(result.toString().isEmpty());
		System.out.println(result);

	}

}
