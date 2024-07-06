package in.devsuman.smartcart.security;

import in.devsuman.smartcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        in.devsuman.smartcart.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Username: " + user.getUsername());
        System.out.println("Authorities: " + Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

        return new User(user.getUsername(), user.getPassword(), user.getActive(),
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
