package com.school.payment.system.payment.settlement.service.domain.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.payment.system.application.handler.ErrorDTO;
import com.school.payment.system.domain.valueobject.Money;
import com.school.payment.system.payment.settlement.service.domain.dto.parent.ParentPaymentSettlementResponse;
import com.school.payment.system.payment.settlement.service.domain.dto.school.SchoolPaymentSettlementResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles("test")
class PaymentSettlementControllerTest {

    private final String apiPath = "/api/v1/payment/settlements";
    private final String schoolPath = "/school";
    private final String parentPath = "/parent";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getSchoolPaymentSettlementShouldReturnOk() throws Exception {
        //GIVEN:
        UUID schoolId = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb50");
        Year year = Year.of(2022);
        Month month = Month.OCTOBER;

        //WHEN:
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(apiPath + schoolPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("schoolId", schoolId.toString())
                        .queryParam("year", year.toString())
                        .queryParam("month", month.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN:
        SchoolPaymentSettlementResponse schoolPaymentSettlementResponse = objectMapper.readValue(response.getContentAsString(), SchoolPaymentSettlementResponse.class);
        assertEquals(new BigDecimal("150.00"), schoolPaymentSettlementResponse.getFee());
        //More asserts...
    }

    @Test
    public void getSchoolPaymentSettlementShouldReturn4XX() throws Exception {
        //GIVEN:
        UUID schoolId = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb50");
        Year year = Year.of(2025);
        Month month = Month.OCTOBER;

        //WHEN:
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(apiPath + schoolPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("schoolId", schoolId.toString())
                        .queryParam("year", year.toString())
                        .queryParam("month", month.toString()))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse();

        //THEN:
        ErrorDTO errorDTO = objectMapper.readValue(response.getContentAsString(), ErrorDTO.class);
        assertEquals("child attendance entities for given data not found, request id: d215b5f8-0249-4dc5-89a3-51fd148cfb50 year: 2025 month: OCTOBER"
                , errorDTO.getMessage());
        //More asserts...
    }

    @Test
    public void getParentPaymentSettlementShouldReturnOk() throws Exception {
        //GIVEN:
        UUID parentId = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb26");
        Year year = Year.of(2022);
        Month month = Month.OCTOBER;

        //WHEN:
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(apiPath + parentPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("parentId", parentId.toString())
                        .queryParam("year", year.toString())
                        .queryParam("month", month.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //THEN:
        ParentPaymentSettlementResponse parentPaymentSettlementResponse = objectMapper.readValue(response.getContentAsString(), ParentPaymentSettlementResponse.class);
        assertEquals(new BigDecimal("100.00"), parentPaymentSettlementResponse.getFee());
        //More asserts...
    }

    @Test
    public void getParentPaymentSettlementShouldReturn4XX() throws Exception {
        //GIVEN:
        UUID parentId = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb26");
        Year year = Year.of(1720);
        Month month = Month.OCTOBER;

        //WHEN:
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get(apiPath + parentPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("parentId", parentId.toString())
                        .queryParam("year", year.toString())
                        .queryParam("month", month.toString()))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse();

        //THEN:
        ErrorDTO errorDTO = objectMapper.readValue(response.getContentAsString(), ErrorDTO.class);
        assertEquals("child attendance entities for given data not found, request id: d215b5f8-0249-4dc5-89a3-51fd148cfb26 year: 1720 month: OCTOBER"
                , errorDTO.getMessage());
        //More asserts...
    }
}