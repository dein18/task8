package ru.dse.task8.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "user_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLimit {
    @Id
    private Long userId;

    @Column(nullable = false)
    private BigDecimal limitValue;

    @Column(nullable = false)
    private LocalDate lastUpdated;
}