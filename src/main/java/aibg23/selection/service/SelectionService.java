package aibg23.selection.service;

import aibg23.selection.domain.User;
import aibg23.selection.dto.DTO;
import aibg23.selection.dto.LoginRequestDTO;
import aibg23.selection.dto.ResultRequestDTO;

import java.io.IOException;
import java.util.List;

public interface SelectionService {

    DTO login(LoginRequestDTO dto) throws IOException;
    DTO join (String token) throws IOException;
    DTO result(ResultRequestDTO dto, String token) throws IOException;

    List<User> getUsers();
}
