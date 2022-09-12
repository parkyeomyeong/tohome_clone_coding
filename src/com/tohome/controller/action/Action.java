package com.tohome.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Written  by 여명
public interface Action {
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}