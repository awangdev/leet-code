/*
Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 

Example
Given matrix:
doaf
agai
dcan
and dictionary:
{"dog", "dad", "dgdg", "can", "again"}

return {"dog", "dad", "can", "again"}


dog:
doaf
agai
dcan

dad:
doaf
agai
dcan

can:
doaf
agai
dcan

again:
doaf
agai
dcan

Challenge
Using trie to implement your algorithm.

Tags Expand 
LintCode Copyright Trie


*/

/*
Attemp2: Trie solution.
http://www.jiuzhang.com/solutions/word-search-ii/

Here is how Tire works, from my understanding: it creates a new data strucutre that maps all words into a trie structure. Then, based on the given 2D matrix of letters, using each individual letter as starting point, and grab all possible combinations, then save the possibilities into final resuts.

The magic 'checking point' is the use of 'isString' of trie.

Note: should also be careful with marking board[x][y] = '#', which helps to prevent re-use used letters.

About TrieTree:
Each string obtains a particular/unique path.
Different strings could share same prefix path, but at certain index when the two strings are differentiating, they will start the following path on different TrieNode, which leads to completely separate subtree path.
At end of the tree, a string will have isString== true and the real string value stored.

That is, 
insert: for all letter, make sure they are all created as nodes and linked together by using subtree.
find: for loop to iterate through subtrees of nodes, then return target on last index letter.


In the search:
node.subtree.get(current).isString: this determines if a string exists or not.
*/
public class Solution {
   	class TrieNode {
   		boolean isString;
   		String s;
   		HashMap<Character, TrieNode> subtree;
   		public TrieNode() {
   			this.isString = false;
   			this.s = "";
   			this.subtree = new HashMap<Character, TrieNode>();
   		}
   	}

   	class TrieTree {
   		TrieNode node;
   		public TrieTree(TrieNode n) {
   			node = n;
   		}
   		public void insert(String s) {
   			TrieNode curr = node;
   			for (int i = 0; i < s.length(); i++) {
   				if (!curr.subtree.containsKey(s.charAt(i))) {
   					curr.subtree.put(s.charAt(i), new TrieNode());
   				}
   				curr = curr.subtree.get(s.charAt(i));
   			}
   			curr.isString = true;
   			curr.s = s;
   		}
   		public boolean find(String s) {
   			TrieNode curr = node;
   			for (int i = 0; i < s.length(); i++) {
   				if (!curr.subtree.containsKey(s.charAt(i))) {
   					return false;
   				}
   				curr = curr.subtree.get(s.charAt(i));
   			}
   			return curr.isString;
   		}
   	}

   	public void search(char[][] board, ArrayList<String> rst, int i, int j, TrieNode node) {
   		if (node.isString) {
   			if(!rst.contains(node.s)) {
   				rst.add(node.s);
   			}
   		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || node == null) {
			return;
		}
		if (node.subtree.containsKey(board[i][j])) {
			char temp = board[i][j];
			TrieNode next = node.subtree.get(board[i][j]);
			board[i][j] = '#';//Mark it, prevent going back-forth
			search(board, rst, i, j + 1, next);
			search(board, rst, i, j - 1, next);
			search(board, rst, i - 1, j, next);
			search(board, rst, i + 1, j, next);
			board[i][j] = temp;
		}
   		
   	}
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    	ArrayList<String> rst = new ArrayList<String>();
    	if (board == null || words == null || words.size() == 0) {
    		return rst;
    	}
    	TrieTree tree = new TrieTree(new TrieNode());
    	for (String word : words) {
    		tree.insert(word);
    	}

    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[i].length; j++) {
    			search(board, rst, i, j, tree.node);
    		}
    	}

    	return rst;
    }
}





/*
Attempt1:
Thoughts:
Use word search1, and do for loop on the words... and that works .........Well, that's not the Trie solution
*/

public class Solution {
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
    	ArrayList<String> rst = new ArrayList<String>();
    	if (board == null || words == null || words.size() == 0) {
    		return rst;
    	}
    	for (String word : words) {
    		if (exist(board, word)) {
    			rst.add(word);
    		}
    	}
    	return rst;
    }
    //The following are from Word Search I
     public boolean exist(char[][] board, String word) {
    	if (word == null || word.length() == 0) {
    		return true;
    	}
    	if (board == null) {
    		return false;
    	}
    	
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			if (board[i][j] == word.charAt(0)) {
    				boolean rst = search(board, word, i, j, 0);
    				if (rst) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

    public boolean search(char[][] board, String word, int i, int j, int start) {
    	if (start == word.length()) {
    		return true;
    	}
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
    		return false;
    	}
    	board[i][j] = '#';
    	boolean rst = search(board, word, i, j - 1, start + 1)
    	|| search(board, word, i, j + 1, start + 1)
    	|| search(board, word, i + 1, j, start + 1)
     	|| search(board, word, i - 1, j, start + 1);   
     	board[i][j] = word.charAt(start);
    	return rst;
    }
}
