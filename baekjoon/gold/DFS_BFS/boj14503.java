import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14503 {

    // 0 : 위
    // 1 : 오
    // 2 : 아래
    // 3 : 왼
    static int[] dRow={-1,0,1,0};
    static int[] dCol={0,1,0,-1};
    static int map[][];
    public static void main(String[] args) throws IOException {
        //map을 받아놓고 컨트롤해야겠음

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        map=new int[row][col];
        st=new StringTokenizer(br.readLine());
        int rowCur=Integer.parseInt(st.nextToken());
        int colCur=Integer.parseInt(st.nextToken());
        int pos=Integer.parseInt(st.nextToken());

        //map받기
        for (int i=0;i<row;i++){
            st=new StringTokenizer(br.readLine());
            for (int j=0;j<col;j++){
                int temp = Integer.parseInt(st.nextToken());
                map[i][j]=temp;
            }

        }
        int cnt=0;
        boolean canGo =false;

        while(true){
            //1. 현재 칸 청소
            canGo=false;
            if (map[rowCur][colCur]==0) {
                cnt++;
                map[rowCur][colCur]=2;
            }
            //2. 빈칸 탐색
            for (int i=0;i<4;i++){
                // 2-1. 반시계방향(index--)으로 탐색. 있으면 go
                int rowNext=rowCur+dRow[(pos-i+3)%4];
                int colNext=colCur+dCol[(pos-i+3)%4];
                if (map[rowNext][colNext]==0){
                    canGo=true;
                    pos=(pos-i+3)%4;
                    rowCur=rowNext;
                    colCur=colNext;
                    break;
                }


            }
            //3. 없으면 pos유지한채로 후퇴
            if (canGo) continue;
            else{
                //pos+2가 반대방향임
                //pos+2에 해당하는 방향이 1이 아니면 갈 수 있음
                //1이면 break;
                int backRow=rowCur+dRow[(pos+2)%4];
                int backCol=colCur+dCol[(pos+2)%4];
//                System.out.println(backRow+":"+backCol);
                if (map[backRow][backCol]==1){
                    break;
                }
                else {
                    colCur=backCol;
                    rowCur=backRow;
                }
            }

        }

        System.out.println(cnt);
    }
}
