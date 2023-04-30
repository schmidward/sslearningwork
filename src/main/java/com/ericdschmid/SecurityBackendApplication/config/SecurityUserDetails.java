package com.ericdschmid.SecurityBackendApplication.config;

import com.ericdschmid.SecurityBackendApplication.model.Customer;
import com.ericdschmid.SecurityBackendApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Need this annotation since it's a service
@Service
public class SecurityUserDetails implements UserDetailsService {
    //Since this depends on the custom repository, we need to have it be autowired here
    @Autowired
    CustomerRepository customerRepository;

    //This override MUST be present because of the implementation
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //All logic goes in here
        //This defines username and password as null
        String userName, password = null;

        //This is an authorities list that is empty and null
        List<GrantedAuthority> authorities = null;

        //This calls the findByEmail from the CustomerRepository interface. The username the end user is passing is the input for this method and executes the SQL scripts
        //the given database. And it will give the list of customers details with the given input
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = customers.get(0).getEmail();
            password = customers.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        //This constructs a new User class with the values from above. This is then sent to Spring Security Framework and the actual authentication happens
        return new User(username, password, authorities);
    }

}
