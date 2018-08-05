H
tags: Trie

```
/*
Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, 
WordFilter.f(String prefix, String suffix). 
It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.


*/

/*
Thought1:
TrieNode {
    boolean isEnd; int weight;
    
    // suffix use as validation for suffix search, built based on [prefixIndex+1, end]. 
    // This will requires lots of in-memory space to save all possible suffix: [e, le, ple, pple] all map to apple
    HashMap<Suffix, List<weight/index>> suffix map; 
    Map<c, TrieNode> children
}
Functions:
insert(); // build trie
generateSubStrings(int i): set<string>
f: search using prefix, find the TrieNode where prefix lands; use suffix to find list of weights. Return max.
search time: O(n) to find max
*/

/*
Thought2:
Build 2 trie structure: regular order, and reverse order.
search: find set1, find set2; 
intersect to return max.
search time: O(n) compare, O(n) find max
*/
```