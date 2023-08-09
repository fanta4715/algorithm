class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        //위상정렬? No
        //Floyd-Warshall Yes!
        //모든 경기결과가 있다고 해보자. 그러면 몇 개의 경기가 있나?
        //10개의 결과 5C2
        //1등 : 나가는 엣지 n-1개, 들어오는 엣지 0개
        //2등 : 나가는 엣지 n-2개, 들어오는 엣지 1개
        //3등 : 나가는 엣지 n-3개, 들어오는 엣지 2개
        //4등 : 나가는 엣지 n-4개, 들어오는 엣지 3개
        //4등에게서 나가는 엣지 -> 최소 5등 밑
        
        //정확히 안다는 것은?
        // 2번이 1,3,4에게 짐 + 5번하고 안 붙음
        // 5번이 1,4에게 졌지만 3번은 이김!
        // 3번이 이긴 모든 애들은 5번 밑임
        // 
        // >> 2번이 꼴등임을 의미하는 것임
        //이게 정확한거냐? 모르겠음
        // 0 W ? ? W
        // L 0 L L ?
        // ? W 0 ? L
        // ? W ? 0 W
        // L ? W L 0
        
        //2와 5의 관계를 알고 싶음. 승패모름
        //2가 1을 이기고, 1이 5를 이김 -> 알 수 있음
        //2가 1에 지고, 1이 5에 짐 -> 알 수 있음
        
        //2가 1을 이기고, 1이 5에 짐 -> 모름
        //2가 1에 지고, 1이 5를 이김 -> 모름
        
        //win  = 1
        //lose = -1
        //iDontKnow = 0
        
        
        // 0 W W ? W
        // L 0 L L L
        // ? W 0 L L
        // ? W W 0 W
        // L W W L 0
        
        //0. n+1 x n+1 table 생성 및 results 반영
        int[][] table = new int[n+1][n+1];
        for (int[] result : results){
            // 4 3이라면 , 4 3에는 1을, 3 4에는 -1을
            int winner=result[0];
            int loser = result[1];
            table[winner][loser]=1;
            table[loser][winner]=-1;
        }
        
        //1. Floyd-Warshall을 통해 n번(1 to n) 반복
        //  1.1 3중 for문 +  k i j 
        //          i to k == k to j -> i to j 판별가능
        //          둘다 1이라면, i가 winner
        //          둘다 -1이라면, i가 loser
        //          그 외라면 continue;
        for (int k=1;k<=n;k++){
            for (int i=1;i<=n;i++){
                for (int j=1;j<=n;j++){
                    if (table[i][k]==table[k][j] 
                       && table[i][k]!=0
                       && table[k][j]!=0) {
                        //둘다 1이라면, i가 winner
                        if (table[i][k]==1) {
                            table[i][j]=1;
                            table[j][i]=-1;
                        }
                        //둘다 -1이라면, i가 loser
                        else{
                            table[i][j]=-1;
                            table[j][i]=1;
                        }
                    }
                }
            }
        }
        
        //2. 같은 열에 경기 결과를 다 알 수 있다면, answer++;
        for (int i=1;i<=n;i++){
            int cnt=0;
            for (int j=1;j<=n;j++){
                if (table[i][j]!=0) cnt++;
            }
            if (cnt==n-1) answer++;
        }
        return answer;
    }
}