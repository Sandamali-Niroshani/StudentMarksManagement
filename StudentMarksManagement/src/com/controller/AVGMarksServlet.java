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
import com.entity.Marks;
import com.entity.TeacherMark;
import com.google.gson.Gson;

/**
 * Servlet implementation class AVGMarksServlet
 */
@WebServlet("/AVGMarksServlet")
public class AVGMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AVGMarksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Marks> tms = SchoolDao.getAVGMarks();

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		String jsonResponse = new Gson().toJson(tms);
		
		out.append(jsonResponse);
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
