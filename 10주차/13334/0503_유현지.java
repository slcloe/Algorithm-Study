/*
 * | 활용 자료구조 | PriorityQueue
 *
 * | 접근 방법 |
 *  1. 각 경로를 도착지 기준 오름차순 정렬한다
 *  2. 각 경로의 도착점에 대해 해당 점이 도착지일 때 철로의 시작점을 구한다
 *  3. 경로의 시작점을 우선순위 큐에 넣는다
 *     해당 우선순위 큐는 오름차순 정렬된다
 *  4. 철로가 바뀔 때마다 우선순위 큐의 첫 값(최소값)을 이용해 철로의 시작점을 벗어나는 경로를 제거한다
 *  5. 철로가 바뀔 때마다 우선순위 큐의 사이즈의 최대값을 갱신한다
 */

package a2405;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class d03_bj_g2_13334_철로 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> paths = new PriorityQueue<>((o1, o2)->{if(o1[1]==o2[1]){return o1[0]-o2[0];}return o1[1]-o2[1];});
        for(int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if(h < o){ paths.add(new int[] {h, o}); }
            else{ paths.add(new int[] {o, h}); }
        }
        int D = Integer.parseInt(br.readLine());
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(!paths.isEmpty()){
            int[] now = paths.poll();
            int from = now[0];
            int to = now[1];
            int limit = to-D;
            pq.offer(from);
            while(!pq.isEmpty() && pq.peek() < limit){ pq.poll(); }
            answer = Math.max(answer, pq.size());
        }
        System.out.println(answer);
    }
}
