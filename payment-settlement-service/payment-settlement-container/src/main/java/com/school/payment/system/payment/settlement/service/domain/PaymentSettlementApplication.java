package com.school.payment.system.payment.settlement.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.school.payment.system.payment.settlement.service.dataaccess"})
@EntityScan(basePackages = {"com.school.payment.system.payment.settlement.service.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.school.payment.system")
public class PaymentSettlementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSettlementApplication.class, args);
    }
}
