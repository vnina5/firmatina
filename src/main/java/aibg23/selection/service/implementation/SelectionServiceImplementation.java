package aibg23.selection.service.implementation;

import aibg23.selection.domain.User;
import aibg23.selection.dto.*;
import aibg23.selection.logic.LogicClass;
import aibg23.selection.service.SelectionService;
import aibg23.selection.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
@Setter
public class SelectionServiceImplementation implements SelectionService {
    private Logger LOG = LoggerFactory.getLogger(SelectionServiceImplementation.class);
    private List<User> users = new ArrayList<>();
    private LogicClass lc = new LogicClass();


    private TokenService tokenService;

    @Autowired
    public SelectionServiceImplementation(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public DTO login(LoginRequestDTO dto) throws IOException {

        for (User user : users) {
            if (user.getUsername().equals(dto.getUsername()) && user.getPassword().equals(dto.getPassword())) {
                Claims claims = Jwts.claims();
                claims.put("username", dto.getUsername());
                claims.put("password", dto.getPassword());

                String token = tokenService.generate(claims);

                if (token == null) {
                    return new ErrorResponseDTO("Token nije uspešno generisan.");
                }

                LOG.info("Tim sa username-om: " + dto.getUsername() + " i password-om: " + dto.getPassword() + " se uspešno ulogovao. Bravo");
                return new LoginResponseDTO(token);

            }
        }

        LOG.info("Tim sa username-om: " + dto.getUsername() + " i password-om: " + dto.getPassword() + " ne postoji.");

        return new ErrorResponseDTO("Tim sa username-om: " + dto.getUsername() + " i password-om: " + dto.getPassword() + " ne postoji.");
    }

    @Override
    public DTO join(String token) throws IOException {

        Claims claims = tokenService.parseToken(token);

        if (token == null) {
            LOG.info("Token nije parsiran kako treba.");

            return new ErrorResponseDTO("Token nije parsiran kako treba.");
        }

        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(claims.get("username"))) {
                user = u;
            }
        }

        if (user == null) {
            LOG.info("Tim ne postoji.");

            return new ErrorResponseDTO("Tim sa username-om " + claims.get("username") + " ne postoji.");
        }

        //bira random brojeve
        //pakuje ih u json
        //prebacuje json u string
        //pravi novi JoinResponse sa tim stringom i vraca

//        String assignment = "{a:5, b:7, c:2}";
        String assignment = lc.getAss(user);

        if (assignment == null) {
            LOG.info("Nije generisan zadatak.");

            return new ErrorResponseDTO("Nije generisan zadatak.");
        }

        LOG.info("Timu sa usermane-om " + claims.get("username") + " je uspesno poslat zadatak");


        return new JoinResponseDTO(assignment);
    }

    @Override
    public DTO result(ResultRequestDTO dto, String token) throws IOException {
        Claims claims = tokenService.parseToken(token);


        if (token == null) {
            LOG.info("Token nije parsiran kako treba.");

            return new ErrorResponseDTO("Token nije parsiran kako treba.");
        }

        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(claims.get("username"))) {
                user = u;
            }
        }

        if (user == null) {
            LOG.info("Tim ne postoji.");

            return new ErrorResponseDTO("Tim sa username-om " + claims.get("username") + " ne postoji.");
        }


        user.setResult(dto.getResult());

        LOG.info("Tim je poslao rezultat: " + dto.getResult());


        lc.calculateTrueResult(user);

        if (user.getResult() != user.getTrueResult()) {
            LOG.info("Tim nije poslao tacan rezultat.");

            return new ResultResponseDTO("Hvala Vam što ste se prijavili za AIBG i što ste uradili selekcioni zadatak! Očekujte rezultate selekcije u narednih nekoliko dana");
        }

        LOG.info("Tim je poslao tacan rezultat.");


        String message = "Hvala Vam što ste se prijavili za AIBG i što ste uradili selekcioni zadatak! Očekujte rezultate selekcije u narednih nekoliko dana";
        return new ResultResponseDTO(message);

    }


}
