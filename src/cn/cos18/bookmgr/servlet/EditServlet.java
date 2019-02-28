package cn.cos18.bookmgr.servlet;

import cn.cos18.bookmgr.dao.BookDao;
import cn.cos18.bookmgr.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditServlet extends HttpServlet

{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        int id = -1;
        try{
            id =Integer.parseInt(req.getParameter("id"));
        }catch (Exception NumberFormatException){

        }

        if(id == -1){
            writer.println("错误的ID");
            return;
        }

        BookDao bookDao = new BookDao();
        Book book = null;
        try {
            book = bookDao.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            writer.println("找不到编号为" + id + "的书籍");
            return;
        }

        writer.println("\n" +
                "<html>\n" +
                "<meta charset=\"utf-8\"/>\n" +
                "  <head>\n" +
                "    <title>修改书籍 - " + book.getName()+ "</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "   <form action=\"edit\" method=\"post\">\n" +
                "     <h1>修改书籍 - " +  book.getName() +"</h1>\n" +
                "\n" +
                "     名称：<input name=\"name\" type=\"text\" value='" + book.getName() +"'/> <br />\n" +
                "\n" +
                "     作者：<input name=\"author\" type=\"text\" value='" + book.getAuther() +"'/><br />\n" +
                "\n" +
                "     价格：<input name=\"price\" type=\"number\" value='" + book.getPrice()+"'/><br />\n" +
                "\n" +
                "     出版日期：<input name=\"pubDate\" type=\"date\" value='" + book.getPubDate() + "'/><br />\n" +
                "\n" +
                "     <input type=\"submit\" value=\"修改\" />\n" +
                "<input name='id' type='hidden' value='" + book.getId() +"'/>" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 修改
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        BookDao bookDao = new BookDao();
        int id = -1;
        try{
            id =Integer.parseInt(req.getParameter("id"));
        }catch (Exception NumberFormatException){

        }

        if(id == -1){
            writer.println("错误的ID");
            return;
        }

        String name = req.getParameter("name");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str ;

        // String转Date
        str = req.getParameter("pubDate");
        try {
            date = format.parse(str);  // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Book book = new Book(id, name, author, price, date);

        if(bookDao.update(book)){
            resp.sendRedirect("list");
        }else{
            writer.println("修改失败");
        }


    }
}
