H
tags: Hash Table, Two Pointers, String, Sliding Window
time: O(n)
space: O(1)

基本思想:
- 用个char[]存string的frequency.
- 2 pointer: 
    - move `end` to find a valid window; 
    - once valid inwindow found: now move `start` to narrow down to minimum window.
    - once window invalid, continue moving `end` and repeat last 2 steps
- HashMap的做法比char[]写起来要复杂一点, 但是更generic

#### Method0: Sliding Window + freq[256] + counter
- Almost identical approach as in `438. Find All Anagrams in a String` 
- use sliding window template:
    - 1) extend right pointer and reduce char count
    - 2) process when count == 0
    - 3) contract/shrink left side
- special on the `3) step`:
    - there is no hard length limit in this problem: in fact, the goal is to find the shortest length
    - `3) step` now apperas in the `while(counter == 0)` loop
    - shrink the left side of the window as long as counter == 0, until we break the `counter==0` balance.
- time: O(n) one pass
- space: O(1), freq[256] can be ignored.


#### Method1: init a valid freq map; maintain with counter
- Two Pointers, use 1 char freq map + counter to determine valid state
- Inspired by: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
- Idea: use freqMap and counter to maintain a valid substring range, use two pointers to iterate; reduce to `counter==0` which is the valid substring state.
- Steps:
    - 1) build valid freq count map based on target string
    - 2) use end index [0~n) to find valid char and reduce counter to find valid range
    - 3) count==0 gives valid range: process; then `map[s.charAt(start++)]++ == 0` to break the peace
- Explain `if (map[s.charAt(start++)]++ == 0) counter++`: 
    - when `count != 0`, `map[s.charAt(end++)]--` reduces freq regardless of what char it visits (it can be ANY char, rather than T characters)
    - when `count == 0`, `map[s.charAt(start++)]++` increases freq regardless of what char that is.
        - if `map[s.charAt(start)] == 0`: it is a T character being reduced to 0 previously (so we can break the balance on this char)
        - YES, map has other index that has 0 freq: however, `start` ONLY covers indexes that `end` has stepped through :)
- time: O(n)
- space: O(1)
- much faster than method2: skip the O(256*n) comparison logic.
- Note: from the concept, it is the reversed thinking of method2.

#### Method2: build valid map on the fly and compare. Two Pointers, Use 2 Char freq map
- Use 2 char freq maps: source/target.
    - target map: fixed freq map, used for comparision
    - source map: attempt to build a valid freq map on the fly
- two pointers: 
    - use index `start=[0, n)` as start index of source candidate
    - have a end pointer that will attempt to as far as possible to find 1st valid sequence
- time: have double while loop, but still O(n), why?
    - end pointer will at most reach full length n, only once
    - start pointer iterate source strichtly once O(n)
    - overall, it will be O(n)
- space: O(1), only used a constant char[256]
- Option2: use map, a bit more generic

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

