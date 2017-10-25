不用额外data structure, O(n^2), double for loop.
用hashSet, space O(n), time O(n)
```
/*
Implement an algorithm to determine if a string has all unique characters.

Example
Given "abc", return true.

Given "aab", return false.

Challenge
What if you can not use additional data structures?

Tags Expand 
String Cracking The Coding Interview Array
*/

/*
	Thought:
	do it without hash set.
	Can do a double-for loop, check from i~j, if str[i] exist later in the string.
	O(n^2)
*/

public class Solution {
    /**
     * @param str: a string
     * @return: a boolean
     */
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



/*
	Thought:
	1st, write hasset, there you go.
*/
public class Solution {
    /**
     * @param str: a string
     * @return: a boolean
     */
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

```