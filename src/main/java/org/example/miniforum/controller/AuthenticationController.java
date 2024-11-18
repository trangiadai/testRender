package org.example.miniforum.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.miniforum.dto.ApiResponse;
import org.example.miniforum.dto.request.AuthenticationRequest;
import org.example.miniforum.dto.response.AuthentiacationResponse;
import org.example.miniforum.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;


    @PostMapping("/token")
    ApiResponse<AuthentiacationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Request sent: "+ request);
        var result= authenticationService.authenticate(request);

        return ApiResponse.<AuthentiacationResponse>builder()
                .data(result)
                .build();
    }

}
