package back.auth.review.mapper;

import back.auth.review.dto.board.request.BoardRequest;
import back.auth.review.dto.board.response.BoardResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    // Create
    void insertBoard(@Param("board") BoardRequest board, @Param("userId") int userId);

    // Read
    BoardResponse selectBoardById(@Param("boardId") int boardId);
    List<BoardResponse> selectAllBoards();

    // Update
    void updateBoard(@Param("boardId") int boardId, @Param("board") BoardRequest board, @Param("userId") int userId);

    // Delete
    void deleteBoard(@Param("boardId") int boardId);
}
