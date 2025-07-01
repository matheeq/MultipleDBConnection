package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value="/home", method= RequestMethod.GET)
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping(value="/lengthOfLongestSubstring", method= RequestMethod.GET)
	public void test() {
		try {
			System.out.println("Hello World");
			String s = "abcabcbb";
			int count = 0;
			char[] str = s.toCharArray();
			List<Integer> resList=new ArrayList<>();
			List<Character> tempList=new ArrayList<>();
			for(int i=0;i<s.length();i++) {
				if(!tempList.contains(str[i])) {
					tempList.add(str[i]);
				}else {
					resList.add(tempList.size());
					tempList.clear();
					if(count<s.length()) {
						i=count;
						count++;
					}
				}
			}
			Collections.sort(resList, Comparator.reverseOrder());
			Integer finalOutput = resList.get(0);
			System.out.println();
			
			///
			 Set<Character> set = new HashSet();
		        int max = 0;
		        int left = 0;
		        for (int right = 0; right < s.length(); right++) {
		            while(!set.add(s.charAt(right))) {
		                set.remove(s.charAt(left++));
		            }
		            max = Math.max(max, right - left + 1);
		        }
		        System.out.println(max);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/findMedianSortedArrays", method= RequestMethod.GET)
	 public double findMedianSortedArrays() {
		int nums1[] = {1,3};
		int nums2[] = {2,4};
			List<Integer> result=new ArrayList<>();
			for(int i=0;i<nums1.length;i++) {
				result.add(nums1[i]);
			}
			for(int i=0;i<nums2.length;i++) {
				result.add(nums2[i]);
			}
			Collections.sort(result);
			int temp = result.size()/2;
			if(result.size()%2==0) {
				Integer a = result.get(temp) +result.get(temp-1);
				return a/2;
			}else {
				return result.get(temp+1)/2;
			}
//			return 0;
	    }
	
	@RequestMapping(value="/lengthOfLastWord", method= RequestMethod.GET)
	public void lengthOfLastWord() {
		System.out.println("lengthOfLastWord");
		String str="Hello World";
		String[] temp = str.split(" ");
		if(null!=temp && temp.length>0) {
		String temp1 = temp[temp.length-1];
		int len = temp1.length();
		System.out.println("lengthOfLastWord");
		
		}
		System.out.println("lengthOfLastWord");
	}
}
