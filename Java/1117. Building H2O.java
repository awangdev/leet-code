M
tags: Thread, Semaphore, Lock


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

```
/*
There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.

In other words:

If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.

Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.

 

Example 1:

Input: "HOH"
Output: "HHO"
Explanation: "HOH" and "OHH" are also valid answers.
Example 2:

Input: "OOHHHH"
Output: "HHOHHO"
Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
 

Constraints:

Total length of input string will be 3n, where 1 ≤ n ≤ 20.
Total number of H will be 2n in the input string.
Total number of O will be n in the input string.
*/

/*
Meethod1: Use lock & counter to lock a thread based on counter
- when counter != 2 , it will execute hydrogen() two times so that 'H' will reach 2
- when count == 2, it will execute oxygen() once so that 'O' will reach 2
*/
class H2O {
    private Object lock = new Object();
    private int count = 0;
    public H2O() { }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (lock) {
            while(count==2) lock.wait();
            releaseHydrogen.run();
            count++;
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (lock) {
            while(count!=2) lock.wait();
            releaseOxygen.run();
            count = 0;
            lock.notifyAll();
        }
    }
}

/*
Method2: use Semaphore to manage the life cycle of 'H' and 'O'
- to start: H is at count 2 and O is at count 0. They need both be at 0 to be unlocked
- hydrogen(): 
  - `h.acquire()` will execute 2 times until H.count is reduced to 0
  - `o.release` will add O.count by 1 for 2 times
- oxygen(): 
  - `o.acquire(2)` can only occur when O.count == 2 due to the 2 calls in `hydrogen(..)`
  - `h.release(2)` will restore the H.count back to 2
*/
class H2O {
    Semaphore h, o;
    public H2O() {
        h = new Semaphore(2, true);
        o = new Semaphore(0, true);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		    releaseOxygen.run();
        h.release(2);
    }
}



```