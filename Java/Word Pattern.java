E

每个char代表一个pattern。用HashMap<char, str>.
但不够，如果a也match dog, b也match dog, 纠错了。比如pattern = "abba", str = "dog dog dog dog"。
因此第二个HashMap<str, char> 反过来。
确保pattern和str一一对应。

```
/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

Credits:
Special thanks to @minglotus6 for adding this problem and creating all test cases.

Hide Company Tags Dropbox
Hide Tags Hash Table
Hide Similar Problems (E) Isomorphic Strings (H) Word Pattern II

*/

/*
	3.1.2016 recap.
	HashMap, one to one mapping
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
            return false;
        }
        
        String[] strArr = str.split(" ");
        if (pattern.length() != strArr.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<Character, String>();        
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String s = strArr[i];
            if (!map.containsKey(c)) {
                if (map.containsValue(s)) {
                    return false;
                }
                map.put(c, s);
            } else if (!map.get(c).equals(s)) {
                return false;
            }
        }
        return true;
    }
}


/*
Thoughts:
2 HashMap<char, string>, HashMap<String, char> double check
*/
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern != null && str != null && pattern.length() == 0 && str.length() == 0) {
	        return true;
	    }
	    if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
	        return false;
	    } 
	    String[] strArr = str.split(" ");
	    if (pattern.length() != strArr.length) {
	        return false;
	    }
	    
	    HashMap<Character, String> map = new HashMap<Character, String>();
	    HashMap<String, Character> mapStr = new HashMap<String, Character>();
	    
	    for (int i = 0; i < strArr.length; i++){
	        if (!map.containsKey(pattern.charAt(i))) {
	            map.put(pattern.charAt(i), strArr[i]);
	        } else {
	            if (!map.get(pattern.charAt(i)).equals(strArr[i])) {
	                return false;
	            }
	        }    
	        if (!mapStr.containsKey(strArr[i])) {
	            mapStr.put(strArr[i], pattern.charAt(i));
	        } else {
	            if (mapStr.get(strArr[i]) != pattern.charAt(i)) {
	                return false;
	            }
	        }  
	    } 
	    return true;
    }
}


```