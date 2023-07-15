//package africa.semicolon.RealtyHub.Security.Manager;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class RealtyHubAuthManager implements AuthenticationManager {
//    private final AuthenticationProvider authenticationProvider;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Authentication authResult =null;
//        if (authenticationProvider.supports(authentication.getClass())) {
//            authResult = authenticationProvider.authenticate(authentication);
//
//            return authResult;
//        } else throw new BadCredentialsException("):");
//    }
//}
