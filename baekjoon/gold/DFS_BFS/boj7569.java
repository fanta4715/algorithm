import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj7569 {
    static int[][][] box;
    static boolean[][][] visit;
    static int[][][] distance;
    static int col, row, h,numTotal,numCur;
    static Queue<int[]> queue=new LinkedList<>();
    //상 왼 위 오 아래 하
    static int pH[] = {1,0,0,0,0,-1};
    static int pRow[] = {0,0,-1,0,1,0};
    static int pCol[] = {0,-1,0,1,0,0};
    public static void main(String[] args) throws IOException {
        // dfs로 SCC를 매 격자에서 1일때마다 시행할 것임(방문x가정)
        // SCC에 편입되는 0이 생길 때 마다 cnt+1해줌
        // cnt==0의 개수 일 때, 종료
        // 0의 개수가 0이라면 0출력
        // 개수가 cnt와 맞지 않으면 -1 출력
        
        //>> bfs로 해야 함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //배열에서는 h row col 순으로 접근해야함
        col= Integer.parseInt(st.nextToken());
        row= Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        box = new int[h][row][col];
        distance= new int[h][row][col];
        visit = new boolean[h][row][col];
        numTotal=0;
        numCur=0; //unripe to ripe

        boolean allRipen=true;
        // for : h
        //  for : row
        //      for : col
        // 입력을 받아서 box채우기
        for (int i=0;i<h;i++){
            for (int j=0;j<row;j++){
                st = new StringTokenizer(br.readLine());
                for (int k=0;k<col;k++){
                    box[i][j][k]=Integer.parseInt(st.nextToken());
                    if (box[i][j][k]==1) {
                        queue.add(new int[]{i,j,k});
                        visit[i][j][k]=true;
                    }
                    else if (box[i][j][k]==0){
                        allRipen=false;
                    }
                }
            }
        }

        //토마토 모두 익어있음
        if (allRipen) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()){
            int[] index=queue.poll();

            //index근처에 unripen이 있는 지 확인하자
            for (int i=0;i<6;i++){
                int nextH=index[0]+pH[i];
                int nextRow=index[1]+pRow[i];
                int nextCol=index[2]+pCol[i];

                if (outOfBound(nextH,nextRow,nextCol)) continue;
                if (!visit[nextH][nextRow][nextCol]
                        &&box[nextH][nextRow][nextCol]==0){
                    visit[nextH][nextRow][nextCol]=true;
                    distance[nextH][nextRow][nextCol]=
                            distance[index[0]][index[1]][index[2]]+1;
                    queue.add(new int[]{nextH,nextRow,nextCol});
                }
            }
        }
        // 여전히 0이 있는가? + max값 tracking
        int max = distance[0][0][0];
        for (int i=0;i<h;i++) {
            for (int j = 0; j < row; j++) {
                for (int k=0;k<col;k++){
                    if (distance[i][j][k]>max){
                        max=distance[i][j][k];
                    }
                    if (box[i][j][k]==0 && !visit[i][j][k]){
                        //System.out.println(i+":"+j+":"+k);
                        System.out.println(-1);
                        return;
                    }


                }

            }
        }
        System.out.println(max);
    }

    static boolean outOfBound(int i,int j,int k){
        if (i<0 || i>=h ||
        j<0 || j>=row ||
        k<0 || k>=col) return true;
        else return false;
    }
}
