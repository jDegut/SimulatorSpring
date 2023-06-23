package fr.polytech.simulatorspring.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private final int id;
    private final String oldPassword;
    private final String newPassword;
    private final String newPassword2;
    private final String role;
}
