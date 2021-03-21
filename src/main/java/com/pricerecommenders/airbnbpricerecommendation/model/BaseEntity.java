package com.pricerecommenders.airbnbpricerecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    private LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("Asia/Istanbul"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}