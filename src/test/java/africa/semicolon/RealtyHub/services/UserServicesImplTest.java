package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserService userService;

    private UserRegistrationResponse userRegistrationResponse;
    private UserRegistrationRequest userRegistrationRequest;

    @BeforeEach
    public void setUp() throws UserResgistrationFailedException, RealtyHubException {
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("oniyideolayinka90@gmail.com");
        userRegistrationRequest.setPassword("");

        userRegistrationResponse = userService.register(userRegistrationRequest);

    }
    @Test
    public void testThatCustomerCanRegister() {
        assertThat(userRegistrationResponse).isNotNull();
    }

}