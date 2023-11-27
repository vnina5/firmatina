package aibg23.selection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO extends DTO {
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
