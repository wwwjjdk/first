package myCode.repository;

import myCode.exception.NotFoundException;
import myCode.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private ConcurrentHashMap<Long,String> map = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Long,String> all(){
        return  map;
    }

    public Post save(Post post){
        if(post.getId()==0){
            post.setId((long)Math.floor(Math.random()*10_000));
            map.put(post.getId(), post.getContent());
        }else if(post.getId()!=0){
            if(map.containsKey(post.getId())){
                map.remove(post.getId());
                map.put(post.getId(), post.getContent());
            }else{
                throw new NotFoundException("Post not saved {id:" + post.getId() + "}");
            }
        }
      return  post;
    }

    public Optional<String> getById(long id) {
        return Optional.ofNullable(map.get(id));
    }
    public boolean removeById(long id) {
        if(map.containsKey(id)){
            map.remove(id);
            return true;
        }else{
            return false;
        }
    }
}
