M

和check anagram 想法一样：转化并sort char array，用来作为key。把所有anagram 存在一起。注意结尾Collections.sort().

```

/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
Hide Company Tags Amazon Bloomberg Uber Facebook
Hide Tags Hash Table String
Hide Similar Problems (E) Valid Anagram (E) Group Shifted Strings

*/

/*
Thoughts:
brutle force: save to Map<String, List> O(n) space,time

Store the anagram in a order list. Collections.sort it. MO(logM)

Note: use Arrays.sort() to sort string.
Note2: can do (element : array, arraylist) in for loop

*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) {
            return rst;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String str = new String(arr);
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<String>());
            }
            map.get(str).add(strs[i]);
        }
        
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
            rst.add(entry.getValue());
        }
        
        return rst;
    }
}



```