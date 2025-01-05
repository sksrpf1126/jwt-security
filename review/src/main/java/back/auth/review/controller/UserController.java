package back.auth.review.controller;

import back.auth.review.dto.user.request.UserRequest;
import back.auth.review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/join")
    public void join(@RequestBody UserRequest userRequest) {
        userService.join(userRequest);
    }

}
