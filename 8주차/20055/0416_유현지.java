/*
 * | 활용 자료구조 | ArrayList
 *
 * | 접근 방법 |
 *  1. 매 수행마다 컨베이어 벨트의 시작점, 끝점을 계산해서 구한다
 *  2. ArrayList에 저장해둔 로봇들을 움직인다
 *     2-1. 이동할 칸의 내구도가 0이거나 다른 로봇이 있다면 건너뛴다
 *     2-1. 움직일 위치가 내리는 위치라면 로봇을 삭제한다
 *     2-2. 움직일 위치가 내리는 위치가 아니라면 로봇이 있는 칸을 갱신한다
 *  3. 올리는 위치의 내구도가 남아 있고 비어있다면 로봇을 올린다
 *  4. 로봇을 움직일 때, 새로 올릴 때마다 내구도가 0인지 체크하며 카운팅한다
 *  5. 카운팅한 수가 K가 될 때까지 수행한다
 */

package a2404.study.week8;

import java.io.*;
import java.util.*;

public class bj_g5_20055_컨베이어_벨트_위의_로봇 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] belts = new int[2*N];
        boolean[] isRobot = new boolean[2*N];

        st = new StringTokenizer(br.readLine());
        for(int n=0; n<2*N; n++){ belts[n] = Integer.parseInt(st.nextToken()); }

        int idxOn = 0;
        int cntZero = 0;
        int answer = 0;
        List<Integer> robots = new ArrayList<>();

        while(cntZero < K){
            answer++;

            // rotate belt
            idxOn = (2*N + idxOn - 1) % (2*N);
            int idxOff = (idxOn + N - 1) % (2*N);
            if(isRobot[idxOff]){
                robots.remove(new Integer(idxOff));
                isRobot[idxOff] = false;
            }

            // move robots
            for(int i=0; i<robots.size(); i++){
                int idxNow = robots.get(i);
                int idxNxt = (idxNow + 1) % (2*N);
                if(belts[idxNxt] > 0 && !isRobot[idxNxt]) {
                    isRobot[idxNow] = false;
                    if(--belts[idxNxt] == 0){ cntZero++; }

                    if(idxNxt == idxOff){
                        robots.remove(new Integer(idxNow));
                        i--;
                    }
                    else{
                        robots.set(i, idxNxt);
                        isRobot[idxNxt] = true;
                    }
                }
            }

            // put robot
            if(belts[idxOn] > 0 && !isRobot[idxOn]) {
                robots.add(idxOn);
                isRobot[idxOn] = true;
                if(--belts[idxOn] == 0){ cntZero++; }
            }
        }
        System.out.println(answer);
    }
}
