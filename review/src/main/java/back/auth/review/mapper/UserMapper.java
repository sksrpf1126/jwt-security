package back.auth.review.mapper;

import back.auth.review.dto.user.request.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(UserRequest userRequest);
}
