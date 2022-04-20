package teksystems.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;

import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

//Repository
@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Integer> {

    public Appointment findByAppointmentId(Integer appointmentId);

    public void deleteById(Integer appointmentId);

    public List<Appointment> findByDate(@Param("date") LocalDate date);

    @Query(value = "select a.appointmentId from Appointment a where a.clinician.clinicianId = :clinicianId")
    public List<Appointment> findByClinicianId(@Param("clinicianId") Integer clinicianId);

    public List<Appointment> findByPatientPatientId(@Param("patientId") Integer patientId);

    public List<Appointment> findByClinicianClinicianId(@Param("clinicianId") Integer clinicianId);
    //Don't need @Param

    public List<Appointment> findByClinicianClinicianIdAndDate(@Param("clinicianId") Integer clinicianId,
                                                                 @Param("date") LocalDate date);

    @Query(value = "select a from Appointment a where a.patient.patientId = :patientId")
    public List<Appointment> getByPatientId(@Param("patientId") Integer patientId);

    public Appointment findByPaqId(@Param("paqId") Integer paqId);

    public List<Appointment> findByDateAndTime(@Param("date") LocalDate date,
                                               @Param("time") LocalTime time);

    @Query(value = "select a.appointmentId from Appointment a where a.patient.patientId = :patientId AND a.date = :date")
    public List<Appointment> findByPatientIdAndDate(@Param("patientId") Integer patientId,
                                                      @Param("date") LocalDate date);

    @Query(value = "select a.appointmentId from Appointment a where a.patient.patientId = :patientId AND a.date = :date AND a.time = :time")
    public Appointment findByPatientIdAndDateAndTime(@Param("patientId") Integer patientId,
                                                       @Param("date") LocalDate date,
                                                       @Param("time") LocalTime time);

    @Query(value = "select a.appointmentId from Appointment a where a.clinician.clinicianId = :clinicianId AND a.date = :date")
    public List<Appointment> findByClinicianIdAndDate(@Param("clinicianId") Integer clinicianId,
                                                      @Param("date") LocalDate date);

    @Query(value = "select a.appointmentId from Appointment a where a.clinician.clinicianId = :clinicianId AND a.date = :date AND a.time = :time")
    public Appointment findByClinicianIdAndDateAndTime(@Param("clinicianId") Integer clinicianId,
                                                       @Param("date") LocalDate date,
                                                       @Param("time") LocalTime time);
}
//    @Query(value = "select u from User u where u.password = :password", nativeQuery = true)
//    public List<User> getByPassword(@Param("password") String password);

