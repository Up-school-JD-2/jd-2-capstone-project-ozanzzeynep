package io.upschool.capstone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @ManyToOne()
    @JoinColumn(name = "departure_airport_id",nullable = false)
    private Airport departure_airport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id",nullable = false)
    private Airport arrival_airport;

}
