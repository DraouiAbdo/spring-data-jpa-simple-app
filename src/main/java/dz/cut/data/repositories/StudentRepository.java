package dz.cut.data.repositories;

import dz.cut.data.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
//     List<Student> findByEtudiant(boolean b);
     Page<Student> findByEtudiant(boolean b, Pageable pageable);

     List<Student> findByEtudiantIsTrueAndScoreLessThan(int score);

     List<Student> findByDateNaissanceBetween(Date begin, Date end);

    @Query("select s from Student s where s.nom like :x and s.score < :y")
    List<Student> chercherEtudiants(@Param("x") String nom, @Param("y") int scoreMin);
}
