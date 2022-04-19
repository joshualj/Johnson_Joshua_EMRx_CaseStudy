package teksystems.casestudy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="appointment_id")
    private Integer appointmentId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

//    @Column(name = "clinician_id")
//    private Integer clinicianId;
//
//    @Column(name = "patient_id")
//    private Integer patientId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clinician_id", nullable = false)
    private Clinician clinician;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    //we need to be able to get a patient's id, not a patient

    @Column(name = "chief_complaint")
    private String chiefComplaint;

    @Column(name = "paq_id")
    private Integer paqId;
}
