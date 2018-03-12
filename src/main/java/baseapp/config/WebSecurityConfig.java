package baseapp.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger LOGGER = Logger
            .getLogger (WebSecurityConfig.class.getName ());


    @Override
    protected void configure (HttpSecurity http) throws Exception
    {

        http.authorizeRequests ()
                    .antMatchers ("/css/**", "/js/**", "/fonts/**", "/jquery/**", "/static/**",
                            "/font-awesome/**").permitAll ()
                    .antMatchers ("/user/**").hasAuthority ("user")
                    .anyRequest ().authenticated ()
                .and ()
                    .formLogin ()
                        .loginPage ("/login").permitAll ()
                .and ()
                    .logout ().permitAll ();
    }


    @Bean
    public PasswordEncoder passwordEncoder ()
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder ();
        return encoder;
    }
}
