package Project.management.services;

import Project.management.dto.AppointmentDTO;
import Project.management.dto.PatientDTO;
import Project.management.entities.Appointment;
import Project.management.entities.Patient;
import Project.management.repositories.AppointmentRepository;
import Project.management.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    private Appointment mapToEntity(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatient())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID : " + dto.getPatient()));
        return Appointment.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reasonForVisit(dto.getReasonForVisit())
                .patient(patient)
                .build();
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = mapToEntity(appointmentDTO);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public Appointment updateAppointment(Long id, AppointmentDTO dto) {
        return appointmentRepository.findById(id).map(existing -> {
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());
            existing.setReasonForVisit(dto.getReasonForVisit());
            return appointmentRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Unable to delete: Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

}