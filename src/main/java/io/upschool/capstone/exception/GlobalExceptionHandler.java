package io.upschool.capstone.exception;

import io.upschool.capstone.dto.Airport.AirportSaveResponse;
import io.upschool.capstone.dto.BaseResponse;
import io.upschool.capstone.dto.Company.CompanySaveResponse;
import io.upschool.capstone.dto.Flight.FlightSaveResponse;
import io.upschool.capstone.dto.Route.RouteSaveResponse;
import io.upschool.capstone.dto.Ticket.TicketSaveResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        final var errorMessage =
                MessageFormat.format("No handler found for {0} {1}",ex.getHttpMethod(), ex.getRequestURL());
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAirport(final Exception exception){
        System.out.println("An error has occurred. Exception : " + exception.getMessage());
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CompanyAlreadySavedException.class)
    public ResponseEntity<Object> handleCompany(final Exception exception){
        System.out.println("An error has occurred. Exception : " + exception.getMessage());
        var response = BaseResponse.<CompanySaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FlightAlreadySavedException.class)
    public ResponseEntity<Object> handleFlight(final Exception exception){
        System.out.println("An error has occurred. Exception : " + exception.getMessage());
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TicketAlreadySavedException.class)
    public ResponseEntity<Object> handleTicket(final Exception exception){
        System.out.println("An error has occurred. Exception : " + exception.getMessage());
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(RouteAlreadySavedException.class)
    public ResponseEntity<Object> handleRoute(final Exception exception) {
        System.out.println("An error has occurred. Exception : " + exception.getMessage());
        var response = BaseResponse.<RouteSaveResponse>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(exception.getMessage())
                .isSuccess(false)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
