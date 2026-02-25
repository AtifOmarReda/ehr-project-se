package Project.management.controllers;

import Project.management.dto.ConsultationDTO;
import Project.management.entities.Consultation;
import Project.management.services.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<Consultation> create(@Valid @RequestBody ConsultationDTO dto) {
        return new ResponseEntity<>(consultationService.saveConsultation(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Consultation> getAll() {
        return consultationService.getAllConsultations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getOne(@PathVariable Long id) {
        return consultationService.getConsultationById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> update(@PathVariable Long id, @Valid @RequestBody ConsultationDTO dto) {
        return ResponseEntity.ok(consultationService.updateConsultation(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}