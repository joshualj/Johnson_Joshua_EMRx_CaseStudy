package teksystems.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.casestudy.database.entity.User;

import java.util.List;

//Repository
@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    //this is a native query which is SQL like you would execute in workbench
//    @Query(value= "select * from users where user_id = :user_id", nativeQuery = true)

    //there are 3 ways to execute a query
    // 1) via @Query with JPA / JQL / HQL
    // 2) via @Query with a native query
    // 3) by using a function for spring to do the query with no query


    // this is a JPA Query like hibernate JQL or HQL query
    // @Query("select u from User u where u.email = :email")
    public List<User>findAll();

    public List<User> findByFirstName(@Param("firstName") String firstName);

    public List<User> findByLastName(@Param("lastName") String lastName);

    public User findByEmail(@Param("email") String email);

    public User findByUserId(@Param("userId") Integer userId);

    public List<User> findByUserRole(@Param("userRole") String userType);

    @Query(value = "select u from User u where u.password = :password", nativeQuery = true)
    public List<User> getByPassword(@Param("password") String password);
}
