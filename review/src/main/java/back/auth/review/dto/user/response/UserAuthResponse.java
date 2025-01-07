package back.auth.review.dto.user.response;

import back.auth.review.common.enumType.UserRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserAuthResponse {
    private int userId;
    private String accountId;
    private String email;
    private UserRoleType role;
    private List<String> menuAuths;
}
