package com.hb.partnerservice.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Partner
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String partnername;
    @Column(unique = true)
    @NotBlank
    private String email;
    @NotNull
    private Long phone;
    @NotBlank
    private String password;

}
