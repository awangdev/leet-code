M
1534312697
tags: Hash Table, String


#### Convert to orginal string
- shit by offset. `int offset = s.charAt(0) - 'a';`
- increase if less than 'a': `if (newChar < 'a') newChar += 26;`

#### Previous notes
- 相同shift规则的string, 能被推算到同一个零起始点，就是共同减去一个char,最后就相等。以此作为key，用HashMap。一目了然。
- 记得根据题目意思，一开始要String[] sort一下。

```
/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.

Hide Company Tags Google Uber
Hide Tags Hash Table String
Hide Similar Problems (M) Group Anagrams

*/

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String origin = convert(s);
            map.putIfAbsent(origin, new ArrayList<>());
            map.get(origin).add(s);
        }
        return new ArrayList<>(map.values());
    }
    
    private String convert(String s) {
        StringBuffer sb = new StringBuffer();
        int offset = s.charAt(0) - 'a';
        for (char c : s.toCharArray()) {
            char newChar = (char)(c - offset);
            if (newChar < 'a') newChar += 26;
            sb.append(newChar);
        }
        return sb.toString();
    }
}

//Reduce each string into initial state: with the char at index 0 equal to integer 0. Save it as key for hashmap
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if (strings == null || strings.length == 0) {
            return rst;
        }
        Arrays.sort(strings);
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for (String s : strings) {
            char[] arr = s.toCharArray();
            char head = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - head < 0) {
                    arr[i] = (char)((arr[i] - head) + 26);
                } else {
                    arr[i] = (char)(arr[i] - head);
                }
            }
            String key = new String(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);
        }
        
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            rst.add(entry.getValue());
        }
        return rst;
    }
}
```