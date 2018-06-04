M
tags: Math, Backtracking

TODO: what about regular DFS/backtracking to compute the kth? dfs(rst, list, candiate list, k)

k是permutation的一个数位。而permutation是有规律的。

也就是说，可以根据k的大小来判断每一个数位的字符（从最大数位开始，因为默认factorio从最大数位开始变化）。

于是先求出n!， 然后 k/n!就可以推算出当下这一个数位的字符。然后分别把factorio 和 k减小。

另外, 用一个boolean[] visited来确保每个数字只出现一次。

这个方法比计算出每个permutation要efficient许多。


```
/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Hide Tags Backtracking Math
Hide Similar Problems (M) Next Permutation (M) Permutations

*/
/*
	Correct solution is to reduce k, and manipulate the sequence
	http://www.jiuzhang.com/solutions/permutation-sequence/

    Thoughts:
    k is the sum of possibilities. 
    Based on attempt1, attempt2, we understand that each digit leads a differnet sets of possibilities. The total is n!
    For example, 
    factorio = # of a paticular set of possibilities, and remain = (k / factorio) means how any sets are there. 
    If remain == 0, that means factorio has more possibiities than k (factorio > k) so there is nothing changed on 1st
    char position. For example, if given [1,2,3], then the string will end up as '1xx'.

    With the above fact, we can find out each char by calculate k vs. factorio.

    Note, each round, the factorio itself need to reduced.
*/

public class Solution {
    public String getPermutation(int n, int k) {
        if (n <= 0 || k <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        boolean[] visited = new boolean[n];
        //Largest possible number of posibilities, n!
        int factorio = 1;
        for (int i = 1; i < n; i++) {
            factorio *= i;
        }
        //Put k into 0-base
        k = k - 1;

        for (int i = 0; i < n; i++) {
            int index = k / factorio;
            k = k % factorio;

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    if (index == 0) {
                        visited[j] = true;
                        sb.append((char)('0' + j + 1));
                        break;
                    } else {
                        index--;
                    }
                } 
            }

            if (i < n - 1) {
                factorio = factorio / (n - i - 1);
            }
        }

        return sb.toString();
    }
}


/*
	Based on attempt1, we can actually find every index in front by calling getPermutation(n,k) recursivly
	Still timeout
*/
public class Solution {
    public String getPermutation(int n, int k) {
        if (n <= 0 || k <= 0) {
        	return "";
        }
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
        	nums.add(i);
        }

        int frontInd = 0;
        for (; frontInd < nums.size(); frontInd++) {
			//find max # of permutations that's less than k, and start permutation with that perticular n at index 0. 
	        int factorio = 1;
	    	for (int i = 1; i < n; i++) {
	    		factorio = factorio * i;
	    	}
	    	int startInd = frontInd;
	    	int numPermute = factorio;
	    	while (numPermute < k) {
	    		numPermute += factorio;
	    		startInd++;
	    	}

	    	if (startInd != frontInd) {
	    		int temp = nums.get(startInd);
	    		nums.remove(startInd);
	    		nums.add(frontInd, temp);
		  		k = k - numPermute;
		  		n--;
	        }
	    }
	    StringBuffer sb = new StringBuffer();
      	for (int num : nums) {
      		sb.append(num);
      	}
        return sb.toString();
    }
}

/*
	Attempt1: timeout
	based on k[1 ~ k], find the max x that has x! < k, where x = [1 ~ n]
	then swap (x + 1) and 1, start permutation, and find the (k - x!)th
*/
public class Solution {
	public String rst = "";
	public int index = 0;
    public String getPermutation(int n, int k) {
        if (n <= 0 || k <= 0) {
        	return "";
        }
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
        	nums[i] = i + 1;
        }

		//find max # of permutations that's less than k, and start permutation with that perticular n at index 0. 
        int factorio = 1;
    	for (int i = 1; i < n; i++) {
    		factorio = factorio * i;
    	}
    	int startInd = 0;
    	int numPermute = factorio;
    	while (numPermute + factorio < k) {
    		numPermute += factorio;
    		startInd++;
    	}

    	if (startInd != 0) {
	        int temp = nums[startInd];//findMax return value in [1 ~ n], and we need index
	        nums[startInd] = nums[0];
	        nums[0] = temp;

	  		k = k - numPermute;
        }

        permute("", k, nums);// startInd + 1 is in [1 ~ n]
        return rst;
    }

    public void permute(String s, int k, int[] nums) {
    	if (rst.length() != 0) {
    		return;
    	}
    	if (s.length() == nums.length) {
    	    index++;
    		if (index == k) {
    			rst = s;
    		}
    		return;
    	}
    	for (int i = 0; i < nums.length; i++) {
    		if (!s.contains(nums[i] + "")) {
    			permute(s + nums[i], k, nums);
    		}
    	}
    }
}
```