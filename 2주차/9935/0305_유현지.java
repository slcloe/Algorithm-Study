package a2403.week1;

/*
 * | 활용 자료구조 | Stack, ArrayList
 * | 접근 방법 |
 *  폭발 문자열은 모두 다른 문자로 구성되어 있다
 *      => 폭발 문자열의 트리거가 되는 문자는 중복되지 않는다
 *          => 문자열 탐색 중 폭발 문자열의 트리거가 되는 문자가 나온다면 즉시 폭발 가능 여부를 판단한다
 * | 해결 단계 |
 *  1. 문자열은 원래 순서대로, 폭발 문자열은 역순으로 저장
 *  2. 문자열의 문자를 순서대로 스택에 push
 *  3. 스택의 top이 폭발 문자열의 마지막 문자일 경우(= 현 시점에서 폭발 가능성 판단 필요한 시점)
 *     폭발 문자열을 역순으로 탐색했을 때 각각의 문자들이 스택에서 pop한 문자와 동일한지 확인
 *  4-1. 폭발 문자열 전체가 포함 되어 있다면 문자열의 다음 문자부터 2~3 과정을 반복
 *  4-2. 폭발 문자열 전체가 포함 되어 있지 않다면 스택에 지금까지 pop한 문자들을 다시 push
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class bj_g4_9935_문자열_폭발 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] target = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();
        for(int i=0; i<bomb.length/2; i++){
            char tmp = bomb[i];
            bomb[i] = bomb[bomb.length-1-i];
            bomb[bomb.length-1-i] = tmp;
        }
        char trigger = bomb[0];
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for(char cTarget: target){
            stack.offerLast(cTarget);
            if(cTarget == trigger){
                ArrayList<Character> save = new ArrayList<>();
                for(char cBomb: bomb){
                    if(stack.isEmpty() || stack.peekLast() != cBomb){
                        for(char cSave: save){ stack.offerLast(cSave); }
                        break;
                    }
                    save.add(0, stack.pollLast());
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("FRULA");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(char cStack: stack){ sb.append(cStack); }
        System.out.println(sb);
    }
}
