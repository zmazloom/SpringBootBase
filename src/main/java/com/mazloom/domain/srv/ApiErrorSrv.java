package com.mazloom.domain.srv;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ApiErrorSrv {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
