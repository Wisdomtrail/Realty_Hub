package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.EmailNotificationRequest;
import africa.semicolon.RealtyHub.dtos.requests.Recipient;
import africa.semicolon.RealtyHub.dtos.requests.Sender;
import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.ApiResponse;
import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
import africa.semicolon.RealtyHub.dtos.response.UserResponse;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;
import africa.semicolon.RealtyHub.models.BioData;
import africa.semicolon.RealtyHub.models.RealtyHubUser;
import africa.semicolon.RealtyHub.repositories.UserRepository;
import africa.semicolon.RealtyHub.services.notification.mail.MailServices;
import africa.semicolon.RealtyHub.utils.JwtUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static africa.semicolon.RealtyHub.models.Role.USER;
import static africa.semicolon.RealtyHub.utils.AppUtils.*;
import static africa.semicolon.RealtyHub.utils.ExceptionUtils.*;
import static africa.semicolon.RealtyHub.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;


@Service
@AllArgsConstructor
 public class UserServicesImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailServices mailServices;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws UserResgistrationFailedException, RealtyHubException {
        BioData bioData = modelMapper.map(userRegistrationRequest, BioData.class);
        bioData.setPassword(passwordEncoder.encode(bioData.getPassword()));
        bioData.setRoles(new HashSet<>());
        RealtyHubUser user = new RealtyHubUser();
        user.setBioData(bioData);
        bioData.getRoles().add(USER);
        RealtyHubUser savedUser = userRepository.save(user);
        EmailNotificationRequest emailRequest = buildEmailRequest(savedUser);
        mailServices.sendMail(emailRequest);
       return buildUserRegistrationResponse(savedUser.getId());
    }

    @Override
    public ApiResponse<?> verifyCustomer(String token) throws RealtyHubException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("secret".getBytes()))
                .build().verify(token);
        if (decodedJWT==null) throw new RealtyHubException("):");
        return ApiResponse.builder().message("Account Verified").build();

    }

    private EmailNotificationRequest buildEmailRequest(RealtyHubUser user) throws RealtyHubException {
        String token = generateToken(user, jwtUtil.getSecret());
    EmailNotificationRequest request = new EmailNotificationRequest();
    Sender sender = new Sender(APP_NAME, APP_EMAIL);
    Recipient recipient = new Recipient(user.getFirstName(), user.getBioData().getEmail());
    request.setEmailSender(sender);
    request.setRecipients(Set.of(recipient));
    request.setSubject(ACTIVATION_LINK_VALUE);
    String template = getEmailTemplate();
    request.setContent(String.format(template, FRONTEND_BASE_URL+"/customer/verify?token="+token));
    return request;
    }
    private String getEmailTemplate() throws RealtyHubException {
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(MAIL_TEMPLATE_LOCATION))){
            return  reader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            throw new RealtyHubException(FAILED_TO_SEND_ACTIVATION_LINK);
        }
    }
    private UserRegistrationResponse buildUserRegistrationResponse(Long userId) {
        UserRegistrationResponse userRegistrationResponse =new UserRegistrationResponse();
        userRegistrationResponse.setMessage(USER_REGISTRATION_SUCCESSFUL);
        userRegistrationResponse.setId(userId);
        return userRegistrationResponse;
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<?> verifyUser(String token) throws RealtyHubException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()))
                .build().verify(token);
        if (decodedJWT == null) throw new RealtyHubException(ACCOUNT_VERIFICATION_FAILED);
        Claim claim = decodedJWT.getClaim(ID);
        Long id = claim.asLong();
        RealtyHubUser foundUser = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format(USER_WITH_ID_NOT_FOUND, id)));
        foundUser.getBioData().setIsEnabled(true);
        userRepository.save(foundUser);
        return ApiResponse.builder()
                .message(ACCOUNT_VERIFIED_SUCCESSFULLY)
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int items) {
        return null;
    }

    @Override
    public ApiResponse<?> deleteUser(long id) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
