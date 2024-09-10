package com.shoppingapp.universal_shopping_app.controller;

import com.shoppingapp.universal_shopping_app.dto.AuthenticationRequest;
import com.shoppingapp.universal_shopping_app.dto.SignupRequest;
import com.shoppingapp.universal_shopping_app.dto.UserDto;
import com.shoppingapp.universal_shopping_app.entity.User;
import com.shoppingapp.universal_shopping_app.repositry.UserRepositry;
import com.shoppingapp.universal_shopping_app.services.auth.AuthService;
import com.shoppingapp.universal_shopping_app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
     //
//     @CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app running on port 4200
//     @GetMapping("/cors-test")
//     public String corsTest() {
//         return "CORS is working!";
//     }
    //
    //
     @PostMapping("/cors-test")
     @CrossOrigin(origins = "http://localhost:4200") // Adjust this to your frontend URL
     public ResponseEntity<String> testCors(@RequestBody String message) {
//         return ResponseEntity.ok("CORS POST Test successful: " + message);
         HttpHeaders headers = new HttpHeaders();
         System.out.println("hi this is cors test");
         logger.info("request");
         headers.setContentType(MediaType.TEXT_PLAIN);
         return new ResponseEntity<>("CORS POST Test successful: " + message, headers, HttpStatus.OK);
     }
    //
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepositry userRepositry;

    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX="Bearer ";

    public static final String HEADER_STRING="Authorization";

    private final AuthService authService;

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try{
            System.out.println("loogin rec");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password.");

        }

        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<User> optionalUser=userRepositry.findFirstByEmail(userDetails.getUsername());
        final String jwt =jwtUtil.generateToken(userDetails.getUsername());

        if(optionalUser.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .put("role", optionalUser.get().getRole())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers","Authorization");
            response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Origin, " +
                    "X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX +jwt);
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/sign-up")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupUser(HttpServletRequest request, @RequestBody SignupRequest signupRequest){
        logger.info("Request received at /sign-up");
//        logger.info("Request Payload: " + signupRequest.toString());
        logger.info("Content-Type: " + request.getHeader("Content-Type"));
        if(authService.hasUserWithEmail(signupRequest.getEmail())){
            logger.info("User already exists");
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto userDto=authService.createUser(signupRequest);
        HttpHeaders headers = new HttpHeaders();
        logger.info("User created successfully");
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
}
