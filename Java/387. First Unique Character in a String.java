E
tags: Hash Table, String
time: O(n)
space: O(256) = O(1)

#### Count appearance with int[256]
- 按照题意, 找到第一个 first index == last index的字母.

#### Count appearance with hashmap (more scalable)
- 用hashmap存字母的index, 有些重复字母的index就会是个list. 
- 找到单一index, 结合成list, sort, return list.get(0)
- slow due 

```
/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
*/
class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}

/*
    Direclty compare first occurance of a character && last occurance, see if at same spot
    // slow
 */
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}


/*
Thoughts:
1. put all letter into map <char, count>
2. If more than 1 occurs, remove it from
*/
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) == 1) return i;
        }
        return -1;
    }
}

```
