/*
 * | 활용 알고리즘 | Greedy
 *
 * | 접근 방법 |
 *  1. 처음 뽑은 카드(hand), 2개씩 추가로 뽑는 카드(card)
 *  2. 동전을 최대한 안 쓰면서 round를 늘려야 한다
 *  3. 동전을 안 쓰는 경우 > 하나만 쓰는 경우 > 둘 다 쓰는 경우 순으로 탐색한다
 *  4. 동전을 다 썼는데 가지고 있는 카드로 N+1을 못 만들면 끝낸다
 */

package a2407.study.week21;

import java.util.*;

class pr_3_258707_n_1_카드게임 {
    static int N;

    public int solution(int coin, int[] cards) {
        N = cards.length;
        int answer = 1;
        ArrayDeque<Integer> hand = new ArrayDeque<>();
        ArrayDeque<Integer> card = new ArrayDeque<>();

        for (int n = 0; n < N / 3; n++) {
            hand.add(cards[n]);
        }

        for (int i = N / 3; i < N; i += 2) {
            card.add(cards[i]);
            card.add(cards[i + 1]);

            if (havePair(hand, hand)) {
            } else if (0 < coin && havePair(hand, card)) {
                coin -= 1;
            } else if (1 < coin && havePair(card, card)) {
                coin -= 2;
            } else {
                break;
            }
            answer++;
        }
        return answer;
    }

    static boolean havePair(ArrayDeque<Integer> A, ArrayDeque<Integer> B) {
        for (int num : A) {
            int other = N + 1 - num;
            if (B.contains(other)) {
                A.remove(num);
                B.remove(other);
                return true;
            }
        }
        return false;
    }
}