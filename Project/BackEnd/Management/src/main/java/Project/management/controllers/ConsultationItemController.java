package Project.management.controllers;

import Project.management.dto.ConsultationItemChildDTO;
import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.ConsultationItem;
import Project.management.services.ConsultationItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation-item")
@RequiredArgsConstructor
public class ConsultationItemController {

    private final ConsultationItemService consultationItemService;

    @PostMapping
    public ResponseEntity<ConsultationItem> create(@Valid @RequestBody ConsultationItemDTO dto) {
        return new ResponseEntity<>(consultationItemService.saveConsultationItem(dto), HttpStatus.CREATED);
    }

    @PutMapping("/add-child/{id}")
    public ResponseEntity<ConsultationItem> addChild(@PathVariable Long id, @Valid @RequestBody ConsultationItemChildDTO dto) {
        return ResponseEntity.ok(consultationItemService.addChild(id, dto));
    }

    @GetMapping
    public List<ConsultationItem> getAll() {
        return consultationItemService.getAllConsultationItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationItem> getOne(@PathVariable Long id) {
        return consultationItemService.getConsultationItemById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationItem> update(@PathVariable Long id, @Valid @RequestBody ConsultationItemDTO dto) {
        return ResponseEntity.ok(consultationItemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            consultationItemService.deleteConsultationItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}