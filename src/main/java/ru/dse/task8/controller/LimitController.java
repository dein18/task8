package ru.dse.task8.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dse.task8.service.LimitService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/limits")
@RequiredArgsConstructor
public class LimitController {
    private final LimitService limitService;

    @Operation(summary = "получение лимита / создание нового записи UserLimit (при отсутсвии)")
    @GetMapping("/{userId}")
    public ResponseEntity<BigDecimal> getLimit(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(limitService.getLimit(userId));
    }

    @Operation(summary = "проведение платежа")
    @PostMapping("/{userId}/consume")
    public ResponseEntity<BigDecimal> consumeLimit(
            @PathVariable ("userId") Long userId,
            @RequestParam ("amount") BigDecimal amount) {
        return ResponseEntity.ok(limitService.consumeLimit(userId, amount));
    }

    @Operation(summary = "пополнение лимита")
    @PostMapping("/{userId}/restore")
    public ResponseEntity<BigDecimal> restoreLimit(
            @PathVariable ("userId") Long userId,
            @RequestParam ("amount") BigDecimal amount) {
        return ResponseEntity.ok(limitService.restoreLimit(userId, amount));
    }
}