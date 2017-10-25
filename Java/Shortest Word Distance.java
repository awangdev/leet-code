E

找short distance, wordB可以在wordA的前后；而同一时间，只需要计算一个最近的up to date的distance。
greedy不断变更A/B index再做比较即可。

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

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int indexWord1 = -1;
        int indexWord2 = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                indexWord1 = i;
            } else if (words[i].equals(word2)) {
                indexWord2 = i;
            }
            if (indexWord1 >= 0 && indexWord2 >= 0) {
                distance = Math.min(distance, Math.abs(indexWord2 - indexWord1));
            }
        }
        return distance;
    }
}
```