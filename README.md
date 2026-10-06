# Spring Boot 백엔드 과제 API 명세서 및 테스트 결과

## 1. 프로젝트 개요
- **기술 스택**: Java 17, Spring Boot, Gradle
- **공통 응답 포맷**: `ApiResponse<T>` (status, data 구조)
- **로깅 미들웨어**: `HandlerInterceptor` 기반 요청/응답 로깅 구현

---

## 2. API 명세 및 Postman 테스트 결과 (총 8개)

### [POST] 1. 아이템 생성 API (성공 - 201 Created)
- **Endpoint**: `POST /api/items`
- **설명**: 새로운 아이템 정보를 받아 저장하고 201 응답을 반환합니다.
*(<img width="1440" height="900" alt="1번 API 스크린샷" src="https://github.com/user-attachments/assets/ca50424e-38ec-4d11-a788-843c7d3d233d" />
)*

---

### [POST] 2. 아이템 중복 생성 검증 API (실패 - 400 Bad Request)
- **Endpoint**: `POST /api/items`
- **설명**: 필수 값이 누락되었거나 유효하지 않은 데이터 요청 시 400 에러를 반환합니다.
*(<img width="1440" height="900" alt="2번 API 스크린샷" src="https://github.com/user-attachments/assets/ee466ee6-568c-41e4-8b5f-1e8ce8fa55a0" />
)*

---

### [GET] 3. 전체 아이템 목록 조회 API (성공 - 200 OK)
- **Endpoint**: `GET /api/items`
- **설명**: 저장된 모든 아이템 목록을 조회합니다.
*(<img width="1440" height="900" alt="3번 API 스크린샷" src="https://github.com/user-attachments/assets/01d05d94-63c4-43f4-a010-e22e2a01c538" />
)*

---

### [GET] 4. 단일 아이템 상세 조회 API (성공 - 200 OK)
- **Endpoint**: `GET /api/items/{id}`
- **설명**: 지정한 ID의 아이템 상세 정보를 조회합니다.
*(<img width="1440" height="900" alt="4번 스크린샷" src="https://github.com/user-attachments/assets/e9efb9de-1a47-4254-a227-b36d13c25fb2" />
)*

---

### [PUT] 5. 아이템 정보 수정 API (성공 - 200 OK)
- **Endpoint**: `PUT /api/items/{id}`
- **설명**: 지정한 ID의 아이템 정보를 수정합니다.
*(<img width="1440" height="900" alt="5번 스크린샷" src="https://github.com/user-attachments/assets/ce41b57f-b6d0-401a-bb26-749d52ce7c27" />
)*

---

### [PUT] 6. 존재하지 않는 아이템 수정 API (실패 - 404 Not Found)
- **Endpoint**: `PUT /api/items/{id}`
- **설명**: 존재하지 않는 ID 수정 요청 시 404 에러를 반환합니다.
*(<img width="1440" height="900" alt="6번 스크린샷" src="https://github.com/user-attachments/assets/3c91a119-09c2-4904-bbb9-2917766a5bee" />
)*

---

### [DELETE] 7. 아이템 삭제 API (성공 - 200 OK)
- **Endpoint**: `DELETE /api/items/{id}`
- **설명**: 지정한 ID의 아이템을 정상적으로 삭제합니다.
*(<img width="1440" height="900" alt="7번 스크린샷" src="https://github.com/user-attachments/assets/5f0b00df-26e2-44a7-bd09-063538a49170" />
)*

---

### [DELETE] 8. 존재하지 않는 아이템 삭제 API (실패 - 500 Internal Server Error / 404)
- **Endpoint**: `DELETE /api/items/{id}`
- **설명**: 예외 발생 시 전역 예외 처리기(`GlobalExceptionHandler`)를 통해 처리된 응답을 반환합니다.
*(<img width="1440" height="900" alt="8번 스크린샷" src="https://github.com/user-attachments/assets/39dd600e-8be9-4b6d-8b8a-106d1fdd0f8b" />
)*
