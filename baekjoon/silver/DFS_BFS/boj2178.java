import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2178 {
    static boolean[][] map;
    static boolean[][] visited;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        int row, col ;
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //row col 받기

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new boolean[row][col];
        visited = new boolean[row][col];
        distance = new int[row][col];
        //row만큼 readline해서 map채우자
        distance[0][0]=1;

        //map 값 대입
        for (int i=0;i<row;i++){
            //""으로 delim 설정시 한 글자씩 리턴한다고 함
            String line = br.readLine();
            for (int j=0;j<col;j++){
                char canGo = line.charAt(j);
                if (canGo=='1') map[i][j]=true;

            }
        }

        //map으로 row-1 col-1로 가는 최소 경로
        //bfs사용 -> queue사용
        //visit[row-1][col-1]이 true가 된 순간->최소임

        Queue<int[]> queue=new LinkedList<>();
        //왼 위 오 아래
        int[] pCol ={-1,0,1,0};
        int[] pRow={0,-1,0,1};
        // 0,0넣고 시작
        queue.add(new int[]{0,0});
        // while not empty
        while (!queue.isEmpty()){
            // poll하고 int[][]받음
            int[] nowPos=queue.poll();

            for (int i=0;i<4;i++){
                int nextRow=nowPos[0]+pRow[i];
                int nextCol=nowPos[1]+pCol[i];
                //애초에 인덱스 범위 내인가?
                if (!(nextRow>=0 && nextRow<row &&
                nextCol>=0 && nextCol<col)) continue;


                if (map[nextRow][nextCol] &&
                !visited[nextRow][nextCol]){
                    queue.add(new int[]{nextRow,nextCol});
                    visited[nextRow][nextCol]=true;
                    distance[nextRow][nextCol]=distance[nowPos[0]][nowPos[1]]+1;
                }
            }
            if (distance[row-1][col-1]!=0) break;
        }

        // [0] [1]로 근처 갈 수 있는 경우(앞뒤양옆+not visit)
        // queue추가
        System.out.println(distance[row-1][col-1]);

    }
}
