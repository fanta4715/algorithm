class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        // 문제 접근 방식
        //특정 격자에 도착하는 경우의 수
        // = 왼쪽의 격자로 도착하는 경우 + 위의 격자로 도착하는 경우
        // 이를 이용하면 DP로 구현이 가능하다. 
        // puddles에 해당하는 격자에는 -1을 넣어서 구분하자.
        
        // 1st : route[m+1][n+1]로 만들고 초기화하자
        int[][] route = new int[m+1][n+1];
        for (int i=0;i<m+1;i++){
            route[i][0]=0;
        }
        for (int i=0;i<n+1;i++){
            route[0][i]=0;
        }
        
        // 2nd : puddles에 해당하는 route에는 -1을 넣자
        // for : puddles.length
        // route[puddles[i][0]][puddles[i][1]]=-1;
        int len = puddles.length;
        for (int i=0;i<len;i++){
            route[puddles[i][0]][puddles[i][1]]=-1;
        }
        
        // 3rd : DP만들자
        // for : m
        //  for : n
        // if 왼쪽 == -1 : 왼쪽 더하지 않기!
        // else if ~~ : ~~!
        // route[i][j] = route[i-1][j]+route[i][j-1]
        for (int i=1;i<m+1;i++){
            for (int j=1;j<n+1;j++){
                int left=0;
                int up=0;
                
                //puddle인 경우는 -1을 유지시켜야함
                if (route[i][j]==-1) continue;
                
                
                //왼쪽이 puddle이 아닌 경우
                //오른쪽이 puddle이 아닌 경우
                if (route[i-1][j]!=-1) up=route[i-1][j];
                if (route[i][j-1]!=-1) left=route[i][j-1];
                route[i][j] = (left+up)%1_000_000_007;
                if (i==1 && j==1) route[i][j]=1;
            }
        }
        
        // 4th : route[m][n] / 1,000,000,007 리턴하자!        
        
        return route[m][n] % 1_000_000_007  ;
    }
}