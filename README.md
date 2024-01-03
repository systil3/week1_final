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

![KakaoTalk_20240103_203106309](https://github.com/systil3/week1_final/assets/70615100/c74f6638-845e-46bd-afbf-9d4dedd0c03d)


## 1. Tab1 - 회사 구성원 데이터베이스 가져오기

### Major features

- 회사 구성원들의 연락처, 이름, 부서 등의 정보가 담긴 데이터를 확인할 수 있습니다.
- 전화 걸기 및 메세지 보내기가 가능합니다.
- 구성원 검색 기능과 부서별 구성원 확인이 가능합니다.

![KakaoTalk_![KakaoTalk_20240103_203106309_02](https://github.com/systil3/week1_final/assets/70615100/58418eb4-b677-4018-8573-ca4293dd340c)
20240103_203106309_01](https://github.com/systil3/week1_final/assets/70615100/55387979-385b-4b4e-bcb1-9096dfa99aea)

![Uploading KakaoTalk_20240103_203106309_02.png…]()


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

![KakaoTalk_20240103_203106309_04](https://github.com/systil3/week1_final/assets/70615100/1d158732-19bc-43a0-859d-d2f9a5c910fc)
![KakaoTalk_20240103_203106309_05](https://github.com/systil3/week1_final/assets/70615100/6048f1ce-e571-4d0b-b540-c74dc7b75b8b)


### 상세 구현 설명


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

![KakaoTalk_20240103_203106309_07](https://github.com/systil3/week1_final/assets/70615100/7ff5f1cd-b3b7-49e0-adbd-8f421f61162e)
![KakaoTalk_20240103_203106309_12](https://github.com/systil3/week1_final/assets/70615100/e46dbd7e-2338-492e-ac3b-2c8c48715575)

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
