E
1524458579
tags: String

#### String
- 可以用 sb.replace(i, j, "replacement string")
- 简单按 window=2 来扫描
- 原来只需要从'++'转到'--'的情况
- O(n)

```
/*
You are playing the following Flip Game with your friend: 
Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and 
therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, 
it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
*/

// 99.83
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() < 2) {
            return rst;
        }
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < s.length() - 1; i++) {
            String str = s.substring(i, i + 2);
            if (str.equals("++")) {
                sb.replace(i, i + 2, "--");
                rst.add(sb.toString());
                sb.replace(i, i + 2, "++");
            }
        }
        return rst;
    }
}

// 12.06.2015, slower than the previous one 
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        StringBuffer sb = new StringBuffer(s);
        while (sb.indexOf("++") != -1) {
            int index = sb.indexOf("++");
            list.add(index);
            sb.replace(index, index + 1, "*");
        }
        for (int index : list) {
            rst.add(s.substring(0, index) + "--" + s.substring(index + 2));
        }
        return rst;
    }
}


/*
Thoughts:
Two pointers to check if p1 and p2 match target patern. If so, add.

Need to ask: are we only looking to change to '--' from '++'?
*/
public class Solution {
    public static List<String> generatePossibleNextMoves(String s) {
        List<String> rst = new ArrayList<String>();
        if (s == null || s.length() < 1) {
        	return rst;
        }
        char[] arr = s.toCharArray();
      	search('+','-',arr,rst);
    	return rst;
    }

    public static void search(char target, char replace, char[] arr, List<String> rst) {
    	int p1 = 0;
    	int p2 = 1;
    	while (p2 <= arr.length - 1) {
    		if (arr[p1] == target && arr[p2] == target) {
    			arr[p1] = replace;
    			arr[p2] = replace;
    			rst.add(new String(arr));
    			arr[p1] = target;
    			arr[p2] = target;
    		}
    		p1++;
    		p2++;
    	}
    }
}





```