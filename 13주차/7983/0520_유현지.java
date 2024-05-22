/*
 * | 활용 자료구조 | PriorityQueue
 *
 * | 접근 방법 |
 *  1. 과제들을 마감 기한 기준 내림차순이 되게 우선순위 큐에 넣는다
 *  2. idx를 최대값으로 설정한다
 *  3. 정렬된 과제에 대해 아래의 과정을 수행한다
 *     3-1. 과제 마감 기한이 idx 이하라면 idx를 (과제 마감 기한-과제 소요 시간)으로 갱신한다
 *     3-1. 과제 마감 기한이 idx 초과라면 idx는 위의 경우에서 (과제 마감 기한-기존 idx)를 더 뺀다
 *          이미 수행 중인 과제가 있는 경우로, 현재 수행 중인 과제가 없을 시점으로 이동해야 한다
 *  4. 모든 과제들에 대해 위의 과정을 수행한 뒤 idx의 값이 답이 된다
 */

package a2405.study.week13;

import java.io.*;
import java.util.*;

public class bj_g5_7983_내일_할거야 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o2[1]-o1[1]);
        for(int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            pq.offer(new int[] {D, T});
        }
        int idx = Integer.MAX_VALUE;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int start = now[0];
            int end = now[1];
            if(end <= idx){ idx = end-start; }
            else{ idx = end - start - (end - idx); }
        }
        System.out.println(idx);
    }
}