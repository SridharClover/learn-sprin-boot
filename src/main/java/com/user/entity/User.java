package com.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity(name = "user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String UserId;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "First name is required")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Column(nullable = false, length = 100, unique = true)
    @NotEmpty(message = "Email Id is required")
    @Email(message = "Invalid Email")
    private String email;

    @Column(nullable = false, length = 12, unique = true)
    @NotEmpty(message = "Mobile No. is required")
    @Pattern(regexp = "(?=.*\\d).{10,}$", message = "Mobile is Invalid")
    private String mobileNo;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "Password is required")
    @Size(min = 6,  message = "Password must contain 6 to 10 chars")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy = "System";
    @LastModifiedBy
    private String updatedBy = "System";;


    @Column(updatable = false)
    @CreatedDate
    private Date createdDateTime;

    @LastModifiedDate
    private Date updatedDateTime;

}
