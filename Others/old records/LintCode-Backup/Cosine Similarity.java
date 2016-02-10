按题目意思，写出来就好了。
```
/*
Cosine similarity is a measure of similarity between two vectors of an inner product space that measures the cosine of the angle between them. 
The cosine of 0° is 1, and it is less than 1 for any other angle.

See wiki: Cosine Similarity

Here is the formula:
http://www.lintcode.com/en/problem/cosine-similarity/#

Given two vectors A and B with the same size, calculate the cosine similarity.

Return 2.0000 if cosine similarity is invalid (for example A = [0] and B = [0]).

Example
Given A = [1, 2, 3], B = [2, 3 ,4].

Return 0.9926.

Given A = [0], B = [0].

Return 2.0000

*/

/*
	Thoughts
	Based on the given equation. Write up calculation
	Check border.
*/

class Solution {
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: Cosine similarity.
     */
    public double cosineSimilarity(int[] A, int[] B) {
    	if (A == null || B == null || A.length == 0 || B.length == 0 || A.length != B.length) {
    		return 2;
    	}

    	double sumProduct = 0;
    	double sumASq = 0;
    	double sumBSq = 0;
    	for (int i = 0; i < A.length; i++) {
    		sumProduct += A[i]*B[i];
    		sumASq += A[i] * A[i];
    		sumBSq += B[i] * B[i];
    	}
    	if (sumASq == 0 && sumBSq == 0) {
    		return 2.0;
    	}
    	return sumProduct / (Math.sqrt(sumASq) * Math.sqrt(sumBSq));
    }
}


```