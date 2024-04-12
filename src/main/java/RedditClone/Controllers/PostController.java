package RedditClone.Controllers;

import RedditClone.DTOs.PostRequestDTO;
import RedditClone.DTOs.PostResponseDTO;
import RedditClone.Model.Post;
import RedditClone.Services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(
            summary = "Create a post",
            description = "Please enter the post name, the content of the post (description), the subreddit ID and your user ID "
    )
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        Post post = postService.createPost(postRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/all")
    @Operation(
            summary = "See all posts ",
            description = "You will be able so visualize all posts "
    )
    public ResponseEntity<List<PostResponseDTO>> allPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "See all posts ",
            description = "You will be able so visualize all posts "
    )
    public ResponseEntity<PostResponseDTO> getPostBy(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostBy(id));
    }

    @GetMapping("/subreddit/{id}")
    @Operation(
            summary = "See all posts by subreddit ID ",
            description = "You will be able so visualize all posts from a specific subreddit "
    )
    public ResponseEntity<List<PostResponseDTO>> getPostsBySubreddit(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("/user/{id}")
    @Operation(
            summary = "See all posts by user ID ",
            description = "You will be able so visualize all posts from a specific user "
    )
    public ResponseEntity<List<PostResponseDTO>> getPostsByUser(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPostsByUser(id));
    }
}
