package aibg23.selection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinResponseDTO extends DTO {
    private String assignment;

    public JoinResponseDTO(String assignment) {
        this.assignment = assignment;
    }
}
