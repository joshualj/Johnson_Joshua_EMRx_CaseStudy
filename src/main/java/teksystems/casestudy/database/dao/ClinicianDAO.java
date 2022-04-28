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

    public List<Clinician> findByLanguagesIgnoreCaseContaining(@Param("languages") String languages);

    public List<Clinician> findByDepartment(@Param("department") String department);



    //To be used for later search depth
//    public List<Clinician> findByFirstNameIgnoreCaseContaining(@Param("firstName") String firstName);
//
//    public List<Clinician> findByLastNameIgnoreCaseContaining(@Param("lastName") String lastName);

    public List<Clinician> findByTitle(@Param("title") String title);

}
