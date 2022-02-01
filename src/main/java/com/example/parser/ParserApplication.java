package com.example.parser;

import com.example.parser.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParserApplication {


    public static void main(String[] args) {
        SpringApplication.run(ParserApplication.class, args);

        ParseService service = new ParseService();

        service.parse("C:\\Users\\u_turdubekov\\Desktop\\customers.xlsx");
    }


}
