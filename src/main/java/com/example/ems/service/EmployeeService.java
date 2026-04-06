package com.example.ems.service;

import java.util.List;

import com.example.ems.dto.EmployeeRequestDTO;
import com.example.ems.dto.EmployeeResponseDTO;

public interface EmployeeService {

    EmployeeResponseDTO add(EmployeeRequestDTO dto);

    EmployeeResponseDTO update(Long id, EmployeeRequestDTO dto);

    void delete(Long id);

    List<EmployeeResponseDTO> getAll(int page, int size);

    List<EmployeeResponseDTO> search(String keyword, int page, int size);

    List<EmployeeResponseDTO> byDepartment(String dept, int page, int size);

    List<EmployeeResponseDTO> byStatus(String status, int page, int size);
}