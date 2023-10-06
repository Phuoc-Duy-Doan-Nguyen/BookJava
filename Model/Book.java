package Model;

import java.util.Date;

public class Book {
    int id;
    String name;
    Date nsx;
    String tacGia;

    public Book() {
    }

    public Book(String name, Date nsx, String tacGia) {
        this.name = name;
        this.nsx = nsx;
        this.tacGia = tacGia;
    }

    public Book(int id, String name, Date nsx, String tacGia) {
        this.id = id;
        this.name = name;
        this.nsx = nsx;
        this.tacGia = tacGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNsx() {
        return nsx;
    }

    public void setNsx(Date nsx) {
        this.nsx = nsx;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
