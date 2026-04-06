package com.example.ems.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> Employee.builder()
            .id(rs.getLong("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .email(rs.getString("email"))
            .department(rs.getString("department"))
            .status(rs.getString("status"))
            .build();

    @Override
    public int save(Employee emp) {
        String sql = "INSERT INTO employees(first_name, last_name, email, department, status) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartment(),
                emp.getStatus());
    }

    @Override
    public int update(Long id, Employee emp) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, department=?, status=? WHERE id=?";
        return jdbcTemplate.update(sql,
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartment(),
                emp.getStatus(),
                id);
    }

    @Override
    public int updateStatus(Long id, String status) {
        String sql = "UPDATE employees SET status=? WHERE id=?";
        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM employees WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public List<Employee> findAll(int limit, int offset) {
        String sql = "SELECT * FROM employees ORDER BY id LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, limit, offset);
    }

    @Override
    public List<Employee> search(String keyword, int limit, int offset) {
        String sql = "SELECT * FROM employees WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?) LIMIT ? OFFSET ?";
        String key = "%" + keyword + "%";
        return jdbcTemplate.query(sql, rowMapper, key, key, limit, offset);
    }

    @Override
    public List<Employee> findByDepartment(String dept, int limit, int offset) {
        String sql = "SELECT * FROM employees WHERE department=? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, dept, limit, offset);
    }

    @Override
    public List<Employee> findByStatus(String status, int limit, int offset) {
        String sql = "SELECT * FROM employees WHERE status=? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, status, limit, offset);
    }
}