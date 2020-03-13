M
tags: Hash Table, Sort, Bucket Sort
time: O(n)
space: O(n)

找到h-index, 给的citation int[] 并不是sorted. h-index 的definition 具体看题目.

#### Sort, find h from end
- 例子写出来，发现可以sort以后按照定义搜索一遍。 nlogn.
- 搜索一遍时候可以优化，用binary search. 但是没意义，因为array.sort已经用了nlogn
- 题目给的规则, 从小到大排序后: 剩下的 paper `n-h`, 全部要 <= h 个 citation.
- time O(nlogn), search O(n)

##### 正向思考
- 从i = 0 开始找第一个 `citations[i] >= h`, 就是第一个符合 h-index 规则的paper, return h

##### 反向思考
- 如果从 h = n, 每次h--; 那么 `x = n - h` 就是从 `[0 ~ n)` 开始找第一个 `dictations[x] >= h`, 就是结果
- 同时, `dictations[x-1]` 就是最后一个(dictation最多的)其余的paper.

#### Couting Sort / Bucket Sort
- O(n)
- Bucket sort的思想(更像是counting sort?): 过一遍 input, 把dictation value 作为 index, 分布在bucket[index]上++
- bucket[x] 是 count when # of citation == x. 
- 如果 x 大于 n的时候, 就超出了index范围, 但是刚好这个问题可以包容, 把这样的情况记位在bucket[n]就可以
- 巧妙: `sum += bucket[h]` where `h = [n ~ 0]` 利用了h-index的definition:
- #of papers (sum of bucket[n]...bucket[0]) has more than h cidations 
- 这里运用到了bucket sort的思想, 但是并不是sorting, 而h-index的定义运用的很巧妙.
- Read more about actual bucket sort: https://en.wikipedia.org/wiki/Bucket_sort

#### More about Counting Sort
1. Application: for number/character range
1. Steps:
	- Find range, define countArray
	- Count element and record in the array
	- PreSum the countArray
	- Start from beginning of the array, `print & decrese count` to produce the sorted elements


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

// O(n), feel likes hashtable, where the key = citation value. 
// when citation value > n, then value must > h, so put the # into bucket[n]
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
 		int n = citations.length;
 		int[] bucket = new int[n + 1]; // bucket[n] stores all extras
 		//fill bucket
 		for (int i = 0; i < n; i++) {
 			int bucketSlot = citations[i];
 			if (bucketSlot <= n) bucket[bucketSlot]++;
            else bucket[n]++; //bucketSlot > n
 		}

 		//Find best H
 		int sum = 0;
 		for (int h = n; h >= 0; h--) {
            sum += bucket[h]; // sum = # of citation canditate who has citationCount >= h
 			if (sum >= h) return h; // h-index definition
 		}
      	return 0;
    }
}


// with while loop, start from beginning, O(nlogn)
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
 		int n = citations.length;
 		Arrays.sort(citations);
        int i = 0;
        // by definition, break when the first citations[i] >= h, where h = n - i
 		while (i < n && citations[i] < n - i) {
 			i++;
 		}
      	return n - i;
    }
}

// with for loop, start from end, O(nlogn)
// 反向思考
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
        	return 0;
        }
 		int n = citations.length;
 		Arrays.sort(citations);
        
        // by definition, break when the first citations[i] < h, where h = n - i
        // to start, h = length of citation
        int index = n;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] >= n - i) {
                index = i;
                continue;
            }
            break;
        }
        
      	return n - index;
    }
}


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
// 正向思考
public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        Arrays.sort(citations); // nlogn
        for (int i = 0; i < n; i++) {
            int h = n - i;
            if (citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }
}


```
