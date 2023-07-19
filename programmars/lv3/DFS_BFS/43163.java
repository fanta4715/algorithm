import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        //BFS - Queue - LinkedList
        // 1개를 제외하고 나머지는 다 같은 경우를 파악하는 방법이 필요함
        // 단어 길이나, words 개수로 보아서, for문 두 번 돌려도 충분함
        boolean[] visit=new boolean[words.length];
        //자바는 기본적으로 false, 0이 저장됨 (배열기준)
        //객체는 null저장
        
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> hashmap= new HashMap<>();
        //해쉬맵 전부 0으로 추가
        
        //targetIndex를 찾아야하는데, 이거 함수 없을까?
        //HashMap으로 극복
        for (int i=0;i<words.length;i++){
            hashmap.put(words[i],0);
        }
//        if (hashmap.get(target)==null) return 0;
        if (!hashmap.containsKey(target)) return 0;     
        int n=0;
        queue.add(begin);
        while (!queue.isEmpty()){
            String oneWord = queue.remove();
            //System.out.println(oneWord);
            int pre=0;
            //문제점 : begin은 words에 없음!!
            if (oneWord.equals(begin)) pre=0;
            else pre = hashmap.get(oneWord);
                
            
            //oneWord를 활용해서 for : words
            //갈 수 있는 words[i]를 다 queue에 넣고, visit 올리자(길이)
            for (int i=0;i<words.length;i++){
                if (canGo(oneWord,words[i])&&!visit[i]){
                    queue.add(words[i]);
                    visit[i]=true;
                    //visit 갱신하고
                    hashmap.put(words[i],pre+1);
                }
                
            }
            //target의 visit값이 변했으면 리턴
            if (hashmap.get(target)!=0) return hashmap.get(target);
        }
        return answer;
        
    }
    public boolean canGo(String w1, String w2){
        int len = w1.length();
        int cnt =0;
        for (int i=0;i<len;i++){
            if (w1.charAt(i)!=w2.charAt(i)) cnt++;
        }
        //cnt는 다른 단어의 개수를 의미함. 1개면 true
        if (cnt==1) return true;
        else return false;
    }
}