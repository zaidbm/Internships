package com.internship.app.services;
package com.gestion.app.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion.app.dao.UserRepository;
import com.gestion.app.entities.User;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	private final UserRepository userRepository;
	@Autowired
	public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
	}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        if (username.equals("user")) {
            return User.builder()
                    .username("user")
                    .password("{noop}password") // {noop} is used for plain text password (not secure, for demonstration purposes only)
                    .roles("USER")
                    .build();
        } else if (username.equals("admin")) {
            return User.builder()
                    .username("admin")
                    .password("{noop}password")
                    .roles("ADMIN", "USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
