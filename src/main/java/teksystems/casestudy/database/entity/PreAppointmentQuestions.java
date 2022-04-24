package teksystems.casestudy.database.entity;

import lombok.*;
import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="pre_appointment_questions")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class PreAppointmentQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "complaint")
    private String complaint;

    @NonNull
    @Column(name = "onset")
    private String onset;

    @NonNull
    @Column(name = "location")
    private String location;

    @NonNull
    @Column(name = "duration")
    private String duration;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "alleviating")
    private String alleviating;

    @NonNull
    @Column(name = "radiation")
    private String radiation;

    @NonNull
    @Column(name = "temporal_patterns")
    private String temporalPatterns;

    @NonNull
    @Column(name = "symptoms")
    private String symptoms;
}
