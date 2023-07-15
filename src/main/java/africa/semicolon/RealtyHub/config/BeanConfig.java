package africa.semicolon.RealtyHub.config;

import africa.semicolon.RealtyHub.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static africa.semicolon.RealtyHub.utils.AppUtils.JWT_SIGNING_SECRET;
import static africa.semicolon.RealtyHub.utils.AppUtils.MAIL_API_KEY;

@Configuration
public class BeanConfig {
    @Value(JWT_SIGNING_SECRET)
    private String jwt_secret;
    @Value(MAIL_API_KEY)
    private String mailApiKey;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public MailConfig mailConfig(){
        return new MailConfig(mailApiKey);
    }

    @Bean
    public JwtUtil jwtUtils(){
        return new JwtUtil(jwt_secret);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
