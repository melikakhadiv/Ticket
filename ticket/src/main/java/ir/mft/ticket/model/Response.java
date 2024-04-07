package ir.mft.ticket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity(name = "responseEntity")
@Table(name = "response_tbl")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "r_responder" )
    private String responder;

    @Column(name = "r_response" )
    private String ticketResponse;

    @Column(name = "r_date" )
    private LocalDate responseDate;

    @Column(name = "r_time" )
    private LocalTime responseTime;

    @Column(name = "r_active")
    private Boolean active;

}
