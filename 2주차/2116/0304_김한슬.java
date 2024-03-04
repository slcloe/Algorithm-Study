package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 1번 주사위의 top 결정
 * 2. n번째 top == n+1 bottom index 구하기 : n 회 진행
 * 3. tops 배열 : 각 주사위의 bottom index 저장되어있음.
 *    bottom & top 에 해당되지 않은 주사위 면 중 최대값 찾고 그의 합을 구함
 */

public class week2_2116_0304_김한슬 {

    static int[] sides = {5, 3, 4, 1, 2, 0};
    static int N;
    static ArrayList<Integer>[] dices;
    static int result = Integer.MIN_VALUE;

    static int calDice(int top){
        int[] tops = new int[N];
        int sumOfSide = 0;

        tops[0] = top;
        for (int i = 0; i < N - 1; i++) {
            int topNext = dices[i + 1].indexOf(dices[i].get(tops[i]));
            tops[i + 1] = sides[topNext];
        }

        for (int i = 0; i < N; i++)
            sumOfSide += calMaxOneDice(i, tops[i]);
        return sumOfSide;
    }

    static int calMaxOneDice(int diceNum, int topIndex){ // 주사위 4개의 옆면 중 가장 큰 index 를 구함.
        int top = dices[diceNum].get(topIndex);
        int bottom = dices[diceNum].get(sides[topIndex]);
        int result = 6;
        while (top == result || bottom == result)
            result--;
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dices = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            dices[i] = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                dices[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < 6; i++) {
            int tmp = calDice(i);
            result = Math.max(result, tmp);
        }

        System.out.println(result);
    }
}
