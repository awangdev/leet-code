/*
Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. Return how many island are there in the matrix after each operator.

Have you met this question in a real interview? Yes
Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

Note
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Tags Expand 
Union Find
*/

/*
Thoughts:
Each pos(x,y) turns that sea spot into a island spot.
Image each isleand spot is a node in the graph, and each island(many island spots) has a root parent.
In for loop, try to add operators into the matrix one after another.
	Every time when adding a new island spot, check its sourandings and see if there are islands existed.
	If there is souranding island:
	To check if the surrouding spot are on common island (use find and union). 
	Since the operator spot was sea, the it's root parent is itself. Then, souranding spot has different island root, 
	they will surely have differet root parent, but they will do after they connect, so we do count--.
	
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
    }
}
