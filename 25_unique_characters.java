// Give a list of string, where every string in the list is of size 5. Return the list of 5 string such that all the characters in each of the strings are unique
// i.e if we combine all the strings(not nnecessary) we will have 25 unique characters)
// eg
// Input explanation
// List of string with length of 5 each
// intput = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver"]


import java.util.*;
class Main {
    public static void main(String[] args) {
        String[] strs = new String[]{"abcde","klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra",  "fghij"};
    Map<String, List<String>> memo = new HashMap<>();
            List<String> ans =  dp(0, 0, 0, strs, memo);;
        System.out.println(ans);
    }

private static List<String> dp(int i, int curWordsCount, int bitmask, String[] strs, Map<String, List<String>> memo) {
    // Base case: If we've selected 5 words, return an empty list.
    if (curWordsCount == 5) 
        return new ArrayList<>();
    
    // Base case: If we've reached the end of the array, return null.
    if (i == strs.length) 
        return null;

    // Create a memoization key
    String key = i + "," + curWordsCount + "," + bitmask;
    if (memo.containsKey(key)) 
        return memo.get(key);

    // Skip the current string
    List<String> result = dp(i + 1, curWordsCount, bitmask, strs, memo);
    if (result != null) {
        memo.put(key, result);
        return result;
    }

    // Try to include the current string if possible
    int strBitmask = 0;
    boolean canAdd = true;
    for (char c : strs[i].toCharArray()) {
        int bit = 1 << (c - 'a');
        System.out.println(c + " : " + bit + " : "+ bitmask);
        if ((bitmask & bit) != 0) { // Character already used
            canAdd = false;
            break;
        }
        strBitmask |= bit;
    }

    if (canAdd) {
        result = dp(i + 1, curWordsCount + 1, bitmask | strBitmask, strs, memo);
        if (result != null) {
            result.add(strs[i]);
            memo.put(key, result);
            return result;
        }
    }

    // Cache and return the result
    memo.put(key, null);
    return null;
}

    
}
