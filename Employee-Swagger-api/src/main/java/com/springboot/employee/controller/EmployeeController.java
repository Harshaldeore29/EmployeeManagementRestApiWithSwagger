
package com.springboot.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.entity.Employee;
import com.springboot.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "View a List of All Employees")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Retrieved List"),
        @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @ApiOperation(value = "Get Employee by ID")
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @ApiParam(value = "Employee ID to retrieve the Employee Details") @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeByID(id).orElse(null); 
        return ResponseEntity.ok().body(employee);
    }

    @ApiOperation(value = "Add New Employee")
    @PostMapping("/employees")
    public Employee createEmployee(
            @ApiParam(value = "Employee Details to be saved") @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @ApiOperation(value = "Update an Employee")
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @ApiParam(value = "Employee ID to update") @PathVariable Long id,
            @ApiParam(value = "Employee updated details") @RequestBody Employee employeeDetail) {
        Employee employee = employeeService.getEmployeeByID(id).orElse(null); 
       
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @ApiOperation(value = "Delete an Employee")
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(
            @ApiParam(value = "Employee ID to delete") @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeByID(id).orElse(null); 
        Map<String, Boolean> response = new HashMap<>();
        if (employee != null) {
            employeeService.deleteEmployee(employee);
            response.put("deleted", Boolean.TRUE);
        } else {
            response.put("deleted", Boolean.FALSE); 
        }
        return response;
    }
}
