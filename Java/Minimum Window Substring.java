/*
Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

Example
source = "ADOBECODEBANC" target = "ABC" Minimum window is "BANC".

Note
If there is no such window in source that covers all characters in target, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

Challenge
Can you do it in time complexity O(n) ?

Clarification
Should the characters in minimum window has the same order in target?

    - Not necessary.

Tags Expand 
Hash Table

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
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
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
