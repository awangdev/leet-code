E
1516264417

方法1: 按照题意, 找到第一个 first index == last index的字母.

方法2: 用hashmap存字母的index, 有些重复字母的index就会是个list. 找到单一index, 结合成list, sort, return list.get(0)

```
/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
*/

/*
    Direclty compare first occurance of a character && last occurance, see if at same spot
 */
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}


/*
Thoughts:
1. put all letter into map <char, index>
2. If more than 1 occurs, remove it from
*/
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        final Map<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final char letter = s.charAt(i);
            if (!map.containsKey(letter)) {
                map.put(letter, new ArrayList<Integer>());
            }
            map.get(letter).add(i);
        }
        final ArrayList<Integer> indexes = new ArrayList<>();
        for (Map.Entry<Character, ArrayList<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                indexes.addAll(entry.getValue());
            }
        }

        if (indexes.size() == 0) {
            return -1;
        }
        Collections.sort(indexes);
        return indexes.get(0);
    }
}

```