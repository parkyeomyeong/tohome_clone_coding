package com.tohome.controller;

import com.tohome.controller.action.Action;
import com.tohome.dao.MemberDAO;
import com.tohome.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/TohomeServlet")
public class TohomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        System.out.println("TohomeServlet에서 요청을 받음을 확인 : " + command);

        ActionFactory af = ActionFactory.getInstance();
        Action action = af.getAction(command);

        if (action != null) {
            action.execute(request, response);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
