E
tags: Array, String
time: O(m + n)
space: O(m + n)

#### Method1: letter frequency map, kinda bucket sort
- Goal: find word count that fits into `f(queries[i]) < f(W)`
- What if: we can store the f(W) as preSum, then goal: `rst[i] = preSum[end] - preSum[queryWordCount]`
    - count(W) and store in count[i]
    - calc preSum
    - processs queries array
- kinda bucket sort: 
    - 1) we know the boundary of the word length, so we can create `bucket`
    - 2) `function count(w)` can produce a value that sort a word into a specific bucket slot
        - extend: the bucket can store keys that links back to the word (if there are follow up questions)
- time: O(m + n)
- space: O(m + n)

#### Method2: No brain solution, basic impl based on the desc, w/o search. 
- time: 
    - O(nm) to count all words, O(nlogn) to sort the wordCount
    - O(nm) to to count all queries
    - O(n^2) to perform the match 
- space: O(n)

```
/*
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 

Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.
*/
/*
#### Method: letter frequency map
- Goal: find word count that fits into `f(queries[i]) < f(W)`
- What if: we can store the f(W) as preSum, then rst[i] = preSum[i] - preSum[i-1]
    - count(W) and store in count[i]
    - calc preSum
    - processs queries array
*/
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] preSum = buildPreSum(words);
        
        int[] rst = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = count(queries[i]);
            rst[i] = preSum[preSum.length - 1] - preSum[count];
        }
        return rst;
    }
   
    private int[] buildPreSum(String[] words) {
        int[] freqCounts = new int[11]; // max word length is 10 as suggested
        for (String w : words) {
            int count = count(w);
            freqCounts[count]++;
        }
        int[] preSum = new int[11];
        preSum[0] = freqCounts[0];
        for (int i = 1; i < freqCounts.length; i++) preSum[i] += preSum[i - 1] + freqCounts[i];

        return preSum;
    }
   
    private int count(String w) {
        int[] count = new int[26];
        for (char c : w.toCharArray()) count[c - 'a']++;
        for (int num : count) {
            if (num != 0) return num;
        }
        return 0;
    }
}


/*
Method2: No brain solution, basic impl based on the desc, w/o search. 
time: 
- O(nm) to count all words, O(nlogn) to sort the wordCount
- O(nm) to to count all queries
- O(n^2) to perform the match 
space: O(n)
*/
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] wordCounts = new int[n];
        for (int i = 0; i < n; i++) wordCounts[i] = count(words[i]);
        Arrays.sort(wordCounts);

        int[] rst = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            rst[i] = calc(wordCounts, count(queries[i]));
        }
        return rst;
    }
    
    private int calc(int[] wordCounts, int count) {
        int rst = 0;
        for (int i = wordCounts.length - 1; i >= 0; i--) {
            if (wordCounts[i] <= count) break;
            rst++;
        }
        return rst;
    }
    
    // cound lowest alphabetical char
    private int count(String w) {
        int[] count = new int[26];
        for (char c : w.toCharArray()) count[c - 'a']++;
        for (int num : count) if (num != 0) return num;
        return 0;
    }
}
```