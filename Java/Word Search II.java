H

Build Trie with target words: insert, search, startWith.

依然要对board matrix做DFS。下面对比不用Trie和用Trie。

Regular:   
for loop on words: inside, do board DFS based on each word.

With Trie:
no for loop on words. 直接对board DFS:   
每一层,都会有个up-to-this-point的string. 在Trie里面check它是不是存在。以此判断。   
若不存在，就不必继续DFS下去了。


```
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
    String str;
    boolean isEnd;
    HashMap<Character, TrieNode> children;
    public TrieNode () {
      this.isEnd = false;
      this.str = "";
      children = new HashMap<Character, TrieNode>();
    }
  }
  public TrieNode root;
  public int[] xd = {1, -1, 0, 0};
  public int[] yd = {0, 0, 1, -1};
  
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
      ArrayList<String> rst = new ArrayList<String>();
      if (board == null || board.length == 0 || board[0].length == 0 
        || words == null || words.size() == 0) {
        return rst;
      }

      //Build Trie with words
      root = new TrieNode();
      for (int i = 0; i < words.size(); i++) {
        insert(words.get(i));
      }

      //Validate existance of the words in board
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          dfs(board, i, j, rst, "");
        }
      }

      return rst;
    }

    //4 direction DFS search
    public void dfs(char[][] board, int x, int y, ArrayList<String> rst, String s) {
      if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
        return;
      }
      if (board[x][y] == '#') {
          return;
      }
      s += board[x][y];
      
      if (!startWith(s)) {
        return;
      }
      if (search(s) && !rst.contains(s)) {
        rst.add(s);
      } 
      
      char temp = board[x][y];
      board[x][y] = '#';
    for (int i = 0; i < 4; i++) {
      int nX = x + xd[i];
      int nY = y + yd[i];
      dfs(board, nX, nY, rst, s);
    }
    board[x][y] = temp;
      
    }

    /**************************Trie section *********************/
    public void insert (String s){
      char[] arr = s.toCharArray();
      TrieNode node = root;
      for (int i = 0; i < arr.length; i++) {
        char c = arr[i];
        if (!node.children.containsKey(c)) {
          node.children.put(c, new TrieNode());
        }
        node = node.children.get(c);
        if (i == arr.length - 1) {
          node.isEnd = true;
          node.str = s;
        }
      }
    }

    public boolean search(String s) {
      char[] arr = s.toCharArray();
      TrieNode node = root;
      for (int i = 0; i < arr.length; i++) {
        char c = arr[i];
        if (!node.children.containsKey(c)) {
          return false;
        }
        node = node.children.get(c);
      }
      return node.isEnd;
    }

    public boolean startWith(String s) {
      char[] arr = s.toCharArray();
      TrieNode node = root;
      for (int i = 0; i < arr.length; i++) {
        char c = arr[i];
        if (!node.children.containsKey(c)) {
          return false;
        }
        node = node.children.get(c);
      }
      return true;
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

```