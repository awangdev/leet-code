
```
/*
Given a string, find the length of the longest substring without repeating characters.

Example
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

For "bbbbb" the longest substring is "b", with the length of 1.

Challenge
O(n) time

Tags Expand 
String Two Pointers Hash Table
*/

/*
Attempt1: What are you 弄啥唻？我第一个attempt每次遇到duplicate都打碎重来，我期待着这个时间pass不了啊..虽然硬跑的逻辑是说得通的。

Thoughts:
Loop the string, HashSet to store the Character, count++, and use a max to store longest length.
Whenever a char exist in hashset, new hashset and count = 0, set i = 1st duplicate char, followed by i++, now start again.
*/

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
    public int lengthOfLongestSubstring(String s) {
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	int count = 0;
    	int max = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (!map.containsKey(s.charAt(i))) {
    			map.put(s.charAt(i), i);
    			count++;
    		} else {
    			i = map.get(s.charAt(i));
    			map = new HashMap<Character, Integer>();
    			count = 0;
    		}
    		max = Math.max(max, count);
    	}//end for

    	return max;
    }
}



```