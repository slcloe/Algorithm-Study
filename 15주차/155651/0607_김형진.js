function solution(book_time) {
    var answer = 0;
    book_time.sort();
    const room = [];

    book_time.forEach((t) => {
        const tmp = t[0].split(":");
        const startTime = tmp[0] * 60 + tmp[1];
        const idx = room.findIndex((e) => e <= startTime);
    });
    return answer;
}