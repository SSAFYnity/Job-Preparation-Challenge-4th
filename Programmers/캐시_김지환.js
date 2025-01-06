function solution(cacheSize, cities) {
  let answer = 0;
  let cache = [];

  if (cacheSize === 0) return cities.length * 5;

  cities.forEach((city) => {
    city = city.toUpperCase();

    const index = cache.indexOf(city);
    if (index !== -1) {
      answer++;
      // cache 재정렬
      cache = [city, ...cache.slice(0, index), ...cache.slice(index + 1, cache.length)];
    } else {
      if (cache.length >= cacheSize) {
        cache.pop();
      }
      cache = [city, ...cache]

      answer += 5;
    }
  })
  return answer;
}