M
1520490310
tags: Design, Trie

Tire, 也即是 Prefix Tree. 

HashMap构建Trie。 Trie三个Method:　   
1. Inset: 加 word   
2. Search: 找word    
3. StartWith: 找prefix    

只有两条children的是binary tree. 那么多个children就是Trie。 那么没有left/right pointer怎么找孩子？   
用HashMap，以child的label为Key，value就是child node。 HashMap走位   

Note:    
node里的char在这是optional. 只要在每个TrieNode里面用map存储向下分布的children就好了.  
另外有种题目，比如是跟其他种类的search相关，在结尾要return whole string，就可以在node里存一个up-to-this-point的String。



```
/*
Implement a trie with insert, search, and startsWith methods.

Have you met this question in a real interview? Yes
Example
Note
You may assume that all inputs are consist of lowercase letters a-z.

Tags Expand 
Trie Facebook Uber Google
*/
/*
Thougths:
TrieNode: contains the single character, and a list of children.
Note: we will use hashmap<child character, child TrieNode>, because child access is O(1)
*/

class Trie {
    class TrieNode {
    public boolean isEnd;
    public Map<Character, TrieNode> children;
    public TrieNode() {
        this.isEnd = false;
        this.children = new HashMap<>();
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        if (search(word)) {
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            if (i == word.length() - 1) {
                node.isEnd = true;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



/*
Previous notes.
	Thoughts:
	- Trie is a like a dictionary that's populated based on given input. 
	- Each level indicates each index of the word, where in each level there are multiple separate nodes 
	(depending on how many words we have used to populate the trie )
	- At end of a string, mark it as end == true;

	- search: find end of word && end == true && 
	- startWith: find till end of prefix
*/
```