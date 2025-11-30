package org.example.exhibitiontimeslotbooking.dto.user.request;

public record UserMeUpdateRequest (
        String name,
        String email,
        String password
) {
}
