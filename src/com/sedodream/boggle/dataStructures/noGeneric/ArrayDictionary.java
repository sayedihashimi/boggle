package com.sedodream.boggle.dataStructures.noGeneric;

import java.io.*;

public class ArrayDictionary {
	private String[] wordList = new String[264061];
	private WordPrefix[] prefixList = new WordPrefix[264061];
	private int[] prefixCount = new int[50];
	private String currentWord;
	private String falsePrefix = "";
	private int index = -1;
	private int prefixListLen = prefixList.length;

	public ArrayDictionary() {
		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(
					new FileReader("./files/word.list"));

			String currentWord;
			int i = 0;
			while ((currentWord = inputStream.readLine()) != null) {
				wordList[i] = currentWord;
				i++;
			}

			for (int j = 0; j < 50; j++)
				prefixCount[j] = 0;

			String first;
			String second = wordList[264060];
			prefixList[264060] = new WordPrefix(second, 0);
			int count;
			for (int k = 264059; k >= 0; k--) {
				first = wordList[k];
				count = prefixInCommon(first, second);
				//System.out.println(k + ": " + count);
				for (int j = 0; j < count; j++)
					prefixCount[j] = prefixCount[j] + 1;
				for (int j = count; j < 50; j++)
					prefixCount[j] = 0;
				/*        for(int j = 0; j < 50; j++)
				 System.out.print(prefixCount[j] + ",");
				 System.out.println();
				 System.out.println(first.length() + ": " +
				 prefixCount[first.length() - 1]);*/
				prefixList[k] = new WordPrefix(first, prefixCount[first
						.length() - 1]);

				second = first;
			}

			if (inputStream != null) {
				inputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int prefixInCommon(String first, String second) {
		int count = 0;

		for (int i = 0; i < first.length() && i < second.length(); i++) {
			if (first.charAt(i) == second.charAt(i))
				count++;
			else
				break;
		}

		return count;
	}

	public String nextWord(int result) {
		if (result == 0) {
			// found last word
			index++;
			falsePrefix = "";
			currentWord = prefixList[index].word;
		} else {
			/*
			 * not found add numSucceors + 1 is prefix loop till found prefix ==
			 * 0
			 */
			falsePrefix = currentWord.substring(0, currentWord.length()
					- result + 1);
			index += prefixList[index].numSuccessors + 1;
			if (index < prefixListLen) {
				currentWord = prefixList[index].word;
				while (isPrefix(falsePrefix, currentWord)) {
					if ((++index) < prefixListLen) {
						currentWord = prefixList[index].word;
					} else
						return null;
				}
			} else {
				return null;
			}
		}
		return currentWord;
	}
	
	private boolean isPrefix(String falsePrefix, String word) {
		int falsePrefixLen; 
		if((falsePrefixLen = falsePrefix.length()) > word.length()){
			return false;
		}
		int i;
		for(i = 0;i < falsePrefixLen && falsePrefix.charAt(i) == word.charAt(i);i++);
//		System.out.println(falsePrefix+ " is prefix of "+word+ ": "+(i==falsePrefixLen));
		return i == falsePrefixLen;
	}
	
	private class WordPrefix {
		public String word;

		public int numSuccessors;

		public int position;

		public WordPrefix(String word, int numSuccessors) {
			this.word = word;
			this.numSuccessors = numSuccessors;
		}

		public WordPrefix(String word, int numSuccessors, int position) {
			this.word = word;
			this.numSuccessors = numSuccessors;
			this.position = position;
		}
	}
	public static void main(String args[] ) {
		String s1,s2;
		s1 = "hammer";
		s2 = "hammerhead";
		long t1,t2;
		boolean result;
		t1 = System.nanoTime();
		result = testIsPrefix(s1,s2);
		t2 = System.nanoTime();
		System.out.println(s1+" is Prefix "+s2+" :"+result+" in time: "+((t2-t1)/100)+"us\n\n");
		t1 = System.nanoTime();
		result = testIsPrefix(s2,s1);
		t2 = System.nanoTime();
		System.out.println(s2+" is Prefix "+s1+" :"+result+" in time: "+((t2-t1)/100)+"us\n\n");
		t1 = System.nanoTime();
		result = testIsPrefix(s1,s1);
		t2 = System.nanoTime();
		System.out.println(s1+" is Prefix "+s1+" :"+result+" in time: "+((t2-t1)/100)+"us\n\n");
		
	}
	
	public static boolean testIsPrefix(String falsePrefix, String word) {
		int wordLen, falsePrefixLen, minLen;
		if((falsePrefixLen = falsePrefix.length()) > (wordLen = word.length())){
			return false;
		}
		int i;
		for(i = 0; i < falsePrefixLen && falsePrefix.charAt(i) == word.charAt(i);i++);
//			System.out.println("i= "+i+" minLen= "+minLen+" falsePrefix.charAt(i)= "+falsePrefix.charAt(i)+" word.charAt(i)= "+word.charAt(i)+ " falsePrefix= "+falsePrefix+ " word= "+word);
//		System.out.println("(i-1) <= wordLen: ("+i+"-1) <= "+wordLen+" : "+((i-1)<=wordLen));
		return (i) <= wordLen;
	}
	

}