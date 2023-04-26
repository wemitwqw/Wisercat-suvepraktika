//package ee.vladislav.backend;
//
//import ee.vladislav.backend.model.User;
//import ee.vladislav.backend.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class AuthenticationProviderImpl implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.findUserByUsername(username);
//
////        List<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(new SimpleGrantedAuthority(user.getRole().getDescription())); // description is a string
//
//        return new UsernamePasswordAuthenticationToken(username, password);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}