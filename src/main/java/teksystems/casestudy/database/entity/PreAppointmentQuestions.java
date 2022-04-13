package teksystems.casestudy.database.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="pre_appointment_questions")
@AllArgsConstructor
@NoArgsConstructor
public class PreAppointmentQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "onset")
    private String onset;

    @Column(name = "location")
    private String location;

    @Column(name = "duration")
    private String duration;

    @Column(name = "description")
    private String description;

    @Column(name = "alleviating")
    private String alleviating;

    @Column(name = "radiation")
    private String radiation;

    @Column(name = "temporal_patterns")
    private String temporalPatterns;

    @Column(name = "symptoms")
    private String symptoms;
}
