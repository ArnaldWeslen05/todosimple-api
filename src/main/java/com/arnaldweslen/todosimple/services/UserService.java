package com.arnaldweslen.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arnaldweslen.todosimple.models.User;
import com.arnaldweslen.todosimple.repositories.TaskRepository;
import com.arnaldweslen.todosimple.repositories.UserRepository;



@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findByid(long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuario não encontrado! Id: " + id + ", Tipo: "+ User.class.getName()
        ));
    }

   @Transactional
    public User create(User obj) {
         obj.setId(0);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findByid(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(long id){
        findByid(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidade relacional");
        }
    }
}
