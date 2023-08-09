class Solution {
    public int solution(String name) {
        int answer=0;
        
        int len= name.length();
        int move=len-1;
        
        //이 문제에서 최대로 움직여봤자. len-1임
        // 그럼 len-1보다 적게 움직일 수 있는 지 확인해야함
        // 적게 움직이려면, A가 여러개 있는 부분을 스킵해야함
        // 그럼 i번동안, 그 다음 A가 아닌 곳으로 갈 수 있는 경우를 구해야 함
        // 결국 스킵할 A구간을 찾고, 어디 방향으로 갈 지도 확인하는 것임
        for (int i=0;i<len;i++){
            char c = name.charAt(i);
            
            answer+=Math.min(c-'A','A'-c+26);
            
            //다음에 도착할 단어 위치의 인덱스
            //A라면 스킵할 수 있으므로 건너뜀
            int index=i+1;
            while(index<len && name.charAt(index)=='A')
            {
                index++;
            }
           
            
            //그 다음 index로 도달하기 위해 더 짧은 길 찾기
            //1) 정방향으로 가고, 돌아가기
            move=Math.min(move,i*2+len-index);
            
            //2) 역방향으로 가고, 돌아가기
            move=Math.min(move,(len-index)*2+i);
            
        }
        
        return answer+move;
        
    }
}