package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spittr.data.SpitterRepository;
import spittr.security.SpitterUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//                // we can use authorities("ROLE_USER") instead of roles("USER")
//                .withUser("user").password(passwordEncoder.encode("password")).roles("USER").and()
//                .withUser("admin").password(passwordEncoder.encode("password")).roles("USER", "ADMIN");

        // use UserDetailService
        auth.userDetailsService(new SpitterUserService(spitterRepository))
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()    // enable default form login
                // .loginPage("/login") if we have a new login page
                .and()
                .httpBasic()    // enable http basic authentication
                .realmName("Spittr")
                .and()
                .rememberMe()   // enable remember me service
                // By default, a remember-me token is stored in a cookie that’s valid for up to two weeks
                // specifies that the token should stay valid for up to four weeks (2,419,200 seconds)
                .tokenValiditySeconds(2419200)
                // by default, the private key is SpringSecuredbut this example sets it to
                // spitterKey to make it specific to the Spittr application
                .key("spitterKey")
                .and()
                .logout()
                    .logoutSuccessUrl("/")      // logout successful and redirect to "/"
                    .logoutUrl("/signout")      // use "/signout" to logout
                .and()
                .authorizeRequests()
                .antMatchers("/spitters/me").authenticated()
                .antMatchers(HttpMethod.POST, "/spittes").authenticated()
                .anyRequest().permitAll()
                .and()
                // requires HTTPS for the following URLs
                .requiresChannel()
                .antMatchers("/spitter/form").requiresSecure();
    }
}
