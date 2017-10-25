/*
Write an algorithm which computes the number of trailing zeros in n factorial.

Example
11! = 39916800, so the out should be 2

Challenge
O(log N) time

Tags Expand 
Mathematics

Thoughts:
Attempt1:
Can this problem be converted to : how many 10's to I have?
Loop through n, and check how many 2s, 5s do we have.
For each i, do while loop and count the number of 2s, and 5s in that particular i.

Note:
5 and 2 makes 10. So don't worry about 10.
Some values will be checked redundantly, so record the ones checked, return the hash value directly.

Attempt2:
Don't even need to worry about 2's because 2 is definitely more than 5's. Only need to care about 5's.

How many 5's? n/5.   loop (1 ~ n)
However, some number within (1 ~ n) may give more 5's, which for example is: 25 = 5 * 5, double 5's. And 125 = triple 5's.

In fact count = n / 5 + n / 25 + n / 125 + ....
*/
class Solution {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
    	if ( n < 5) {
    		return 0;
    	}
    	long count = 0;
    	for (long i = 5; n / i != 0; i *= 5) {
    		count += n / i;
    	}
    	return count;
    }

}



/*
//Attempt 1:
//This solution exceed time limit, and it's over-complex. 滚粗。
class Solution {

    private HashMap<Long, Long> mapTwo = new HashMap<Long, Long>();
	private HashMap<Long, Long> mapFive = new HashMap<Long, Long>();
    public long trailingZeros(long n) {
    	if (n < 5) {
    		return 0;
    	}
    	long countFive = 0;
    	long countTwo = 0;
    	for (int i = 1; i <= n; i++) {
    		if (i % 2 == 0) {
    			countTwo += countExistance(i, 2, mapTwo);
    		}
    		if (i % 5 == 0) {
    			countFive += countExistance(i, 5,mapFive);
    		}
    	}
    	return (countFive < countTwo) ? countFive : countTwo;
    }
    public long countExistance(long n, long m, HashMap<Long, Long> map) {
    	long temp = n;
    	long count = 0;
    	double num = (double)n;
    	while (num / m == n / m) {
    		count++;
    		n = n / m;
    		num = (double)n;
    		if (map.containsKey(n)) {
    			count += map.get(n);
    			break;
    		}
    	}
    	if (!map.containsKey(temp)) {
    		map.put(temp, count);
    	}
    	return count;
    }
};

*/