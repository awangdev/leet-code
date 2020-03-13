M
tags: Array, Hash Table, Design, Two Pointers
time: O(n) to build map, O(a + b) to query
space: O(n)

#### Map
- Prep: 存Map<word, index list>
- Process: 相继从两个 index list 里面拿出 p1,p2
    - 根据index的大小, 移动双指针: try to move the pointers closer; always calculate diff
- Optionally: if one list is much larger, do binary search on the larger list

```
/*
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
1. HashMap stores the indexes to reduce the dataset
1. Use 2 pointers do regular wordDistance calculation to find answer
time: O(n) to build map, O(a + b) for function
space: O(n)
*/
class WordDistance {
    Map<String, List<Integer>> map = new HashMap<>();
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int p1 = list1.get(i), p2 = list2.get(j);
            min = Math.min(min, Math.abs(p1 - p2));
            if (p1 < p2) i++; // move 1 occurance at a time: trying to move 2 words closer
            else j++;
        }
        return min;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
```