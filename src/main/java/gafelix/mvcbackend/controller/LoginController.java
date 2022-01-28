package gafelix.mvcbackend.controller;

import antlr.Token;
import gafelix.mvcbackend.controller.dto.TokenDto;
import gafelix.mvcbackend.controller.form.LoginForm;
import gafelix.mvcbackend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken login = form.convert();
        try {
            Authentication authentication = authenticationManager.authenticate(login);
            return ResponseEntity.ok(new TokenDto(tokenService.createToken(authentication), "Bearer"));
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}