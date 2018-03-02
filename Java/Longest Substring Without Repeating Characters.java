M
1519971156
tags: Hash Table, Two Pointers, String

方法1:
Two Pointers
双指针: 从start开始遍历, 但是第一步是while loop来推进end; 直到推不动end, 然后start++
巧妙点: 因为end是外围variable, 在start的loop上, end不会重置;[star ~ end] 中间不需要重复计算.
最终可以O(n);

Previous verison of two pointers:
用两个pointer, head和i.
注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯

方法2:
   HashMap<Char, Integer>: <character, last occurance index>
   一旦有重复, rest map.
   没有重复时候, 不断map.put(), 然后求max值

问题: 每次reset map之后就开始从新从一个最早的index计算, 最坏情况是O(n^2):
'abcdef....xyza'


```
/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


 */
/*
Thoughts:
two pointers
Find the end fisrt, then move the start.
O(n)
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[] chars = new boolean[256]; // 256 ASCII code
        int rst = 0;
        int start = 0;
        int end = 0;
        while (start < s.length()) {
            while (end < s.length() && !chars[s.charAt(end)]) {
                chars[s.charAt(end)] = true;
                rst = Math.max(rst, end - start + 1);
                end++;
            }
            chars[s.charAt(start)] = false;
            start++;
        }
        return rst;
    }
}

/*
Thoughts:
1. Use hashmap<c, index> to mark indexes of chars.
2. When no duplicate, put in map, and compare Math.max(rst, map.size())
3. If duplicated c appears, should ignore all index before the fist c, and start fresh:
    reset i = map.get(c); map = new HashMap<>(), clean up the hash.

Time:
O(n^2): when 'abcdefg.....xyza' happends
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        int rst = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (map.containsKey(c)) {
                i = map.get(c);
                map = new HashMap<>();
            } else {
                map.put(c, i);
            }
            rst = Math.max(rst, map.size());
        }
        return rst == Integer.MIN_VALUE ? 0 : rst;
    }
}



/*
LintCode
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
    02.02.2016
    Attempt2, Thoughts:
    HashMap<Char,Integer> map
    When char re-appear in map, 1. move head to repeating char's index + 1, 2. renew map with current index

    Note: head could repeat in earlier index, so make sure head does not travel back
*/

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int head = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) + 1 > head) {//make sure head does not travel back
                    head = map.get(c) + 1;
                }
            }
            map.put(c, i);
            String str = s.substring(head, i + 1);
            max = Math.max(max, str.length());
        }
        return max;
    }
}

/*
Attempt1, Thoughts:
Loop the string, HashSet to store the Character, count++, and use a max to store longest length.
Whenever a char exist in hashset, new hashset and count = 0, set i = 1st duplicate char, followed by i++, now start again.
*/

public class Solution {
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