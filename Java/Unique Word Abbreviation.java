M
1527830038
tags: Hash Table, Design


给一个string[] dict, 和一个word. 

每个word都可以缩写成固定的abbreviation `<first letter><number><last letter>`(详细看原题)

检查input word是否满足unique

#### HashMap<string, Set>
- 简单算出abbreviatioin
- 检查abbr是否存在; 如果存在, 是不是input word本身.

```
/*
An abbreviation of a word follows the form <first letter><number><last letter>. 
Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

*/

/*
Given dict is String[], can't do contains(), so we'd convert the entire dict to abbreviation format.
Convert word into abbreviation as well, then perform check
*/

class ValidWordAbbr {
    HashMap<String, Set<String>> map = new HashMap<>();
    public ValidWordAbbr(String[] dict) {
        if (dict == null || dict.length == 0) {
            return;
        }
        for (String word : dict) {
            String abbr = convertToAbbr(word);
            if (!map.containsKey(abbr)){
                map.put(abbr, new HashSet<>());
            }
            map.get(abbr).add(word);
        }
    }
    
    public boolean isUnique(String word) {
        if (word == null) {
            return false;
        }
        String abbr = convertToAbbr(word);
        if (!map.containsKey(abbr)) {
            return true;
        } else {
            Set<String> words = map.get(abbr);
            if (words.size() == 1 && words.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private String convertToAbbr(String word) {
        if (word == null || word.length() <= 2) {
            return word;
        }
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
    }
}



/*
Thought:
Originally, used a hashset to store all existing pattern. If checked word exist in dict hashset, then return false.
However, there is a case that: the word existed in the dict only for once, which is by accident the same as the checked work, then return true.
Therefore, we need to keep track of what word has been catagrize into pattern. SO, use a HashMap<String, ArrayList> instead.

Note: Dealing with char, integer, string. Be careful if char are turnning int integers.
*/
public class ValidWordAbbr {
	HashMap<String, ArrayList<String>> map;
    public ValidWordAbbr(String[] dict) {
        if (dict == null || dict.length == 0) {
        	return;
        }
        map = new HashMap<String, ArrayList<String>>();
        for (String s : dict) {
        	String str = "";
        	if (s.length() <= 2) {
        		str = s;
        	} else {
        		str += s.charAt(0) + (s.length() - 2 + "") + s.charAt(s.length() - 1);
        	}
        	if (!map.containsKey(str)) {
        		ArrayList<String> list = new ArrayList<String>();
 				list.add(s);
 				map.put(str, list);
        	} else {
        	    if (!map.get(str).contains(s)) {
        	       	map.get(str).add(s); 
        	    }
        	
        	}
        }
    }

    public boolean isUnique(String word) {
        if (map == null || map.size() == 0) {
            return true;
        }
        String str = "";
        if (word.length() <= 2) {
        	str =  word;
        } else {
        	str += word.charAt(0) + (word.length() - 2 + "") + word.charAt(word.length() - 1);
        }
        if (map.containsKey(str) && map.get(str).size() == 1 && map.get(str).get(0).equals(word)) {
        	return true;
        }
        return !map.containsKey(str);
    }
}





// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
```