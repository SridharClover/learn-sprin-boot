package com.institution.institution.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity(name = "user")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String UserId;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Email Id is required")
    @Email(message = "Invalid Email")
    private String email;

    @Column(nullable = false, length = 12)
    @NotEmpty(message = "Mobile No. is required")
    @Pattern(regexp = "(?=.*\\d).{10,}$", message = "Mobile is Invalid")
    private String mobileNo;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 10, message = "Password must contain 6 to 10 chars")
    private String password;

    @Column(nullable = false)
    private boolean isActive = true;

    @CreatedBy
    private String createdBy = "System";
    @LastModifiedBy
    private String updatedBy = "System";;

    @CreatedDate
    private LocalDateTime createdDateTime;
    @LastModifiedDate
    private LocalDateTime updatedDateTime;
}
