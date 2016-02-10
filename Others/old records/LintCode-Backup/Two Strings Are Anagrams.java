E

方法1:char ascii 用count[256]   
坑：不要想象这个是个26letter lowercase. may not be true.

方法2: 若是其他字符encoding, 而不只是utf16-encoding (java char)?   
那么就继续用string去做

```
/*
Write a method anagram(s,t) to decide if two strings are anagrams or not.

Example
Given s="abcd", t="dcab", return true.

Challenge
O(n) time, O(1) extra space

Tags Expand 
String Cracking The Coding Interview

*/
/*
    Recap 12.09.2015
    O(n) time: cannot Arrays.sort()
    O(1) extra space: cannot convert to char array. But we can use a count[256] to count the occuranceo of letters.

    do 1 for loop:
        + occurance index
        - occurance index

    border cases:
    null: false.
    both length() == 0, true
    length（） not equal: false
    
    don't assume all lower case.
    there are ' ' space, so it can't be just 26 letters. make it 256
*/
public class Solution {
    public boolean anagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        } else if (s.length() != t.length()) {
            return false;
        } else if (s.length() == 0 && t.length() == 0) {
            return true;
        }
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            count[t.charAt(i)]--;
        }
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
};


/*
What if it's not just ascii code, maybe uni-code? 
Then the character (utf16-encoding) may not be enough. So we use String here.
*/

//check length. compare 
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            String ss = s.substring(i, i + 1);
            String tt = t.substring(i, i + 1);
            if (!map.containsKey(ss)) {
                map.put(ss, 0);
            } 
            map.put(ss, map.get(ss) + 1);
            if (!map.containsKey(tt)) {
                map.put(tt, 0);
            }
            map.put(tt, map.get(tt) - 1);
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }
        
        return true;
    }
}


```