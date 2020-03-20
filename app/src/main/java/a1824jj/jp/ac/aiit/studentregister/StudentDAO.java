package a1824jj.jp.ac.aiit.studentregister;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDAO {

    @Insert
    void insert(Student student);

    @Query("SELECT * FROM student_table")
    List<Student> getAllStudent();

    @Delete
    void delete(Student student);
}
