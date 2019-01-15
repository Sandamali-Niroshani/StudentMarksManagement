package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SchoolDao;
import com.entity.TeacherMark;
import com.google.gson.Gson;

/**
 * Servlet implementation class TeacherMarksServlet
 */
@WebServlet("/TeacherMarksServlet")
public class TeacherMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherMarksServlet() {
		super();

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String teacher = request.getParameter("teacher");
		List<TeacherMark> tms = SchoolDao.getTeacherMarks(Integer.parseInt(teacher));

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		String jsonResponse = new Gson().toJson(tms);
		
		out.append(jsonResponse);
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
