package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final Map<Long, String> database = new HashMap<>();
    private Long idCounter = 1L;

    // 1. POST - 아이템 생성 (201 Created)
    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> createItem(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("아이템 이름은 필수입니다.");
        }
        Long id = idCounter++;
        database.put(id, name);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(result, "아이템이 성공적으로 생성되었습니다."));
    }

    // 2. POST - 검증 실패 테스트 (400 Bad Request)
    @PostMapping("/validate")
    public ResponseEntity<ApiResponse<String>> validateAndCreate(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        if (code == null || code.length() < 3) {
            throw new IllegalArgumentException("코드는 3자리 이상이어야 합니다.");
        }
        return ResponseEntity.ok(ApiResponse.success("검증 성공: " + code));
    }

    // 3. GET - 전체 목록 조회 (200 OK)
    @GetMapping
    public ResponseEntity<ApiResponse<Map<Long, String>>> getAllItems() {
        return ResponseEntity.ok(ApiResponse.success(database));
    }

    // 4. GET - 단일 아이템 조회 (200 OK 또는 404 Not Found)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> getItem(@PathVariable Long id) {
        if (!database.containsKey(id)) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(ApiResponse.success(database.get(id)));
    }

    // 5. PUT - 아이템 수정 (200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateItem(@PathVariable Long id, @RequestBody Map<String, String> request) {
        if (!database.containsKey(id)) {
            throw new NoSuchElementException();
        }
        String newName = request.get("name");
        database.put(id, newName);
        return ResponseEntity.ok(ApiResponse.success(newName, "아이템이 수정되었습니다."));
    }

    // 6. PUT - 점검 모드 에러 테스트 (503 Service Unavailable)
    @PutMapping("/maintenance/{id}")
    public ResponseEntity<ApiResponse<Void>> maintenanceTest(@PathVariable Long id) {
        throw new IllegalStateException("서버 점검 중입니다.");
    }

    // 7. DELETE - 아이템 삭제 (200 OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable Long id) {
        if (!database.containsKey(id)) {
            throw new NoSuchElementException();
        }
        database.remove(id);
        return ResponseEntity.ok(ApiResponse.success(null, "성공적으로 삭제되었습니다."));
    }

    // 8. DELETE - 서버 에러 테스트 (500 Internal Server Error)
    @DeleteMapping("/error/{id}")
    public ResponseEntity<ApiResponse<Void>> forceError(@PathVariable Long id) {
        throw new RuntimeException("DB 연결 실패 강제 예외 발생");
    }
}