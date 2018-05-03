E
1525327296
tags: Hash Map, String

给一串String, 找到最长的长度, 把最长的String全都return

#### HashMap
- <Integer,List<String>>
- 存最长值, 最后map.get(max) 

```
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

*/

/*
Thoughts:
1. one pass, save in <Integer, List>.
2. Maintain max length
3. return
*/
public class Solution {
    /*
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    public List<String> longestWords(String[] dictionary) {
    	if (dictionary == null || dictionary.length == 0) {
    		return null;
    	}
    	Map<Integer, ArrayList<String>> map = new HashMap<>();
    	int max = 0;

    	for (String word : dictionary) {
    		int length = word.length();
    		if (!map.containsKey(length)) {
    		    map.put(length, new ArrayList<>());
    		}
    		map.get(length).add(word);
    		max = Math.max(max, length);
    	}
    	return map.get(max);
    }
}
```