package back.auth.review.dto.user.response;

import back.auth.review.common.enumType.UserRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponse {
    private Integer userId;
    private String email;
    private UserRoleType role;
}
