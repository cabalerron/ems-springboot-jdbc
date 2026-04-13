package com.example.ems.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.dto.EmployeeRequestDTO;
import com.example.ems.dto.EmployeeResponseDTO;
import com.example.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    
    // ✅ CREATE (NM004 style using params)
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> create(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String status) {

        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setDepartment(department);
        dto.setStatus(status);

        return ResponseEntity.status(201).body(service.add(dto));
    }

    // ✅ UPDATE
    @PostMapping("/update")
    public ResponseEntity<EmployeeResponseDTO> update(
            @RequestParam Long id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String department,
            @RequestParam String status) {

        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setDepartment(department);
        dto.setStatus(status);

        return ResponseEntity.ok(service.update(id, dto));
    }

    // ✅ DELETE (soft delete)
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.ok("Employee marked as INACTIVE");
    }

    // ✅ GET ALL (with params)
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(service.getAll(page, size));
    }

    // ✅ SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDTO>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(service.search(keyword, page, size));
    }
}