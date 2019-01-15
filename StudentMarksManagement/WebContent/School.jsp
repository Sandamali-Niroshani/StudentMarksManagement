<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Class room</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<style>
.main-wrapper {
	max-width: 700px;
}
</style>

	<div class="container main-wrapper">
		<h1>
			<center>Student Marks</center>
		</h1>

		<select id="teacher-dropdown" name="teacher" class="form-control"
			onchange="getMarksByTeacher(this.value)">
			<!-- <option value="1">Nimal Perera</option>
			<option value="2">Chathuri Sewwandi</option>
			<option value="3">Last Name</option> -->


		</select> <br>

		<table align="center" class="table table-striped"
			id="tbl-student-marks">
			<thead>
				<tr>
					<th>Student</th>
					<th>Subject</th>
					<th>Marks</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>


		<h1>
			<center>Average Marks</center>
		</h1>
		<table align="center" id="tbl-avg-marks" class="table table-striped">
			<thead>
				<tr>
					<th>Subject</th>
					<th>AVG_Marks</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>



	</div>
</body>





<script>
	loadTeachers();
	loadAVGMarks();
	
	function getMarksByTeacher(teacher_id) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var responseObjs = JSON.parse(xhttp.responseText);
				$('#tbl-student-marks tbody').html('');
				$.each(responseObjs, function(index, value) {
					$('#tbl-student-marks tbody').append(
							'<tr><td>' + value.student + '</td>' + '<td>'
									+ value.subject + '</td>' + '<td>'
									+ value.marks + '</td></tr>');
				});
			}
		};
		xhttp.open("GET",
				"http://localhost:8080/Intern/TeacherMarksServlet?teacher="
						+ teacher_id, true);
		xhttp.send();
	}

	function loadAVGMarks() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var responseObjs = JSON.parse(xhttp.responseText);
				$('#tbl-avg-marks tbody').html('');
				$.each(responseObjs, function(index, value) {
					$('#tbl-avg-marks tbody').append(
							'<tr><td>' + value.subject + '</td>' + '<td>'
									+ value.marks + '</td></tr>');
				});
			}
		};
		xhttp.open("GET", "http://localhost:8080/Intern/AVGMarksServlet", true);
		xhttp.send();
	}

	function loadTeachers() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var responseObjs = JSON.parse(xhttp.responseText);
				$('#teacher-dropdown').html('');
				$('#teacher-dropdown')
						.append(
								'<option selected disabled>Select Your Teacher</option>');
				$.each(responseObjs, function(index, value) {
					$('#teacher-dropdown').append(
							'<option value='+value.id+'>' + value.firstName
									+ '</option>');
				});
			}
		};
		xhttp.open("GET", "http://localhost:8080/Intern/TeacherServlet", true);
		xhttp.send();
	}
</script>
</html>