import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class boj1260  {
    static Queue<Integer> queue ;
    static StringBuilder sb = new StringBuilder(); // 방문 순서 담는 StringBuilder
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //List<PriorityQueue<Integer>> list = new ArrayList<>();
        // int N, M, V;
        int numV = Integer.parseInt(st.nextToken());
        int numE = Integer.parseInt(st.nextToken());
        int startV = Integer.parseInt(st.nextToken());

        queue=new LinkedList<>();
        queue.add(startV);
        // ***배열생성과 요소 초기화는 다르다
        ArrayList<Integer>[] matrix = new ArrayList[numV+1];

        for (int i=0;i<numV+1;i++){
            matrix[i]=new ArrayList<>();

        }

        //numE만큼 엣지 받음
        for (int i=0;i<numE;i++){
            st= new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());

            //start 인덱스 -> end 넣어두기
            matrix[start].add(end);
            matrix[end].add(start);

        }

        for (int i=0;i<numV+1;i++){
            matrix[i].sort(Comparator.naturalOrder());
        }

        //matrix는 이제 adjacency matrix다.
        boolean visitDFS[] =new boolean[numV+1];
        boolean visitBFS[] =new boolean[numV+1];

        //DFS
        //*** static을 사용해야함 (메인함수가 static이기 떄문??)
        //*** static이 뭔지 어떻게 시작을 하는지 알아보자!
        dfs(matrix,visitDFS,startV);
        sb.append('\n');
        bfs(matrix,visitBFS);
        System.out.println(sb);
        // ***리팩토링이 쉬울까, 처음부터 꼼꼼하게 짜는 게 쉬울까
    }

    //DFS를 "출력" 해주는 함수
    static void dfs(ArrayList<Integer>[] matrix, boolean visitDFS[], int vertex){

        //1. 방문했으면 티를 내야지 (출력 + visit check)
        sb.append(vertex).append(' ');
        visitDFS[vertex]=true;

        //2. 근처로 가야지. 언제까지? 근처 친구가 없을 떄 까지
        //while not empty
        //  poll하고 얻은 int
        //  visit한 적 없으면 dfs
        for (int friend : matrix[vertex]){

            if (!visitDFS[friend]) dfs(matrix,visitDFS,friend);
        }



        //3. vertex의 인접 vertex 다 방문했으면 끝나야지


    }

    static void bfs(ArrayList<Integer>[] matrix, boolean visitBFS[]){
        // 1. vertex 왔다는 거 티내기


        // 2. 인접 vertex queue에 등록
        while (!queue.isEmpty()){
            int vertex= queue.poll();
            visitBFS[vertex]=true;
            sb.append(vertex).append(' ');


            for (int friend : matrix[vertex]){
                if (!visitBFS[friend]) {
                    queue.add(friend);
                    visitBFS[friend]=true;
                }
            }

        }
        // 반복 : queue가 빌 때 까지
    }
}