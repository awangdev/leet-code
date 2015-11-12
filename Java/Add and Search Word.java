Trie结构, prefix tree.
节点里面有char, isEnd, HashMap<Character, TrieNode>
记得怎么造trie：无增有移，没node就加，有Node就移动。
寻找word也一样，无错有移。
```
/*
Design a data structure that supports the following two operations:

void addWord(word)

bool search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example
addWord("bad")

addWord("dad")

addWord("mad")

search("pad") -> false

search("bad") -> true

search(".ad") -> true

search("b..") -> true

Note
You may assume that all words are consist of lowercase letters a-z.

Tags:
Backtracking
*/
/*
Build the WordDictionary like a TrieTree.
Note: the '.' indicates any letter, which means we'd have to traverse through all possible letters in current level. 
Only one of the returning search results needs to be true

Note:
TrieNode contains that char, boolean, and HashMap of children
*/

//NOT DONE YET: http://www.lintcode.com/en/problem/add-and-search-word/#

public class WordDictionary {
    class TrieNode{
        HashMap<Character, TrieNode> children;
        boolean isEnd;
        
        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
            this.isEnd = false;
        }
    }
    
    TrieNode root;
    public WordDictionary(){
        this.root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        for (int i =0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    public boolean searchHelper(TrieNode root, String word, int index) {
        if (index == word.length()) {
            return root.isEnd;
        }
        TrieNode node = root;
        char c = word.charAt(index);
        //border conditon:
        if (node.children.containsKey(c)) {
            return searchHelper(node.children.get(c), word, index + 1);
        } else if (c == '.'){
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                if (searchHelper(entry.getValue(), word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        } 
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");















```