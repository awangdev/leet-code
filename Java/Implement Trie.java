M

Tire, 也即是 Prefix Tree. 

HashMap构建Trie。 Trie三个Method:　   
1. Inset: 加 word   
2. Search: 找word    
3. StartWith: 找prefix    

只有两条children的是binary tree. 那么多个children就是Trie。 那么没有left/right pointer怎么找孩子？   
用HashMap，以child的label为Key，value就是child node。 HashMap走位   

Note:    
node里的char在这是optional。    
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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */

/*
	Thoughts:
	- Trie is a like a dictionary that's populated based on given input. 
	- Each level indicates each index of the word, where in each level there are multiple separate nodes 
	(depending on how many words we have used to populate the trie )
	- At end of a string, mark it as end == true;

	- search: find end of word && end == true && 
	- startWith: find till end of prefix
*/
class TrieNode {
	HashMap<Character, TrieNode> children;
	boolean isEnd;
    // Initialize your data structure here.
    public TrieNode() {
        this.children = new HashMap<Character, TrieNode>();
        this.isEnd = false;
    }
}

public class Solution {
    private TrieNode root;

    public Solution() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
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
    
    // Returns if the word is in the trie.
    public boolean search(String word) {
         if (word == null || word.length() == 0) {
        	return true;
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

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
        	return true;
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











```