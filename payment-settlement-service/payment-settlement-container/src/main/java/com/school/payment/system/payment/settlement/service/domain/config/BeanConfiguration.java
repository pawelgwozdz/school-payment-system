package com.school.payment.system.payment.settlement.service.domain.config;

import com.school.payment.system.payment.settlement.service.domain.PaymentSettlementDomainService;
import com.school.payment.system.payment.settlement.service.domain.PaymentSettlementDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentSettlementDomainService paymentSettlementService() {
        return new PaymentSettlementDomainServiceImpl();
    }
}
