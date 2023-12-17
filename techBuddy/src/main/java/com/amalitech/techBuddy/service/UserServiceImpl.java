package com.amalitech.techBuddy.service;

import com.amalitech.techBuddy.Dao.RoleDao;
import com.amalitech.techBuddy.Dao.UserDao;
import com.amalitech.techBuddy.dto.UserDto;
import com.amalitech.techBuddy.entity.Role;
import com.amalitech.techBuddy.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleDao roleDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        if(userDao.findByUsername(userDto.getUsername()) != null){
            return false;
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(roleDao.findByName("ROLE_PARTNER"));
        userDao.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> role) {
        return role.stream().map(
                r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
