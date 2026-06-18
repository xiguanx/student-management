package net.javaguides.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import net.javaguides.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            student = entityManager.merge(student);
        }
        return student;
    }

    public Optional<Student> findById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
