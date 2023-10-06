package DAO;

import Database.DatabaseConnection;
import Model.Book;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Book> selectAll(){
var list =new ArrayList<Book>();
var query ="select * from Sach";
try(var statement = DatabaseConnection.getConnectionInstance().createStatement()){
var resultSet =statement.executeQuery(query);
while (resultSet.next()){
list.add(
        new Book(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDate("nsx"),
                resultSet.getString("tacGia")
        )
);
}
} catch (SQLException e) {
    throw new RuntimeException(e);
}
 return list;
    }

public static Book selectByID(int id){
    var book =new Book();
    var query =" Select * from Sach where id=?";
    try(var ps =DatabaseConnection.getConnectionInstance().prepareStatement(query)){
    ps.setInt(1,id);
    var resultSet =ps.executeQuery();
    if(resultSet.next()){
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setNsx(resultSet.getDate("nsx"));
        book.setTacGia(resultSet.getString("tacGia"));
        return book;
}
} catch (SQLException e) {
    throw new RuntimeException(e);
}
        return null;
}

    public static boolean insert(Book book){
        var query ="insert into Sach(id,name,nsx,tacGia) values(?,?,?,?)";
        try(var ps=DatabaseConnection.getConnectionInstance().prepareStatement(query)) {
                ps.setInt(1,book.getId());
                ps.setString(2,book.getName());
                ps.setDate(3, Date.valueOf(String.valueOf(book.getNsx())));
                ps.setString(4,book.getTacGia());
                var count =ps.executeUpdate();
                return count !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  static boolean update(Book book){
        var query="update Sach set name =?, nsx=?, tacGia=? where id=?";
        try(var ps=DatabaseConnection.getConnectionInstance().prepareStatement(query)) {
            ps.setString(1,book.getName());
            ps.setDate(2, (Date) book.getNsx());
            ps.setString(3,book.getTacGia());
            ps.setInt(4,book.getId());
            var count =ps.executeUpdate();
            return count !=0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean delete(int id){
        var query="delete from Sach where id=?";
        try(var ps =DatabaseConnection.getConnectionInstance().prepareStatement(query)) {
             ps.setInt(1,id);
            var count =ps.executeUpdate();
            return count !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
