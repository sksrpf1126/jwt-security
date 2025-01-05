package back.auth.review.dto.user.request;

import back.auth.review.common.enumType.UserRoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {
    private String accountId;
    private String pwd;
    private UserRoleType role;
    private String email;
}
