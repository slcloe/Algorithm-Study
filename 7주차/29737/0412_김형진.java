package week7;

import java.util.*;
import java.io.*;

public class BOJ_29737 {
    static int N,W;
    static char[][] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Human> rank = new PriorityQueue<>();
        PriorityQueue<Human> tmp = new PriorityQueue<>();

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new char[7][W];
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            Human current = new Human(name,0,0,0,0);
            for (int j = 0; j < 7; j++) {
                String line = br.readLine();
                for (int k = 0; k < W; k++) {
                    board[j][k] = line.charAt(k);
                }
            }
            int streak = 0;
            int freeze = 0;
            int start = 0;
            int fail = 0;
            char before = ' ';
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < 7; k++) {
                    char cur = board[k][j];
                    switch(cur){
                        case 'O':
                            // Before
                            // O : 스트릭 ++
                            // X : 스트릭들 일괄 저장, 초기화
                            // F : 스트릭 그대로, 초기화 x
                            // 스트릭이 직전에 끊겼을 때
                            if(before == 'X'){
//                                tmp.offer(new Human(name,streak,freeze,start,fail));
                                streak = 0; freeze= 0; start= 0;
                            }
                            if(streak == 0){
                                start = j*7 + k +1 ;
                            }
                            streak ++;
                            break;
                        case 'F' :
                            if(streak > 0) freeze ++;
                            break;
                        case 'X' :
                            fail ++;
                            if(before =='F') streak--;
                            break;
                    }
                    before = cur;
                }
            }
            if(before == 'F') freeze = 0;
            tmp.offer(new Human(name,streak,freeze,start,fail));

            rank.offer(tmp.poll());
            tmp.clear();
        }
        int ranknum = 0;
        Human before = null;
        while(!rank.isEmpty()){
            Human h = rank.poll();
            if(!(before != null && before.streak == h.streak && before.freeze == h.freeze && before.fail == h.fail && before.start == h.start)){
                ranknum ++ ;
            }
            System.out.print(ranknum+". " + h.name+"\n");
            before = h;
        }
    }
}
class Human implements Comparable<Human>{
    String name;
    int streak;
    int freeze;
    int start;
    int fail;

    public Human(String name, int streak, int freeze, int start, int fail) {
        this.name = name;
        this.streak = streak;
        this.freeze = freeze;
        this.start = start;
        this.fail = fail;
    }

    @Override
    public int compareTo(Human h) {
        if (this.streak != h.streak) {
            return h.streak - this.streak;
        } else if (this.freeze != h.freeze) {
            return this.freeze - h.freeze;
        } else if (this.start != h.start) {
            return this.start - h.start;
        } else if (this.fail != h.fail) {
            return this.fail - h.fail;
        } else {
            return this.name.compareTo(h.name);
        }
    }


}