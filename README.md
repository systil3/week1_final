# ComManager

<aside>
💡 회사 생활의 편의를 높여주는 관리앱!

</aside>

- 회사 구성원들의 정보를 부서별로 관리해줍니다.
- 부서별 Todo list를 통해 해야할 일, 완료된 일을 관리해줍니다.
- 공유 갤러리를 통해서 회사 내 행사 포스터, 함께 놀러간 사진 등의 공유가 가능합니다.

---

# 개발 팀원

- 김융 - KAIST 22학번
- 양혜민 - DGIST 20학번

진행과정 

[Week1](https://www.notion.so/Week1-803b3104bb3c4bb486a638654e3f2b8a?pvs=21)

# 개발 환경

- Language : JAVA
- Database : Room
- OS : Android
- IDE : Android Studio

---

# 어플리케이션 소개

## 어플리케이션 구조

Android Room DB를 이용하여 다음과 같은 스키마를 설계하였습니다.

- Department : 부서 ID(primary), 부서 이름
- Person : ID(primary), 이미지 URI, 사람 이름, 소속 부서, 전화번호
- Image : ID(primary), 이미지 URI, 이미지 이름, 소속 부서(null 허용)
- TodoItem : todoID(primary), task, 소속 부서

---

## 0. Main 화면

### Major features

- 앱 시작 시 휴대폰 외부 저장소에 대한 접근 권한을 묻는 창이 표시됩니다.
- 각각의 Tab으로 연결되는 버튼이 세 개 배치되어 있습니다.
- 홈 버튼을 통해 어느 페이지에서든 main 화면으로 돌아오고, 아이콘 이미지를 클릭하면 뒤로 갈 수 있습니다.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/333e597c-cee6-4463-bdf3-e5a6f5f190c0/Untitled.png)

---

## 1. Tab1 - 회사 구성원 데이터베이스 가져오기

### Major features

- 회사 구성원들의 연락처, 이름, 부서 등의 정보가 담긴 데이터를 확인할 수 있습니다.
- 전화 걸기 및 메세지 보내기가 가능합니다.
- 구성원 검색 기능과 부서별 구성원 확인이 가능합니다.

![캡처_2024_01_03_17_33_03_703.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/2bf3103c-36d5-4c45-b221-cbc9d572a3ed/%EC%BA%A1%EC%B2%98_2024_01_03_17_33_03_703.png)

![캡처_2024_01_03_17_33_06_728.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/c2cf9457-6192-443e-a748-b45445a2afd0/%EC%BA%A1%EC%B2%98_2024_01_03_17_33_06_728.png)

### 상세 구현 설명

- 내장된 데이터베이스에 액세스하여 모든 Person 개체들을 가져옵니다. 만약 데이터베이스가 비어 있다면, 애셋 폴더에 위치한 JSON 파일을 읽어들여 데이터베이스에 추가합니다.
- 가져온 Person 개체들을 RecyclerView를 이용하여 부서명과 함께 전부 표시합니다. 이 때 부서 [ ID → 이름 ] 순서대로 개체들을 정렬해 줍니다.
- 오른쪽에 전화와 문자 아이콘을 추가하여 휴대폰의 전화 / 문자 앱과 연동할 수 있도록 합니다.
- 사람의 이름을 클릭하면 이름과 함께 그 사람의 이미지, 그 사람이 속한 부서, 전화번호가 recyclerview를 통해 표시되도록 합니다.
- 상단의 검색 창을 통해 사람 이름을 검색할 수 있습니다. department 스피너를 추가하여 각 부서별로 부서에 속한 사람만 표시하는 기능도 추가하였습니다.

### 향후 구현 계획

- JSON에서 읽어오는 것뿐만이 아닌 사용자가 직접 데이터를 추가하여 DB에 넣는 기능을 추가할 계획입니다.

## 2. Tab2 - 공동 갤러리

### Major features

- 휴대폰 외부 저장소에서 이미지를 가져와 등록할 수 있습니다.
- 등록된 이미지를 클릭할 경우 확대된 모습을 확인할 수 있습니다.

![캡처_2024_01_03_17_40_48_687.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/54c87159-6331-438e-823b-498aaf4ee45e/%EC%BA%A1%EC%B2%98_2024_01_03_17_40_48_687.png)

### 상세 구현 설명

![캡처_2024_01_03_17_41_07_87.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/515d3367-17e8-4cb4-ad84-afed08aa3c63/%EC%BA%A1%EC%B2%98_2024_01_03_17_41_07_87.png)

- 휴대폰 외부 저장소와 연동하여 이미지를 가져와 GridView에 추가하는 방식으로 갤러리를 구현하였습니다.
- 이미지를 추가할 경우 URI 형식으로 데이터베이스에 저장되며 기본적으로 파일 이름과 동일한 이름을 개체 이름으로 갖습니다.
- 이미지를 클릭하면 해당 이미지로 구성된 fragment로 교체하여 해당 이미지의 확대된 모습을 볼 수 있습니다.

### 향후 구현 계획

- 이미지 클릭 후 삭제 및 태그로 관련된 부서를 추가할 수 있는 기능도 구현하고자 합니다.

## 3. Tab3 - Todo List

### Major features

- 처음 들어가면 부서별 Todo list를 선택할 수 있고, 해당 페이지로 들어가면 부서별 todo list를 작성할 수 있습니다.
- 사용자가 todo list를 추가, 수정, 삭제할 수 있습니다.
- 체크 버튼을 통해 완료된 todo list를 표시할 수 있습니다.

![캡처_2024_01_03_17_58_50_409.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/d652b836-ad88-45b2-b9cd-6e91a132c7e2/%EC%BA%A1%EC%B2%98_2024_01_03_17_58_50_409.png)

![캡처_2024_01_03_18_04_20_936.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/46c1278b-05f2-4c29-8df8-443eb33d5d0b/%EC%BA%A1%EC%B2%98_2024_01_03_18_04_20_936.png)

### 상세 구현 설명

- 각 부서별 페이지로 들어간 후, 데이터베이스에 저장된 부서별 Todo 리스트를 읽어와 표시합니다.
- ADD 버튼으로 새로운 Todo 리스트를 추가할 수도 있습니다. 작성한 Todo 리스트의 경우 일단 뒤로 가기 버튼을 누르면 변동 사항이 모두 저장되어 DB에 입력됩니다.
- 체크박스의 경우 각 Todo 리스트가 완료되었음을 표시하기 위해 UI에 들어가 있긴 하나, 제대로 작동하는 기능을 구현하지 못하였습니다.

### 향후 구현 계획

- 현재 발생하는 오류를 수정할 예정입니다.
    - 현재 뒤로 가기 버튼을 누르고 다시 들어갔을 때 Todo 리스트가 사라지는 오류
    - ADD 버튼을 누를 시 아무런 반응이 없다가 Todo 리스트가 2개 이상 생기는 오류
    - 갑자기 앱이 강제 종료되는 오류
 
### 산출물
https://drive.google.com/drive/folders/1O8HRh9kL_EIIhJhswAaIo8lTOYzJyu4-?usp=sharing
