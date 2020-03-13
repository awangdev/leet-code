M
tags: Hash Table, Two Pointers, String, Sliding Window
time: O(n)
space: O(1)

如题.

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == 2, process and record max len
    - 3) if map.size() > 2, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(1)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(n) 
- space: O(1)

```
/*
Given a string s , find the length of the longest substring t that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

*/

/*
Method1: Slinding window
- Typical slinding window: the goal is to keep a distinct char size/window of size 2:
- use a map<char, count> to track; map.size() is the window size
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == 2, process and record max len
    - 3) if map.size() > 2, maintain window size: drop curr left char, update map
- return max
*/
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null)  return 0;
        int left = 0, right = 0, max = 0, n = s.length();
        Map<Character, Integer> freq = new HashMap<>();

        while (right < n) {
            // 1) expand right
            char head = s.charAt(right++);
            freq.put(head, freq.getOrDefault(head, 0) + 1);
            
            // 2) process when window is reached
            if (freq.size() <= 2) max = Math.max(max, right - left);
            
            // 3) contract left
            if (freq.size() > 2) {
                char tail = s.charAt(left++);
                freq.put(tail, freq.get(tail) - 1);
                if (freq.get(tail) == 0) freq.remove(tail);
            }
        }
        
        return max;
    }
}


/*
#### Method2: sliding window but truncate whole block  
Map<Character, Integer> to map last occurrance of certain character.
if map.size() > 2, we'll remove trop off left-most char
maintain max based on curr right - left
*/
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        Map<Character, Integer> lastOccurMap = new HashMap<>();
        int left = 0, right = 0, max = 0;

        while (right < n) {
            if (lastOccurMap.size() <= 2) { // add new char
                lastOccurMap.put(s.charAt(right), right++);
            }
            if (lastOccurMap.size() > 2) { // clean up left-most char
                int leftMost = right;
                for (int index : lastOccurMap.values()) {
                    leftMost = Math.min(leftMost, index);
                }
                lastOccurMap.remove(s.charAt(leftMost));
                left = leftMost + 1;
            }
            max = Math.max(max, right - left);
        }

        return max;
    }
}
```