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

    public void deleteByAppointmentId(Integer appointmentId);

    @Query(value = "select a.appointmentId from Appointment a where a.clinician.clinicianId = :clinicianId")
    public List<Appointment> findByClinicianId(@Param("clinicianId") Integer clinicianId);

    public List<Appointment> findByPatientPatientId(@Param("patientId") Integer patientId);

    public List<Appointment> findByClinicianClinicianIdAndDate(@Param("clinicianId") Integer clinicianId,
                                                                 @Param("date") LocalDate date);


    List<Appointment> findByDateAndTimeLessThanEqualAndTimeGreaterThanEqualAndPatientPatientId(
                                              @Param("date") LocalDate date,
                                              @Param("endTime") LocalTime endTime,
                                              @Param("startTime") LocalTime startTime,
                                              @Param("patientId") Integer patientId);


    public Appointment findByPaqId(@Param("paqId") Integer paqId);

    //findByDateAndTime will be used for downstream Ajax implementation,
    // to restrict Appointment editor clinician selectors based on date and time
    public List<Appointment> findByDateAndTime(@Param("date") LocalDate date,
                                               @Param("time") LocalTime time);
}


