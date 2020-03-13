E
tags: Array, Two Pointers
time: O(n)
space: O(1)


#### Two Pointers
- Use 2 pointers to record **most recent** pos of word1 and word2
    - move pointer i [0 ~ n) and keep refreshing pos1 and pos2
    - both pos1 and pos2 will be as adjacent as possible since they both moving towards same direction
- keep recalculating best distance when either word is matched
- 而同一时间，只需要计算一个最近的curr distance: greedy不断变更A/B index, 做比较


```
/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

/*
Thoughts:
For word A and B.
At one time, A can only be most close to two possible B's from left or right.
For the current A, left-B is known and right-B is unkown, but will encounter in the future.
Therefore, we always only have to keep the two index: indexA, indexB updated and always try to calculate the latest amount the two.
This is quite Greedy.

O(n)
*/

/*
- Use 2 pointers to record pos of word1 and word2
- keep recalculating best distance when either word is matched
*/
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        Integer p1 = null, p2 = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) p1 = i;
            if (words[i].equals(word2)) p2 = i;
            if (p1 != null && p2 != null) min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;
    }
}

```