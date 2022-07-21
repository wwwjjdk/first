package myCode.conroller;

import myCode.model.Post;
import myCode.service.PostService;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service){
        this.service = service;
    }
    @GetMapping
    public ConcurrentHashMap<Long,String> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable long id){
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post){
        return service.save(post);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.removeById(id);
    }
}
