# 9Gong9

---

> Flow Week 3분반
> 
- 자취생을 위한 Android 기반 식료품 공동 구매 어플리케이션입니다.
- 이메일과 비밀번호를 통해 회원가입 및 로그인할 수 있습니다.
- 카카오 소셜 로그인을 지원합니다.
- 본인이 지정한 동네의 커뮤니티원들과 함께 공동구매를 진행할 수 있습니다.

![1](https://user-images.githubusercontent.com/76472415/184139044-0334dc95-cd45-4bbf-b1ee-b5c7226b2ba0.PNG)
![2](https://user-images.githubusercontent.com/76472415/184139082-b378968f-6640-49fd-86ba-099603f093d3.PNG)

    
---

### A. 개발 팀원

- 숙명여자대학교 소프트웨어융합전공 박예나
- KAIST 전기및전자공학부 박종은
- KAIST 전산학부 김지나

---

### B. 개발 환경

- OS : Android (minSdk: 21, targetSdk: 31)
- Language : Kotlin, NestJs
- IDE : Android Studio
- Target Device : Galaxy S10

---

### C. 어플리케이션 소개

### SIGNUP & LOGIN

![3](https://user-images.githubusercontent.com/76472415/184139124-cbc4609e-f66a-48c5-b039-8aa822b52315.PNG)

    
**Main features**

- Sign up
    - “SIGN UP” 버튼을 통해 id, password기반의 회원가입을 할 수 있습니다.
- Login
    - email과 password를 입력한 후, “LOGIN” 버튼을 통해 앱을 사용할 수 있습니다.
    - 카카오 소셜 로그인을 통해 로그인이 가능합니다.
    - 이전에 로그인이 되어 있었던 상태일 경우 자동 
로그인이 가능합니다.

---

### 상품 리스트

![4](https://user-images.githubusercontent.com/76472415/184139183-eeb65784-5125-4c7a-89cc-2724f827451b.PNG)

**Main features**

- 지역별 상품 필터링
    - 사용자가 시/군/구/동을 선택하면 해당 상품 목록을 필터링하여 제공합니다.
- 카테고리별 상품 필터링
    - 사용자가 선택한 상품 종류에 따라 해당 상품들을 필터링하여 제공합니다.
- 상품 검색창
    - 검색창을 통해 원하는 상품을 검색할 수 있습니다.
    - 상품 검색 시 상품명 자동완성이 가능합니다.

---

### 상품 상세보기

![5](https://user-images.githubusercontent.com/76472415/184139236-e9abc6c6-e55c-47b1-ae61-a638bf6888ef.PNG)
    
**Main features**

- 저장하기
    - 하트 모양의 버튼을 눌러 사용자가 관심 있는 상품들의 목록을 저장할 수 있습니다. 버튼을 한번 더 누른다면 관심 상품 목록에서 취소할 수 있습니다.
    - 관심 목록에서 사용자가 관심있는 상품들의 목록을 확인할 수 있습니다.
- 공구 참여하기
    - 공구 참여하기 버튼을 통해 공구 신청 및 결제를 진행할 수 있습니다.
    - 공동구매에 필요한 최소인원이 모이면 상품의 현재 모집 인원이 초기화됩니다.
    - 공구 참여 중 목록에서 본인이 참여 중인 공동구매 목록들을 확인할 수 있습니다.

---

### 유저 정보

![6](https://user-images.githubusercontent.com/76472415/184139259-64778529-42e6-4441-91fe-7f8ede65fc78.PNG)
    
**Main features**

- 사용자 프로필
    - 사용자 프로필 사진을 불러와서 프로필을 보여줍니다.
- 현재 잔고
    - 현재 잔고 탭을 통해 유저의 포인트 현황을 확인할 수 있습니다.
- 충전하기
    - 충전하기 버튼을 통해 포인트를 충전할 수 있습니다.
- 지난 참여 목록
    - 지난 참여 목록을 통해 사용자가 이전에 참여했던 공구 중 이미 배송이 완료된 상품들을 보여줍니다.

---

### Install

- DB 초기화 시, ```GET “http://domain/item/itemCrawl”``` 요청을 1회 보내야 합니다.

- 위 요청은 서버로 하여금 webscraping을 통해 emart 온라인 쇼핑몰 사이트에서 예시 상품 데이터를 fetch해 본 서비스에 적합한 형태로 가공해, DB에 저장합니다.
