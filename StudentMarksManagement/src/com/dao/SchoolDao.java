package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Marks;
import com.entity.Teacher;
import com.entity.TeacherMark;
import com.util.DbConnection;

public class SchoolDao {

	public static List<TeacherMark> getTeacherMarks(int teacher_id) {

		List<TeacherMark> result = new ArrayList();

		try {
			Connection con = DbConnection.creatConnection();
			String sql = "select students.firstName as student, subjects.title, marks.mark"
					+ " From students inner join marks" + " on students.student_id=marks.student_id"
					+ " inner join subjects on subjects.subject_id= marks.subject_id"
					+ " inner join subj_teach on subjects.subject_id=subj_teach.subject_id where subj_teach.teacher_id ="
					+ teacher_id;

			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				TeacherMark tm = new TeacherMark();
				tm.setStudent(rs.getString(1));
				tm.setSubject(rs.getString(2));
				tm.setMarks(rs.getInt(3));

				result.add(tm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static List<Teacher> getAllTeachers() {

		List<Teacher> result = new ArrayList();

		try {
			Connection con = DbConnection.creatConnection();
			String sql = "SELECT * FROM teachers";

			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setFirstName(rs.getString(2));
				teacher.setLastName(rs.getString(3));

				result.add(teacher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static List<Marks> getAVGMarks() {

		List<Marks> result = new ArrayList();

		try {
			Connection con = DbConnection.creatConnection();
			String sql = "select  subjects.title as subject, avg(mark) as marks_average"
					+ " From marks inner join subjects" + " On subjects.subject_id=marks.subject_id"
					+ " Group by (subjects.title)";

			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Marks mark = new Marks();
				mark.setSubject(rs.getString(1));
				mark.setMarks(rs.getString(2));

				result.add(mark);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
