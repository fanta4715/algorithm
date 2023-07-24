import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj2606 {
    //dfs
    //ArrayList<>[]를 사용하자
    static ArrayList<Integer>[] computer;
    static boolean[] visit;
    //dfs돌려서 root[]에 숫자 박아놓고
    //숫자가 1이 몇 개인지 카운팅 후 출력하면 됨
    public static void main(String[] args) throws IOException {
        int cnt,comNum,netNum;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        comNum=Integer.parseInt(br.readLine());
        netNum=Integer.parseInt(br.readLine());

        //1번 컴퓨터 -> 1번 인덱스 대응을 위함
        visit=new boolean[comNum+1];
        computer=new ArrayList[comNum+1];

        for (int i=0;i<comNum+1;i++){
            computer[i]=new ArrayList<>();
        }

        StringTokenizer st;
        for (int i=0;i<netNum;i++){
            st=new StringTokenizer(br.readLine());
            int com1=Integer.parseInt(st.nextToken());
            int com2=Integer.parseInt(st.nextToken());

            computer[com1].add(com2);
            computer[com2].add(com1);
        }

        //dfs 진행
        System.out.println(dfs(0,1)-1);

    }
    static int dfs(int cnt, int com){
        //cnt는 SCC의 크기를 의미
        //com은 vertex 번호를 의미

        //1. 방문 티내기
        cnt++;
        visit[com]=true;

        //2. 인접 컴퓨터 조사
        for (int friend : computer[com]){
            if (!visit[friend]){
                cnt=dfs(cnt,friend);
            }
        }



        return cnt;
    }
}
