// 머쓱이의 틱택토
// 머쓱이의 실수
// 1. O를 표시해야할 때 X를 표시(반대 포함)
// 2. 게임 종료 시에도 진행
// 나올 수 있는 게임 결과?1:0

// 1. O===X || O===X+1 이어야 한다
// 2. OOO 와 XXX중 하나만 존재해야 한다
// 3. 한쪽만 이기더라도 개수가 맞아야한다

function check(board, player) {
    // 가로 체크
    for (let i = 0; i < 3; i++) {
        if (board[i][0] === player && board[i][1] === player && board[i][2] === player) {
            return true;
        }
    }
    // 세로 체크
    for (let i = 0; i < 3; i++) {
        if (board[0][i] === player && board[1][i] === player && board[2][i] === player) {
            return true;
        }
    }
    // 대각선 체크
    if (board[0][0] === player && board[1][1] === player && board[2][2] === player) {
        return true;
    }
    if (board[2][0] === player && board[1][1] === player && board[0][2] === player) {
        return true;
    }
    return false;
}

function solution(board) {
    board = board.map(v => v.split(''));

    let cntO = 0;
    let cntX = 0;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (board[i][j] === 'O') cntO++;
            if (board[i][j] === 'X') cntX++;
        }
    }

    let Owin = check(board, 'O');
    let Xwin = check(board, 'X');

    // 개수 먼저 확인
    if (!(cntO === cntX || cntO === cntX + 1)) return 0;

    // 둘다 이길 수는 없음
    if (Owin && Xwin) return 0;

    // O가 이겼을 떄는 O==X
    if (Owin && cntO !== cntX + 1) return 0;

    // X가 이겼을 때는 O==X+1
    if (Xwin && cntO !== cntX) return 0;

    return 1;
}
