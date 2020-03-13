M
tags: Array, Hash Table, Design, Two Pointers
time: O(n)
space: O(1)

跟243/244不同: 这里允许list里面有重复的word.

#### Method1: Two Pointers, one pass
- Follow up of 243. Shortested Word Distance
- 特别handle `word == word1 == word2` case:
    - p1 and p2 will always be the same
    - when `word == word1 == word2`, simply calculate distance using the `old p1 or p2` with `curr index i`
- The rest impl aligns with 243.

#### Method2: Hash Table
- when `word1==word2`, make usre to skip `p1==p2` by increasing i or j
- The rest impl aligns with 244
- Time: still O(n), but slower than Method1: 2 passes
- Space: uses extra space O(n) to hold all indexes

```

/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.
*/

// Built based on shortest word distance. Handle word1 == word2 case.
/**
Method1: Two Pointers, process words in 1 pass. 
Follow up of 243. Shortest Word Distance I
*/
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        Integer p1 = null, p2 = null;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            // word1 == word2 on same index. Choose old p1 or p2 operator (old p1 == old p2 anyways)
            if (word.equals(word1) && word.equals(word2)) { 
                if (p1 != null) distance = Math.min(distance, i - p1); // i = curr index of word1/word2; p1/p2 are last index of word1/word2
                p1 = i; // move p1 to new spot
                continue;
            }
            if (words[i].equals(word1)) p1 = i;
            if (words[i].equals(word2)) p2 = i;
            if (p1 != null && p2 != null) distance = Math.min(distance, Math.abs(p1 - p2));
        }// end for
        return distance;
    }
}


/*
Method2: Hash Table to store <word, list<index>>. Follow up of 244. Shortest Word Distance II
*/
class Solution {
    
    public int shortestWordDistance(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }
        
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, m = list1.size(), n = list2.size();
        while (i < m && j < n) {
            int p1 = list1.get(i), p2 = list2.get(j);
            if (p1 == p2) { // on same index, move i or j forward
                if (i + 1 < m) i++;
                else if (j + 1 <= n) j++; // at the end, move j -> n to end processing
                continue;
            }
            // p1 != p2
            min = Math.min(min, Math.abs(p1 - p2));
            if (p1 < p2) i++; // move 1 occurance at a time: trying to move 2 words closer
            else j++;
        }
        return min;
    }
}

```