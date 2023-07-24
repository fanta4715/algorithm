import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj2667 {
    static boolean[][] map;
    static boolean[][] visit;
    static int n;
    //왼 위 오 아래
    static int[] pRow = {0,-1,0,1};
    static int[] pCol = {-1,0,1,0};

    public static void main(String[] args) throws IOException {
        //dfs를 통해서 트래킹을 시도, 언제까지? nxn까지
        //그래서 dfs 한 번 시도할 때마다 결과값을 ArrayList에 인덱스로 저장

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //n받기
        n = Integer.parseInt(br.readLine());
        map=new boolean[n][n];
        visit=new boolean[n][n];
        int[] cntHouse = new int[n*n];
        for (int i=0;i<n*n;i++){
            cntHouse[i]=n*n+1;
        }

        //n*n만큼 배열 받기
        //for : n
        // 한 줄 받고, char로 끊어서 다시 받기
        for (int i=0;i<n;i++){
            String line = br.readLine();
            for (int j=0;j<n;j++){
                char c = line.charAt(j);
                if (c=='1') map[i][j]=true;
            }
        }
        int cnt=0;
        //for 0 : n
        //  for 0 : n
        //      if ( i j not visit ) dfs & cnt++
        //      else continue

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (!visit[i][j] && map[i][j]){
                    cntHouse[cnt++]=dfs(i,j);


                }
            }
        }
        Arrays.sort(cntHouse);
        System.out.println(cnt);
       for (int i=0;i<cnt;i++){
           System.out.println(cntHouse[i]);
       }
    }
    static boolean outOfIndex(int row, int col){
        if (row<0 || row>=n ||
                col<0 || col>=n) return true;
        else return false;
    }
    static int dfs(int row, int col){

        //1. 온 거 티내기
        visit[row][col]=true;
        int cnt=1;
        //2. 친척 돌면서 가능하면 dfs 안 되면 스킵
        for (int i=0;i<4;i++){
            int nextCol = col+pCol[i];
            int nextRow = row+pRow[i];

            //인덱스 범위 내인지 확인
            if (!outOfIndex(nextRow,nextCol)){
                //visit도 아니고, map도 1이면 dfs실행
                if (!visit[nextRow][nextCol]&&
                map[nextRow][nextCol]) {
                    cnt+=dfs(nextRow,nextCol);
                }
            }
        }
        return cnt;
    }
}
