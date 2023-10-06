package Test;

import View.BookView;

public class test {
    public static void main(String[] args) {
        try {
            new BookView();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
