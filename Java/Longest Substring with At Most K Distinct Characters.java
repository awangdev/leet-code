大清洗。
map.size一旦超标，要把longest string最开头（marked by pointer:start）的那个char抹掉，而且要把它所有的appearance都抹掉；这样还不够，它最后一次出现以前的其他所有chars，也都要抹掉。
大清洗的原因是： 一旦某一个char要被清除，由于substring必须是连续的，所以在它之前的所有chars都要被清洗。
我去，黑帮大哥除龙头啊。
简直就是要消灭伏地魔的7个魂器。
```
/*
Given a string s, find the length of the longest substring T that contains at most k distinct characters.

Have you met this question in a real interview? Yes
Example
For example, Given s = "eceba", k = 3,

T is "eceb" which its length is 4.

Challenge
O(n), n is the size of the string s.

Tags Expand 
String Two Pointers LintCode Copyright Hash Table
*/

/*
Thoughts:
HashMap size cannot go over k.
Map<Char, #of apperance>.
Use a start pointer to track the begin of current longest substring.
If map.size() > k, then remove: count-= map.get(s.charAt(i)); map.remove(s.charAt(i));

Note:
assume we need to remove the first char of current longest string, and that char has multiple apperances later in the string
Do while on map.size>k, and remove all chars before the last appearce of that particular string.
Therefore, we need erase that particular char and all other chars before that particular char's last apperance.
*/
public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring 
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    	if (s == null || s.length() == 0 || k <= 0) {
    		return 0;
    	}
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	int count = 0;
    	int start = 0;
    	int max = 0;
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (map.containsKey(c)) {
    			map.put(c, map.get(c) + 1);
    		} else {
    			map.put(c, 1);
    			while (map.size() > k) {
	    			char rm = s.charAt(start);
	    			int tempCount = map.get(rm);
	    			if (tempCount > 1) {
	    				map.put(rm, tempCount - 1);
	    			} else {
	    				map.remove(rm);
	    			}
	    			start++;
	    			count--;
	    		}
    		}
    		count++;
    		max = Math.max(max, count);
    	}
    	return max;
    }
}

```