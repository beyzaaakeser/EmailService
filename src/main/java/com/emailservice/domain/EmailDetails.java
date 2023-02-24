package com.emailservice.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    @Email(message = "Please provide valid e-mail")
    private String recipient;

    @NotBlank
    @Size(max=50)
    private String subject;

    @NotBlank
    @Size(min=5)
    private String msgBody;

    private String attachment;


}
