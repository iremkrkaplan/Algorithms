class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        long[] prefixSum = new long[n+1];
        prefixSum[0] = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += (target==nums[i]) ? 1 : -1; //if elevatorfinal>elevatorbeginning targets>> in this range
            prefixSum[i+1] = sum;
        }

        return mergeSortAndCount(prefixSum,0,n);

    }

    private int mergeSortAndCount(long[] nums,int left, int right) {
        if (left>=right){
            return 0;
        }

        int mid = left+ (right-left)/2;
        int count = 0;

        count +=mergeSortAndCount(nums,left,mid);
        count +=mergeSortAndCount(nums,mid+1,right);
        count +=merge(nums,left,mid,right);

        return count;
    }

    private int merge(long[] nums, int left,int mid,int right){
        int count =0;
        int i =left;
        int j=mid+1;
        int k=mid+1;

        for (i = left; i <= mid; i++) {
            while (k <= right && nums[k] <= nums[i]) {
                k++;
            }
            count += (right - k + 1);
        }
        long[] temp= new long[right-left+1];
        i=left;
        j=mid+1;
        int p = 0;
        while(i<=mid && j<= right){
            if(nums[i] <= nums[j]){
                temp[p++]=nums[i++];
            } else{
                temp[p++]=nums[j++];
             }
        }
        while (i<= mid){
            temp[p++]=nums[i++];
        }
        while (j<=right){
            temp[p++]=nums[j++];
        }
        System.arraycopy(temp,0,nums,left,temp.length);

        return count;

        }
}