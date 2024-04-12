/*
 * | 활용 자료구조 | ArrayList, Node Array, Priority Queue
 *
 * | 접근 방법 |
 *  1. 우선순위 조건을 충족하는 Class를 만든다
 *  2. 각 멤버의 최장 스트릭을 저장하는 Class의 배열을 만든다
 *  3. 스트릭 내역을 하나의 String으로 이어 붙인다(이하 history)
 *  4. history를 X 기준으로 tokenize 하되, X의 포함 여부에 따라 두 가지로 진행하여
 *     토큰 수 차이로 전체 실패 횟수를 구한다
 *  5. 토큰(스트릭)들에 대해 아래 과정을 진행한다
 *     5-1. 최장 스트릭은 프리즈로 시작/종료할 수 없으므로 양 끝의 프리즈를 없앤다
 *     5-2. trim된 스트릭을 Priority Queue에 넣는다
 *  6. Priority Queue의 첫 원소가 해당 멤버의 최장 스트릭이 된다
 *  7. 모든 멤버에 대해 3~6의 과정을 진행한다
 *  8. 이름순, Class 우선순위에 따라 정렬을 두 번 진행한다
 *  9. 이전 원소와 우선순위가 같다면 rank를 증가시키지 않는다
 */

package a2404.study.week7;

import java.io.*;
import java.util.*;

public class bj_g4_29737_브실이는_잔디가_좋아 {
    private static class Streak implements Comparable<Streak>{
        String name;
        int length;
        int freeze;
        int start;
        int fail;

        public Streak(String name, int length, int freeze, int start){
            this.name = name;
            this.length = length;
            this.freeze = freeze;
            this.start = start;
            this.fail = -1;
        }

        @Override
        public int compareTo(Streak o){
        if(length > o.length){ return -1; }
        if(length < o.length){ return 1; }
        if(freeze < o.freeze){ return -1; }
        if(freeze > o.freeze){ return 1; }
        if(start < o.start){ return -1; }
        if(start > o.start){ return 1; }
        return Integer.compare(fail, o.fail);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        Streak[] bestArr = new Streak[N];
        for(int n=0; n<N; n++){
            char[][] historyArr = new char[7][W];
            StringBuilder sb = new StringBuilder();
            String history;
            String name = br.readLine();

            for(int d=0; d<7; d++){ historyArr[d] = br.readLine().toCharArray(); }
            for(int w=0; w<W; w++){ for(int d=0; d<7; d++){ sb.append(historyArr[d][w]); } }
            history = sb.toString();

            StringTokenizer withX = new StringTokenizer(history, "X", true);
            StringTokenizer withoutX = new StringTokenizer(history, "X");
            int fail = withX.countTokens() - withoutX.countTokens();

            int nowIdx = 0;
            PriorityQueue<Streak> pq = new PriorityQueue<>();

            while(withX.hasMoreTokens()) {
                String token = withX.nextToken();

                if (token.equals("X")) {
                    nowIdx++;
                    continue;
                }

                int length = token.length();
                int startIdx = 0;
                int endIdx = length;

                for (int i = 0; i < length; i++) {
                    if (token.charAt(i) != 'F') { break; }
                    startIdx++;
                }
                if (startIdx != length) {
                    for (int i = length - 1; i >= 0; i--) {
                        if (token.charAt(i) != 'F') { break; }
                        endIdx--;
                    }
                }
                String trimmedToken = token.substring(startIdx, endIdx);

                int freeze = 0;
                for (int i = 0; i < trimmedToken.length(); i++) {
                    if (trimmedToken.charAt(i) == 'F') { freeze++; }
                }

                pq.offer(new Streak(trimmedToken, endIdx - startIdx - freeze, freeze, nowIdx + startIdx));
                nowIdx += length;
            }
            pq.offer(new Streak(name, 0, 0, 0));
            bestArr[n] = pq.poll();
            bestArr[n].name = name;
            bestArr[n].fail = fail;
        }
        Arrays.sort(bestArr, (o1, o2) -> o1.name.compareTo(o2.name));
        Arrays.sort(bestArr);

        StringBuilder answer = new StringBuilder();
        int rank = 1;
        answer.append(rank).append(". ").append(bestArr[0].name).append("\n");
        for(int n=1; n<N; n++){
            if(bestArr[n-1].compareTo(bestArr[n]) < 0){ rank++; }
            answer.append(rank).append(". ").append(bestArr[n].name).append("\n");
        }
        System.out.println(answer);
    }
}