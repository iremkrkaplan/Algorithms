class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if(n==0) return 0;
        if(n==1) return nums[0];

        int robRange1 = RobRange(nums,0,n-2);
        int robRange2 = RobRange(nums,1,n-1);

        return Math.max(robRange1,robRange2);        

    }
    private int RobRange(int nums[], int start, int end){
        int previous = 0;
        int current=0;

        for(int i=start;i<=end;i++){
            int temp = Math.max(previous+nums[i], current);
            
            previous = current;
            current = temp;
        }
        return current;
    }
    }