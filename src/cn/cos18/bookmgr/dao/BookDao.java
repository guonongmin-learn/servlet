package cn.cos18.bookmgr.dao;

import cn.cos18.bookmgr.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends BaseDao{


    public List<Book> getBookList() throws SQLException {
        List<Book> list = new ArrayList<>();

        ResultSet ret = query("select * from book");
        if(ret!= null){
            while (ret.next()){
                Book book = new Book(ret.getInt("id"),
                        ret.getString("name"),
                        ret.getString("author"),
                        ret.getFloat("price"),
                        ret.getDate("pubDate"));
                list.add(book);
            }
        }
        return list;
    }


    public boolean addBook(Book b ){
        String sql = "INSERT INTO book (`name`,`author`,`price`, `pubDate`) VALUES (?,?,?,?)";

        return excute(sql, b.getName(), b.getAuther(), b.getPrice(), b.getPubDate());
    }

    public Book getById(int id ) throws SQLException {
        ResultSet ret = query("select * from book where id = ?", id);
        if(ret!= null){
            if (ret.next()){
                return  new Book(ret.getInt("id"),
                        ret.getString("name"),
                        ret.getString("author"),
                        ret.getFloat("price"),
                        ret.getDate("pubDate"));

            }
        }
        return null;
    }


    public boolean update(Book b){
        String sql = "UPDATE  book SET `name` = ?,`author` = ?,`price` = ?, `pubDate` = ? WHERE id = ?";

        return excute(sql, b.getName(), b.getAuther(), b.getPrice(), b.getPubDate(), b.getId());
    }

    public boolean deleteById(int id){
        String sql = "DELETE FROM  book WHERE id = ?";

        return excute(sql, id);
    }

}
