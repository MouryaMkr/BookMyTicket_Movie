package com.example.Movie.AuthenticationConfig.Service;

import com.example.Movie.AuthenticationConfig.Repository.MovieAuthConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MovieUserDetailsService implements UserDetailsService
{
    @Autowired
    MovieAuthConfigRepository movieAuthConfigRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return movieAuthConfigRepository.getUser(s);
    }
}
