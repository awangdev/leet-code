H


用priority queue. 每次把最小的展开，移位。分别x+1,或者y+1:   
因为当下的Min里面x,y都是最小的。所以下一个最小的不是（x+1,y）,就是（x,y+1）。

每次就poll（）一个，放2个新candidate进去就好了。
注意，这样的做法会用重复，比如例子（7,4）会出现两次。用一个HashSet挡一下。

注意，HashSet的唯一性，用一个"x,y"的string就可以代为解决。

```
/*
Given two integer arrays sorted in ascending order and an integer k. Define sum = a + b, 
where a is an element from the first array and b is an element from the second one. 
Find the kth smallest sum out of all possible sums.

Example
Given [1, 7, 11] and [2, 4, 6].

For k = 3, return 7.

For k = 4, return 9.

For k = 8, return 15.

Challenge
Do it in either of the following time complexity:

1. O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
2. O( (m + n) log maxValue). where maxValue is the max number in A and B.
Tags Expand 
Heap Priority Queue Sorted Matrix

*/
//NOT DONE
/*
	Thoughts:
	Inspired by:http://stackoverflow.com/questions/5212037/find-the-pair-across-2-arrays-with-kth-largest-sum
	
	User a priority queue <Point[x,y]> and sort based on the smallest sum
	Add k-1 times into the heap. Each time poll the smallest and expand.
	Finally poll the top of the heap, which will be the smallest

	Note: There will be duplicates,so use a hashstet to mark duplicates. Becareful with what we put int hashset. 
*/
public class Solution {
	public class Point{
        int x,y, val;
        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
   
    public int kthSmallestSum(int[] A, int[] B, int k) {
        if (A == null || B == null || A.length == 0 || B.length == 0 || k < 0) {
            return -1;
        }
        PriorityQueue<Point> queue = new PriorityQueue<Point>(2, new Comparator<Point>(){
            public int compare(Point p1, Point p2) {
                return p1.val - p2.val;
            }
        });
        HashSet<String> set = new HashSet<String>();
        Point min = new Point(0, 0, A[0] + B[0]);
        queue.offer(min);
        set.add(min.x + "," + min.y);

        int n = A.length;
        int m = B.length;

        for (int i = 0; i < k - 1; i++) {
            min = queue.poll();

            if (min.x + 1 < n) {
                Point newP = new Point(min.x + 1, min.y, A[min.x + 1] + B[min.y]);
                if (!set.contains(newP.x + "," + newP.y)) {
                    set.add(newP.x + "," + newP.y);
                    queue.offer(newP);
                }
            }
            if (min.y + 1 < m) {
                Point newP = new Point(min.x, min.y + 1, A[min.x] + B[min.y + 1]);
                if (!set.contains(newP.x + "," + newP.y)) {
                    set.add(newP.x + "," + newP.y);
                    queue.offer(newP);
                }
            }
        }

        min = queue.poll();
        return min.val;
    }
}

```