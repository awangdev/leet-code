M

方法一: 60%

和check anagram 想法一样：转化并sort char array，用来作为key。

把所有anagram 存在一起。注意结尾Collections.sort().

O(NKlog(K)), N = string[] length, k = longest word length    


优化：80%

用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
因为每个位子上的frequency的变化，就能构建一个unique的string


错误的示范: 尝试先sort input strs[]，但是NlogN 其实效率更低. 13%


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
    optmize1:
    Use collectoins.sort() instead of Arrays.sort() in front

    分析: 
    n: str[].length
    p: parts: number of keys in hashmap
    m: max string length
    (n/p): average ['abc', 'cab', 'cba', ... etc] length
    Collections.sort(...) : (n/p)log(n/p) * p = n*log(n/p)
    
    so, how large is n/p?
    1. p is small, -> result goes large
    2. p is large  -> result goes Small
    
    overal： 
        n*m + n*log(n/p)

    If Arrays.sort(strs) at first:
        n*m + nlogn

    Therefore, using Collections.sort() in this problem is faster than using Arrays.sort() in front.

    optimize2:
    char[26] arr: [1,1,2,0,0,0,0,0,0,0,...]
    new String(arr)   -> key of hashmap
    n * m

    optimize3:
    If not necessary, we don't have to use map.entrySet();
    we can just use map.keySet(); It's faster
    
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
            rst.add(entry.getValue());
        }

    LEET CODE SPEED: 80% ~ 97% (25ms ~ 22ms)



*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0) {
            return rst;
        }
        
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        
        for (int i = 0; i < strs.length; i++) {
            String str = calcUniqueKey(strs[i]);
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<String>());
            }
            map.get(str).add(strs[i]);
        }
        
        for(String key: map.keySet()){//FASTER
            Collections.sort(map.get(key));
            rst.add(map.get(key));
        }

        return rst;
    }
    
    public String calcUniqueKey(String s) {
        char[] arr = new char[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] += 1;
        }
        return new String(arr);
    }
}


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
        /*
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {//SLOW
            Collections.sort(entry.getValue());
            rst.add(entry.getValue());
        }
        */
        for(String key: map.keySet()){//FASTER
            Collections.sort(map.get(key));
            rst.add(map.get(key));
        }

        return rst;
    }
}







```