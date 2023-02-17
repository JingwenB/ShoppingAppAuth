package com.beaconfire.springsecurityauth.domain.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
     private String message;
     private String exceptionType;
     private String status;
}