package com.example.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeRequestDTO;
import com.example.ems.dto.EmployeeResponseDTO;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public EmployeeResponseDTO add(EmployeeRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email is already existing");
        }

        Employee emp = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .status("ACTIVE") // default status
                .build();

        repository.save(emp);

        return toResponse(emp); // now returns DTO
    }

    @Override
    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto) {
        Employee emp = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .status(dto.getStatus())
                .build();

        repository.update(id, emp);

        return toResponse(emp); // return updated DTO
    }

    @Override
    public void delete(Long id) {
        repository.updateStatus(id, "INACTIVE"); // soft delete
    }

    @Override
    public List<EmployeeResponseDTO> getAll(int page, int size) {
        int offset = page * size;
        return repository.findAll(size, offset)
                .stream()
                .filter(e -> "ACTIVE".equalsIgnoreCase(e.getStatus()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> search(String keyword, int page, int size) {
        int offset = page * size;
        return repository.search(keyword, size, offset)
                .stream()
                .filter(e -> "ACTIVE".equalsIgnoreCase(e.getStatus()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> byDepartment(String dept, int page, int size) {
        int offset = page * size;
        return repository.findByDepartment(dept, size, offset)
                .stream()
                .filter(e -> "ACTIVE".equalsIgnoreCase(e.getStatus()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponseDTO> byStatus(String status, int page, int size) {
        int offset = page * size;
        return repository.findByStatus(status, size, offset)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDTO toResponse(Employee emp) {
        return new EmployeeResponseDTO(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getDepartment(),
                emp.getStatus()
        );
    }
}