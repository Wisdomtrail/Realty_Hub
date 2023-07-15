package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
import africa.semicolon.RealtyHub.dtos.response.UserResponse;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;
import africa.semicolon.RealtyHub.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.math.BigInteger.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServicesImplTest {
    @Autowired
    private UserService userService;

    private UserRegistrationResponse userRegistrationResponse;
    private UserRegistrationRequest userRegistrationRequest;

    @BeforeEach
    public void setUp() throws UserResgistrationFailedException, RealtyHubException {
        userService.deleteAll();
        userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("oniyideolayinka90@gmail.com");
        userRegistrationRequest.setPassword("");

        userRegistrationResponse = userService.register(userRegistrationRequest);

    }
    @Test
    public void testThatUserCanRegister() {
        assertThat(userRegistrationResponse).isNotNull();
    }
    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        var foundUser = userService.getUserById(userRegistrationResponse.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(userRegistrationRequest.getEmail());
    }
    @Test
    public void getAllCustomersTest() throws RealtyHubException, UserResgistrationFailedException {
        userRegistrationRequest.setEmail("SundayEmmanuel@gmail.com");
        userRegistrationRequest.setPassword("");
        userService.register(userRegistrationRequest);
        List<UserResponse> users = userService.getAllUsers(ONE.intValue(), TEN.intValue());
        assertThat(users.size()).isEqualTo(TWO.intValue());


    }

}