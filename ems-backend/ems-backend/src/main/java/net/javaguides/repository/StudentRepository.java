package net.javaguides.repository;

import lombok.AllArgsConstructor;
import net.javaguides.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setEmail(rs.getString("email"));
        return student;
    };

    public Student save(Student student) {
        if (student.getId() == null) {
            String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getEmail());
            Long id = jdbcTemplate.queryForObject("SELECT lastval()", Long.class);
            student.setId(id);
        } else {
            String sql = "UPDATE students SET first_name=?, last_name=?, email=? WHERE id=?";
            jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
        }
        return student;
    }

    public Optional<Student> findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        List<Student> results = jdbcTemplate.query(sql, studentRowMapper, id);
        return results.stream().findFirst();
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
