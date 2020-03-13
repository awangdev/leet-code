M
tags: Trie, Design, Backtracking
time: O(n) to search and to add word
space: < O(mn), depends on the input. m = # of words

#### Trie, prefix tree.
- Trie Structure: `boolean isEnd`, `HashMap<Character, TrieNode> children`
    - trie.addWord: 没node就加，有node就移动
    - trie.search: 没node就return false，有node就移动
- Alternatively, the hash can be `TrieNode[26]` a fixed size array when applicable
    - I like map better for the simplicity to write (w/o converting char -> index)


```
/*
Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

Example
addWord("bad")
addWord("dad")
addWord("mad")
search("pad")  // return false
search("bad")  // return true
search(".ad")  // return true
search("b..")  // return true
Note
You may assume that all words are consist of lowercase letters a-z.

Tags Expand 
Trie
*/


/*
Build the WordDictionary like a TrieTree.
Note: the '.' indicates any letter, which means we'd have to traverse through all possible letters in current level. 
Only one of the returning search results needs to be true

Note:
TrieNode contains that char, boolean, and HashMap of children
*/

public class WordDictionary {
    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isEnd = false;
    }
    
    TrieNode root;
    public WordDictionary(){
        this.root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    public boolean searchHelper(TrieNode node, String word, int index) {
        if (index == word.length()) return node.isEnd;
        HashMap<Character, TrieNode> children = node.children;
        char c = word.charAt(index);
        if (children.containsKey(c)) {
            return searchHelper(children.get(c), word, index + 1);
        } else if (c == '.'){ // wild card
            for (TrieNode childNode : children.values()) {
                if (searchHelper(childNode, word, index + 1)) return true;
            }
        }
        return false; // all search failed
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

```