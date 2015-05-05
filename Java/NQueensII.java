/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Example
For n=4, there are 2 distinct solutions.

Tags Expand 
Recursion

Thoughts:
Exact same as NQueens, except we don't print the map. Instead, simply add the record in rst.
At the end, return rst.size(), which would be unique answer.
*/
class Solution {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
    	ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    	if (n <= 0) {
    		return 0;
    	}
    	search(n, new ArrayList<Integer>(), rst);
    	return rst.size();
    }

    boolean isValid (ArrayList<Integer> cols, int col) {
   		int row = cols.size();
   		for (int i = 0; i < row; i++) {
			if (cols.get(i) == col ) {
				return false;
			}   	
			if (i - cols.get(i) == row - col) {
				return false;
			}		
			if (i + cols.get(i) == row + col) {
				return false;
			}
   		}
   		return true;
   	}

   	void search(int n, ArrayList<Integer> cols, ArrayList<ArrayList<Integer>> rst) {
   		if (cols.size() == n) {
   			rst.add(cols);
   			return;
   		}
   		for (int i = 0; i < n; i++) {
   			if (!isValid(cols, i)) {
   				continue;
   			}
   			cols.add(i);
   			search(n, cols, rst);
   			cols.remove(cols.size() - 1);
   		}
   	}

};


