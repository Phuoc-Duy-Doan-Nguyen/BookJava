package View;

import Controller.BookController;
import DAO.BookDAO;
import Model.Book;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

public class BookView extends JFrame {
    private JButton buttonFilter;
    private JButton buttonReset;
    private JButton buttonInsert;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JPanel component;
    private JTable table1;
    private JTextField textFieldName;
    private JTextField textFieldNSX;
    private JTextField textFieldTacGia;
    private JTextField textFieldFilter;
    private JTextField textFieldID;
    private Book book= null;
    private DefaultTableModel columnModel;
    private DefaultTableModel rowModel;
    private TableRowSorter<TableModel> rowSorter = null;
    private List<Book> list;
   public BookView(){
       ActionListener actionListener = new BookController(this);
       MouseListener mouseListener = new BookController(this);
       init();
       this.setTitle("BookView");
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(component);
       this.pack();
       this.setLocationRelativeTo(null);
       buttonReset.addActionListener(actionListener);
       buttonUpdate.addActionListener(actionListener);
       buttonInsert.addActionListener(actionListener);
       buttonFilter.addActionListener(actionListener);
       buttonDelete.addActionListener(actionListener);
       table1.addMouseListener(mouseListener);

       this.setVisible(true);
       fillDataToTable();
       makeTableSearchable();
   }

    private void init(){
      textFieldID.setEnabled(false);
      table1.setDefaultEditor(Object.class, null);
      table1.getTableHeader().setReorderingAllowed(false);
      columnModel = new DefaultTableModel(
              new Object[][]{},
              new String[]{"Mã Sách", "Tên Sách", "NSX", "Tác Giả"}
      );
      table1.setModel(columnModel);
      rowModel = (DefaultTableModel) table1.getModel();
  }
    public void TableMouseClicked(){
    resetInputField();
     textFieldID.setEnabled(false);
    var index =table1.getSelectedRow();
     book =list.get(index);
    textFieldID.setText(String.valueOf(book.getId()));
     textFieldName.setText(String.valueOf(book.getName()));
     textFieldNSX.setText(String.valueOf(book.getNsx()));
     textFieldTacGia.setText(String.valueOf(book.getTacGia()));
    }
    public   void setButtonInsert(){
      if (textFieldName.getText().isEmpty()||textFieldNSX.getText().isEmpty()
      ||textFieldTacGia.getText().isEmpty()){
JOptionPane.showMessageDialog(this,
        "Các trường thông tin không được bỏ trống",
        "Cảnh báo",
        JOptionPane.WARNING_MESSAGE
); }   else{
    var name = textFieldName.getText().trim();
    var nsx = Date.valueOf(this.textFieldNSX.getText());
    var tacGia = textFieldTacGia.getText().trim();
    var book = new Book(name,nsx,tacGia);
    var isSuccess = BookDAO.insert(book);
    if(isSuccess){
JOptionPane.showMessageDialog(this,
        "Thêm thành công",
        "Thông báo ",
        JOptionPane.INFORMATION_MESSAGE);
        fillDataToTable();
    }
      else {
         JOptionPane.showMessageDialog(this,
                 "Thêm thất bại",
                 "Lỗi",
                 JOptionPane.ERROR_MESSAGE);
        }
          resetInputField();
      }
}
public void setButtonUpdate(){
 if(textFieldID.getText().isEmpty()||textFieldNSX.getText().isEmpty()
         ||textFieldName.getText().isEmpty()|| textFieldTacGia.getText().isEmpty()){
    JOptionPane.showMessageDialog(this,
            "Các trường thông tin không được bỏ trống",
    "Cảnh báo",
            JOptionPane.ERROR_MESSAGE);
 }else {
    var id = Integer.parseInt(textFieldID.getText().trim());
    var name =String.valueOf(textFieldName.getText().trim());
    var nsx = java.sql.Date.valueOf(this.textFieldNSX.getText());
    var tacGia = String.valueOf(textFieldTacGia.getText().trim());
    var book = new Book(id,name,nsx,tacGia);
    var isSuccess = BookDAO.update(book);
    if(isSuccess){
        JOptionPane.showMessageDialog(
                this,
                "Cập nhật thành công.",
                "Thông Báo",
                JOptionPane.INFORMATION_MESSAGE
        );
        fillDataToTable();
    } else {
        JOptionPane.showMessageDialog(
                this,
                "Cập nhật thất bại. Xin hãy thử lại!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
        );
    }
     resetInputField();
    }
 }
 public   void setButtonDelete(){
 var id = textFieldID.getText().trim();
 if(!id.isEmpty()){
  var isSuccess = BookDAO.delete(Integer.parseInt(id));
  if(isSuccess){
      JOptionPane.showMessageDialog(
              this,
              "Xoá thành công.",
              "Thông Báo",
              JOptionPane.INFORMATION_MESSAGE
      );
      fillDataToTable();
  } else {
      JOptionPane.showMessageDialog(
              this,
              "Xoá thất bại. Xin hãy thử lại!",
              "Lỗi",
              JOptionPane.ERROR_MESSAGE
      );
  }
     resetInputField();
 } else {
     JOptionPane.showMessageDialog(
             this,
             "Hãy chọn đề thi cần xoá để tiến hành xoá!",
             "Cảnh Báo",
             JOptionPane.WARNING_MESSAGE
     );
  }
 }
 public void setButtonReset(){
       resetInputField();
       JOptionPane.showMessageDialog(this,"Đã reset Input thông tin",
               "Thông báo",JOptionPane.INFORMATION_MESSAGE);
 }
 public void setButtonFilter(){
       makeTableSearchable();
       Filter();
 }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void resetInputField() {
        textFieldID.setText("");
        textFieldName.setText("");
        textFieldNSX.setText("");
        textFieldTacGia.setText("");
    }

    private void fillDataToTable() {
    list = BookDAO.selectAll();
    rowModel.setRowCount(0);
    for(var book: list){
        rowModel.addRow(new Object[]{
                book.getId(),
                book.getName(),
                book.getNsx(),
                book.getTacGia()
        });
    }
    }
    public void makeTableSearchable(){
    rowSorter = new TableRowSorter<>(rowModel);
   var i=0;
   while (i<columnModel.getColumnCount()){
        rowSorter.setSortable(i,false);
        ++i;
   }
   table1.setRowSorter(rowSorter);
    }
   public void Filter(){
       var text =textFieldFilter.getText().trim();
     if(text.length()!=0){
        rowSorter.setRowFilter(RowFilter.regexFilter(text));
     }else {
        rowSorter.setRowFilter(null);
     }
   }

    public static void main(String[] args) {
        EventQueue.invokeLater(BookView::new);
    }
}


