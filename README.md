# GradeProgram

git clone https://github.com/codingHack/GradeProgram.git
<hr /> 

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
