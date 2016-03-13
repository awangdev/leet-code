H

Big improvement: use boolean visited on TrieNode!     
不要用rst.contains(...), 因为这个是O(n) 在leetcode还是会timeout（lintcode竟然可以pass）!    
在Trie search() method 里面，凡是visit过的，mark一下。  

Regular:   
for loop on words: inside, do board DFS based on each word.     
Time cpmplexity: word[].length * boardWidth * boardHeight * (4^wordMaxLength)

Build Trie with target words: insert, search, startWith.    
依然要对board matrix做DFS。

no for loop on words. 直接对board DFS:   
每一层,都会有个up-to-this-point的string. 在Trie里面check它是不是存在。以此判断。   
若不存在，就不必继续DFS下去了。

Trie solution time complexity, much better:      
build Trie:   n * wordMaxLength
search: boardWidth * boardHeight * (4^wordMaxLength + wordMaxLength[Trie Search])


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

/*NOTE: is boolean visited on Trie!!! */
public class Solution {
  class TrieNode {
    String str;
    boolean isEnd;
    boolean visited;
    HashMap<Character, TrieNode> children;
    public TrieNode () {
      this.isEnd = false;
      this.visited = false;
      this.str = "";
      children = new HashMap<Character, TrieNode>();
    }
  }
  public TrieNode root;
  
    public List<String> findWords(char[][] board, String[] words) {
      List<String> rst = new ArrayList<String>();
      if (board == null || board.length == 0 || board[0].length == 0 
        || words == null || words.length == 0) {
        return rst;
      }

      //Build Trie with words
      root = new TrieNode();
      for (int i = 0; i < words.length; i++) {
        insert(words[i]);
      }

      //Validate existance of the words in board
      boolean[][] visited = new boolean[board.length][board[0].length];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          dfs(rst, "", i, j, visited, board);
        }
      }

      return rst;
    }

    //4 direction DFS search
    public void dfs(List<String> rst, String s, int x, int y, boolean[][] visited, char[][] board) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        s += board[x][y];
        if (!startWith(s)) {
            return;
        }
        
        if (search(s)) {
            rst.add(s);
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(rst, s, nx, ny, visited, board);
        }
        visited[x][y] = false;
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
      if (node.visited || !node.isEnd) {
          return false;
      }
      node.visited = true;
      return true;
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