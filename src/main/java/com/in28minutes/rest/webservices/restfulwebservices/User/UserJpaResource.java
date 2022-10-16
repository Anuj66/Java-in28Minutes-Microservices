package com.in28minutes.rest.webservices.restfulwebservices.User;

import com.in28minutes.rest.webservices.restfulwebservices.ExceptionHandling.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.UserRepository.PostRepository;
import com.in28minutes.rest.webservices.restfulwebservices.UserRepository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public ResponseEntity<List<Post>> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return new ResponseEntity<>(user.get().getPosts(), HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }
}
