function solution(cacheSize, cities) {
    if (cacheSize === 0) return cities.length * 5;

    let cache = [];
    let answer = 0;

    for (let city of cities) {
        city = city.toLowerCase();
        const index = cache.indexOf(city);

        // 이미 있는 경우
        if (index !== -1) {
            cache.splice(index, 1);
            cache.push(city);
            answer += 1;
        } else {
            // 꽉찼는데 없는 경우
            if (cache.length === cacheSize) {
                cache.shift();
            }
            cache.push(city);
            answer += 5;
        }
    }

    return answer;
}
