# GradeProgram

#본 프로젝트 목적은 아래와 같은 특징을 지니는 출석 및 성적 처리 프로그램이다.

• 1학기(16주)의 출석을 체크할 수 있다.(출결, 지각, 결석)<br>
• 학생의 성적 등급을 결정할 수 있다.<br>
  (출석점수, 중간 및 기말시험 점수, 과제 점수, 퀴즈 점수, 발표 점수, 보고서 점수, 기타 점수로 판단)<br>
• 학생 성적에 대한 점수를 입력할 수 있다.<br>
• 각 성적의 등급의 비율은 설정/변경할 수 있다.<br>
• 학생 성적의 총점에 대한 평균을 계산할 수 있으며, 총점에 관련하여 점수분포 그래프를 나타낼 수 있다.<br>
• 입력된 데이터 및 처리결과를 모두 DB에 저장하고 다시 불러올 수 있다.<br>

#시스템구성<br>
•

#GUI구성<br>
•

#기본 기능 외 추가구성<br>
•

<hr /> 

<h2> git 사용법 </h2>

git clone https://github.com/codingHack/GradeProgram.git <br>

1. 자신의 branch 생성 <br>
git branch 'BranchName'

2. 원격 저장소에 자신의 branch 올리기<br>
git push --set-upstream origin 'BranchName'

3. develop branch 이동 <br>
git checkout develop

4. 최신 정보 확인 및 원격저장소(develop)에서 내 로컬(develop branch)로 최신 받아오기<br>
git fetch -> git pull origin develop

5. 내 브렌치에서 로컬(develop)에 merge <br>
git checkout develop 이후
git merge 'BranchName'

6. 브렌치 삭제(하고싶은 경우만) <br> 
git branch -d 'BranchName'
<hr /> 
