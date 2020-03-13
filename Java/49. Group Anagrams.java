M
tags: String, Hash Table
time: O(nk)
space: O(nk)

给一串string, return list of list, 把anagram 放在一起.

#### Hash Table, key 是 character frequency
- 存anagram
- 用 character frequency 来做unique key
    - 用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
    - 因为每个位子上的frequency的变化，就能构建一个unique的string
- O(nk), k = max word length

#### Hash Table, key 是 sorted string (too slow)
- 和check anagram 想法一样：转化并sort char array，用来作为key。
- 把所有anagram 存在一起。注意结尾Collections.sort().
- O(NKlog(K)), N = string[] length, k = longest word length    


```

/*
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

*/

/*
Thoughts:
1. Use HashMap to store anagram. Anagram should be stored in same key
2. Figure out the key: use 26-letter to count frequency
3. convrt to list of list
*/
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rst = new ArrayList<>();
        if (strs == null) return rst;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        // convert
        for (List<String> list : map.values()) rst.add(list);
        return rst;
    }
    
    private String getKey(String s) { // O(n)
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) sb.append(count[i]);
        return sb.toString();
    }
}
```