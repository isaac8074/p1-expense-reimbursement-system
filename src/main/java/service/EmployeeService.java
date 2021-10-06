package service;

import models.Employees;
import repository.EmployeeRepository;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService()    {
        this.employeeRepository = new EmployeeRepository();
    }

    public Employees getByUsername(String username)   {
        return this.employeeRepository.getByUsername(username);
    }
}
