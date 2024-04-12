package RedditClone.Controllers;


import RedditClone.DTOs.AuthRequestDTO;
import RedditClone.Model.User;
import RedditClone.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    @Operation(
            summary = "User authentication",
            description = "Optain a token by authenticating with your username and password"
            )
    public ResponseEntity<String> authenticate (@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(userService.authenticate(authRequestDTO));
    }

    @PostMapping("/register")
    @Operation(
            summary = "User registration",
            description = "To register, enter username, password, email address and role type."
    )

    public ResponseEntity<User> register (@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(userService.register(authRequestDTO));
    }


}
