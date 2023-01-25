package eu.dreamTeam.isabackend.service.appointment;

import eu.dreamTeam.isabackend.dto.*;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidCreateAppointmentDTOException;
import eu.dreamTeam.isabackend.model.Appointment;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.User;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.repository.AppointmentRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import eu.dreamTeam.isabackend.repository.UserRepository;
import eu.dreamTeam.isabackend.service.email.EmailService;
import eu.dreamTeam.isabackend.service.qr_code.QRCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class AppointmentService {
    private static ReentrantLock lock = new ReentrantLock();
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BloodBankRepository bloodBankRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    public CreateAppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO) throws InterruptedException {
        if(lock.isLocked())
            throw new InterruptedException();
        lock.lock();
        try {
            Appointment appointment = CreateAppointmentDTOToEntity(createAppointmentDTO);
            appointmentRepository.save(appointment);
            Thread.sleep(10000);
            return  createAppointmentDTO;
        }catch (Exception ex){
            throw new InvalidCreateAppointmentDTOException();
        }
        finally {
            lock.unlock();
        }
}

    private Appointment CreateAppointmentDTOToEntity(CreateAppointmentDTO createAppointmentDTO) {
        LocalDateTime localDateTime = TransformStringToLocalDateTime(createAppointmentDTO.getDate());
        Set<Staff> staffs = new HashSet<>();
        if (createAppointmentDTO.getStaff() != null){
            for(Long id : createAppointmentDTO.getStaff()){
                staffs.add(staffRepository.findById(id).stream().findFirst().orElse(null));
            }
        }
        if(createAppointmentDTO.getPrice() < 0) throw new InvalidCreateAppointmentDTOException();
        return  Appointment.builder()
                .date(localDateTime)
                .duration(createAppointmentDTO.getDuration())
                .status(AppointmentStatus.FREE)
                .description(createAppointmentDTO.getDescription())
                .price(createAppointmentDTO.getPrice())
                .bloodBankForAppointment(createAppointmentDTO.getBloodBankForAppointment() == null ? null : bloodBankRepository.findById(createAppointmentDTO.getBloodBankForAppointment()).stream().findFirst().orElse(null))
                .staff(staffs)
                .build();
    }

    private LocalDateTime TransformStringToLocalDateTime(String date) {
        String[] parts = date.split("-");
        LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), Integer.valueOf(parts[4]), 0);
        LocalDateTime currentDateTime = LocalDateTime.now();
        if(currentDateTime.isAfter(localDateTime)) throw new InvalidCreateAppointmentDTOException();
        return localDateTime;
    }

    public List<ScheduleAppointmentDTO> getAllFreeAppointments() {
        try {
             var freeAppointments = appointmentRepository.findAllFreeAppointments();
             List<ScheduleAppointmentDTO> scheduledAppointmentsDTOs = new ArrayList<ScheduleAppointmentDTO>();
             for(Appointment a : freeAppointments) {
                 scheduledAppointmentsDTOs.add(FromAppointmentToScheduleAppointmentDto(a));
             }
             return scheduledAppointmentsDTOs;
        } catch (Exception e) {
            return null;
        }
    }

    public ScheduleAppointmentDTO FromAppointmentToScheduleAppointmentDto(Appointment appointment) {
        return ScheduleAppointmentDTO.builder()
                .id(appointment.getId())
                .date(appointment.getDate().toString())
                .duration(appointment.getDuration())
                .description(appointment.getDescription())
                .price(appointment.getPrice())
                .bloodBankForAppointment(convertFromBloodBankToBloodBankDto(appointment.getBloodBankForAppointment()))
                .staff(null)
                .appointmentStatus(appointment.getStatus())
                .userEmail(appointment.getUserEmail())
                .QR(appointment.getQR())
                .build();
    }

    private BloodBankDTO convertFromBloodBankToBloodBankDto(BloodBank bloodBankForAppointment) {
        return BloodBankDTO.builder()
                .id(bloodBankForAppointment.getId())
                .description(bloodBankForAppointment.getDescription())
                .averageRating(bloodBankForAppointment.getAverageRating())
                .name(bloodBankForAppointment.getName())
                .city(bloodBankForAppointment.getAddress().getCity())
                .country(bloodBankForAppointment.getAddress().getCountry())
                .endTime(bloodBankForAppointment.getWorkTime().getEndTime())
                .startTime(bloodBankForAppointment.getWorkTime().getStartTime())
                .number(bloodBankForAppointment.getAddress().getNumber())
                .street(bloodBankForAppointment.getAddress().getStreet())
                .build();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ScheduleAppointmentDTO scheduleAppointment(ScheduleAppointmentDTO scheduleAppointmentDTO){
        Appointment appointment = appointmentRepository.findById(scheduleAppointmentDTO.getId()).stream().findFirst().orElse(null);
        if(appointment == null) return null;
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointment.setUserEmail(scheduleAppointmentDTO.getUserEmail());
        try{
            var QR = QRCodeGenerator.getQRCodeImage(appointment.toString(), 250, 250);
            appointment.setQR(QR);
            emailService.sendMessageWithAttachment(scheduleAppointmentDTO.getUserEmail(),
                    "Schedule appointment confirmation",
                    "You have successfully scheduled appointment.",
                    QR);
        }catch (Exception e){
            log.error("Error occurred during email sending...");
            log.error(e.getMessage());
        }
        appointment = appointmentRepository.save(appointment);
        return scheduleAppointmentDTO;
    }

    public List<ScheduleAppointmentDTO> getAllAppointmentsByUserEmail(String userEmail) {
        try {
            var appointmentsByUserEmail = appointmentRepository.findAllByUserEmail(userEmail);
            var scheduledAppointmentsDTOs = new ArrayList<ScheduleAppointmentDTO>();
            for(Appointment a : appointmentsByUserEmail) {
                scheduledAppointmentsDTOs.add(FromAppointmentToScheduleAppointmentDto(a));
            }
            return scheduledAppointmentsDTOs;
        } catch (Exception e) {
            return null;
        }
    }


    public List<ScheduleAppointmentDTO> getAllAppointmentsBySelectedDateTime(String selectedDateTime) {
        try {
            var selectedDateTimeConvertedToLocalDateTime = TransformStringToLocalDateTime(selectedDateTime);
            var allAppointments = appointmentRepository.findAll();
            List<ScheduleAppointmentDTO> scheduledAppointmentsDTOs = new ArrayList<ScheduleAppointmentDTO>();
            for(Appointment a : allAppointments) {
                if(a.getDate().equals(selectedDateTimeConvertedToLocalDateTime)){
                    scheduledAppointmentsDTOs.add(FromAppointmentToScheduleAppointmentDto(a));
                }
            }
            return scheduledAppointmentsDTOs;
        } catch (Exception e) {
            return null;
        }
    }

    public ScheduleAppointmentDTO unscheduleAppointment(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        Appointment appointment = appointmentRepository.findById(scheduleAppointmentDTO.getId()).stream().findFirst().orElse(null);
        if(appointment == null) return null;

        appointment.setStatus(AppointmentStatus.FREE);
        appointment.setUserEmail(null);
        appointmentRepository.save(appointment);
        return scheduleAppointmentDTO;
    }

    public void cancel(Long id) {
        Appointment appointment = appointmentRepository.findById(id).get();
        if(appointment == null) return;
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
    }

    public void finish(Long id, String text) {
        Appointment appointment = appointmentRepository.findById(id).get();
        if(appointment == null) return;
        appointment.setStatus(AppointmentStatus.DONE);
        appointment.setDescription(text);
        appointmentRepository.save(appointment);
    }

    public void didntAppear(Long id, String email) {
        Appointment appointment = appointmentRepository.findById(id).get();
        if(appointment == null) return;
        appointment.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appointment);
        User user = userRepository.getUserByEmail(email);
        user.setPenalties(user.getPenalties() + 1);
        userRepository.save(user);
    }

    public List<ScheduleAppointmentDTO> getAllFreeAppointmentsByBloodBankId(Long bloodBankId) {
        try {
            var freeAppointments = appointmentRepository.findAllFreeAppointments();
            var appointmentsByBloodBank = freeAppointments.stream().filter(
                    x->x.getBloodBankForAppointment().getId().equals(bloodBankId)).toList();
            var scheduledAppointmentsDTOs = new ArrayList<ScheduleAppointmentDTO>();
            for(Appointment a : appointmentsByBloodBank) {
                scheduledAppointmentsDTOs.add(FromAppointmentToScheduleAppointmentDto(a));
            }
            return scheduledAppointmentsDTOs;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean checkForAppointmentInLast6Months(String userEmail) {
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(getAllAppointmentsByUserEmail(userEmail));
        for(ScheduleAppointmentDTO sad : scheduledAppointmentsDTOs.getScheduleAppointmentDTOS()){
            if(sad.getAppointmentStatus().equals(AppointmentStatus.DONE)) {
                if(LocalDateTime.now().minusMonths(6).isBefore(LocalDateTime.parse(sad.getDate()))){
                    return true;
                }
            }
        }
        return false;
    }

    public List<AppointmentDTO> getUserAppointments(String email){
        var appointments = appointmentRepository.findAllByUserEmail(email);
        return getAppointmentDTOS(appointments);
    }

    private List<AppointmentDTO> getAppointmentDTOS(List<Appointment> appointments) {
        List<AppointmentDTO> dtos = new ArrayList<AppointmentDTO>();
        for(Appointment appointment: appointments){
            AppointmentDTO dto = new AppointmentDTO();
            dto.setAppointmentStatus(appointment.getStatus());
            dto.setId(appointment.getId());
            dto.setDate(appointment.getDate().toString());
            dto.setDuration(appointment.getDuration());
            dto.setEmail(appointment.getUserEmail());
            dto.setUser("");
            dto.setDescription(appointment.getDescription());
            if(appointment.getStatus() != AppointmentStatus.FREE && appointment.getStatus() !=
                    AppointmentStatus.CANCELED && appointment.getUserEmail()!=null){
                User user = userRepository.getUserByEmail(appointment.getUserEmail());
                dto.setUser(user.getName() + " " + user.getSurname());
            }
            dtos.add(dto);
        }

        return dtos;
    }

    public List<AppointmentDTO> getAllAppointments(){
        var appointments = appointmentRepository.findAll();
        return getAppointmentDTOS(appointments);
    }

    public AppointmentDTO getTextFromAppointmentQR(File imageFile)
    {
        String appointmentString = QRCodeGenerator.getTextFromImage(imageFile);
        Appointment appointment = new Appointment();
        appointment = appointment.fromString(appointmentString);
        if (appointment.getUserEmail() == null)
            return null;
        List<AppointmentDTO> appointments = getUserAppointments(appointment.getUserEmail());
        for (AppointmentDTO dto : appointments){
            if (dto.getDate().equals(appointment.getDate().toString())){
                return dto;
            }
        }
        return null;
    }
//    private String adjustSelectedDateTimeForQuery(String selectedDateTime) {
//        //"dd.MM.yyyy. HH:mm"
//        String[] parts = selectedDateTime.split("-");
//        if(Integer.valueOf(parts[4]) == 0) {
//            String adjustedSelectedDateTime = Integer.valueOf(parts[2]) + "." + Integer.valueOf(parts[1]) + "." +
//                    Integer.valueOf(parts[0])+ "." + " " + Integer.valueOf(parts[3]) + ":" + "00" + ":" + "00";
//            return adjustedSelectedDateTime;
//        } else {
//            String adjustedSelectedDateTime = Integer.valueOf(parts[2]) + "." + Integer.valueOf(parts[1]) + "." +
//                    Integer.valueOf(parts[0])+ "." + " " + Integer.valueOf(parts[3]) + ":" + Integer.valueOf(parts[4]) + ":" + "00";
//            return adjustedSelectedDateTime;
//        }
//    }
}
