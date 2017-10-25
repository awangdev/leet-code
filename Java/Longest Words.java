/*
Given a dictionary, find all of the longest words in the dictionary.

Example
Given

{
  "dog",
  "google",
  "facebook",
  "internationalization",
  "blabla"
}
the longest words are(is) ["internationalization"].

Given

{
  "like",
  "love",
  "hate",
  "yes"
}
the longest words are ["like", "love", "hate"].

Challenge
It's easy to solve it in two passes, can you do it in one pass?

Tags Expand 
Enumeration String LintCode Copyright

Thoughts:
Two pass: 1st, get longest length. 2nd pass, get all words.

One pass:
1. Use hashmap: <lengthOfString, ArrayList<String>>
2. keep track of the longest length

Review:
Map: put, get
ArrayList: add
We can get a value from map, and change directly on it, if that's an object (basically refer to the original object)
*/


class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
    	if (dictionary == null || dictionary.length == 0) {
    		return null;
    	}
    	HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
    	int longestLength = 0;

    	for (int i = 0; i < dictionary.length; i++) {
    		int strLength = dictionary[i].length();
    		if (map.containsKey(strLength)) {
    			map.get(strLength).add(dictionary[i]);
    		} else {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(dictionary[i]);
    			map.put(strLength, list);
    		}
    		longestLength = strLength > longestLength ? strLength : longestLength;
    	}
    	return map.get(longestLength);
    }
};
