# SSAFYnity 취준 챌린지 4기

SSAFYnity 동문회원을 위해 주기적으로 운영하는 취준챌린지! <br>
4기에 참여해주신 모든 동문회원분들의 건승을 바랍니다. <br>
<br>
매일 업데이트되는 알고리즘 소식과 문제 풀이 이야기는 [SSAFYnity Discord](https://cafe.naver.com/ssafynity/862)에서 진행해주시면 됩니다.<br>
<br>

## 문제 풀이 안내

### 📝 챌린지 안내
1. 챌린지의 목표 인정은 평일(공휴일 제외)만 진행됩니다.
2. **매일 00시 00분에 다음날 풀이해야할 문제가 Issue에 업로드 됩니다.** <br>
당일 문제 인정은 문제 업로드부터 23:59까지 Pull Request가 만들어져야 인정됩니다. 
1. 문제는 오직 운영하는 인원이 Issue 생성하여 제출됩니다.<br>문제 추천을 하고 싶으시다면, [여기](https://forms.gle/MwiedpiQ3AbVn8na6)로 제출해주세요.
2. Merge 조건은 다음과 같습니다.
   - (참여자 공통) Pull Request를 Open한지 7일을 초과하는 시점
   - (코드리뷰 희망자) 리뷰팀원들의 리뷰를 충분히 받았다고 판단한 경우
3. Merge가 되면, 사용한 branch는 꼭 삭제해주세요.

<br> 

### 💻 챌린지 가이드
> 많은 인원이 참여하여 만든 **권장사항**입니다.<br>
> 가이드와 다르다고 해서, 목표 달성에 불이익이 있진 않으니 걱정마세요.

| 종류 | 가이드 양식 | 설명 |
| :---: | :---: | :--- |
| Branch | <본인이름>_<문제명> | 1주일 동안 최소 5개의 branch가 나와야 합니다. |
| Commit Message | <풀이여부> <문제명> - <실행시간>ms <메모리>mb | - 풀이여부 : (성공 시) solved, (실패 시) unsolved<br>전체 실행시간과 메모리가 나오지 않는 경우, 각 최대 값을 써주세요.<br>추가적으로 남기실 사항은 자유롭게 가능합니다. |
| Folder | /<사이트명>/<문제명> | 1. 사이트명:- 백준 : Baekjoon<br>- 코드트리 : CodeTree<br>- SW Expert Academy : SWEA<br>- 프로그래머스 : Programmers<br>2. 문제명 : 특수기호, 문제번호, 공백 없이 문제 이름 그대로 작성합니다. |
| File | <문제명>_<본인이름>.<확장자> | 특수기호, 문제번호, 공백 없이 문제 이름 그대로 작성합니다.<br>- 아기상어_김싸피.java<br>- 돌아가는팔각의자_김싸피.py<br>- 문자열세개_김싸피.cpp |
| Pull Request 제목 | Commit Message와 동일 | 본문에는 트래킹이 될 수 있도록 꼭 이슈번호를 기재해주세요. |
* 헷갈리지 않도록, 또 잘 정돈될 수 있도록 문제 폴더와 README.md를 제공해드릴 예정입니다.

<br> 

### 💻 챌린지 컨벤션
> 해당 부분은 꼭 지켜야하는 **필수사항**입니다.<br>
> 컨벤션은 지키지 않으면, 미션 달성 체크에 불이익이 생길 수 있습니다.

| 종류 | 컨벤션 양식 | 설명 |
| :---: | :---: | :- |
| Pull Request 본문 | #<문제이슈번호> 를 포함합니다. | 트래킹에 필수적입니다.<br>PR Template에 작성되어 있으니 참고해주세요.|


<br> 

### 🏷️ 라벨 규칙
| 작성 범위 | 등록 라벨 | 설명 | 작성자 |
| :----: | :---: | :---:| :---:|
| Issue | Easy, Hard, Noaml | 책정 문제 난이도 | 챌린지 운영자 `@hadevyi`
| Pull Request | Easy, Hard, Nomal | 체감 문제 난이도 | 참여자 |
| Pull Reuqest | C, C++, Java, JavaScript, Python | 문제 풀이한 언어 | Github Action 자동 배정 |
- Pull Request에는 체감 난도 1개를 선택해주시면 다음 문제 출제 때 참고합니다.


<br>

### 🤖 Github Action 시스템 적용
> 많은 인원이 참여하는 만큼, 필요한 사항에 대해서는 자동화에 힘썼습니다.<br>
> 오류가 발생한다면 취준 챌린자 운영자에게 알려주세요.

| 대상 | 상세 내용 |
|:--:|:--|
|PR Assignees | Pull Request를 요청한 사람에게 자동 할당 |
|PR Label | Pull Request 요청으로 등록된 파일을 확인해 언어별 Label을 자동 할당 |
|PR Reviwers | 챌린지 참여 시, 제출해주신 언어 조사에 따라 자동 랜덤 할당 |

<br> 

### 📃 코드리뷰
- 서로 코드를 확인하고, 풀이를 공유할 수 있도록 만들어진 규칙입니다.
- Pull Request에 Template을 만들어두었습니다! 양식에 맞게 작성해주세요.
- Reviewer는 Github Action을 통해 본인이 풀이하겠다고 한 언어 기반으로 아래 팀대로 할당됩니다.
- 이번 취준 챌린지 운영자에게 Review를 받고 싶다면, PR 본문 혹은 Reviwer에 `@hadevyi`을 태그해주세요.

<br>

### 👥 코드리뷰 팀
| 언어팀 | 인원 |
| :---: | :---|
| 리뷰 미참여 |   리뷰 팀 외 11명   |
| Python 팀 | `@thstmddns` `@jang93042@gmail.com` |
| Java 1팀 | `@gijeogiya` `@abovenormal5023` `@SeLino98` |
| Java 2팀 | `@cheolsoonP` `@Park0bin` `@ganjisriver` |
