H
tags: Hash Table, String, Sliding Window, Two Pointers, LinkedHashMap
time: O(n)
space: O(k)

- Method1 and Method2 are identical to `159. Longest Substring with At Most Two Distinct Characters`. 
- However, time complexity for Method2 in increases to O(nk). https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- we want to do better than that (Method3)

#### Method1: Slinding Window, Two Pointers: move 1 element at a time
- Typical slinding window: the goal is to keep a distinct char size/window of size 2.
- use a map<char, count> to track; map.size() is the window size. Follow the template
    - 1) move right pointer, and update freq count map
    - 2) if map.size() == k, process and record max len
    - 3) if map.size() > k, maintain window size: drop curr left char, update map
- return max
- time: O(n)
- space: O(k)

#### Method2: Sliding window, Two Pointer: truncate the entire block at a time
- record last occurance index in map<char, index>
    - when size goes over limit, find last occurance of left-most element
    - set left = leftMostIndex + 1. 
    - This truncates entire block before the last occurance of left-most element
- sliding window 的切割: 用hashmap 存 last occurrance of char index; 
- map.remove(c) 之后, 就等于彻底切掉了那一段; 那么 map.get(c) + 1 也就是新的 left window border
- time: O(nk) to find the left-most element
- space: O(k)

#### Method3: Sliding window + LinkedHashMap
- https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/
- as mentioned above, Method2 uses O(nk), because it takes O(k) to find head item that was inserted first
    - meanwhile, we still need the hash map feature to get/put/remove last occurance of a char with O(1)
- Solution: use a LinkedHashMap: 
    - `map.entrySet().iterator()` maintains the insertion order!
- Special handling:
    - since we want the `lastOccurMap` to preserve laset insertion order
    - we need to `remove` the char every time before put.
- time: O(n)
- space: O(k)


```
/*
Given a string s, find the length of the longest substring T that contains at most k distinct characters.

Example
For example, Given s = "eceba", k = 3,

T is "eceb" which its length is 4.

Challenge
O(n), n is the size of the string s.

Tags Expand
String Two Pointers LintCode Copyright Hash Table

*/

// Method1: Slinding window
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null)  return 0;
        int left = 0, right = 0, max = 0, n = s.length();
        Map<Character, Integer> freq = new HashMap<>();

        while (right < n) {
            // 1) expand right
            char head = s.charAt(right++);
            freq.put(head, freq.getOrDefault(head, 0) + 1);
            
            // 2) process when window is reached
            if (freq.size() <= k) max = Math.max(max, right - left);
            
            // 3) contract left
            if (freq.size() > k) {
                char tail = s.charAt(left++);
                freq.put(tail, freq.get(tail) - 1);
                if (freq.get(tail) == 0) freq.remove(tail);
            }
        }
        
        return max;
    }
}

// Method2: Sliding window, store last occurance of char, and truncate
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        Map<Character, Integer> lastOccurMap = new HashMap<>();
        int left = 0, right = 0, max = 0;

        while (right < n) {
            if (lastOccurMap.size() <= k) { // add new char
                lastOccurMap.put(s.charAt(right), right++);
            }
            if (lastOccurMap.size() > k) { // clean up left-most char
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


// Method3: based on method2, update the map with LinkedHashMap
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        Map<Character, Integer> lastOccurMap = new LinkedHashMap<>(k + 1);
        int left = 0, right = 0, max = 0;

        while (right < n) {
            // add new char
            char c = s.charAt(right);
            if (lastOccurMap.containsKey(c)) lastOccurMap.remove(c);
            lastOccurMap.put(c, right++);
            
            if (lastOccurMap.size() > k) { // clean up left-most char
                Map.Entry<Character, Integer> leftMost = lastOccurMap.entrySet().iterator().next();
                lastOccurMap.remove(leftMost.getKey());
                left = leftMost.getValue() + 1;
            }
            max = Math.max(max, right - left);
        }

        return max;
    }
}



```