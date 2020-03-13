 
 
 
## Reservior Sampling (1)
**0. [398. Random Pick Index.java](https://github.com/awangdev/LintCode/blob/master/Java/398.%20Random%20Pick%20Index.java)**      Level: Medium      Tags: [Reservior Sampling]
      

#### Reservior sampling
- Random choose: think about reservoir sampling. https://www.youtube.com/watch?v=A1iwzSew5QY
    - Use random generator rd.nextInt(x) pick integer between [0, x)
    - try all numbers, when target is met, we want to model reservoir sampling:
    - item was chosen out of i samples, and all other samples are failed.
- where we can use 'count' to represent the denominator/base to choose.
- `**HAVE TO finish all samples** to make sure equal opportunity`
- we can pick that last matched item as result
- `rd.nextInt(count++) == 0` make sure we are always picking num == 0 to meet definition of reservoir sampling.
- probability theory:
    - If multiply these probablities together to get the probability of one item being chosen with reservior sampling:
    - probability = 1/i * (1 - 1/i+1) * (1 - 1/i+2) ....(1 - 1/n) = 1/n




---

