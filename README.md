# JShop #


### 운영환경 ###

- Orcale Cloud Servcie - Instance  (Ubuntu)
- Orcale Cloud Servcie - ADW  (Oracle Database)



### 적용 기술 ###
>공통

- REST API
- JWT / Spring Security 를 이용한 인증/인가 구현

>Server

- JPA / QueryDSL
- Auditing 을 이용한 Entity 공통 속성 설정
- JUnit5를 활용한 테스트 코드 작성

>Client

- React.js / BootStrap 을 이용한 화면 구현
- CK Editor 를 이용한 컨텐츠 업로드



### 구현 API ###

>계정

`post` **api/join** - 회원가입

`post` **api/login** - 로그인

`get` **api/users?** - 유저 조회 (Admin)

`patch` **api/user/roles** - 유저 권한 변경 (Admin)

>상품

`get`   **api/item/{id}** - 상품 상세 조회

`get`   **api/items?** - 상품 조건 조회 

`post` **api/manager/item** - 상품 등록 (Manager / Admin)

`put`   **api/manager/item/{id}** - 상품 수정 (Manager / Admin)

`get`   **api/manager/items?** - 관리 상품 조건 조회 (Manager / Admin)

>주문

`get` **api/orders?** - 주문 조회

`post` **api/order** - 상품 주문

`patch` **api/order/{orderId}** - 주문 취소

>장바구니

`get` **api/cart** - 장바구니 조회

`post` **api/cart** - 장바구니 생성

`patch` **api/cart** **/{id}** - 장바구니 상품 수량 변경

`delete` **api/cart** **/{id}** - 장바구니 상품 삭제 변경

`post` **api/cart/orders** - 장바구니 상품 주문

**  ? - QueryString을 이용한 조건 추가
