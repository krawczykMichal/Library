package org.example.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationsDTO {

    private Integer reservationId;
    private LocalDateTime reservationDate;;

    private String reservationsBooksTitle;

    private String reservationsUsersName;
    private String reservationsUsersSurname;
    private String reservationsUsersUsername;
    private String reservationsUsersEmail;
}
