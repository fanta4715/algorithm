import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n=triangle.length;
        
        // 1 : 7
        // 2 : 7+3  7+8
        // 3 : 10+8 10+1 or 15+1    15
       
        
        // 1st : n+1 x n+1크기 배열 max 선언 및 초기화
        int[][] max = new int[n+1][n+1];
        for (int i=0;i<n+1;i++){
            max[0][i]=0;
        }
        for (int i=0;i<n+1;i++){
            max[i][0]=0;
        }
        // 2nd : tri활용해서 table 채우기
           // for : 1 to n+1
                //for : 1 to n+1
        for (int i=1;i<n+1;i++){
            for (int j=1;j<=i;j++){
        // 1번인덱스 : max[n-1][1]+tri[n][1]
        // n번인덱스 : max[n-1][n-1]+tri[n][n]
        // k번인덱스 : max[n-1][k-1]+tri[n][k] vs max[n-1][k]+tri[n][k];
                if (j==1) max[i][j]=max[i-1][1]+triangle[i-1][0];
                else if (j==i) max[i][j]=max[i-1][i-1]+triangle[i-1][i-1];
                else {
                    if (max[i-1][j-1]>max[i-1][j]){
                        max[i][j]=max[i-1][j-1]+triangle[i-1][j-1];
                    }
                    else {
                        // System.out.println((i-1)+":"+(j-1));
                        // System.out.println(triangle[i-1][j-1]);
                        max[i][j]=max[i-1][j]+triangle[i-1][j-1];
                        
                    }
                }
          }
        }
        // 3rd : n번 인덱스의 최대값 구하기
        Arrays.sort(max[n]);
        answer=max[n][n];
        return answer;
    }
}