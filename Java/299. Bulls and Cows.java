M
tags: Hash Table
time: O(n)
space: O(n)

#### Solution1: use int[10] to count frequency
1. check match chars
1. check unmatched chars by counting and offset their frequency
    - count++ on secret chars: if secretCount is ever < 0 => `char g` has match, then cows++
    - count-- on guess chars: if guessCount is ever >0 => `char s` has match, then cows++

#### Solution2: Use hashmap to count
- Improvement: since all digit, use int[10] to count

```
/**
You are playing the following Bulls and Cows game with your friend: 
You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint that indicates how many digits 
in said guess match your secret number exactly in both digit and position (called "bulls") 
and how many digits match the secret number but locate in the wrong position (called "cows"). 
Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

*/
// solution1
class Solution {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() == 0 || guess.length() == 0) {
            return "0A0B";
        }
        
        int[] nums = new int[10]; // 0 - 9
        int bulls = 0, cows = 0;

        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {
                bulls++;
            } else {
                if (nums[s] < 0) cows++;
                if (nums[g] > 0) cows++;
                nums[s]++;
                nums[g]--;
            }
        }

        return String.format("%sA%sB", bulls, cows);
    }
}

//solution2: Use HashMap to count
class Solution {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() == 0 || guess.length() == 0) {
            return "0A0B";
        }
        
        char[] secretArr = secret.toCharArray();
        char[] guessArr = guess.toCharArray();
        
        Map<Character, Integer> secretMap = new HashMap<>();
        Map<Character, Integer> guessMap = new HashMap<>();
        
        // count matches
        int bulls = 0;
        for (int i = 0; i < secretArr.length; i++) {
            char a = secretArr[i], b = guessArr[i];
            if (a == b) {
                bulls++;
            } else {
                secretMap.putIfAbsent(a, 0);
                secretMap.put(a, secretMap.get(a) + 1);
                guessMap.putIfAbsent(b, 0);
                guessMap.put(b, guessMap.get(b) + 1);
            }
        }
        
        // count miss positioned chars
        int cows = 0;
        for (Map.Entry<Character, Integer> entry : secretMap.entrySet()) {
            char key = entry.getKey();
            if (guessMap.containsKey(key)) {
                cows += Math.min(entry.getValue(), guessMap.get(key));
            }
        }

        return String.format("%sA%sB", bulls, cows);
    }
}
```