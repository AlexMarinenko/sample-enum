package com.example.demo.service;

import java.util.List;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsersWithRoles(List<UserRole> roles) {
        Specification<User> spec = (r, cq, cb) -> r.join("roles").in(roles);
        return userRepository
            .findAll(spec, PageRequest.of(0, 100))
            .getContent();
    }

}
