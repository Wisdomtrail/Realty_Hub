package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.InitRequest;
import africa.semicolon.RealtyHub.dtos.requests.LoginUserRequest;
import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.*;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;

import java.util.List;

public interface UserService {
        UserRegistrationResponse register(UserRegistrationRequest customerRegistrationRequest) throws UserResgistrationFailedException, RealtyHubException;

        ApiResponse<?> verifyCustomer(String token) throws RealtyHubException;
        UserResponse getUserById(Long id) throws UserNotFoundException;

        ApiResponse<?> verifyUser(String token) throws RealtyHubException;

        List<UserResponse> getAllUsers(int page, int items);

        ApiResponse<?> deleteUser(long id);

        void deleteAll();
}
