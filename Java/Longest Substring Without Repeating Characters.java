M


方法2:用两个pointer, head和i.    
   HashMap<Char, Integer>: <character, last occurance index>
   head从index 0 开始。若没有重复char, 每次只有for loop的i++。每次取substring[head,i]作为最新的string.
   一旦有重复，那么意味着，从重复的老的那个index要往后加一格开始。所以head = map.get(i) +１.


注意：head很可能被退回到很早的地方，比如abbbbbba,当遇到第二个a，head竟然变成了 head = 0+1 = 1.      
当然这是不对的，所以head要确保一直增长，不回溯。


方法1：只要有non-existing char就count++. 一旦有重复char:   
   i = 新出现重复Char的位置.
   重新init HashMap, count.

这个方法每次都把map打碎重来, 是可以的，也没什么不好。就是在for里面改i，自己觉得不太顺.方法二可能顺一点。

   

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