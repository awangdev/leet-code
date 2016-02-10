/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
Update (2015-08-09):
The signature of the function had been updated to return list<list<string>> instead of list<string>, as suggested here. If you still see your function signature return a list<string>, please click the reload button  to reset your code definition.

Tags: Hash Table String
Similar Problems: (E) Valid Anagram, (E) Group Shifted Strings

*/

/*
Thoughts:
Store the anagram in a order list. Collections.sort it.
Store all the anagram strings into <anagram, list>
Colelction.sort all lists and put them into final result.

Note: use Arrays.sort() to sort string.
Note2: can do (element : array, arraylist) in for loop

*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> rst = new ArrayList<List<String>>();
        if (strs == null) {
        	return rst;
        }

		List<String> order = new ArrayList<String>();
		HashMap<String, List<String>> map  = new HashMap<String, List<String>>();
		for (String str : strs) {
		    char[] arr = str.toCharArray();
		    Arrays.sort(arr);
			String s = new String(arr);
			if (!map.containsKey(s)){
				List<String> l = new ArrayList<String>();
				l.add(str);
				order.add(s);
				map.put(s, l);
			} else {
				map.get(s).add(str);
			}
		}
		Collections.sort(order);
		for (String str : order) {
			Collections.sort(map.get(str));
			rst.add(map.get(str));
		}
		return rst;
    }
}