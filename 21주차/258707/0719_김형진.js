function solution(coin, cards) {
    let answer = 1;
    N = cards.length;
    let hands = new Set();
    let draw = new Set();

    for (let i = 0; i < Math.floor(N / 3); i++) {
        hands.add(cards[i]);
    }

    let nextIdx = Math.floor(N / 3);

    while (nextIdx < N) {
        for (let i = 0; i < 2; i++) {
            draw.add(cards[nextIdx++]);
        }

        if (hands.size >= 2 && nextRound(hands, new Set(hands))) {
            answer++;
        } else if (hands.size >= 1 && coin >= 1 && nextRound(hands, draw)) {
            answer++;
            coin--;
        } else if (coin >= 2 && nextRound(draw, new Set(draw))) {
            answer++;
            coin -= 2;
        } else {
            break;
        }
    }
    return answer;
}
const nextRound =(s, matching)=> {
    for (let card of s) {
        let matchingCard = (N + 1) - card;

        if (matching.has(matchingCard)) {
            s.delete(card);
            matching.delete(matchingCard);
            return true;
        }
    }
    return false;
}
