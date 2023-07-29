package africa.semicolon.RealtyHub.controllers;

import africa.semicolon.RealtyHub.dtos.requests.RequestInspectionRequest;
import africa.semicolon.RealtyHub.dtos.requests.RequestSharingRequest;
import africa.semicolon.RealtyHub.dtos.requests.UserRegistrationRequest;
import africa.semicolon.RealtyHub.dtos.response.UserRegistrationResponse;
import africa.semicolon.RealtyHub.exceptions.RealtyHubException;
import africa.semicolon.RealtyHub.exceptions.UserResgistrationFailedException;
import africa.semicolon.RealtyHub.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest) throws UserResgistrationFailedException {
        try{
            var response = userService.register(userRegistrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RealtyHubException exception){
            var response = new UserRegistrationResponse();
            response.setMessage(exception.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<?> getAllUsers(@PathVariable int page, @PathVariable int size ){
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    @PostMapping("/request/sharing")
    public ResponseEntity<?> requestSharing(@RequestBody RequestSharingRequest request){
        var response = userService.requestSharing(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/request/inspection")
    public ResponseEntity<?> requestInspection(@RequestBody RequestInspectionRequest request){
        var response = userService.requestInspection(request);
        return ResponseEntity.ok(response);
    }
}
