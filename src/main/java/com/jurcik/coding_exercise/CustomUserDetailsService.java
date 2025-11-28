package com.jurcik.coding_exercise;

import com.jurcik.coding_exercise.jooq.tables.records.UsersRecord;
import com.jurcik.coding_exercise.repository.UserRepository;
import com.jurcik.coding_exercise.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<UsersRecord> optionalUser = userRepository.findUser(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        UsersRecord user = optionalUser.get();
        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
