package back.auth.review.mapper;

import back.auth.review.dto.user.request.UserLoginRequest;
import back.auth.review.dto.user.request.UserRequest;
import back.auth.review.dto.user.response.UserLoginResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserRequest userRequest);

    UserLoginResponse selectUser(UserLoginRequest userLoginRequest);
}
