package com.example.parser.service;

import com.example.parser.model.Customer;
import com.example.parser.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final CustomerRepo repository;
    private final CustomerUpdateService updateService;

//    @Scheduled(cron = "*/10 * * * * *")
    public void makeUpdate(){
        log.info("Запущена задача");
        List<Customer> customerList = repository.getTop1000ByIsProcessingIsFalse();

        for(Customer customer : customerList){
            updateService.changeFields(customer.getSubjId());
        }
    }
}
