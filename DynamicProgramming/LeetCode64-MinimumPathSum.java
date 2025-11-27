class Solution {
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if(i==0&&j==0){
                    continue;                            
                }

                int costFromTop=Integer.MAX_VALUE;
                int costFromLeft=Integer.MAX_VALUE;

                if(i>0){
                    costFromTop = dp[i-1][j];
                }

                if(j>0){
                    costFromLeft = dp[i][j-1];
                }
                
                dp[i][j] = grid[i][j] + Math.min(costFromLeft,costFromTop);
            }
        }
            return dp[m-1][n-1];
    }
}