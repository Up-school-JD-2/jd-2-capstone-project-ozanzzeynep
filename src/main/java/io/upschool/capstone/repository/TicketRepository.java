package io.upschool.capstone.repository;

import io.upschool.capstone.entity.Ticket;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.Date;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select t from Ticket t where t.ticketNumber = :query")
    Ticket searchTicketByNumber(String query);

    @Query("select count(tp) from Ticket tp where tp.passengerName = :passengerName")
    int findTicketCountByName(@Param("passengerName") String passengerName);

}
