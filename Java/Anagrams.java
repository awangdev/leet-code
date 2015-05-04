/*
Given an array of strings, return all groups of strings that are anagrams.

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].

Note
All inputs will be in lower-case

Tags Expand 
String Hash Table

Thoughts:
Sorry char array, add to hashtable.
If exist in hashtable, add to final result
Note: rst does not have duplicates.
*/
import java.util.*;
public class Anagrams {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
   public List<String> anagrams(String[] strs) {
    	List<String> rst = new ArrayList<String>();
    	if (strs == null || strs.length == 0) {
    		return rst;
    	}
    	HashMap<String, String> map = new HashMap<String, String>();
    	for (int i = 0; i < strs.length; i++) {
    		char[] array = strs[i].toCharArray();
    		Arrays.sort(array);
    		String s = new String(array);
    		if (map.containsKey(s)) {
    			if (!rst.contains(map.get(s))) {//The first appearnce of this anagrams
    				rst.add(map.get(s));
    			}
    			rst.add(strs[i]);
    		} else {
    			map.put(s, strs[i]);
    		}
    	}
    	return rst;
    }
}

    public static void main(String[] args){
    	Anagrams test = new Anagrams();
    	String[] t = {"", ""};
    	List<String> rst = test.anagrams(t);
    	for (String s : rst) {
    		System.out.println(s);
    	}
    	System.out.println("Done");
    }
}
