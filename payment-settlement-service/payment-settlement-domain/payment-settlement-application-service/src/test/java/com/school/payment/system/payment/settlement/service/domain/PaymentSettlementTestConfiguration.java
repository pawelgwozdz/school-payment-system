package com.school.payment.system.payment.settlement.service.domain;

import com.school.payment.system.payment.settlement.service.domain.ports.output.repository.ChildAttendanceRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.school.payment.system")
public class PaymentSettlementTestConfiguration {

    @Bean
    public ChildAttendanceRepository childAttendanceRepository() {
        return Mockito.mock(ChildAttendanceRepository.class);
    }

    @Bean
    public PaymentSettlementDomainService paymentSettlementDomainService() {
        return new PaymentSettlementDomainServiceImpl();
    }
}
