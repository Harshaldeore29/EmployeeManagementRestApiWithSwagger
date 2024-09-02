package com.springboot.employee.service;

import java.util.List;
import java.util.Optional;

import com.springboot.employee.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeByID(Long employeeID);

	public Employee saveEmployee(Employee employee);

	public Employee updateEmployee(Employee employeeDetails);

	public void deleteEmployee(Employee employee);
}
