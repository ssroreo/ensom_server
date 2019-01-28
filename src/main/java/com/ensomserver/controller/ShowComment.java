package com.ensomserver.controller;

import com.ensomserver.controller.db.ConnDB;
import com.ensomserver.controller.domain.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String sql = "select * from comment order by rand() limit 5";
        List<Comment> commentList = showComment(sql);
        PrintWriter out = response.getWriter();
        out.println("{\"comment\":[");
        String strComment = "";
        for (int i=0;i<5;i++){
            strComment+=("{\"id\":\""+commentList.get(i).getId()+"\",\"comment\":\""+(commentList.get(i).getComment()).replaceAll("\"","'").replaceAll("\n","\\\\n")+"\"}");
            if(i!=commentList.size()-1) strComment+=",";
        }
        out.println(strComment+"]}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public List<Comment> showComment(String sql) {
        // TODO Auto-generated method stub
        Connection conn = ConnDB.getConnection();
        PreparedStatement stmt = ConnDB.getPreparedStatement(conn,sql);
        ResultSet rs = ConnDB.getResultSet(stmt);
        List<Comment> commentList = new ArrayList();
        try {
            while(rs.next())
            {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setIp(rs.getString("ip"));
                comment.setComment(rs.getString("comment"));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("UserDaoImpl中取得数据失败");
            e.printStackTrace();
        }
        ConnDB.closePreparedStatement(stmt);
        ConnDB.closeResultSet(rs);
        ConnDB.closeConnection(conn);
        return commentList;
    }
}
