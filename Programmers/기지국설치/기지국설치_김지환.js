function solution(n, stations, w) {
  let answer = 0;
  let position = 1;
  let stationIndex = 0;

  while (position <= n) {
    if (stations[stationIndex] - w <= position && position <= stations[stationIndex] + w) {
      position = stations[stationIndex] + w + 1
      stationIndex++;
    } else {
      answer++;
      position += w * 2 + 1;
    }
  }

  return answer;
}