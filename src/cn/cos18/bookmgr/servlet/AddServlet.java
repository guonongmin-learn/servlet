package cn.cos18.bookmgr.servlet;

import cn.cos18.bookmgr.dao.BookDao;
import cn.cos18.bookmgr.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String id = req.getParameter("id");
//        try{
//            Integer.parseInt(id);
//        }catch (Exception ){
//
//        }

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        BookDao bookDao = new BookDao();
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String str = null;

        // String转Date
        str = req.getParameter("pubDate");
        try {
            date = format.parse(str);  // Thu Jan 18 00:00:00 CST 2007
        } catch (ParseException e) {
            e.printStackTrace();
            resp.getWriter().println("添加失败:日期错误");
            return;
        }
        Book book = new Book(name, author, price, date);

        if(bookDao.addBook(book)){
            // 添加成功
            resp.sendRedirect("list");
        }else {

            resp.getWriter().println("添加失败");
        }


    }
}
