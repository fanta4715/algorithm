class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 2 0 2 0 2
        // 1 0 1 0 1
        // 1st : n+1크기의 배열 만든다 (인덱스 n까지 사용하기 위함) 
        int[] arr = new int[n+2];
        arr[0]=0;
        arr[n+1]=0;
        //초기화
        for (int i=1;i<=n;i++){
            arr[i]=1;
        }
        
        // 2nd : lost빼준다
        for (int i=0;i<lost.length;i++){
            arr[lost[i]]--;
        }
        
        // 3rd : reserve더해준다.
        for (int i=0;i<reserve.length;i++){
            arr[reserve[i]]++;
        }
        // 4th : 순회하면서, 0인 애들 만나면 앞이 2인지 확인 -> 뒤가 2인지 확인
        for (int i=1;i<=n;i++){
            if (arr[i]==0) {
                if (arr[i-1]==2) {
                    arr[i-1]--;
                    arr[i]++;
                }
                else if (arr[i+1]==2){
                    arr[i+1]--;
                    arr[i]++;
                }
            }
        }
        //  근처에 2가 존재하면 -1시키고 자신은 +1
        // 5th : 배열에서 0이 아닌 개수 cnt++
        int cnt=0;
        for (int i=1;i<=n;i++){
            if (arr[i]!=0) cnt++;
        }
        return cnt;
        
        
    }
}