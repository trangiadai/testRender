package org.example.miniforum.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.example.miniforum.dto.request.AuthenticationRequest;
import org.example.miniforum.dto.response.AuthentiacationResponse;
import org.example.miniforum.exception.AppException;
import org.example.miniforum.exception.ErrorCode;
import org.example.miniforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthentiacationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Auth request: " + request);

        // Kiểm tra null cho giá trị username và password
        if (request.getUsername() == null || request.getPassword() == null) {
            System.err.println("Username or password cannot be null");
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        }

        // Tìm người dùng theo username
        var userOptional = userRepository.findByUsername(request.getUsername());
        var user = userOptional.orElseThrow(() -> {
            System.err.println("User not found for username: " + request.getUsername());
            return new AppException(ErrorCode.USER_NOT_EXIST);
        });

        System.out.println("User: " + user);

        // Kiểm tra mật khẩu
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            System.err.println("Password does not match for user: " + request.getUsername());
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        }

        // Tạo token
        var token = generateToken(user.getUsername(), user.getId(), user.getAccountName());

        return AuthentiacationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public String generateToken(String username, int userId, String accountName) {
        // Dùng JWSHeader, JWTClaimsSet và các thành phần khác để tạo JWT
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("anhLong")
                .claim("id", userId)
                .claim("accountName", accountName)
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (  JOSEException e) {
            System.err.println("Cannot create token: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
