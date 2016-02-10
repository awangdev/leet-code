什么是prime number: >=2的没有除自己和1以外公约数的数。
还有另外一个中定义方法：
这个n,有没有小于n的一个i,而达到： i*i + # of i = n. 如果有，那就不是 prime。 

方法很牛逼也很数学。没做的时候可能想不到。做了之后就觉得，哎，我去，有道理啊。
简而言之：简历一个boolean长条，存isPrime[]。 然后从i=2， 全部变true.
然后利用这个因子的性质，非prime满足条件： self*self, self * self + self ... etc.
所以就check每一个j, j+i, j+i+i, 然后把所有non-prime全部mark成false.
最后，数一遍还剩下的true个数就好了
```
/*
Description:
Count the number of prime numbers less than a non-negative number, n.
Tags: Hash Table, Math
Similar Problems: (E) Ugly Number, (M) Ugly Number II, (M) Perfect Squares
*/

/*
Attempt2: https://leetcode.com/problems/count-primes/ explains it well
1. Ignore 1 and n. Don't count 1 and the number itself in.
2. Assume all numbers are prime in a boolean[]. Check off those are certainly not prime, the remaining will be prime.
3. For any n, only need to check up to i * i < n; more than that, for example 2 x 6 is same as checking 6x2, but 6x2 is not necessary to check.
4. How to mark things off:
	The first non-prime is always i^2: self * self.
	Then more non-primes:self * self, self * (self + 1), self * (self + 2) ... etc.
	So, mark all of these index of in the boolean[]
	
*/
public class Solution {
    public int countPrimes(int n) {
    	if (n <= 1) {
    		return 0;
    	}
    	boolean[] primes = new boolean[n];
    	for (int i = 2; i < primes.length; i++) {
    		primes[i] = true;
    	}

    	for (int i = 2; i * i< n; i++) {
			if (!primes[i]) {
				continue;
			}
    		for (int j = i * i; j < n; j += i) {
    			primes[j] = false;
    		}
    	}
    	int count = 0;
    	for (int i = 2; i < primes.length; i++) {
    		count += primes[i] ? 1 : 0;
    	}
    	return count;
    }
}


/*Timeout version*/
//prime is a number n that cannot be divided by any number < n.
//In fact, only need to check sqrt(n) numbers from 1

public class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }
    
    public boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}


```