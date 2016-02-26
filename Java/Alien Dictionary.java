H

Not Done yet。 Topological sort.

```

/*
There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. 
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
Hide Company Tags Google Facebook
Hide Tags Graph Topological Sort
Hide Similar Problems (M) Course Schedule II

*/

/*
	NOT DONE
	Thoughts:
	They have sink node. They form a valid tree, without sycle.
	A char can visit another node, does not mean they have order.
	A char appear in a lower row means they have different order.
	For 1st column, w appears before e, e appears before r.
	For 2nd column:r appears before t, t appears before f
	For 3rd col: t appears before f.
	For 4th col, nothing to compare.
	So make in[][]: [w,e] [e,r][r,t][t,f] based on the possible order.

	Then do topological sort on the sequence and mark the sequence like in course schedule II
*/

public class Solution {
    public String alienOrder(String[] words) {
        
    }
}









```