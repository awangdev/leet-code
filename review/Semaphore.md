 
 
 
## Semaphore (1)
**0. [1117. Building H2O.java](https://github.com/awangdev/LintCode/blob/master/Java/1117.%20Building%20H2O.java)**      Level: Medium      Tags: [Lock, Semaphore, Thread]
      

#### Meethod1: Use lock & counter to lock a thread based on counter
- when counter != 2 , it will execute hydrogen() two times so that 'H' will reach 2
- when count == 2, it will execute oxygen() once so that 'O' will reach 2

#### Method2: use Semaphore to manage the life cycle of 'H' and 'O'
- to start: H is at count 2 and O is at count 0. They need both be at 0 to be unlocked
- hydrogen(): 
  - `h.acquire()` will execute 2 times until H.count is reduced to 0
  - `o.release` will add O.count by 1 for 2 times
- oxygen(): 
  - `o.acquire(2)` can only occur when O.count == 2 due to the 2 calls in `hydrogen(..)`
  - `h.release(2)` will restore the H.count back to 2
- semaphore: https://www.geeksforgeeks.org/semaphore-in-java/



---

