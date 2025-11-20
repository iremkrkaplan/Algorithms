class Solution {

    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }

    private int maxSubArrayHelper(int[] nums, int left, int right) {
        if(left == right){
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        int leftMax = maxSubArrayHelper(nums,left,mid);
        int rightMax = maxSubArrayHelper(nums,mid+1,right);

        int crossMax = maxCrossingSubArray(nums,left,mid,right);

        return Math.max(Math.max(rightMax, leftMax), crossMax);
    }

    private int maxCrossingSubArray(int[] nums, int left, int mid, int right){
        int leftSum = Integer.MIN_VALUE;
        int sum=0;

        for(int i=mid; i>= left; i--){
            sum += nums[i];
            leftSum=Math.max(sum, leftSum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for(int j = mid+1; j<= right; j++){
            sum += nums[j];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum+rightSum;
    }
}
    
    
