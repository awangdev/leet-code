M
tags: Array, Greedy
time: O(n)
space: O(1)


#### Method1: Count all occurrance, and count on overlap indexes
- when there is a value that can cover entire row of size n
    - it must be: `n = countA[i] + countB[i] - overlap[i]`
- Code easy to write and read
- time: O(n)
- space: O(1)

#### Method2: Negative count
- Observation: if A[0] works, no need to check B[0].
- Because if both A[0] and B[0] exist in all dominoes,
    - when you swap A[0] in a whole row,
    - you will swap B[0] in a whole at the same time.
    - The result of trying A[0] and B[0] will be the same.
- time: O(n)
- space: O(1)

#### Method3: positive count Match
- there should exist 1 numbers, that can appear in (A[i], B[i]).
- failure case: there exist at least 1 index, that does not have the common number
- maximum case: there can be 2 numbers, that both will make it work.
- findCommon2, and count them:
    - set.add(A[0], A[B]),
    - if any new one does not exist in set, remove it from set
        - if set is empty() , return -1
- use the 2 numbers from set to do a sweep and count in A, O(n), return the less appearance one.
- time: O(n)
- space: O(1)

```

/**
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.

 
Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation: 
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation: 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
 

Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
*/

/*
#### Method1: Count all occurrance, and count on overlap indexes
    - when there is a value that can cover entire row of size n
    - it must be: n = countA[i] + countB[i] - overlap[i].
    - return a min flip count
- time: O(n)
- space: O(1)
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int[] countA = new int[7], countB = new int[7], overlap = new int[7];
        int n = A.length;
        for (int i = 0; i < n; ++i) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i]) overlap[A[i]]++;
        }
        for (int i = 1; i < 7; ++i) {
            if (countA[i] + countB[i] - overlap[i] == n) return n - Math.max(countA[i], countB[i]);
        }
        return -1;
    }
}

/*
#### Method2: Negative count
- Observation: if A[0] works, no need to check B[0].
    - Because if both A[0] and B[0] exist in all dominoes,
        - when you swap A[0] in a whole row,
        - you will swap B[0] in a whole at the same time.
        - The result of trying A[0] and B[0] will be the same.
- time: O(n)
- space: O(1)
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
       
        int rotationWithA = checkRotation(A, B, A[0]);
        if (rotationWithA != -1 || A[0] == B[0]) return rotationWithA;
        return checkRotation(A, B, B[0]); // if trial with A[0], try B[0]
    }
   
    private int checkRotation(int[] A, int[] B, int candidate) {
        int rotationA = 0, rotationB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != candidate && B[i] != candidate) return -1;
            else if (A[i] != candidate) rotationA++; // flip A to set to correct candidate
            else if (B[i] != candidate) rotationB++;
        }
        return Math.min(rotationA, rotationB);
    }
}


/*
#### Method3: positive count Match
- there should exist 1 numbers, that can appear in (A[i], B[i]).
- failure case: there exist at least 1 index, that does not have the common number
- maximum case: there can be 2 numbers, that both will make it work.
- findCommon2, and count them:
    - set.add(A[0], A[B]),
    - if any new one does not exist in set, remove it from set
        - if set is empty() , return -1
- use the 2 numbers from set to do a sweep and count in A, O(n), return the less appearance one.
- time: O(n)
- space: O(1)
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int n = A.length;
        // validation
        Integer a = A[0], b = B[0];
        for (int i = 1; i < n; i++) {
            if (a != null && a != A[i] && a != B[i]) a = null;
            if (b != null && b != A[i] && b != B[i]) b = null;
            if (a == null && b == null) return -1;
        }
       
        if (a == null) return n - Math.max(count(B, b), count(A, b));
        if (b == null) return n - Math.max(count(B, a), count(A, a));
        int countA = n - Math.max(count(A, a), count(A, b));
        int countB = n - Math.max(count(B, a), count(B, b));
        return Math.min(countA, countB);
    }
   
    private int count(int[] A, Integer candidate) {
        int count = 0;
        for (int num : A) count += (candidate == num) ? 1 : 0;
        return count;
    }
}

```