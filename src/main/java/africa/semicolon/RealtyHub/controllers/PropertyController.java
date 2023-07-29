package africa.semicolon.RealtyHub.controllers;

import africa.semicolon.RealtyHub.dtos.requests.SearchPropertyRequest;
import africa.semicolon.RealtyHub.dtos.requests.UploadPropertyRequest;
import africa.semicolon.RealtyHub.dtos.requests.ViewPropertyListingRequest;
import africa.semicolon.RealtyHub.dtos.requests.ViewPropertyRequest;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.services.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/property")
public class PropertyController {

    private PropertyService propertyService;


    @PostMapping
    public ResponseEntity<?> uploadProperty(@RequestBody UploadPropertyRequest request){
        try {
            var response = propertyService.uploadProperty(request);
            return ResponseEntity.ok(response);
        }catch (UserNotFoundException e){
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }

    }

    @GetMapping("/view/property")
    public ResponseEntity<?> viewProperty(@RequestBody ViewPropertyRequest request){
        try {
            var response = propertyService.viewProperty(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping("/view/properties")
    public ResponseEntity<?> viewProperties(@RequestBody ViewPropertyListingRequest request){
        try {
            var response = propertyService.viewProperties(request);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping("/search/properties")
    public ResponseEntity<?> searchProperties(@RequestBody SearchPropertyRequest request){
        try {
            var response = propertyService.searchProperty(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

}
