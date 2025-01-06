function solution(cacheSize, cities) {
    if (cacheSize === 0) return cities.length * 5;

    let cache = [];
    let answer = 0;

    for (let city of cities) {
        city = city.toLowerCase();
        const index = cache.indexOf(city);

        if (index !== -1) {
            // cache hit
            cache.splice(index, 1);
            cache.push(city);
            time += 1;
        } else {
            // cache miss
            if (cache.length >= cacheSize) {
                cache.shift();
            }
            cache.push(city);
            time += 5;
        }
    }

    return answer;
}
