H
1528420589
tags: Array, BST, TreeSet

题目解析后: find 2 number, that: 1. k slots between the 2 number, 2. no slots taken between the two number.

#### BST
- BST structure not given, use TreeSet to build BST with each node
- Every time find last/next inorder element 
- `treeSet.lower(x)`, `treeSet.higher(x)`
- 一旦位置相隔(k + 1), 就满足题目条件
- O(nlogn), good enough

#### Track slots of days
- Reverse the array, save days index into days[], where the new index is slot.
- days[i]: at slot i, which day a flower will be planted
- O(n)
- Needs to understand: http://www.cnblogs.com/grandyang/p/8415880.html

```
/*
There is a garden with N slots. In each slot, there is a flower. 
The N flowers will bloom one by one in N days. In each day, 
there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. 
Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, 
where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, 
and also the number of flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].

*/

/*
Thoughts:
Goal is to find 2 number, that is k distance from the other, and no nodes between them.
- Build Binary Search Tree using flow position from flowers[], insert node each day
- Ingest iteratively and check if there is inorder node that has value diff of (k+1)
- example: [1, 100, 50, 20, 3, 2] => in BST, 1 and 3 are consecutive in inorder sequence, valid.
NOTE: When BST not given, just use TreeSet: treeSet.add(x), treeSet.lower(x), treeSet.higher(x)
Time, O(nlogn)
*/

class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        // check edge case
        if (flowers == null || flowers.length == 0 || k < 0) {
            return - 1;
        }

        // build binary tree with each node
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < flowers.length; i++) {
            int num = flowers[i];
            treeSet.add(num);
            Integer lower = treeSet.lower(num);
            Integer higher = treeSet.higher(num);
            if (higher != null && higher - num == k + 1 ) {
                return i + 1;
            }
            if (lower != null && num - lower == k + 1) {
                return i + 1;
            }
        }

        return -1;
    }
}


// Track days of slot of days
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length == 0 || k < 0) {
            return - 1;
        }
        int n = flowers.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[flowers[i] - 1] = i + 1; // 1-based
        }

        int left = 0, right = k + 1, result = Integer.MAX_VALUE;
        for (int i = 0; right < n; i++) {
            if (days[i] < days[left] || days[i] <= days[right]) {
                if (i == right) {
                    result = Math.min(result, Math.max(days[left], days[right]));
                }
                left = i;
                right = k + 1 + i;
            }
        }
        return result == Integer.MAX_VALUE ? - 1 : result;
    }
}

```