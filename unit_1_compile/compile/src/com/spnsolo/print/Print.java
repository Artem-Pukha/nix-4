package com.spnsolo.print;

import org.apache.commons.lang3.*;

import com.spnsolo.similarity.Similarity;

public class Print {
	public void hello() {
		
		System.out.println("Ohh, hello!");
        System.out.println(StringUtils.upperCase("Hello World!"));
        
		Similarity sim = new Similarity();
		int d = sim.difference("New York", "New Hampshire");
		System.out.println("Hello, " + d + " times");
		 
	}

}
