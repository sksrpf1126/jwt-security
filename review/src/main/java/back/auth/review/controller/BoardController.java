package back.auth.review.controller;

import back.auth.review.dto.board.request.BoardRequest;
import back.auth.review.dto.board.response.BoardResponse;
import back.auth.review.mapper.BoardMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardMapper boardMapper;

    @PostMapping
    public String createBoard(@RequestBody BoardRequest board) {
        boardMapper.insertBoard(board, 1);
        return "생성 성공!";
    }

    @GetMapping("/{boardId}")
    public BoardResponse getBoardById(@PathVariable int boardId) {
        return boardMapper.selectBoardById(boardId);
    }

    @GetMapping
    public List<BoardResponse> getAllBoards(HttpServletRequest request) {
        return boardMapper.selectAllBoards();
    }

    @PutMapping("/{boardId}")
    public String updateBoard(@PathVariable int boardId, @RequestBody BoardRequest board) {
        boardMapper.updateBoard(boardId, board, 1);
        return "업데이트 성공!";
    }

    // Delete
    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable int boardId) {
        boardMapper.deleteBoard(boardId);
        return "삭제 성공!";
    }
}
