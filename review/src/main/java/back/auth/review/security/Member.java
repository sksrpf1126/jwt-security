package back.auth.review.security;

import back.auth.review.common.enumType.UserRoleType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private int userId;
    private String accountId;
    private String email;
    private UserRoleType role;

}
