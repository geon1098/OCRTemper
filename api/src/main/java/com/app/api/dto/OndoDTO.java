package com.app.api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OndoDTO {

    private long id;
    private String name;
    private LocalDateTime regDate;
    private float temperature09;
    private float temperature13;
    private float temperature17;
    private Boolean del;
}
