M

Brutle: 遍历所有26个字母。

方法2:    
用Trie。 理应更快. However implementation可能有点重复计算的地方，LeetCode timeout. 需要再做做。

```
/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
Tags Expand 
Breadth First Search

Thoughts:
Use the dict (check if mutation exist in dict) as base to create a directed graph, use BFS to find shortest path.

Note:
Be careful with queue size when trying to do for loop on it. Use a pre-fixed size = q.size(), in case queue's size changes during for loop.
*/

//Solution1: nested for loop
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
    	if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return 0;
    	}
    	Queue<String> q = new LinkedList<String>();
    	HashSet<String> set = new HashSet<String>();
    	int level = 1;
    	int len = Integer.MAX_VALUE;
    	q.offer(start);
    	set.add(start);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		for (int i = 0; i < str.length(); i++) {//Alternate each letter position
	    			for (int j = 0; j < 26; j++) {//Alter 26 letters
	    				String newStr;
	    				if (i == 0 && str.length() == 1) {
	    				    newStr = "" + (char)('a' + j);
	    				}
	    				else if (i == str.length() - 1) {
	    					newStr = str.substring(0, i) + (char)('a' + j);
	    				} else {
	 						newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
	    				}
	    				if (!set.contains(newStr) && dict.contains(newStr)) {
	    					if (newStr.equals(end)) {//Found end
	    						len = Math.min(len, level);
	    					} else {
	    						set.add(newStr);
	    						q.offer(newStr);
	    					}
	    				}
	    			}//END FOR J
	    		}//END FOR I
	    	}//END FOR K
    	}//END WHILE
    	return len;
    }
}



//Solution2: separate methods, and hope to make Word Ladder II problem easier
public class Solution {
	private Queue<String> q = new LinkedList<String>();
    private HashSet<String> set = new HashSet<String>();
    private Set<String> dict;
    private String end;
    private int level = 1;
    private int len = Integer.MAX_VALUE;

    public int ladderLength(String start, String end, Set<String> dict) {
    	if (start == null || end == null || dict == null || start.length() != end.length()) {
    		return 0;
    	}
    	this.dict = dict;
    	this.end = end;
    	q.offer(start);
    	set.add(start);
    	while(!q.isEmpty()) {//BFS
    		int size = q.size();//Fix size
    		level++;
    		for (int k = 0; k < size; k++) {//LOOP through existing queue: for this specific level
	    		String str = q.poll();
	    		validateMutations(str);
	    	}//END FOR K
    	}//END WHILE
    	return len;
    }

    public void validateMutations(String str) {
    	for (int i = 0; i < str.length(); i++) {//Alternate each letter position
			for (int j = 0; j < 26; j++) {//Alter 26 letters
				String newStr;
				if (i == 0 && str.length() == 1) {
				    newStr = "" + (char)('a' + j);
				}
				else if (i == str.length() - 1) {
					newStr = str.substring(0, i) + (char)('a' + j);
				} else {
						newStr = str.substring(0, i) + (char)('a' + j) + str.substring(i + 1);
				}
				if (!set.contains(newStr) && dict.contains(newStr)) {
					if (newStr.equals(end)) {//Found end
						len = Math.min(len, level);
					} else {
						set.add(newStr);
						q.offer(newStr);
					}
				}
			}//END FOR J
		}//END FOR I
    }
}







/*
  Use a new method in Trie: 
  getChildrenMap(string s): get the children hashmap at last char of s.
  getCurrMap(s): get the hashmap where the last char of s is at.

  However, still timeout in LeetCode. So there might be some case that's using extra calculation.
  It should ideally be faster than brutle force.
*/
import java.io.*;
import java.util.*;

class Solution {
  
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
  
  
  
    public TrieNode root = new TrieNode();
    public int leng = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length()
            || wordList == null || wordList.size() == 0) {
            return 0;
        }
        
        //build Trie
        for (String s : wordList) {
            insert(s);
        }
        dfs(beginWord, endWord, 1);
        
        return leng;
    }
    
    public void dfs(String start, String end, int step) {
        if (step >= leng) {
            return;
        }

        if (compare(start, end) == 0) {
            leng = Math.min(leng, step);
            return;
        }
        //catch last step, so it covers the case where last change is not in dictionary
        if (compare(start, end) == 1) {
          leng = Math.min(leng, step + 1);
          return;
        }

        for (int i = 0; i < start.length(); i++) {//try to replace every index
            String s = start.substring(0, i + 1);
            HashMap<Character, TrieNode> candidates = getCurrentMap(s);
            
            //If char(i) not in dictinary, go back up one level, 
            //try to find all possible children candidates of previous char. Keep going with the process       
            if (candidates == null) {
              s = start.substring(0, i);
              candidates = getChildrenMap(s);
              //If prev char is not found neither, fail it.
              //Example, when 'b' in "ab" not found, try to find 'a' and its children; however, if 'a' not found neither, cut it off here
              if (candidates == null) {
                continue;
              } 
            } 
          
            //Replace char at i with all candidates existing in Trie dictionary
            for (Map.Entry<Character, TrieNode> entry : candidates.entrySet()) {
              char c = entry.getKey();
              String newStr = start.substring(0,i) + c + start.substring(i+1);
              if (c != start.charAt(i) && !entry.getValue().visited) {
                candidates.get(c).visited = true;
                dfs(newStr, end, step + 1);
              }
            }
        }
    }
    
    /**************************Trie section *********************/
    //In this problem ,didin't use startWith() or search(). 
    //Instead, use a similar function, getChildrenMap. See below
  
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
  
    /*Return the HashMap where the last char of s is in*/
    public HashMap<Character, TrieNode> getCurrentMap(String s) {
        char[] arr = s.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (!node.children.containsKey(c)) {
              return null;
            }
            if (i == arr.length - 1) {
                return node.children;
            }
            node = node.children.get(c);
        }
        return null;
    }
  
    /*Return the children HashMap of the last char*/
    public HashMap<Character, TrieNode> getChildrenMap(String s) {
        if (s == null || s.length() == 0) {
          return root.children;
        }
        char[] arr = s.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (!node.children.containsKey(c)) {
              return null;
            }
            node = node.children.get(c);
        }
        return node.children;
    }
    
    //Helper to comapre string and return the difference if not <= 1
    public int compare(String s1, String s2) {
      int count = 0;
      for (int i = 0; i < s1.length(); i++) {
        count += s1.charAt(i) != s2.charAt(i) ? 1 : 0;
        if (count > 1) {
          return count;
        }
      }
      return count;
    }
  
  
  public static void main(String[] args) {
    String beginWord = "hit";//"hit";
    String endWord = "cog";
    Set<String> set = new HashSet<String>();
    set.add("hot"); set.add("dot"); set.add("dog"); set.add("lot"); set.add("log"); 
    
    
    Solution sol = new Solution();
    int leng = sol.ladderLength(beginWord, endWord, set);
    
    System.out.println("rst " + leng );
    System.out.println("END");
  }
  
}


```