// Method0: sliding window template
class Solution {
    public String minWindow(String s, String t) {
        int left = 0, right = 0, start = 0, counter = t.length(); 
        int minLen = Integer.MAX_VALUE, n = s.length();
        
        // Starting state: build source freq freq based on t
        int[] freq = new int[256];
        for (char c : t.toCharArray()) freq[c]++;

        while (right < n) {
            // 1) expand right index
            char head = s.charAt(right++);
            if (freq[head] > 0) counter--; //counter-- whening meeting a valid char on right
            freq[head]--;

            // 2) process, 
            while (counter == 0) { // counter == 0 indicates valid substring[start, start+minLen]
                int len = right - left;
                if (len < minLen) { // assign rst start
                    minLen = len;
                    start = left;
                }
                // 3) contract left
                char tail = s.charAt(left++);
                if (freq[tail] == 0) counter++; 
                freq[tail]++; // freq[x]++ to feed the char back and make freq map invalid
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen); // 
    }
}

/**
Method1: Two Pointers, use 1 char freq map + counter to determine valid state
- Inspired by: https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
- Idea: use freqMap and counter to maintain a valid substring range, use two pointers to iterate; reduce to `counter==0` which is the valid substring state.
- Steps:
    - 1) build valid freq count map based on target string
    - 2) use end index [0~n) to find valid char and reduce counter to find valid range
    - 3) count==0 gives valid range: process; then `map[s.charAt(start++)]++ == 0` to break the peace
- O(n)
*/
class Solution {
    public String minWindow(String s, String t) {
        int end = 0, start = 0, head = 0, counter = t.length(); 
        int minLength = Integer.MAX_VALUE, n = s.length();
        String rst = "";

        // Starting state: build source freq map based on t
        int[] map = new int[256];
        for (char c : t.toCharArray()) map[c]++;

        while (end < n) {
            if (map[s.charAt(end++)]-- > 0) counter--;  //reduce counter -> 0 to find a valid end index
            
            while (counter == 0) { // counter == 0 indicates valid substring[head, head+minLen]
                int len = end - start;
                if (len < minLength) { // reassign rst head
                    minLength = len;
                    head = start;
                }
                // map[s.charAt(start)]==0 means: it was a positive count, and was reduced to 0
                if (map[s.charAt(start++)]++ == 0) counter++; // map[x]++ to feed the char back and make freq map invalid
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(head, head + minLength);
    }
}

/*
Method2: build valid map on the fly and compare. Two Pointers, Use 2 Char freq map. 
- Use 2 char freq maps: source/target.
    - target map: fixed freq map, used for comparision
    - source map: attempt to build a valid freq map on the fly
- two pointers: 
    - use index `start=[0, n)` as start index of source candidate
    - have a end pointer that will attempt to as far as possible to find 1st valid sequence
- time: have double while loop, but still O(n), why?
    - end pointer will at most reach full length n, only once
    - start pointer iterate source strichtly once O(n)
    - overall, it will be O(n)
- space: O(1), only used a constant char[256]
*/
class Solution {
    public String minWindow(String s, String t) {
        int end = 0, start = 0, minLength = Integer.MAX_VALUE, n = s.length();
        String rst = "";

        // Initialize source map for validation usage
        int[] source = new int[256], target = new int[256];
        for (char c : t.toCharArray()) target[c]++;

        while (start < n) {
            while (end < n && !valid(source, target)) { // maintain a valid source[] map
                source[s.charAt(end)]++;
                end++;
            }
            int length = end - start;
            if (valid(source, target) && length < minLength) {
                minLength = length;
                rst = s.substring(start, end);
            }
            source[s.charAt(start++)]--; // move forward, skip a head char
        }
        return rst;
    }
    
    /*
        Validate if the count of source map matches targetMap.
        source[i] < target[i]: not enough char count, false
    */
    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < 256; i++) {
            if (source[i] < target[i]) return false;
        }
        return true;
    }
}

// Option2: Use Mapclass Solution {
    Map<Character, Integer> source = new HashMap<>(), target = new HashMap<>();
    public String minWindow(String s, String t) {
        int end = 0, start = 0, minLength = Integer.MAX_VALUE, n = s.length();
        String rst = "";

        // Initialize source map for validation usage
        for (char c : t.toCharArray()) target.put(c, target.getOrDefault(c, 0) + 1);

        while (start < n) {
            while (end < n && !valid()) { // maintain a valid source[] map
                char c = s.charAt(end);
                source.put(c, source.getOrDefault(c, 0) + 1);
                end++;
            }
            int length = end - start;
            if (valid() && length < minLength) {
                minLength = length;
                rst = s.substring(start, end);
            }
            char c = s.charAt(start++);
            source.put(c, source.get(c) - 1); // move forward, skip a head char
        }
        return rst;
    }
    
    /*
        Validate if the count of source map matches targetMap.
        source[i] < target[i]: not enough char count, false
    */
    private boolean valid() {
        for (char c : target.keySet()) {
            if (!source.containsKey(c) || source.get(c) < target.get(c)) return false;
        }
        return true;
    }
}

```