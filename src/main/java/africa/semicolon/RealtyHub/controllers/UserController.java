//package africa.semicolon.RealtyHub.controllers;
//
//import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
//import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
//import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
//import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;
//import africa.semicolon.RealtyHub.services.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/user")
//@AllArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @PostMapping
//    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest) throws UserResgistrationFailedException {
//        try{
//            var response = userService.register(userRegistrationRequest);
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }catch (RealtyHubException exception){
//            var response = new UserRegistrationResponse();
//            response.setMessage(exception.getMessage());
//            return ResponseEntity.badRequest().body(response);
//        }
//    }
//}
