class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1; //base case

        for (int i = 0 ; i < m; i++){ //'i' represents the ROW (vertical position)
            for(int j=0; j<n; j++){ // 'j' represents the COLUMN (horizontal position)
                if(i==0 && j ==0){
                    continue;
                }

                int val = 0;
                if(i>0){ // Check if there is a row above (avoid IndexOutOfBounds)
                    val += dp[i-1][j]; // Grab value from the cell directly ABOVE
                }
                if(j>0){ // Check if there is a column to the left
                    val += dp[i][j-1]; // Grab value from the cell directly to the LEFT
                }
                dp[i][j]=val;
            }
        }
        return dp[m-1][n-1];
    }
}
