import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20055 {
    static int N;
    static int index;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //N K받기
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        int belt[] = new int[2*N];
        boolean robot[] = new boolean[2*N];


        //2*N 받기
        for (int i=0;i<2*N;i++){
            belt[i]=Integer.parseInt(st.nextToken());

        }

//        //




//        4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다

        // index를 통해서 옮긴 위치를 파악하자.
        // 로봇은 0+index에만 올릴 수 있다.
        index=0;
        int answer = 0;
        int cnt=0;
        while (cnt<K){
            // 1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            index=(index+1)%(2*N);
            answer++;
            // 2.가장 먼저 벨트에 올라간 로봇부터,
            // 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            // 만약 이동할 수 없다면 가만히 있는다.
            //         -로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며,
            //         그 칸의 내구도가 1 이상 남아 있어야 한다.
            if (robot[beltOfIndex(N-1)])robot[beltOfIndex(N-1)]=false;
            //for N-2번 위치 to 0번 위치
            for (int i=N-2;i>=0;i--){
                //이동가능한가?
                if (robot[beltOfIndex(i)] && !robot[beltOfIndex(i+1)] && belt[beltOfIndex(i+1)]>=1){
                    //YES 이동
                    robot[beltOfIndex(i)]=false;
                    robot[beltOfIndex(i+1)]=true;
                    belt[beltOfIndex(i+1)]--;
                    if (belt[beltOfIndex(i+1)]==0) {
                        cnt++;
                        if (cnt>=K) break;
                    }
                    if (i==N-2) {
                        robot[beltOfIndex(i+1)]=false;
                    }
                }

                //NO 가만히


            }

            if (cnt>=K) break;
            // 3.올리는 위치에 있는 칸의 내구도가 0이 아니면
            // 올리는 위치에 로봇을 올린다.
            if (belt[beltOfIndex(0)]!=0){
                robot[beltOfIndex(0)]=true;
                belt[beltOfIndex(0)]--;
                if (belt[beltOfIndex(0)]==0) cnt++;
            }

            if (cnt>=K) break;

        }
        System.out.println(answer);
        //로봇위치는 0~N-1로 한다.
        //로봇의 위치가 N-1로 가면 뿌서진다.


    }

    static public int beltOfIndex(int now){
        // K번의 위치 :
        return (now-index+2*N)%(2*N);
    }
}
