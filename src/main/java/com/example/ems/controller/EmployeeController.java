package com.example.ems.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.example.ems.dto.EmployeeRequestDTO;
import com.example.ems.dto.EmployeeResponseDTO;
import com.example.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Add employee with validation + unique email check
    @PostMapping
    public EmployeeResponseDTO add(@Valid @RequestBody EmployeeRequestDTO dto) {
        return service.add(dto);
    }

    // Update employee
    @PutMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO dto) {
        return service.update(id, dto);
    }

    // Soft delete employee
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Employee marked as INACTIVE";
    }

    // Get all active employees with pagination
    @GetMapping
    public List<EmployeeResponseDTO> getAll(@RequestParam int page,
                                            @RequestParam int size) {
        if (page < 0) throw new IllegalArgumentException("Page index must be >= 0");
        if (size <= 0) throw new IllegalArgumentException("Page size must be > 0");

        return service.getAll(page, size);
    }

    // Search employees by keyword
    @GetMapping("/search")
    public List<EmployeeResponseDTO> search(@RequestParam String keyword,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        if (page < 0) throw new IllegalArgumentException("Page index must be >= 0");
        if (size <= 0) throw new IllegalArgumentException("Page size must be > 0");

        return service.search(keyword, page, size);
    }
}