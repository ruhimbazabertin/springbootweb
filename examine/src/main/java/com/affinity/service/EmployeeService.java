package com.affinity.service;

import com.affinity.model.Employee;
import com.affinity.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;
    public void create(Employee employee){
         repo.save(employee);
    }
    public List<Employee> findEmployees(){
        return repo.findAll();
    }
    public Optional<Employee> findEmployeeId(int id){
        return repo.findById(id);
    }
    //public void delete(Employee employee){
      //  repo.delete(employee);
   // }
    public void delete(int id){
        repo.deleteById(id);
    }
}
