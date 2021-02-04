package com.example.Movie.AuthenticationConfig.Entity;

import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "movie_user_details",indexes = {@Index(name = "my_movie_index",columnList = "userName",unique = true)}) /*
unique = true indicates this table(movie_user_details) won't allows the duplicate userNames to the table. and also creates
the indexes on the username to make the query done the userName faster.
*/
public class MovieUserDetails implements UserDetails
{

    private static final String StringSeparator = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /*Auto indicates Hibernate will creates the id in the separate table*/
    private Integer id;
    private String userName;
    private String password;
    private String authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authoritiesToReturn = new ArrayList<>();
        String authorities[] =  this.authorities.split(StringSeparator); /*User may send the Authorities as USER:ADMIN. So this
        split function will it into two USER and ADMIN. And stores into the array. And the below code will adds the Authorities
        for the user*/

        for(String authority : authorities)
        {
            GrantedAuthority auth = new SimpleGrantedAuthority(authority);
            authoritiesToReturn.add(auth);
        }

        return authoritiesToReturn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
