package com.example.Movie.AuthenticationConfig.Controller;

import com.example.Movie.AuthenticationConfig.Entity.MovieUserDetails;
import com.example.Movie.AuthenticationConfig.Repository.MovieAuthConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSignUpController
{

    @Autowired
    PasswordEncoder encode;

    @Autowired
    MovieAuthConfigRepository movieAuthConfigRepository;


    @GetMapping("/movie/signup")
    public String signUp(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("authorities") String authorities)
    {
        /*Encoding the password */
        String encryptedPassword = encode.encode(password);
        System.out.println("Encoder value in UserController : "+encode.hashCode());
        /*Making the new user*/
        MovieUserDetails user = new MovieUserDetails();
        user.setUserName(userName);
        user.setPassword(encryptedPassword);
        user.setAuthorities(authorities);

        try
        {
            /*Saving the user to the data base*/
            movieAuthConfigRepository.save(user);    /*this will throws exception if the user already exits. check will be done on the basis of the userName*/
        }
        catch (Exception e)
        {
            return "User Name taken. please choose different user name.";
        }
        return "Signup Successful";
    }
}
