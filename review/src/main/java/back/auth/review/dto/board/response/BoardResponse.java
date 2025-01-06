package back.auth.review.dto.board.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponse {

    private int boardId;
    private String title;
    private String contents;
    private int userId;
}
