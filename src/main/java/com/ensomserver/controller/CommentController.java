package com.ensomserver.controller;
import com.ensomserver.controller.db.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        String ip = request.getRemoteAddr();
        response.setContentType("application/json");
        String sql = "insert into comment(ip,comment) values (\""+ip+"\",\""+comment+"\")";
        boolean ok = addComment(sql);
        PrintWriter out = response.getWriter();
        String result = (ok==true?"success":"fail");
        out.println("{\"result\":\""+result+"\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public boolean addComment(String sql) {
        // TODO Auto-generated method stub
        Connection conn = ConnDB.getConnection();
        Statement stmt = ConnDB.getStatement(conn);
        boolean ok = false;
        try {
            stmt.executeUpdate(sql);
            ok = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("ÃÌº”œ˚œ¢ ß∞‹£°");
            e.printStackTrace();
        }
        finally
        {
            ConnDB.closeStatement(stmt);
            ConnDB.closeConnection(conn);
        }
        return ok;
    }
}
