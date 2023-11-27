package aibg23.selection.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginRequestDTO extends DTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
