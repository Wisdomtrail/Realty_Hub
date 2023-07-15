package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.LoginUserRequest;
import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.ApiResponse;
import africa.semicolon.RealtyHub.dtos.response.LoginUserResponse;
import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
import africa.semicolon.RealtyHub.dtos.response.UserResponse;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;

import java.util.List;

public interface UserService {
        UserRegistrationResponse register(UserRegistrationRequest customerRegistrationRequest) throws UserResgistrationFailedException, RealtyHubException;

//        LoginUserResponse login(LoginUserRequest loginUserRequest);
        ApiResponse<?> verifyCustomer(String token) throws RealtyHubException;
        UserResponse getUserById(Long id);

        ApiResponse<?> verifyUser(String token) throws RealtyHubException;

        List<UserResponse> getAllUsers(int page, int items);

        ApiResponse<?> deleteUser(long id);

        void deleteAll();
}
