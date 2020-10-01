package org.lmcgee.technicaltest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String ipAddress;
    private double latitude;
    private double longitude;
}
