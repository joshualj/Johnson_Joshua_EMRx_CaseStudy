package teksystems.casestudy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "clinicians")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Clinician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinician_id")
    private Integer clinicianId;

    @NonNull
    @Column(name = "user_id")
    private Integer userId;
    //probably delete this

//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "department")
    private String department;

//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "password")
//    private String password;

    @NonNull
    @Column(name = "languages")
    private String languages;

    @OneToMany(mappedBy = "clinician", fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    //private List<String> languages; = new ArrayList<>();
}
