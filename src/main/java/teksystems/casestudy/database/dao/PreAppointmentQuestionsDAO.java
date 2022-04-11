package teksystems.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.PreAppointmentQuestions;

import java.util.Date;
import java.util.List;

@Repository
public interface PreAppointmentQuestionsDAO extends JpaRepository<PreAppointmentQuestions, Integer> {


}
