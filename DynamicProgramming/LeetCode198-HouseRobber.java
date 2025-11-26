class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n==0) return 0;
        if (n==1) return nums[0];

        int prev=nums[0];
        int current = Math.max(prev,nums[1]);
        for(int i=2; i<n;i++){

            int temp = Math.max(nums[i]+prev, current);
            prev = current;
            current = temp;
        }
        return current;
    }
}