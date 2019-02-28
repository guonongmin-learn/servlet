package cn.cos18.bookmgr.servlet;

import cn.cos18.bookmgr.dao.BookDao;
import cn.cos18.bookmgr.entity.Book;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ListServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        BookDao bookDao = new BookDao();
        servletResponse.setContentType("text/html;charset=utf-8");

        PrintWriter writer = servletResponse.getWriter();

        writer.println("<html>");

        writer.println("<head>");
        writer.println("<title>书籍管理</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<table align='center'>");

        List<Book> bookList = null;

        writer.println("<a href='add.html'> 添加书籍 </a><br />");

        writer.println("<thead>");
        writer.println("<tr>");
        writer.println("<td>序号</td>");
        writer.println("<td>书名</td>"); writer.println("<td>作者</td>");writer.println("<td>价格</td>");writer.println("<td>出版日期</td>");writer.println("<td>操作</td>");
        writer.println("</tr>");
        writer.println("</thead");

        try {
            bookList = bookDao.getBookList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bookList == null || bookList.size() == 0){
            writer.println("没有书籍");
        }else{
            for(Book b :bookList) {

                writer.println("<tbody>");
                    writer.println("<tr>");
                    writer.println("<td>");
                    writer.print(b.getId());
                    writer.println("</td>");

                    writer.println("<td>");
                    writer.print(b.getName());
                    writer.println("</td>");

                    writer.println("<td>");
                    writer.print(b.getAuther());
                    writer.println("</td>");

                    writer.println("<td>");
                    writer.print(b.getPrice());
                    writer.println("</td>");

                    writer.println("<td>");
                    if(b.getPubDate() != null) {
                        writer.print(b.getPubDate().toString());
                    }else {
                        writer.print("未知日期");
                    }
                    writer.println("</td>");

                    writer.println("<td>");
                    writer.print("<a href='edit?id=" + b.getId()+"'> 修改 </a>");

                    writer.print("  <a href='delete?id=" + b.getId()+"'> 删除 </a>");
                    writer.println("</td>");


                    writer.println("</tr>");
                }

        }
        writer.println("</tbody");
        writer.println("</table");
        writer.println("</body");
        writer.println("</html>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
