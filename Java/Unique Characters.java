E
1531457659
tags: String, Array

determine if characters are unique in string

#### HashSet
- space O(n), time O(n)

#### char[]
- space O(n), time O(nlogn)

#### no additional data structure
- double for loop:  O(n^2)


```
/*
LintCode
Implement an algorithm to determine if a string has all unique characters.

Example
Given "abc", return true.

Given "aab", return false.

Challenge
What if you can not use additional data structures?

Tags Expand 
String Cracking The Coding Interview Array
*/

public class Solution {
    public boolean isUnique(String str) {
    	if (str == null || str.length() == 0) return true;

    	char[] arr = str.toCharArray(); // nlogn
		Arrays.sort(arr);
    	for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) return false;
    	}
    	return true;
    }
}

/*
	Thought:
	1st, write hasset, there you go.
*/
public class Solution {
    public boolean isUnique(String str) {
    	if (str == null || str.length() == 0) {
    		return true;
    	}
    	HashSet<Character> set = new HashSet<Character>();
    	for (int i = 0; i < str.length(); i++) {
    		if (!set.contains(str.charAt(i))) {
    			set.add(str.charAt(i));
    		} else {
    			return false;
    		}
    	}//end for

    	return true;
    }
}

/*
	Thought:
	do it without hash set.
	Can do a double-for loop, check from i~j, if str[i] exist later in the string.
	O(n^2)
*/

public class Solution {
    public boolean isUnique(String str) {
		if (str == null || str.length() == 0) {
    		return true;
    	}
    	for (int i = 0; i < str.length(); i++) {
    		for (int j = i + 1; j < str.length(); j++) {
    			if (str.charAt(i) == str.charAt(j)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}


```