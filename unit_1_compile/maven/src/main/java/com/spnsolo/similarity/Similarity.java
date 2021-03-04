package com.spnsolo.similarity;

import org.apache.commons.text.similarity.LongestCommonSubsequence;

public class Similarity {

	public int difference(String s1, String s2) {
		
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(); 
		int countLcs = lcs.apply(s1, s2); 
		return countLcs;
	}
}
