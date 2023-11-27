package aibg23.selection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO extends DTO {
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
