public class LIS {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (nums.length == 0 || nums.length == 1)
            return nums.length;
        int[] dp = new int[nums.length];
        int size = 0;

        for (int i = 0; i < n; i++) {
            int lo = 0;
            int hi = size;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2; // Fixed: added parentheses

                if (dp[mid] < nums[i]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            dp[lo] = nums[i];
            if (lo == size) {
                size++;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        LIS solution = new LIS();

        System.out.println("=== Longest Increasing Subsequence (LIS) Tests ===");
        System.out.println();

        // Test Case 1: Classic example
        System.out.println("Test Case 1: Classic Example");
        int[] nums1 = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.print("Input: [");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + (i < nums1.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result1 = solution.lengthOfLIS(nums1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 4");
        System.out.println("Explanation: LIS is [2, 3, 7, 101] or [2, 3, 7, 18]");
        System.out.println();

        // Test Case 2: All increasing
        System.out.println("Test Case 2: Strictly Increasing Array");
        int[] nums2 = { 1, 2, 3, 4, 5 };
        System.out.print("Input: [");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i] + (i < nums2.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result2 = solution.lengthOfLIS(nums2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 5");
        System.out.println("Explanation: The entire array is increasing");
        System.out.println();

        // Test Case 3: All decreasing
        System.out.println("Test Case 3: Strictly Decreasing Array");
        int[] nums3 = { 5, 4, 3, 2, 1 };
        System.out.print("Input: [");
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i] + (i < nums3.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result3 = solution.lengthOfLIS(nums3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 1");
        System.out.println("Explanation: Any single element is a valid LIS");
        System.out.println();

        // Test Case 4: All same elements
        System.out.println("Test Case 4: All Same Elements");
        int[] nums4 = { 7, 7, 7, 7, 7 };
        System.out.print("Input: [");
        for (int i = 0; i < nums4.length; i++) {
            System.out.print(nums4[i] + (i < nums4.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result4 = solution.lengthOfLIS(nums4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 1");
        System.out.println("Explanation: No strictly increasing subsequence possible");
        System.out.println();

        // Test Case 5: Single element
        System.out.println("Test Case 5: Single Element");
        int[] nums5 = { 1 };
        System.out.print("Input: [");
        for (int i = 0; i < nums5.length; i++) {
            System.out.print(nums5[i] + (i < nums5.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result5 = solution.lengthOfLIS(nums5);
        System.out.println("Output: " + result5);
        System.out.println("Expected: 1");
        System.out.println();

        // Test Case 6: Alternating pattern
        System.out.println("Test Case 6: Alternating Pattern");
        int[] nums6 = { 1, 3, 2, 4, 3, 5, 4, 6 };
        System.out.print("Input: [");
        for (int i = 0; i < nums6.length; i++) {
            System.out.print(nums6[i] + (i < nums6.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result6 = solution.lengthOfLIS(nums6);
        System.out.println("Output: " + result6);
        System.out.println("Expected: 4");
        System.out.println("Explanation: LIS is [1, 2, 3, 4] or [1, 2, 4, 6], etc.");
        System.out.println();

        // Test Case 7: Large numbers
        System.out.println("Test Case 7: Mixed Large and Small Numbers");
        int[] nums7 = { 0, 1, 0, 3, 2, 3 };
        System.out.print("Input: [");
        for (int i = 0; i < nums7.length; i++) {
            System.out.print(nums7[i] + (i < nums7.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        int result7 = solution.lengthOfLIS(nums7);
        System.out.println("Output: " + result7);
        System.out.println("Expected: 4");
        System.out.println("Explanation: LIS is [0, 1, 2, 3]");
        System.out.println();

        System.out.println("=== All Tests Completed ===");
        System.out.println();
        System.out.println("Algorithm: Binary Search + DP (O(n log n) time complexity)");
        System.out.println("This uses an efficient approach with a dp array that maintains");
        System.out.println("the smallest tail element for all increasing subsequences of length i+1.");
    }
}
