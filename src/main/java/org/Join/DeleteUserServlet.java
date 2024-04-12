package org.Join;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String dbURL = "jdbc:mysql://localhost:3306/YourDatabase"; // 데이터베이스 접속 정보 수정 필요
            String dbUser = "yourUsername"; // DB 사용자 이름
            String dbPassword = "yourPassword"; // DB 비밀번호
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            String sql = "DELETE FROM Users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                response.getWriter().print("User successfully deleted!");
            } else {
                response.getWriter().print("Error deleting user or user not found.");
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
