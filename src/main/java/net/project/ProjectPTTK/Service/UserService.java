package net.project.ProjectPTTK.Service;

import net.project.ProjectPTTK.Entities.User;
import net.project.ProjectPTTK.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
        @Autowired
        private UserRepository repo;

        public List<User> listAll() { return repo.findAll(); }

        public void save(User user) { repo.save(user); }

        public User get(Integer id) {
                return repo.findById(id).get();
        }

        public void delete(Integer id) { repo.deleteById(id); }
}
