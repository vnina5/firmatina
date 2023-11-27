package aibg23.selection.controller;

import aibg23.selection.dto.*;
import aibg23.selection.service.SelectionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/selection")
@Getter
@Setter
public class SelectionController {
    private SelectionService selectionService;

    @Autowired
    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @PostMapping("/login")
    public ResponseEntity<DTO> login(@RequestBody @Valid LoginRequestDTO dto) throws IOException {
        DTO response = selectionService.login(dto);

        if (response instanceof LoginResponseDTO) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/join")
    public ResponseEntity<DTO> join(@RequestHeader("Authorization") String authorization) throws IOException {
        DTO response = selectionService.join(authorization);

        if (response instanceof JoinRequestDTO) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/myResult")
    public ResponseEntity<DTO> result(@RequestBody @Valid ResultRequestDTO dto,
                                      @RequestHeader("Authorization") String authorization) throws IOException {
        DTO response = selectionService.result(dto, authorization);

        if (response instanceof ResultResponseDTO) {
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}
