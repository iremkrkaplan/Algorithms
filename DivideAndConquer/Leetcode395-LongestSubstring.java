class Solution {

    public int longestSubstring(String s, int k) {
        return longestSubstringHelper(s, 0, s.length()-1, k);
    }

    public int longestSubstringHelper(String s,int left,int right,int k){
        if(left>right){
            return 0;
        }

        if(left==right){
            //we know that freq is 1 for just 1 letter
            return (k<=1) ? 1 : 0;
        }

        int[] freq = new int[26];
        for(int i=left;i<=right;i++){
            freq[s.charAt(i) - 'a']++;
        }

        boolean allValid=true;

        for(int i=0;i<26;i++){
            if(freq[i]>0&&freq[i]<k){
                allValid=false;
                break;
            }
        }

        if (allValid){
            return right - left + 1;
        }

        int splitIndex = left;
        while(splitIndex<=right){
            char ch = s.charAt(splitIndex);
            int charFreq = freq[ch- 'a'];

            if (charFreq > 0 && charFreq < k){
                break;
            }
            splitIndex++;
        }

        int leftResult = longestSubstringHelper(s,left,splitIndex-1,k);
        int rightResult = longestSubstringHelper(s,splitIndex+1,right,k);

        return Math.max(leftResult,rightResult);

    }

}