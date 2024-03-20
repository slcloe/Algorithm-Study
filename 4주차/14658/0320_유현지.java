/*
 * | 활용 자료구조 | Node Array
 *
 * | 접근 방법 |
 *  1. 맵의 크기가 500,000*500,000이므로 맵에 대해 탐색하지 않고, 별똥별에 대해 탐색한다
 *  2. 별똥별 배열을 대상으로 크기가 2인 중복 순열을 만든다
 *  3. 첫 번째 별똥별의 x좌표, 두 번째 별똥별의 y좌표를 가져와 한 점을 만든다
 *     중복을 허용하기 때문에 하나의 별똥별의 x, y좌표를 모두 사용해 점을 만들 수 있다
 *  4. 해당 점을 트램펄린의 좌상단 점으로 삼아 L만큼 범위에 속하는 별똥별을 센다
 *
 * | 주의 사항 |
 *  1. 최대한 많이 튕길 수 있는 별똥별의 개수가 아니라 그런 경우에 지구에 떨어지는 별똥별의 개수를 구해야 한다...
 *  2. 한 별똥별을 중심으로 하는 사분면(4개의 트램펄린)을 그리는 방법을 구상했으나 반례가 존재한다
 *     별똥별이 트램펄린의 꼭짓점이 아닌 선 위에 존재할 수 있다
 */

package a2403.week3;

import java.io.*;
import java.util.*;

public class bj_g3_14658_하늘에서_별똥별이_빗발친다 {
    static class Star{
        int x;
        int y;

        public Star(int y, int x){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        st.nextToken();
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Star[] stars = new Star[K];
        int answer = 0;
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[k] = new Star(y, x);
        }

        for(int s1=0; s1<K; s1++){
            for(int s2=0; s2<K; s2++){
                int r = stars[s1].y;
                int c = stars[s2].x;
                int cnt = 0;
                for(int k=0; k<K; k++){
                    if(c <= stars[k].x && stars[k].x <= c+L && r <= stars[k].y && stars[k].y <= r+L){ cnt++; }
                }
                answer = Math.max(answer, cnt);
            }
        }
        System.out.println(K - answer);
    }
}