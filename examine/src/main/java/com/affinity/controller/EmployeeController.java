package com.affinity.controller;

import com.affinity.model.Employee;
import com.affinity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping("/")
    public String homapage(){

        return "index";
    }
    @GetMapping("/employeeForm")
    public String EmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employeeForm";
    }
    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") Employee employee){
            service.create(employee);
        return "redirect:/employees";
    }
    @GetMapping("/employees")
    public String allEmployees(Model model){
       List<Employee> employees =  service.findEmployees();
       model.addAttribute("employees", employees);

        return "employeeList";
    }
    @GetMapping("/edit/{id}")
    public String showEmployeeUpdateForm(@PathVariable("id") int id, Model model ){
        Employee employee = service.findEmployeeId(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Employee Id:"+id));
        model.addAttribute("employee", employee);
        return"employeeUpdateForm";
    }
    @PostMapping("/update/{id}")
    public String updateEmployee(@ModelAttribute("employee")  Employee employee, @PathVariable("id") int id){
                employee.setId(id);
                service.create(employee);
        return "redirect:/employees";
    }
    //
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id, Model model){
        Optional<Employee> employee = service.findEmployeeId(id);
        if (employee.isPresent()) {
            service.delete(id);
        }else{
            model.addAttribute("error","no emplotyee");
        }
          //  service.delete(employee);

        return "redirect:/employees";
    }
}
