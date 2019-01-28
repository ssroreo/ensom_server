package com.ensomserver.controller;
import com.ensomserver.controller.db.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        String ip = request.getRemoteAddr();
        response.setContentType("application/json");
        String sql = "insert into comment(ip,comment) values (?,?)";
        boolean ok = addComment(sql,ip,comment);
        PrintWriter out = response.getWriter();
        String result = (ok==true?"success":"fail");
        out.println("{\"result\":\""+result+"\",\"comment\":\""+(comment)+"\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public boolean addComment(String sql, String ip, String comment) {
        // TODO Auto-generated method stub
        Connection conn = ConnDB.getConnection();
        PreparedStatement stmt = ConnDB.getPreparedStatement(conn,sql);
        boolean ok = false;
        try {
            stmt.setString(1,ip);
            stmt.setString(2,comment);
            stmt.executeUpdate();
            ok = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("ÃÌº”œ˚œ¢ ß∞‹£°");
            e.printStackTrace();
        }
        finally
        {
            ConnDB.closePreparedStatement(stmt);
            ConnDB.closeConnection(conn);
        }
        return ok;
    }
}
