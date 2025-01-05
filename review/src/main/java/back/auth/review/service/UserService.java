package back.auth.review.service;

import back.auth.review.dto.user.request.UserLoginRequest;
import back.auth.review.dto.user.request.UserRequest;
import back.auth.review.dto.user.response.UserLoginResponse;
import back.auth.review.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    public void join(UserRequest userRequest) {
        userMapper.insertUser(userRequest);
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return userMapper.selectUser(userLoginRequest);
    }

}
