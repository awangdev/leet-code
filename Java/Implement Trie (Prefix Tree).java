M

Trie自己不多用到。
如果是遇到一个一个字查询的题，可以考虑一下。
构建TrieNode的时候要注意：如何找孩子？如果是个map的话，其实就挺好走位的。
而且，每个node里面的 char 或者string有时候用处不大，
可以为空。但是有些题目，比如在结尾要return一些什么String，就可以在end string那边存一个真的String。


```
/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

Tags: Trie Design
Similar Problems: (M) Add and Search Word - Data structure design

*/
/*
Thoughts:
Understand how prefix tree works, create TrieNode() smartly. (http://www.cnblogs.com/tonyluis/p/4563990.html)
Insert: whenever node does not exist, create a new node.
Search: search through each node, if any node not exist, false; at the end, that node has to be end of that path.
StartWith: very similar to search; at the end, as long as all nodes appear through the path, just return true.

Note:
The contents of the TrieNode can be just a char, or it can be empty String except the end string: for that case 
when we need return the end string.
Also, search and startWith is quite similar, remember that.
*/

class TrieNode {
	char c;
	boolean isEnd = false;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    // Initialize your data structure here.
    public TrieNode() {
    }
    public TrieNode(char c) {
    	this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        HashMap<Character, TrieNode> children = node.children;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (!children.containsKey(c)) {
        		TrieNode newNode = new TrieNode(c);
        		children.put(c, newNode);
        	}
        	node = children.get(c);
        	children = node.children;
        	if (i == word.length() - 1) {
        		node.isEnd = true;
        	}
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        HashMap<Character, TrieNode> children = node.children;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (!children.containsKey(c)) {
        		return false;
        	} else {
        		node = children.get(c);
        		children = node.children;
        	}
			if (i == word.length() - 1) {
        		return node.isEnd;
        	}
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        HashMap<Character, TrieNode> children = node.children;
        for (char c : prefix.toCharArray()) {
        	if (!children.containsKey(c)) {
        		return false;
        	} else {
        		node = children.get(c);
        		children = node.children;
        	}
        }
        return true;
    }


}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
```