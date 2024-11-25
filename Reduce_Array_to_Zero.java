// You are given an array of length n and q queries. Each query consists of two indices [l, r], representing a subarray from index l to r (both inclusive). For each query, we are allowed to subtract 1 from any subsequence within the range [l, r]. After processing all the queries, determine whether it's possible to make all elements of the array equal to zero.


// Example:
// arr = [1, 2, 3]
// queries = [[0, 1], [1, 2], [0, 2], [1, 2]]


// Output: true


// Explanation:
// query --> arr -- subsequence
// [0, 1] --> [0, 1, 3] -- {1, 2}
// [1, 2] --> [0, 0, 2] -- {1 , 3}
// [0, 2] --> [0, 0, 1] -- {2}
// [1, 2] --> [0, 0, 0] -- {1}

import java.util.*;

public class ArrayReduction {

    public static boolean canMakeArrZero(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] temp = new int[n + 1];

        // Apply range updates based on queries
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            temp[l] += 1;
            if (r + 1 < temp.length) {
                temp[r + 1] -= 1;
            }
        }

        // Calculate prefix sums
        for (int i = 1; i < n; i++) {
            temp[i] += temp[i - 1];
        }

        // Check if we can reduce all elements to 0
        for (int i = 0; i < n; i++) {
            if (temp[i] < arr[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 3};
        int[][] queries1 = {
            {0, 1},
            {1, 2},
            {0, 2},
            {1, 2}
        };

        System.out.println("Can make arr zero (Test Case 1): " + canMakeArrZero(arr1, queries1)); // Expected output: true

        int[] arr2 = {3, 3, 3};
        int[][] queries2 = {
            {0, 1},
            {1, 1}
        };

        System.out.println("Can make arr zero (Test Case 2): " + canMakeArrZero(arr2, queries2)); // Expected output: false
    }
}

