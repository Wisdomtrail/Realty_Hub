package africa.semicolon.RealtyHub.Securityyyyyy.User;

import africa.semicolon.RealtyHub.models.BioData;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@RequiredArgsConstructor
@Builder
public class RealtyHubUser implements UserDetails {
    private final BioData bioData;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return bioData.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return bioData.getPassword();
    }

    @Override
    public String getUsername() {
        return bioData.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
