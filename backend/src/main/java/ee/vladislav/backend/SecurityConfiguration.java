package ee.vladislav.backend;

//import ee.vladislav.backend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
//                .authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?");
//                .authoritiesByUsernameQuery("select user_id, authority from authorities where user_id = ?")
//                .userDetailsService(userDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .csrf().disable();
        //                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
//                .authenticationProvider(authenticationProvider())
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("password")
////                .roles("USER")
////                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
////        users.createUser(user);
//        return users;
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/js/**", "/images/**"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
        return new BCryptPasswordEncoder();
    }


}
