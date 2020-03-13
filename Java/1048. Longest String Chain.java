M
tags: Hash Table, DP, Sort, Bucket Sort
time: O(n)
space: O(n)

#### Hash table, DP
- store `Map<Word, Longest Chain Length>`
- sort all words, try from short to long: short word will be calculated first to serve later words as candidate
- time: O(nlogn)
- space: O(n)

#### Hash Table, Bucket Sort,DP
- store `Bucket: List[17] of words`, given word size limit [0 ~ 16]
- time: O(n)
- space: O(n)

```
/*
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.

 

Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 

Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
*/
/*
Method1: sort, HashMap, DP
- store Map<Word, Longest Chain Length>
- sort all words, try from short to long: short word will be calculated first to serve later words as candidate
- time: O(nlogn)
- space: O(n)
*/
class Solution {
    public int longestStrChain(String[] words) {
        int rst = 0;
        Arrays.sort(words, Comparator.comparing(a -> a.length()));
        HashMap<String, Integer> wordChainMap = new HashMap();
        for (String word : words) {
            if (wordChainMap.containsKey(word)) continue;
            wordChainMap.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                sb.deleteCharAt(i);
                String lastWord = sb.toString();
                if (wordChainMap.containsKey(lastWord) && wordChainMap.get(lastWord) + 1 > wordChainMap.get(word)) {
                    wordChainMap.put(word, wordChainMap.get(lastWord) + 1);
                }
            }
            if (wordChainMap.get(word) > rst) rst = wordChainMap.get(word);
        }
        return rst;
    }   
}

// Method2: bucket sort O(n) time
class Solution {
    public int longestStrChain(String[] words) {
        int rst = 0;
        
        List[] bucket = buildBucket(words);
        HashMap<String, Integer> map = new HashMap();

        for (List<String> list : bucket) {
            if (list == null) continue;
            for (String word : list) {
                if (map.containsKey(word)) continue;
                map.put(word, 1);
                for (int i = 0; i < word.length(); i++) {
                    StringBuilder sb = new StringBuilder(word);
                    sb.deleteCharAt(i);
                    String lastWord = sb.toString();
                    if (map.containsKey(lastWord) && map.get(lastWord) + 1 > map.get(word)) {
                        map.put(word, map.get(lastWord) + 1);
                    }
                }
                if (map.get(word) > rst) rst = map.get(word);    
            }
        }
        return rst;
    }   
    
    // O(n)
    private List[] buildBucket(String[] words) {
        List[] bucket = new List[17];
        for (String w : words) {
            int len = w.length();
            if (bucket[len] == null) bucket[len] = new ArrayList<>();
            bucket[len].add(w);
        }
        return bucket;
    }
}
```