package jp.co.axa.apidemo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.services.UserInfoService;

@Service
public class CustUserDetailsService implements UserDetailsService {
	
	@Autowired
    private  UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoService.getByUsername(username)
                .map(e -> User.withUsername(e.getUsername())
                        .password("{noop}" + e.getPassword())
                        .roles(e.getRole().split(","))
                        .build()).orElse(null);
    }

    public List<String> getAllUserNames() {
        return userInfoService.getAllUserNames();
    }
}
