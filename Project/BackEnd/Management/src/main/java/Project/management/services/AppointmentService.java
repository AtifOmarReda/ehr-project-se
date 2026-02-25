package Project.management.services;

import Project.management.dto.AppointmentDTO;
import Project.management.entities.Appointment;
import Project.management.entities.Patient;
import Project.management.exceptions.ValidationException;
import Project.management.repositories.AppointmentRepository;
import Project.management.repositories.PatientRepository;
import Project.management.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final RemoteIsDoctorService remoteIsDoctorService;

//    private Appointment mapToEntity(AppointmentDTO dto) {
//        Patient patient = patientRepository.findById(dto.getPatient()).orElseThrow(() -> new RuntimeException("Patient not found with ID : " + dto.getPatient()));
//        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId;
//        if (currentUser.getRole().equalsIgnoreCase("Doctor")) {
//            userId = currentUser.getId();
//        } else if (currentUser.getRole().equalsIgnoreCase("ADMIN")) {
//            if (currentUser.isDoctor()) {
//                if (dto.getUserId() == null) {
//                    userId = currentUser.getId();
//                } else {
//                    if (remoteIsDoctorService.isUserDoctor(dto.getUserId())) {
//                        userId = dto.getUserId();
//                    } else {
//                        throw new ValidationException("Patient not found or not doctor");
//                    }
//                }
//            } else {
//                if (dto.getUserId() == null) {
//                    throw new ValidationException("Le champ userId est obligatoire");
//                } else {
//                    if (remoteIsDoctorService.isUserDoctor(dto.getUserId())) {
//                        userId = dto.getUserId();
//                    } else {
//                        throw new ValidationException("Patient not found or not doctor");
//                    }
//                }
//            }
//        } else {
//            if (dto.getUserId() == null) {
//                throw new ValidationException("Le champ userId est obligatoire");
//            } else {
//                if (remoteIsDoctorService.isUserDoctor(dto.getUserId())) {
//                    userId = dto.getUserId();
//                } else {
//                    throw new ValidationException("Patient not found or not doctor");
//                }
//            }
//        }
//        return Appointment.builder().startDate(dto.getStartDate()).endDate(dto.getEndDate()).reasonForVisit(dto.getReasonForVisit()).patient(patient).userId(userId).build();
//    }

    private Appointment mapToEntity(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatient()).orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + dto.getPatient()));
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = currentUser.getRole().toUpperCase();
        Long userId = determineDoctorId(dto, currentUser, role);
        return Appointment.builder().startDate(dto.getStartDate()).endDate(dto.getEndDate()).reasonForVisit(dto.getReasonForVisit()).patient(patient).userId(userId).build();
    }

    private Long determineDoctorId(AppointmentDTO dto, UserPrincipal currentUser, String role) {
        if ("DOCTOR".equals(role)) {
            return currentUser.getId();
        }
        Long targetUserId = Optional.ofNullable(dto.getUserId()).filter(id -> id != null).orElseGet(() -> {
            if ("ADMIN".equals(role) && currentUser.isDoctor()) {
                return currentUser.getId();
            }
            throw new ValidationException("Le champ userId est obligatoire");
        });
        if (!remoteIsDoctorService.isUserDoctor(targetUserId)) {
            throw new ValidationException("Patient not found or not doctor");
        }
        return targetUserId;
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