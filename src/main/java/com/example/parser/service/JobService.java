package com.example.parser.service;

import com.example.parser.model.Customer;
import com.example.parser.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final CustomerRepo repository;
    private final CustomerUpdateService updateService;

    @Scheduled(cron = "*/60 * * * * *")
    public void makeUpdate(){
        log.info("Запущена задача");
        List<Customer> customerList = repository.getTop100ByIsUpdatedFalse();

        for(Customer customer : customerList){
            updateService.changeFields(customer.getSubjId());
            log.info("RBS ID: {}",customer.getSubjId());
        }
    }
}
