package Project.management.controllers;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.services.MedicalHistoryInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical_history_info")
@RequiredArgsConstructor
public class MedicalHistoryInfoController {

    private final MedicalHistoryInfoService medicalHistoryInfoService;

    @PostMapping
    public ResponseEntity<MedicalHistoryInfo> create(@Valid @RequestBody MedicalHistoryInfoDTO dto) {
        return new ResponseEntity<>(medicalHistoryInfoService.saveMedicalHistoryInfo(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MedicalHistoryInfo> getAll() {
        return medicalHistoryInfoService.getAllMedicalHistoryInfos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryInfo> getOne(@PathVariable Long id) {
        return medicalHistoryInfoService.getMedicalHistoryInfoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistoryInfo> update(@PathVariable Long id, @Valid @RequestBody MedicalHistoryInfoDTO dto) {
        return ResponseEntity.ok(medicalHistoryInfoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            medicalHistoryInfoService.deleteMedicalHistoryInfo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
