package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.models.BioData;
import africa.semicolon.RealtyHub.repositories.BioDataRepository;
import africa.semicolon.RealtyHub.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import static africa.semicolon.RealtyHub.utils.ExceptionUtils.USER_WITH_EMAIL_NOT_FOUND;

@AllArgsConstructor
@Repository
@Builder
public class RealtyHubBioDataService implements UserDetailsService {
    private final BioDataRepository bioDataRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BioData bioData = bioDataRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(
                String.format(USER_WITH_EMAIL_NOT_FOUND, email)
        ));
        return new User(bioData);
    }
}
