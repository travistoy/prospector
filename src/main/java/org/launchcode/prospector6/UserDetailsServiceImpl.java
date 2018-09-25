package org.launchcode.prospector6;

import org.launchcode.prospector6.controllers.UserController;
import org.launchcode.prospector6.models.User;
import org.launchcode.prospector6.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // set the current user for convenience
        UserController.currentUser = user;

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // TODO: configure the appropriate roles. For now just use one called ADMIN
//       for (Role role : user.getRoles()){
//          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//      }
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
