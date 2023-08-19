**요약**

쇼핑몰을 간소화 하여 구현하였습니다.

>계정 - 회원가입, 로그인, 유저 조회, 유저 권한 변경

>상품 - 상품 조건 조회(검색), 상품 상세 조회, 상품 등록, 수정

>주문 - 주문 조회, 주문 생성, 주문 취소

>장바구니 - 장바구니 조회, 장바구니 생성, 장바구니 수량 변경, 장바구니 상품 삭제 

**개발/운영 환경**

 Server는 Spring Boot 를 Client는 React.js를 이용하여 구현하였습니다.
 AWS 프리티어 기간 만료로 인해 Oracle Cloud Service를 이용하고 있습니다.
 Ubuntu 기반의 인스턴스에 배포하였습니다. Database는 Oracle 기반의 Autonomous Data Warehouse를 이용하여 원격으로 사용합니다.

**REST API**

 Rest API을 이용해서 Client와 Server의 데이터를 전송하였습니다.
데이터 요청에 맞게 get, post, put, patch, delete 메소드를 이용하여 구현하였습니다.

**JWT / Spring Security 를 이용한 인증/인가 구현**

 기본적으로 Spring Security를 이용하여 Server측으로 오는 요청을 관리하고 있습니다.
 로그인 요청과 관련하여 UsernamePasswordAuthenticationFilter를 상속한 인증 필터가 JWT Token을 제공하며 BasicAuthenticationFilter를 상속한 인증/인가 필터가 Token을 검증하여 인증/인가를 진행하고 있습니다.

**JPA/QueryDSL**

 ORM을 이용하여 Entity와 Database Table을 매핑하고 있습니다.
 간단한 CURD에 대해서는 JpaRepository 인터페이스에서 Named Query와 NativeQuery를 다소 복잡한 조건에는 QueryDSL을 사용하여 쿼리를 생성하였습니다. 

**Auditing 을 이용한 Entity 공통 속성 설정**

다수의 데이터에서 필요로 하는 데이터의 생성자 / 수정자 , 그리고 생성시간/수정시간을 Auditing을 이용하여 공통 속성으로 설정하였습니다.

**JUnit5을 활용한 테스트 코드 작성**

JUnit5를 활용하여 Entity, Service, Controller 단위 서비스에 대한 테스트를 진행하였습니다.

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
