package com.example.parser.service;


import com.example.parser.model.Customer;
import com.example.parser.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CustomerUpdateService {

    @Value("${old.MB}")
    private String oldMb;

    @Value("${new.MB}")
    private String newMb;

    private final WebClient webClient = WebClient.create();
    private final CustomerRepo repository;


    public Boolean  changeFields(Long rbsId){


        String customerTypeFromNewMbank = webClient.get()
                .uri(newMb + "customer-type/" + rbsId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Integer customerTypeFromOldMbank = webClient.get()
                .uri( oldMb + "get-customer-type?rbsId=" + rbsId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        Customer customer = repository.getBySubjId(rbsId);

        customer.setMbType(String.valueOf(customerTypeFromOldMbank));
        customer.setStType(customerTypeFromNewMbank);
        customer.setUpdated(true);

        repository.save(customer);

        return true;

    }


}
