package com.example.health_data_service.command.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity 
@Getter 
@Setter 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor
@Table(name = "measurements_command")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double value;
    private LocalDateTime timestamp;
    private String deviceId;
} 