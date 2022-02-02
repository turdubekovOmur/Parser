package com.example.parser.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long subjId;
    private String s;

    @DateTimeFormat(pattern = "dd/M/yyyy hh:mm:ss")
    private Date dateOfCreation;

    private boolean isUpdated;
    private boolean isProcessing;
    private String stType;
    private String mbType;
}
