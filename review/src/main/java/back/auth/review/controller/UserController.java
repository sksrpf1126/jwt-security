package back.auth.review.controller;

import back.auth.review.dto.user.request.UserLoginRequest;
import back.auth.review.dto.user.request.UserRequest;
import back.auth.review.dto.user.response.UserLoginResponse;
import back.auth.review.dto.user.response.UserAuthResponse;
import back.auth.review.mapper.UserMapper;
import back.auth.review.security.JwtTokenProvider;
import back.auth.review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/user/join")
    public void join(@RequestBody UserRequest userRequest) {
        userService.join(userRequest);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {

        UserLoginResponse loginUser = userService.login(userLoginRequest);

        HttpHeaders headers = null;

        if(loginUser.getUserId() != null) {
            String accessToken = jwtTokenProvider.createAccessToken(loginUser.getUserId(), loginUser.getRole());
            headers = createHeaders(accessToken);
        }

        return new ResponseEntity<>(loginUser, headers, HttpStatus.OK);
    }

    @GetMapping("/api/{userId}/menus")
    public UserAuthResponse selectUserMenus(@PathVariable("userId") int userId) {
        return userMapper.selectUserMenus(userId);
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.SET_COOKIE, createCookie(accessToken).toString());
        return headers;
    }

    private ResponseCookie createCookie(String accessToken) {
        return ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .build();
    }

}
