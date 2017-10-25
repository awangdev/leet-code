耗了一点时间。本以为需要DP一下，把做过的n存一下。后来发现，其实就是剥皮，一层一层，是一个central-depth-first的，钻到底时候，return n=1,或者n=2的case，然后开始backtracking。
难的case先不handle.到底之后来一次O(n) scan.
总共的时间起码是O(n/2) + O(n), 所以还是O(n)
```
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:
Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

Tags: Math Recursion
Similar Problems: (E) Strobogrammatic Number, (H) Strobogrammatic Number III

*/
/*
Thoughts:
For n, there can be k kinds of combination. Save it to map(n,k-list)
For n+2, there can be x + k-kinds-of-inner-number + y;
Treat n=0,1,2 differently. Then recurse on rest, layer by layer
At end end, do a O(n) scan to remove non-wanted items.
*/
public class Solution {
	private HashMap<String, String> candidate = new HashMap<String, String>();
    public List<String> findStrobogrammatic(int n) {
		List<String> rst = new ArrayList<String>();
		candidate.put("0", "0");
		candidate.put("1", "1");
		candidate.put("8", "8");
		candidate.put("6", "9");
		candidate.put("9", "6");
		rst = searchAndCombine(n);
		for (int i = 0; i < rst.size(); i++) {
			if ((Long.parseLong(rst.get(i))+"").length() != n) {
				rst.remove(i);
				i--;
			}
		}
		return rst;
    }

    public List<String> searchAndCombine(int n) {
    	List<String> list = new ArrayList<String>();
    	if (n <= 0) {
			return list;
		} else if (n == 1) {
			list.add("0");
			list.add("1");
			list.add("8");
			return list;
		} else if (n == 2){
			list.add("69");
			list.add("11");
			list.add("88");
			list.add("96");
			list.add("00");
			return list;
		}else {//n >= 2
			List<String> temp = searchAndCombine(n - 2);
			for (String str : temp) {
				for (Map.Entry<String, String> entry : candidate.entrySet()) {
					list.add(entry.getKey() + str + entry.getValue());
				}
			}
		}
		return list;
    }
}
```