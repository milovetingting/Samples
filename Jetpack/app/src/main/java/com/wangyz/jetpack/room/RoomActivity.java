package com.wangyz.jetpack.room;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.wangyz.jetpack.MainActivity;

import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/15 15:05
 * @description RoomActivity
 */
public class RoomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBase dataBase = Room.databaseBuilder(getApplicationContext(), DataBase.class, "room").build();
        final StudentDao dao = dataBase.studentDao();
        final AddressDao addressDao = dataBase.addressDao();
        new Thread(new Runnable() {
            @Override
            public void run() {

                /*for (int i = 0; i < 50; i++) {
                    addressDao.insert(new Address((i + 1), "address" + (i + 1)));
                    Student student = new Student("student" + (i + 1), i + 1, i + 1);
                    student.school = new School((i + 1), "school:" + (i + 1));
                    dao.insert(student);
                }*/

//                dao.delete(new Student(1, "student1", 1));

//                dao.update(new Student(2, "student-1", 108));

                List<Student> students = dao.getAll();

//                List<Student> students = dao.findByIds(new int[]{1, 2, 3, 4, 5});

//                List<Student> students = dao.findByName("student1");

                for (Student student : students) {
                    Log.i(MainActivity.TAG, student.toString());
                }

               /* List<StudentTuple> tuples = dao.getTuple();
                for (StudentTuple tuple : tuples) {
                    Log.i(MainActivity.TAG, tuple.toString());
                }*/

               /* List<Address> addresses = addressDao.getAll();
                for (Address address : addresses) {
                    Log.i(MainActivity.TAG, address.toString());
                }*/

               /* List<StudentWithAddress> studentWithAddresses = dao.getStudentInfo();
                for (StudentWithAddress studentWithAddress : studentWithAddresses) {
                    Log.i(MainActivity.TAG, studentWithAddress.toString());
                }*/
            }
        }).start();
    }
}
