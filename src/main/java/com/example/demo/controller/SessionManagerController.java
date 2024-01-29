package com.example.demo.controller;

import com.example.demo.Exception.SessionExistException;
import com.example.demo.Exception.SessionNotFoundException;
import com.example.demo.Exception.UnSupportedAccessException;
import com.example.demo.config.JwtService;
import com.example.demo.entity.*;
import com.example.demo.service.SessionManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SessionManagerController {
    @Autowired
    SessionManagerService sessionManagerService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    //    @Autowired
//    public SessionManagerController(SessionManagerService sessionManagerService) {
//        this.sessionManagerService = sessionManagerService;
//    }
    @PostMapping("auth/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication auth = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(auth);
        if (authentication.isAuthenticated()) {
            JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
            jwtResponseDTO.setAccessToken(jwtService.GenerateToken(authRequestDTO.getUsername()));
            return jwtResponseDTO;
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<String> getSession() throws SessionExistException {
        return new ResponseEntity<>("Success boy", HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createSession(@RequestBody @Valid String initiator) throws SessionExistException {
        Long sessionId = sessionManagerService.createSession(initiator);
        return new ResponseEntity<>(sessionId, HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<LunchSession> joinSession(@RequestBody @Valid RequestDTO req) throws SessionNotFoundException {
        LunchSession lunchSession = sessionManagerService.joinSession(req.sessionId, req.user);
        return new ResponseEntity<>(lunchSession, HttpStatus.CREATED);
    }

    @PostMapping("/submit")
    public ResponseEntity<LunchSession> submitSession(@RequestBody @Valid String sessionId, @Valid String user, @Valid String restaurant) throws SessionNotFoundException {
        LunchSession lunchSession = sessionManagerService.submitRestaurant(sessionId, user, restaurant);
        return new ResponseEntity<>(lunchSession, HttpStatus.CREATED);
    }

    @PostMapping("/end")
    public ResponseEntity<Restaurant> endSession(@RequestBody @Valid String sessionId, @Valid String user) throws IllegalStateException, UnSupportedAccessException {
        Restaurant restaurant = sessionManagerService.endSession(sessionId, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearSession() throws IllegalStateException, UnSupportedAccessException {
        sessionManagerService.explicitlyendSession();
        return new ResponseEntity<>("Cleared", HttpStatus.CREATED);
    }
}
