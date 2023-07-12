//package africa.semicolon.RealtyHub.Security.config;
//
//import africa.semicolon.RealtyHub.Security.Filters.RealtyHubAuthenticationFilter;
//import africa.semicolon.RealtyHub.Security.User.RealtyHubUser;
//import africa.semicolon.RealtyHub.repositories.UserRepository;
//import africa.semicolon.RealtyHub.utils.JwtUtil;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static africa.semicolon.RealtyHub.utils.AppUtils.LOGIN_ENDPOINT;
//import static org.springframework.http.HttpMethod.PATCH;
//import static org.springframework.http.HttpMethod.POST;
//
//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final AuthenticationProvider authenticationProvider;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        UsernamePasswordAuthenticationFilter authFilter = new RealtyHubAuthenticationFilter(authenticationManager, jwtUtil);
//        authFilter.setFilterProcessesUrl(LOGIN_ENDPOINT);
//    http
//            .csrf(AbstractHttpConfigurer::disable)
//            .cors(Customizer.withDefaults())
//            .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(c -> c.requestMatchers(POST, LOGIN_ENDPOINT)
//                    .permitAll()
//                    .anyRequest().authenticated())
//            .authenticationProvider(authenticationProvider);
//
//    return http.build();
//
//    }
//
//
//
//
//
//}
