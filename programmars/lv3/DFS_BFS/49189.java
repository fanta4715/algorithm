import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        
        int distance[] = new int[n+1];
        boolean visit[] = new boolean[n+1];
        ArrayList<Integer> edges[] = new ArrayList[n+1];
        for (int i=1;i<=n;i++){
            edges[i]=new ArrayList<Integer>();
        }
        
        //0. edge를 받음 ArrayList로
        for (int[] vv : edge){
            //vv[0]에 vv[1]넣고
            //vv[1]에 vv[0]넣고
            edges[vv[0]].add(vv[1]);
            edges[vv[1]].add(vv[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visit[1]=true;
        //1. BFS로, 1번 노드부터 시작
        while (!queue.isEmpty()){
            //1-1. 큐에서 팝
            int vertex=queue.poll();
            
            //1-2. 팝한 친구 근처 노드 탐색
            //  distance갱신, visit갱신
            for (int friend : edges[vertex]){
                if (visit[friend]) continue;
                else{
                    distance[friend]=distance[vertex]+1;
                    visit[friend]=true;
                    // System.out.println(friend+"의 거리는"+distance[friend]);
                    queue.add(friend);
                }
            }
            
            
        }
        //그 후, 정렬해서, 맨 뒤 값과 똑같은 개수 확인!
        Arrays.sort(distance);
        int cnt = 1;
        int max=distance[n];
        // System.out.println(distance[2]);
        for (int i=n-1;i>=0;i--){
            if (max==distance[i]) cnt++;
            else break;
        }
        return cnt;
    }
}