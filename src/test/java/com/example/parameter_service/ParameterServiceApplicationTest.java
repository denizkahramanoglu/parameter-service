package com.example.parameter_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class ParameterServiceApplicationTest {

    @Test
    @DisplayName("Uygulama main metodu çağrıldığında SpringApplication.run tetiklenmeli")
    void main_shouldCallSpringApplicationRun() {
        try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {

            String[] args = new String[]{};

            ParameterServiceApplication.main(args);

            mockedSpringApplication.verify(
                    () -> SpringApplication.run(ParameterServiceApplication.class, args),
                    times(1)
            );
        }
    }
        @Test
        @DisplayName("Application sınıfı initialize edilebilmeli (Coverage için)")
        void constructor_shouldBeInvoked() {
            ParameterServiceApplication app = new ParameterServiceApplication();
            assertNotNull(app);
        }
}