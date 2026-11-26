package com.confitech.credit;
import com.confitech.credit.service.EvaluacionCreditoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditEvaluationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditEvaluationApplication.class, args);
    }
    @Bean
    public EvaluacionCreditoService evaluacionCreditoService() {
        return new EvaluacionCreditoService();
    }
}
