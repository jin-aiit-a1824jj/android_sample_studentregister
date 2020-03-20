package a1824jj.jp.ac.aiit.studentregister;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentAppDatabase extends RoomDatabase {

    public abstract StudentDAO getStudentDao();
}
