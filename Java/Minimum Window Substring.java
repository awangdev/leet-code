H
1519980648
tags: Hash Table, Two Pointers, String

基本思想: 用个char[]存string的frequency. 然后2pointer, end走到底, 不断validate.
符合的就process as result candidate.

HashMap的做法比char[]写起来要复杂一点, 但是更generic

```
/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.


Challenge
Can you do it in time complexity O(n) ?

Clarification
Should the characters in minimum window has the same order in target?

    - Not necessary.

Tags Expand 
Hash Table
*/

/*
Thoughts:
Use 2 pointer to track window.
Use set to track t's characters: charSet
end pionter: subtracting c from the charSet.
start pointer: if start++ skipping a candidate, we add it back to the charSet.
*/

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int end = 0;
        int length = Integer.MAX_VALUE;
        String rst = "";

        // Initialize source map for validation usage
        int[] source = new int[256];
        int[] target = new int[256];
        for (char c : t.toCharArray()) {
            target[c]++;
        }

        for (int i = 0; i < s.length(); i++){
            while (end < s.length() && !valid(source, target)) {
                source[s.charAt(end)]++;
                end++;
            }
            if (valid(source, target)) {
                if (end - i < length) {
                    length = Math.min(length, end - i);
                    rst = s.substring(i, end);
                }
            }
            source[s.charAt(i)]--;
        }
        return rst;
    }
    
    /*
        Validate if the count of source map matches targetMap.
        Use target as base, since it's substring.
    */
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < 256; i++) {
            if (source[i] < target[i]) {
                return false;
            }
        }
        return true;
    }
}

//Previous notes
/*
03.09.2016
http://blog.sina.com.cn/s/blog_eb52001d0102v2il.html
http://www.rudy-yuan.net/archives/185/

只利用一个hashmap save frequency of target.
1. 从map里面 减去 source char match target char 的frequency. 如果发现frequency = 0, size++
    size == map.size,说明有一个candidate.
2. 维持left,right pointer。
记住一个规则: 如果map里面在left index上面的frequency >=0, 也就是frequency 没有小于0，所以暂时没有多余的这个char on left index.    
因此left不能动。这里要测试并且break。
然而，当frequency < 0时候，说明在后续的String里面出现了多余的char,也就是frequency--使其小于0. 这里，我们就可以动left++了，并且frequency++。
这个效果就等于，当matched char在后面有重复出现（多余）时，我们可以left++，安全移动。

另外. 其余那些不在target里面的char, 用left++过滤掉。

3. right pointer一直在按规律跑动。 

4. 当size == map.size(), 截取string

*/
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        //Init map based on t
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        
        int count = 0;
        int start = 0;
        int end = 0;
        int lengS = s.length();
        int leng = Integer.MAX_VALUE;
        String rst = "";
        //Iteratve through s
        for (; end < lengS; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count++;
                }
            }
            
            while (start < lengS) {
                char cs = s.charAt(start);
                if (map.containsKey(cs)) {
                    //If >= 0 still being used in map, not ready yet: can't move
                    if (map.get(cs) >= 0) {
                        break;
                    } else {//less than 0, so we can skip previous cs, move start++
                        map.put(cs, map.get(cs) + 1);
                    }
                }
                //skip non-t chars
                start++;
            }
            
            if (count == map.size() && (end - start + 1) < leng) {
                rst = s.substring(start, end + 1);
                leng = rst.length();
            }
        }//end for
        
        return rst;
    }
}



/*
Same as below solution, incorrect: only picks the first possible solution, not minimum window.
HOWEVER, it accidentally passed LintCode.

只能找到第一个, 所以有错。

//HashT<char, frequency>
//HashS<char, frequency>: count the frequency of chars from source. 
//once found one candidate, we may have a large window. Now move left-most char by comparison using HashS, to minimize the window

*/
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        
        HashMap<Character, Integer> hashT = new HashMap<Character, Integer>();
        HashMap<Character, Integer> hashS = new HashMap<Character, Integer>();
        //init hashT
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!hashT.containsKey(c)) {
                hashT.put(c, 0);
            }
            hashT.put(c, hashT.get(c) + 1);
        }
        
        //Check against S
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!hashT.containsKey(c)) {
                continue;
            }
            if (!hashS.containsKey(c)) {
                hashS.put(c, 0);
            }
            hashS.put(c, hashS.get(c) + 1);
            
            if (hashS.get(c) <= hashT.get(c)) {
                count++;
            }
            
            //Found string
            if (count == t.length()) {
                return findStr(i, s, hashT, hashS);
            }
        }
        return "";
    }
    
    public String findStr(int end, String s, 
            HashMap<Character, Integer> hashT, HashMap<Character, Integer> hashS) {
        int start = 0;
        while (start < s.length()) {
            char c = s.charAt(start);
            if (!hashS.containsKey(c)) {
                start++;
                continue;
            }
            if (hashS.get(c) > hashT.get(c)) {
                hashS.put(c, hashS.get(c) - 1);
                start++;
                continue;
            }
            break;
        }//end while
        
        return s.substring(start, end + 1);
    }
}




/*
Not working: it only picks up the first possible solution, but not the shortest
Thoughts:
The idea was from jiuzhang.com.
1. count target Characters: store each Character with HashMap:tCounter<Character, # appearance>
2. Test against the source string. Here create another HashMap to keep records of the window:minWindowCounter
3. For any char appears in both target and source, count++ in the minWindowCounter.
4. As long as the number of a specific Character source.charAt(i) in minWindowCounter is less than that in tCounter,
	use a count++ to keep the record.
5. Once count == target.length(), that means there is a candidate macthcing. Now we get into next level and look for the
minimum window. Note, this condition only meets once, and after found the solution, we can return the result, minWindow.
Note, at this point, we confirm target exist in source. Now we just test against target and find minimum window.
6. Now use a leftBound = 0, and loop through the source, as long as leftBound<s.length(): A. If tCounter HashMap doesn't have current Character, leftBound++ and continue.
 B. If minWindowCounter.get(c) is greater than tCounter.get(c), that means there is at least one other c in right side of the 
 srouce string and can also be used as head of the result string (with shorter length), in this case leftBount++, countinue.
7. After the abobe 6.A, 6.B check, break out of the loop and cut out a minWindow and return.
*/
import java.util.*;

public class Solution {

    public String minWindow(String source, String target) {
    	if (source == null || source.length() == 0) {
    		return source;
    	}
    	if (target == null || target.length() == 0) {
    		return "";
    	}
    	//Count Characters in target
    	HashMap<Character, Integer> tCounter = new HashMap<Character, Integer>();
    	for (int i = 0; i < target.length(); i++) {
    		Character c = target.charAt(i);
    		if (!tCounter.containsKey(c)) {
    			tCounter.put(c, 1);
    		} else {
    			tCounter.put(c, tCounter.get(c) + 1);
    		}
    	}

		HashMap<Character, Integer> minWindowCounter = new HashMap<Character, Integer>();
		int count = 0;
		String rst = "";
		for (int i = 0; i < source.length(); i++) {
			Character c = source.charAt(i);
			if (!tCounter.containsKey(c)) {
				continue;
			}

			if (minWindowCounter.containsKey(c)) {
				minWindowCounter.put(c, minWindowCounter.get(c) + 1);
			} else {
				minWindowCounter.put(c, 1);
			}
					
			if (minWindowCounter.get(c) <= tCounter.get(c)) {
				count++;
			}

			//Once the target exists in soruce: count  = target.length(), find the result
			if (count == target.length()) {
				int leftBound = 0;
				while (leftBound < source.length()) {
					Character cs = source.charAt(leftBound);
					if (!minWindowCounter.containsKey(cs)) {//Not part of window
						leftBound++;
						continue;
					}
					if (minWindowCounter.get(cs) > tCounter.get(cs)) {//Can find shorter window
						minWindowCounter.put(cs, minWindowCounter.get(cs) - 1);
						leftBound++;
						continue;
					}
					break;
				}
				rst = source.substring(leftBound, i + 1);
				return rst;
			}
		}
		return rst;
    }

    public static void main(String[] args) {
    	Solution test = new Solution();
    	String rst = test.minWindow("abcd", "ac");
    	System.out.println("resutl is : " + rst);
    }
}


```