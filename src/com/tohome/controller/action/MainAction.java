package com.tohome.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Written  by 여명
public class MainAction  implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/main.jsp";

        request.getRequestDispatcher(url).forward(request,response);

    }
}
