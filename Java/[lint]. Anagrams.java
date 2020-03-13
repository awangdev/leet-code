M
tags: Array, Hash Table, Lint
time: O(n)
space: O(n) 

把anagram找到并output

#### HashMap
- 存在int[26], Arrays.toString(arr) 就是 string key: character frequency map
- anagram都有一样的key, 存进hashmap<string, list of anagrams>
- output anagrams

#### HashMap + Sort
- HashMap 的做法. sort每个string, 存进HashMap, 重复的就是anagrams,最后输出。   
- toCharArray
- Arrays.sort
- Stirng.valueOf(char[])
- 时间n*L*O(logL),L是最长string的长度。

#### Previous Notes
- Arrays.toString(arr)的做法。arr是int[26], assuming only have 26 lowercase letters.    
- Count occurrance, 然后convert to String，作为map的key.
- Time complexity: nO(L)
- 另一种做法：http://www.jiuzhang.com/solutions/anagrams/   
- 1. take each string, count the occurrance of the 26 letters. save in int[]count.   
- 2. hash the int[] count and output a unique hash value; hash = hash * a + num; a = a * b.   
- 3. save to hashmap in the same way as we do. 
- 这一步把for s: strs 里面的时间复杂度降到了O(L). L = s.length().   
- Need to work on the getHash() function.
- 时间变成n*O(L). Better.


```
/*
LintCode
Given an array of strings, return all groups of strings that are anagrams.

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Note
All inputs will be in lower-case

Tags Expand 
String Hash Table


*/
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> rst = new ArrayList<>();
        if (strs == null || strs == null) return rst;
        Map<String, List<String>> map = new HashMap<>();
		for (String word : strs){
            int[] arr = new int[26];
            for (char c : word.toCharArray()) arr[c - 'a']++;
            String key = Arrays.toString(arr);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        
        for (List<String> list : map.values()) {
            if (list.size() >= 2) rst.addAll(list);
        }
        return rst;
    }
}

```