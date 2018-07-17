M
1531813417
tags: Stack

#### Stack
- 1. later function always appears after prior fn: 1 is called by 0
- 2. `Not mentione in the question`: a function can be started multiple times
- 3. `Not mentione in the question`: a fn cannot start if children fn starts
- 4. Use stack to keep id
- TODO: what leads to the choice of stack? stacking fn id

```
/*
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, 
find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. 
For example, "0:start:0" means function 0 starts from the very beginning of time 0. 
"0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, 
the time spent by calling other functions should not be considered as this function's exclusive time. 
You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means 
the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100
*/

// Stack
class Solution {
    class Record {
        int id, time;
        boolean start;
        public Record (int id, boolean start, int time) {
            this.id = id;
            this.start = start;
            this.time = time;
        }
    }
    
    public int[] exclusiveTime(int n, List<String> logs) {
        if (validate(n, logs)) return new int[]{};
        
        // use int[] as data structure to track time of fn i
        int[] rst = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String log : logs) {
            Record record = parse(log);
            if (!stack.isEmpty()) rst[stack.peek()] += record.time - prevTime;
            prevTime = record.time;
            if (record.start) {
                stack.push(record.id);
            } else {
                rst[stack.pop()]++;
                prevTime++;
            }
        }
        return rst;
    }
    
    private boolean validate(int n, List<String> logs) {
        return n <= 0 || logs == null || logs.size() == 0;
    }
    
    private Record parse(String s) {
        String[] ss = s.split(":");
        return new Record(Integer.parseInt(ss[0]), ss[1].equals("start"), Integer.parseInt(ss[2]));
    }
}
```