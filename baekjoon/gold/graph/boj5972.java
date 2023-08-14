import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj5972 {
    public static int[] distance;
    public static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //vertex 개수, edge 개수 받기
        int vertex_num = Integer.parseInt(st.nextToken());
        int edge_num = Integer.parseInt(st.nextToken());

        //distance와 visit선언
        distance=new int[vertex_num+1];
        visit=new boolean[vertex_num+1];

        //vertex만큼 반복하면서 PriorityQueue초기화
        PriorityQueue<int[]>[] edge = new PriorityQueue[vertex_num+1];
        for (int i=0;i<vertex_num+1;i++){
            edge[i]=new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o2[1],o1[1]);
                }
            });
            distance[i]=100_000_000;
        }
        distance[1]=0;
        //edge개수만큼 반복하면서, PriorityQueue에 넣어두기
        for (int i=0;i<edge_num;i++){
            st= new StringTokenizer(br.readLine());
            int vertex1 =Integer.parseInt(st.nextToken());
            int vertex2 =Integer.parseInt(st.nextToken());
            int value =Integer.parseInt(st.nextToken());

            //어떻게 길의 최소값을 구할까
            //PriorityQueue안쓰면 구현 못할 거 같음
            edge[vertex1].add(new int[]{vertex2,value});
            edge[vertex2].add(new int[]{vertex1,value});
        }

        //이제 그대로 dijkstra구현하면 끝!
        //dijk또한 priorityqueue를 통해 구현
        //Integer에는 그냥 vertex번호만 넣을 수가 없네
        //visit을 확정짓는 식으로 하면 될 듯
        PriorityQueue<int[]> pr=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);
            }
        });

        pr.add(new int[]{1,0});
        while (!pr.isEmpty()){
            int min_vertex[] =pr.poll();
            if (visit[min_vertex[0]]==true) continue;
            visit[min_vertex[0]]=true; //true가 되면 distance이제 안바뀜
            distance[min_vertex[0]]=min_vertex[1];
            //근처에 갈 수 있는 범위 탐방
            //재사용하지 않으니, 그냥 뽑아서 범위 초기화하면 됨
            while (!edge[min_vertex[0]].isEmpty()){
                int friend[] = edge[min_vertex[0]].poll();
                if (min_vertex[1]+friend[1]<distance[friend[0]]){
                    distance[friend[0]]=min_vertex[1]+friend[1];
                    pr.add(new int[]{friend[0],distance[friend[0]]});
                }
            }

        }
        System.out.println(distance[vertex_num]);
    }
}
