hashtable 的做法。
toCharArray
Arrays.sort
Stirng.valueOf(char[])



http://www.jiuzhang.com/solutions/anagrams/
做法不太一样 lOl
1. take each string, count the occurrance of the 26 letters. save in int[]count.
2. hash the int[] count and output a unique hash value.
    hash = hash * a + num
    a = a * b.
3. save to hashmap in the same way as we do. 

这一步把for s: strs 
里面的时间复杂度降到了O(L). L = s.length()

而普通的，for 里面的时间复杂度是 Llog(L)


```
/*
Given an array of strings, return all groups of strings that are anagrams.

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Note
All inputs will be in lower-case

Tags Expand 
String Hash Table


*/

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