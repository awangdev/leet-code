H
tags: Trie
time: O(N + Q)
space: O(N)

#### Chain `suffix # prefix`
- Build Trie for all combinations of `suffix#prefix`; all assigned with weight
- how does it make sure to return largest weight/index?
    - when we build trie, always update weight for the path nodes it goes through
    - yes, it overrides, but this problem does not care if some words are not found
- Time: 
    - build: go through all words O(n) * word length * 2 => O(n)
    - query: O(1) tree height is just at most 20.
- Space: O(N) store all words

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
- Build Trie for all combinations of `suffix#prefix`; all assigned with weight
- how does it make sure to return largest weight/index?
    - when we build trie, always update weight for the path nodes it goes through
    - yes, it overrides, but this problem does not care if some words are not found
*/
class WordFilter {
    public class TrieNode {
        int weight;
        Map<Character, TrieNode> children = new HashMap<>();
        public TrieNode (int weight) {
            this.weight = weight;
        }
    }
    
    TrieNode root = new TrieNode(0);
    public WordFilter(String[] words) {
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight];
            StringBuffer prefix = new StringBuffer("#");

            for (int i = 0; i < word.length(); i++) {
                // Insert prefix only
                prefix.append(word.charAt(i));
                insert(prefix.toString(), weight);
                // Insert variations of suffix in front of "#"
                StringBuffer combo = new StringBuffer(prefix.toString());
                for(int j = word.length() - 1; j >= 0; j--) {
                    combo.insert(0, word.charAt(j));
                    insert(combo.toString(), weight);
                }
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        String word = suffix + "#" + prefix;
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) return -1;
            node = node.children.get(c);
        }
        return node.weight;
    }
    
    private void insert(String word, int weight) {
        TrieNode node = root;
        node.weight = weight;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode(weight));
            node = node.children.get(c);
            node.weight = weight;
        }
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
```