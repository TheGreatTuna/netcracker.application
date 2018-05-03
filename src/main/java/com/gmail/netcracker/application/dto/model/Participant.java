package com.gmail.netcracker.application.dto.model;

import lombok.Data;

@Data
public class Participant {
    private Long person;
    private int eventId;
    private String priority;
    private boolean countdown;
    private boolean is_accepted;
}