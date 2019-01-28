package com.ensomserver.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ServletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=gbk");
        PrintWriter out = response.getWriter();
        String strOut = "ÄãºÃ°¡";
        out.println("{\"cc\":\""+ strOut +"\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public String transCode(String gbkString){
        String utf8 = "";
        try {
            String iso = new String(gbkString.getBytes("UTF-8"),"ISO-8859-1");
            utf8 = new String(iso.getBytes("ISO-8859-1"),"UTF-8");
        }catch (UnsupportedEncodingException e){
            System.out.println("error");
        }
        return utf8;
    }
}
