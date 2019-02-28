package cn.cos18.bookmgr.servlet;

import cn.cos18.bookmgr.dao.BookDao;
import cn.cos18.bookmgr.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 删除

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

        if(bookDao.deleteById(id)){
            resp.sendRedirect("list");
        }else{
            writer.println("删除失败");
        }
    }
}
