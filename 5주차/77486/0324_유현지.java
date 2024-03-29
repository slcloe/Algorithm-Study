/*
 * | 활용 자료구조 | Node Array, HashMap
 *
 * | 접근 방법 |
 *  1. 이름 -> Info(index, 추천인 이름)을 저장하는 Map을 만든다
 *  2. 발생한 수익 내역에 대해 수익을 낸 사람부터 root(민호)까지 올라가며 수익을 나눈다
 *     2-1. 중간에 더 나눌 수익이 없다면(한자리 수일 경우) 올라가는 것을 멈춘다
 */

package a2403.week5;

import java.util.*;

public class pr_3_77486_다단계_칫솔_판매 {
    static class Info{
        int idx;
        String parent;

        public Info(int idx, String parent){
            this.idx = idx;
            this.parent = parent;
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int lenMember = enroll.length;
        int lenIncome = seller.length;
        int[] answer = new int[lenMember];
        HashMap<String, Info> map = new HashMap<>();

        for(int i=0; i<lenMember; i++){ map.put(enroll[i], new Info(i, referral[i])); }
        for(int i=0; i<lenIncome; i++){
            String member = seller[i];
            int income = amount[i] * 100;
            int parentIncome = income / 10;
            int childIncome = income - parentIncome;
            while(true){
                if(member.equals("-") || childIncome == 0){ break; }
                answer[map.get(member).idx] += childIncome;
                member = map.get(member).parent;
                childIncome = parentIncome;
                parentIncome = childIncome / 10;
                childIncome -= parentIncome;
            }
        }
        return answer;
    }
}
