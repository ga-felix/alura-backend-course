package gafelix.alurabackendcourse.config.security;

import gafelix.alurabackendcourse.model.User;
import gafelix.alurabackendcourse.service.RepositoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private RepositoryManager repositories;

    @Autowired
    public void setRepositories(RepositoryManager repositories) { this.repositories = repositories; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repositories.getUser().findByEmail(username);
        if(user.isPresent()) return user.get();
        else throw new UsernameNotFoundException("The credentials provided are invalid.");
    }
}
