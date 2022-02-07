package com.example.parser.service;

import com.example.parser.model.Customer;
import com.example.parser.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParseService {

    private final CustomerRepo repo;

    public void parse(String path) {

        List<Customer> list = new ArrayList<>();

        try {
            File file = new File(path);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0);

            log.info("Начало парсинга");

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Row row = worksheet.getRow(i);

                String subjId = row.getCell(1).getStringCellValue();
                String s = row.getCell(2).getStringCellValue();
                Date date = row.getCell(6).getDateCellValue();

                Customer customer = Customer.builder()
                        .subjId(Long.parseLong(subjId))
                        .s(s)
                        .dateOfCreation(date)
                        .build();
                list.add(customer);
            }
        } catch (IOException | InvalidFormatException e) {
            log.error("Error : {}", e.getMessage());
        }
        log.info("Конец парсинга");
        log.info("Запись в базу данных");

        repo.saveAll(list);
    }
}
