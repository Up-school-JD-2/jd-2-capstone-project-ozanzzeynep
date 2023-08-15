package io.upschool.capstone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.model.source.spi.EmbeddedAttributeMapping;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Flight_Code")
    private String flightCode;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @Getter
    @Column(name = "Price")
    @Builder.Default
    private double price = 200;

    @Getter
    @Column(name = "Date",nullable = false)
    private String date;
    @Getter
    @Column(name = "Time",nullable = false)
    private String time;
    @Getter
    @Column(name = "Total_Seat")
    @Builder.Default
    private int totalSeat = 5;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

}
