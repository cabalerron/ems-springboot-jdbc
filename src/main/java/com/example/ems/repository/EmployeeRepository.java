package com.example.ems.repository;

import java.util.List;

import com.example.ems.entity.Employee;

public interface EmployeeRepository {
    int save(Employee emp);
    int update(Long id, Employee emp);
    int updateStatus(Long id, String status);
    boolean existsByEmail(String email);
    List<Employee> findAll(int limit, int offset);
    List<Employee> search(String keyword, int limit, int offset);
    List<Employee> findByDepartment(String dept, int limit, int offset);
    List<Employee> findByStatus(String status, int limit, int offset);
}