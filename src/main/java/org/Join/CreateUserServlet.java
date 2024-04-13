package org.Join;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/CreateUser")
public class CreateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        int userAge = Integer.parseInt(request.getParameter("age"));
        String userGender = request.getParameter("gender");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String dbURL = "jdbc:mysql://172.17.0.2:3306/YourDatabase"; // 데이터베이스 접속 정보 수정 필요
            String dbUser = "root"; // DB 사용자 이름
            String dbPassword = "1234"; // DB 비밀번호
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            String sql = "INSERT INTO Users (id, password, name, age, gender) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);
            pstmt.setString(3, userName);
            pstmt.setInt(4, userAge);
            pstmt.setString(5, userGender);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                response.getWriter().print("User successfully created!");
            } else {
                response.getWriter().print("Error creating user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Database connection error.");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
