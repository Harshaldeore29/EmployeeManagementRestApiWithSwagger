package com.springboot.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.employee.entity.Employee;
import com.springboot.employee.repo.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeByID(Long employeeID) {
		return employeeRepository.findById(employeeID);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employeeDetails) {
		return employeeRepository.save(employeeDetails);
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

}
