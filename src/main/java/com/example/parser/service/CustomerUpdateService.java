package com.example.parser.service;


import com.example.parser.model.Customer;
import com.example.parser.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerUpdateService {

    @Value("${old.MB}")
    private String oldMb;

    @Value("${new.MB}")
    private String newMb;

    private final WebClient webClient = WebClient.create();
    private final CustomerRepo repository;


    public Boolean changeFields(Long rbsId) {
        try {

            String customerTypeFromNewMbank = webClient.get()
                    .uri(newMb + "customer-type/" + rbsId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            Integer customerTypeFromOldMbank = webClient.get()
                    .uri(oldMb + "get-customer-type?rbsId=" + rbsId)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();

            Customer customer = repository.getBySubjId(rbsId);

            customer.setMbType(String.valueOf(customerTypeFromOldMbank));
            customer.setStType(customerTypeFromNewMbank);
            customer.setProcessing(true);

            repository.save(customer);
            return true;

        } catch (WebClientException | IncorrectResultSizeDataAccessException e) {
            log.error("Get some error: {}", e.getMessage());
            return false;
        }


    }


}
