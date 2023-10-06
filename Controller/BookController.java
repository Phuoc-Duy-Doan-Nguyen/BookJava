package Controller;

import View.BookView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BookController implements ActionListener, MouseListener {
    private BookView bookView;

    public BookController() {
        this.bookView =new BookView();
    }

    public BookController(BookView bookView) {
        this.bookView = bookView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals("Insert")){
            this.bookView.setButtonInsert();
        } else if (str.equals("Update")) {
            this.bookView.setButtonUpdate();
        } else if (str.equals("Delete")) {
            this.bookView.setButtonDelete();
        } else if (str.equals("Reset")) {
            this.bookView.setButtonReset();
        } else if (str.equals("Filter")) {
            this.bookView.setButtonFilter();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.bookView.TableMouseClicked();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
