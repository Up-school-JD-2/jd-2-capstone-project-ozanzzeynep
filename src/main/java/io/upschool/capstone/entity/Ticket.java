package io.upschool.capstone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Ticket")
@Where(clause = "is_active = true")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;

    @Column(name = "Passenger_Name_Surname",nullable = false)
    private String passengerName;

    private String cardNo;

    private String expirationDate;

    private String cvv;

    @Column(name = "price")
    private double ticketPrice;

    @Column(name = "ticket_number",nullable = false)
    private String ticketNumber;

    @Column(name = "isActive")
    private boolean active;

}
