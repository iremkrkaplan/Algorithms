public class MatrixChainMultiplication {
    public static void main(String[] args) {
        //A1(5X4),A2(4X6),A3(6X2),A4(2X7)
        int n = 5; 
        int p[] = {5, 4, 6, 2, 7};

        int m[][] = new int[n][n];
        int s[][] = new int[n][n];
        
        int j,min,q;

        for(int d=1; d<n-1 ;d++){
            for(int i=1; i<n-d; i++){
                j = d+i;
                min=Integer.MAX_VALUE;
                for(int k = i; k<j; k++){
                    q = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
                    if(q<min){
                        min=q;
                        s[i][j]=k;
                    }
                }
                m[i][j] = min;
            }
        }
    }
}    