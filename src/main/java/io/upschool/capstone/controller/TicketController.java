package io.upschool.capstone.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.dto.Ticket.*;
import io.upschool.capstone.service.TicketService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capstone/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    @Operation(
            tags = {"Ticket System"},
            summary = "search ticket"
    )
    public ResponseEntity<Object> searchTicket(@RequestParam("Ticket Number : ") String ticketNumber){
        var ticketRequest = TicketSearchRequest.builder()
                .ticketNumber(ticketNumber).build();
        var ticketResponse = ticketService.getTicketbyNumber(ticketNumber);
        var response = BaseResponse.<TicketSearchResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            tags = {"Ticket System"},
            summary = "buy ticket"
    )
    public ResponseEntity<Object> createTicket(@RequestBody TicketSaveRequest request){
        var ticketSaveResponse = ticketService.save(request     );
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Operation(
            tags = {"Ticket System"},
            summary = "delete ticket"
    )
    public ResponseEntity<Object> deleteTicket(@RequestParam ("Ticket Number : ") String ticketNumber) {
        var ticketRequest = TicketDeleteRequest.builder().ticketNumber(ticketNumber).build();
        var ticketDeleteRespone = ticketService.delete(ticketRequest);

        var response = BaseResponse.<TicketDeleteResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketDeleteRespone)
                .build();
        return ResponseEntity.ok(response);
    }
}
