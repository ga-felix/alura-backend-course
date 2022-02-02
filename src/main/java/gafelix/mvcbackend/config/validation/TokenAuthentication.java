package gafelix.mvcbackend.config.validation;

import gafelix.mvcbackend.model.User;
import gafelix.mvcbackend.service.RepositoryManager;
import gafelix.mvcbackend.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Setter @Getter @AllArgsConstructor
public class TokenAuthentication extends OncePerRequestFilter {

    private TokenService tokenService;
    private RepositoryManager repositories;

    private void authenticate(String token) {
        Long id = Long.parseLong(getTokenService().getClaims(token).getSubject());
        User user = getRepositories().getUser().findById(id).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(String authorization) {
        if(authorization == null || authorization.isBlank() || !authorization.startsWith("Bearer ")) return null;
        String tokenValue = authorization.substring(7);
        if(getTokenService().isTokenValid(tokenValue)) return tokenValue;
        else return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = retrieveToken(request.getHeader("Authorization"));
        if(token != null) {
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }
}
