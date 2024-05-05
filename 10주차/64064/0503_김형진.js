function solution(user_id, banned_id) {
    let answerSet = new Set();
    let visited = new Array(user_id.length).fill(false);

    function isMatch(banned, user) {
        if (banned.length !== user.length) return false;
        for (let i = 0; i < banned.length; i++) {
            if (banned[i] !== '*' && banned[i] !== user[i]) return false;
        }
        return true;
    }

    function combs(idx, selected) {
        if (selected.length === banned_id.length) {
            let sortedSelected = selected.slice().sort();
            let key = sortedSelected.join('');
            answerSet.add(key);
            return;
        }

        if (idx >= banned_id.length) return;

        for (let i = 0; i < user_id.length; i++) {
            if (!visited[i] && isMatch(banned_id[idx], user_id[i])) {
                visited[i] = true;
                combs(idx + 1, selected.concat(user_id[i]));
                visited[i] = false;
            }
        }
    }

    combs(0, []);
    return answerSet.size
}
