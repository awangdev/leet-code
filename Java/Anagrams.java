M
1526453112
tags: Array, Hash Map

把anagram找到并output

#### HashMap
- 存在int[26], Arrays.toString(arr) 就是 string key
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
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        List<String> rst = new ArrayList<>();
        if (strs == null || strs == null) {
    	  	return rst;
        }
        Map<String, List<String>> map = new HashMap<>();
		for (String word : strs){
            int[] arr = new int[26];
            for (char c : word.toCharArray()){
                arr[c - 'a']++;
            }
            String key = Arrays.toString(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }
        
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 2) {
                rst.addAll(entry.getValue());
            }
        }
        return rst;
    }
}

/*
//2.29.2016
 use int[26] assuming it's all lowercase letters
 count each string char in a letter array int[], convert the array into string.
 HashMap carray string as key, and actualy string as value
 outupt all values
*/
public class Solution {
    public List<String> anagrams(String[] strs) {   
        List<String> rst = new ArrayList<String>();
        if (strs == null || strs.length == 0) {
            return rst;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for (int i = 0; i < strs.length; i++) {
            int[] arr = new int[26];
            for (int j = 0; j < strs[i].length(); j++) {
                arr[strs[i].charAt(j) - 'a'] += 1;
            }
            String arrString =  Arrays.toString(arr);
            if (!map.containsKey(arrString)) {
                map.put(arrString, new ArrayList<String>());
            }
            map.get(arrString).add(strs[i]);
        }
        
        //Output
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 2) 
                rst.addAll(entry.getValue());
        }
        
        return rst;
    }
}


/*

    Recap 12.09.2015
    Feels like put into a hashmap of each string's sorted version. <String, ArrayList<Sting>>
    compare each string. If match, add into it.
    reurn all that has >= 2 items
*/
public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> rst = new ArrayList<String>();
        if (strs == null || strs.length == 0) {
            return rst;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray(); 
            Arrays.sort(arr);
            String s = String.valueOf(arr);
            if (!map.containsKey(s)) {
                ArrayList<String> list = new ArrayList<String>();
                map.put(s, list);
            }
            map.get(s).add(strs[i]);
        } 
        //check instance occurs >= 2
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getValue().size() >= 2) {
                rst.addAll(entry.getValue());
            }
        }
        return rst;
    }
}

```