package com.example.lab02.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lab02.model.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    
    static Map<Long, Employee> employees =
    Collections.synchronizedMap(new HashMap<Long, Employee>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Employee> getEmployeeList() {
        List<Employee> r = new ArrayList<Employee>(employees.values());
        return r;
    }

    @RequestMapping(value ="/", method = RequestMethod.POST)
    public Employee postEmployee(@RequestBody Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id) {
        if (!employees.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found: id=" + id);
        }
        return employees.get(id);
    }
}
