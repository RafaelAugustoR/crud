package com.crud.rest.dtos;

import com.crud.entities.Client;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ClientDTO {

    @NotBlank(message = "field name cannot be null")
    private String name;

    @NotBlank(message = "field cpf cannot be null")
    private String cpf;

    @NotNull
    @Positive(message = "field income must be positive")
    private Double income;

    @NotNull
    @PastOrPresent(message = "birth date cannot be in the future")
    private Date birthDate;

    @NotNull(message = "field children cannot be null")
    @PositiveOrZero(message = "field income must be positive or zero")
    private Integer children;

    public ClientDTO(Client entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
