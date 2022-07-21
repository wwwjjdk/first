package myCode.service;

import myCode.exception.NotFoundException;
import myCode.model.Post;
import myCode.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public ConcurrentHashMap<Long, String> all() {
        return repository.all();
    }

    public String getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public boolean removeById(long id) {
        return repository.removeById(id);
    }
}
