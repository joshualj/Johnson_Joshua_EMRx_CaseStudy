package teksystems.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;

import java.util.Date;
import java.util.List;

//Repository
@Repository
public interface ClinicianDAO extends JpaRepository<Clinician, Integer> {

    public Clinician findByClinicianId(@Param("clinicianId") Integer clinicianId);

    public Clinician findByUserId(@Param("userId") Integer userId);

    public Clinician findByEmail(@Param("email") String email);

    public List<Clinician> findByPassword(@Param("password") String password);

    public List<Clinician> findByFirstName(@Param("firstName") String firstName);

    public List<Clinician> findByLastName(@Param("lastName") String lastName);

    public List<Clinician> findByFirstNameIgnoreCaseContaining(@Param("firstName") String firstName);

    public List<Clinician> findByLastNameIgnoreCaseContaining(@Param("lastName") String lastName);

    public List<Clinician> findByTitle(@Param("title") String title);

    public List<Clinician> findByDepartment(@Param("department") String department);

    public List<Clinician> findByLanguagesContaining(@Param("languages") String languages);

    public List<Clinician> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

//    @Query(value = "select u from User u where u.password = :password", nativeQuery = true)
//    public List<User> getByPassword(@Param("password") String password);
}
