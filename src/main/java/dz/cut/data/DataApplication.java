package dz.cut.data;

import dz.cut.data.entities.Student;
import dz.cut.data.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    // Creation of dummy data using save() method
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 50; i++) {
            studentRepository.save(new Student(null,"Khaled",new Date(),Math.random()>0.5?false:true,(int)(Math.random()*100)));
        }

        // fetching for all records of students table
//        List<Student> students = studentRepository.findAll();
        Page<Student> students = studentRepository.findAll(PageRequest.of(2,5));
        System.out.println("Total pages "+students.getTotalPages());
        System.out.println("Total elements "+students.getTotalElements());
        System.out.println("page nbr "+students.getNumber());

//        List<Student> content = students.getContent();
//        content.forEach(p-> {
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDateNaissance());
//            System.out.println(p.isEtudiant());
//            System.out.println("----------------");
//        });


        Student s = studentRepository.findById(1L).orElse(null);
        if(s != null) {
            System.out.println(s.getNom());
        }

        // updating one entry in one record
        s.setScore(100);
        studentRepository.save(s);

        // deleting
        studentRepository.deleteById(2L);

//        List<Student> students1 = studentRepository.findByEtudiant(true);
//        Page<Student> students1 = studentRepository.findByEtudiant(true,PageRequest.of(2,5));
//        students1.forEach(p->{
//            System.out.println("nom : "+p.getNom());
//        });

//        List<Student> students2 = studentRepository.findByEtudiantIsTrueAndScoreLessThan(40);
//        students2.forEach(p->{
//            System.out.println("is student "+p.isEtudiant());
//            System.out.println("score : "+p.getScore());
//        });

//        List<Student> student = studentRepository.findByDateNaissanceBetween(10.03.1991,20.05.2000);
//        students.forEach(p->{
//            System.out.println("Date de naissance "+p.getDateNaissance());
//        });

        // method defined using HQL (JPA QL)
        List<Student> studentsWanted = studentRepository.chercherEtudiants("%mi%",30);
        studentsWanted.forEach(p->{
            System.out.println("is student "+p.isEtudiant());
            System.out.println("score : "+p.getScore());
        });
    }
}
