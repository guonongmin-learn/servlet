package cn.cos18.bookmgr.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("\n" +
                "<html>\n" +
                "<meta charset=\"utf-8\"/>\n" +
                "  <head>\n" +
                "    <title>登录</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "   <form action=\"login\" method=\"post\">\n" +
                "     <h1>登录</h1>\n" +
                "\n" +
                "     用户名：<input name=\"username\" type=\"text\"/> <br />\n" +
                "\n" +
                "     密码：<input name=\"pwd\" type=\"password\" /><br />\n" +

                "     <input type=\"submit\" value=\"登录\" />\n" +

                "   </form>\n" +
                "  </body>\n" +
                "</html>"
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("pwd");

        if("admin".equals(username) && "admin".equals(password)){
            // ok
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("list");
        }else{

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("\n" +
                    "<html>\n" +
                    "<meta charset=\"utf-8\"/>\n" +
                    "  <head>\n" +
                    "    <title>登录</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "   <form action=\"login\" method=\"post\">\n" +
                    "     <h1>登录</h1>\n" +
                    "\n" + " <span style='color:red'>用户名或密码错误</span><br />" +
                    "     用户名：<input name=\"username\" type=\"text\"/> <br />\n" +
                    "\n" +
                    "     密码：<input name=\"pwd\" type=\"password\" /><br />\n" +

                    "     <input type=\"submit\" value=\"登录\" />\n" +

                    "   </form>\n" +
                    "  </body>\n" +
                    "</html>"
            );
        }
    }
}
