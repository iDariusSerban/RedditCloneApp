package RedditClone.Controllers;

import RedditClone.DTOs.CommentRequestDTO;
import RedditClone.DTOs.CommentResponseDTO;
import RedditClone.Model.Comment;
import RedditClone.Model.Post;
import RedditClone.Services.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    @Operation(
            summary = "Create a new comment",
            description = "Please enter the post ID, your user ID and the comment"
    )
    public ResponseEntity<Object> createComment(@RequestBody CommentRequestDTO commentRequestDTO)  {
        try {
            Comment comment = commentService.addComment(commentRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } catch(JsonProcessingException | MessagingException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/post/{id}")
    @Operation(
            summary = "Optain all comments for a post",
            description = "Please enter the post ID and you will get all the comments for it"
    )
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(commentService.getCommentsByPost(id));
    }

    @GetMapping("/user/{id}")
    @Operation(
            summary = "See all comments posted by a user",
            description = "Please enter the user ID and you will get all the comments posted by it"
    )
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUser(@PathVariable Long id) {
        return status(HttpStatus.OK).body(commentService.getCommentsByUser(id));
    }
}
