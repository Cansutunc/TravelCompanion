// Define the package of the class
package com.travelcompanion.TravelCompanion.config;
import com.travelcompanion.TravelCompanion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




import lombok.RequiredArgsConstructor;

// Mark this class as a configuration class
@Configuration

// Generate a constructor with required arguments
@RequiredArgsConstructor
public class ApplicationConfig {

//    @Autowired
    private final UserService userService ;

    // Define a UserDetailsService bean that retrieves UserDetails from SellerRepository
    @Bean
    public UserDetailsService userDetailsService() {
        
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.out.println("Finding user by his username: " + username);
                
                UserDetails userDetails = (UserDetails) userService.getUserByEmail(username);

                if (userDetails != null) {
                    return userDetails;
                }

                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    // Define an AuthenticationProvider bean that uses DaoAuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // Create a new DaoAuthenticationProvider object
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // Set the UserDetailsService for the DaoAuthenticationProvider object
        authenticationProvider.setUserDetailsService(userDetailsService());
        // Set the PasswordEncoder for the DaoAuthenticationProvider object
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        // Return the DaoAuthenticationProvider object
        return authenticationProvider;
    }

    // Define an AuthenticationManager bean that gets AuthenticationManager from AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Define a PasswordEncoder bean that uses BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
