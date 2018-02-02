M

例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
	当然，搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn

o(n)也可以，用bucket. 比较巧妙。


```
/*
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers 
have at least h citations each, 
and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and 
each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.

Google Facebook
Hide Tags Hash Table Sort
Hide Similar Problems (M) H-Index II

*/

/*
	Thoughts:O(nlogn)
	N = 5, so max of h = 5. min of h = 0.
		1. h = 5, loop through the array and count the # of citations that are >= h.
		2 .h = 4 ... h=1, h=0.
		=> O(n^2).
	
	If sort it : 0,1,3,5,6
		Find find index x = N - h, and arr[x] >= h
		that becomes find index x that arr[x] >=h ,where h = N - x.
			Foor loop on h, O(n)
				pick x = N - h, and check if arr[x] >= h
		h = 5, x = 5 -5 = 0. arr[x] = 0 < h. not working
		h = 4, x = 5 - 4 = 1. arr[x] = 1. no.
		h=3,x=5-3 =2,arr[x]=3  3>=3, okay.
	nLogn + N = O(nlogn)


*/
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
        Arrays.sort(citations);
        for (int h = citations.length; h > 0; h--) {
        	int x = citations.length - h;
        	if (citations[x] >= h) {
        		return h;
        	}
        }
        return 0;
    }
}


/*
	Thoughts: as the hint shows, use extra space and make it faster.
	citations = [3, 0, 6, 1, 5],
	(http://buttercola.blogspot.com/2015/09/leetcode-h-index.html?_sm_au_=iHVFjb76ZHj7ND5D)
	1. For loop to count++ in correct buttkit regardless of the index.
		If arr[x] <= n, then bucket[arr[x]]++. that means, the bucket with index of arr[x] should store this arr[x] element.
		If arr[x] > n, well, that means it exceeds bucket.length (in this application, it means it's already greater than the max of h=n)
			so let's just put it in bucket[n]++.
		Obvisouly, we need bucket[n + 1]
	2. Each bucket slot now stores #of values that's >= it's index h, in ascending order ofcourse.
		so we can do another for loop, to sum one by one, from h = n ~ 0, (because we need higest possible h)
		if sum >= h, that is the rst.
*/
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
 		int n = citations.length;
 		int[] bucket = new int[n + 1];
 		//fill bucket
 		for (int i = 0; i < n; i++) {
 			int bucketSlot = citations[i];
 			if (citations[i] <= n) {
 				bucket[bucketSlot]++;
 			} else {//bucketSlot > n
 				bucket[n]++;
 			}
 		}

 		//Find best H
 		int sum = 0;
 		for (int h = n; h >= 0; h--) {
 			if (sum + bucket[h] >= h) {
 				return h;
 			} 		
 			sum += bucket[h];
 		}
      	return 0;
    }
}
```