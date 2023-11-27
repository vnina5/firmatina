package aibg23.selection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponseDTO extends DTO {
    private String message;

    public ResultResponseDTO(String message) {
        this.message = message;
    }
}
