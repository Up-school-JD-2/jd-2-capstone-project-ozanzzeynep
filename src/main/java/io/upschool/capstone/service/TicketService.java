package io.upschool.capstone.service;

import io.upschool.capstone.dto.Ticket.*;
import io.upschool.capstone.entity.Flight;
import io.upschool.capstone.entity.Ticket;
import io.upschool.capstone.exception.TicketAlreadySavedException;
import io.upschool.capstone.repository.TicketRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Builder
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightService flightService;

    @Transactional
    public TicketSaveResponse save(TicketSaveRequest request) {
        checkIsTicketAlreadySaved(request);
        Flight flight = flightService.searchFlightByCode(request.getFlightCode());
        if ((checkPassengerControl(request)) && (flight.getTotalSeat() != 0)) {
            String maskedNumber = request.getCardNo().replaceAll("[\\s-,]", "")
                    .replaceAll("(?<=\\d{4})\\d(?=\\d{4})", "*");
            String maskedCvv = request.getCvv().replaceAll("\\d(?=\\d{1})", "*");
            flight.setTotalSeat(flight.getTotalSeat() - 1);
            Ticket newTicket = Ticket.builder()
                    .flight(flight)
                    .passengerName(request.getPassengerName())
                    .cardNo(maskedNumber)
                    .expirationDate(request.getExpirationDate())
                    .cvv(maskedCvv)
                    .ticketPrice(flight.getPrice())
                    .ticketNumber(flight.getFlightCode() + (int) (Math.random() * 1000))
                    .active(true)
                    .build();
            var ticketResponse = ticketRepository.save(newTicket);
            return TicketSaveResponse.builder()
                    .ticketId(ticketResponse.getId())
                    .flightCode(request.getFlightCode())
                    .pasengerName(ticketResponse.getPassengerName())
                    .ticketNumber(ticketResponse.getTicketNumber())
                    .active(true)
                    .message("Payment is success")
                    .build();
        }
        throw new TicketAlreadySavedException("Ticket already added.");
    }

    @Transactional
    public TicketDeleteResponse delete(TicketDeleteRequest request) {
        Ticket ticket = ticketRepository.searchTicketByNumber(request.getTicketNumber());
        if (ticket != null) {
            ticket.setActive(false);
            ticket.getFlight().setTotalSeat(ticket.getFlight().getTotalSeat() + 1);
            var ticketResponse = ticketRepository.save(ticket);
            return TicketDeleteResponse.builder()
                    .id(ticketResponse.getId())
                    .active(ticketResponse.isActive())
                    .build();
        }
        throw new TicketAlreadySavedException("Ticket already added.");
    }

    private boolean checkPassengerControl(TicketSaveRequest request) {
        String newCardNum = request.getCardNo().replaceAll("[\\s-,]", "");
        String newDate = request.getExpirationDate();
        String newCvv = request.getCvv();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth yearMonth = YearMonth.parse(newDate, formatter);
        LocalDate inputDate = yearMonth.atDay(1);

        return newCardNum.length() == 16 &&
                newCardNum.matches("\\d+") &&
                inputDate.isAfter(LocalDate.now().withDayOfMonth(1)) &&
                newCvv.length() == 3 &&
                newCvv.matches("\\d+");
    }

    public TicketSearchResponse getTicketbyNumber(String ticketNumber) {
        Ticket ticketResponse = ticketRepository.searchTicketByNumber(ticketNumber);
        return TicketSearchResponse.builder()
                .company(ticketResponse.getFlight().getCompany().getName())
                .departureAirport(ticketResponse.getFlight().getRoute().getDeparture_airport().getName())
                .arrivalAirport(ticketResponse.getFlight().getRoute().getArrival_airport().getName())
                .date(ticketResponse.getFlight().getDate())
                .time(ticketResponse.getFlight().getTime())
                .passengerName(ticketResponse.getPassengerName())
                .ticketPrice(ticketResponse.getTicketPrice())
                .active(ticketResponse.isActive())
                .build();
    }

    private void checkIsTicketAlreadySaved(TicketSaveRequest request) {
        int ticketCount = ticketRepository.findTicketCountByName(request.getPassengerName());
        if (ticketCount > 0) {
            throw new TicketAlreadySavedException("Ticket already saved.");
        }
    }
}

