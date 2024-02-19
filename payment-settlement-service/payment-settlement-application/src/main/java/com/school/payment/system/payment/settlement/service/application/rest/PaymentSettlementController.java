package com.school.payment.system.payment.settlement.service.application.rest;

import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.ports.input.service.PaymentSettlementApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

/**
 * Rest controller for payment settlement
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/payment/settlements", produces = "application/vnd.api.v1+json")
@Tag(name = "Payment settlements",
        description = "Creates endpoints for retrieving payment settlements data")
public class PaymentSettlementController {

    private final PaymentSettlementApplicationService paymentSettlementApplicationService;

    /**
     * Returns payment settlement for all kids in school
     *
     * @param schoolId Id of school
     * @param year     year of payment settlement
     * @param month    month of payment settlement
     * @return payment settlement
     */
    //I didn't set any authorization/authentication, but I'm aware that it should be implemented in production ready app
    @Operation(
            description = "Calculates and returns payment settlement for school")
    @GetMapping("/school")
    public ResponseEntity<SchoolPaymentSettlementResponse> getSchoolPaymentSettlement(
            @RequestParam UUID schoolId,
            @RequestParam Year year,
            @RequestParam Month month) {
        return ResponseEntity.ok(paymentSettlementApplicationService.getSchoolPaymentSettlement(schoolId, year, month));
    }

    /**
     * Returns payment settlement for all kids of parent
     *
     * @param parentId Id of parent
     * @param year     year of payment settlement
     * @param month    month of payment settlement
     * @return payment settlement
     */
    @Operation(
            description = "Calculates and returns payment settlement for parent")
    @GetMapping("/parent")
    public ResponseEntity<ParentPaymentSettlementResponse> getParentPaymentSettlement(
            @RequestParam UUID parentId,
            @RequestParam Year year,
            @RequestParam Month month) {
        return ResponseEntity.ok(paymentSettlementApplicationService.getParentPaymentSettlement(parentId, year, month));
    }
}
