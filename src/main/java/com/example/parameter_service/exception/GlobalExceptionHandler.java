package com.example.parameter_service.exception;

import com.example.parameter_service.config.TimeConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Clock clock;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {

        // Hatayı arka planda konsola yazdırıyoruz (Loglama)
        log.error("İş Kuralı Hatası Fırlatıldı: {}", ex.getMessage());

        // Kullanıcıya döneceğimiz JSON kutusunu hazırlıyoruz
        Map<String, Object> errorResponse = new HashMap<>();


        errorResponse.put("timestamp", LocalDateTime.now(clock).toString());
        errorResponse.put("status", ex.getStatus().value());
        errorResponse.put("message", ex.getMessage());

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        log.error("Beklenmeyen Sistem Hatası: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now(clock).toString());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        errorResponse.put("message", "Sistemde beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyin.");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}