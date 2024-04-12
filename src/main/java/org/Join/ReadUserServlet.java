package org.Join;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReadUser")
public class ReadUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String dbURL = "jdbc:mysql://localhost:3306/YourDatabase"; // 데이터베이스 접속 정보 수정 필요
            String dbUser = "yourUsername"; // DB 사용자 이름
            String dbPassword = "yourPassword"; // DB 비밀번호
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            String sql = "SELECT * FROM Users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String userPassword = rs.getString("password");
                String userName = rs.getString("name");
                int userAge = rs.getInt("age");
                String userGender = rs.getString("gender");

                response.setContentType("text/html");
                response.getWriter().print("<html><body>");
                response.getWriter().print("ID: " + userId + "<br>");
                response.getWriter().print("Name: " + userName + "<br>");
                response.getWriter().print("Age: " + userAge + "<br>");
                response.getWriter().print("Gender: " + userGender + "<br>");
                response.getWriter().print("</body></html>");
            } else {
                response.getWriter().print("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Database connection error.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
