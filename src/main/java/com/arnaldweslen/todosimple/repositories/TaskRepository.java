package com.arnaldweslen.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arnaldweslen.todosimple.models.Task;

@Repository
public interface  TaskRepository extends JpaRepository<Task, Long>{
    
    
     List<Task> findByUser_id(long id);


    // @Query (value= "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery= true)
    // List<Task> findByUser_id(@Param ("id") long id);
}
