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
