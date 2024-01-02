import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class MineSweeper implements MouseListener{
    JFrame frame=new JFrame();
    Buton [][] board=new Buton[10][10];
    int Opennig_Button;


    public MineSweeper(){
        Opennig_Button=0;
        frame.setTitle("MİNESWEEPER GAME");
        frame.setSize(600,600);

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(new GridLayout(10,10));
        frame.setVisible(true);

        CreateBoard();
        GenerateMine();
        Counting();
       // print();


    }
    public void CreateBoard(){
        for(int row=0;row<board.length;row++) {
            for (int col = 0; col < board[0].length; col++) {
                Buton b = new Buton(row, col);
                frame.add(b);
               b.setBackground(Color.darkGray);
                b.setOpaque(true);
                b.addMouseListener(this);
                board[row][col] = b;
                board[row][col].setFont(new Font("Font1",Font.BOLD,20));
            }
        }

    }
    public void GenerateMine() {
        int i=0;
        while(i<10){
            int RandomRow= (int)(Math.random() * board.length);
            int RandomCol= (int) (Math.random() *board[0].length);
            while(board[RandomRow][RandomCol].isMine()){
                RandomRow=(int) (Math.random()* board.length);
                RandomCol=(int) (Math.random()* board[0].length);
            }

            board[RandomRow][RandomCol].setMine(true);
            i++;
            System.out.println("i= "+i);
        }
    }
    public void print(){

        for(int row=0;row<board.length;row++) {
        for (int col = 0; col < board[0].length; col++) {
            if (board[row][col].isMine()){
                board[row][col].setText("M");
               // board[row][col].setIcon(new ImageIcon("mine.png"));
            }
            else {
                board[row][col].setText(board[row][col].getCount()+"");
            }
        }
        }

    }
    public void Counting(){
        for(int row=0;row<board.length;row++) {
        for (int col = 0; col < board[0].length; col++) {
            if (board[row][col].isMine()){
                countingArround(row,col);
            }
        }
    }
    }
public void countingArround(int row,int col){
      for (int i=row-1;i<=row+1;i++){
  for(int k=col-1;k<=col+1;k++){
      try{
          int value=board[i][k].getCount();
          board[i][k].setCount(++value);

      }
      catch (Exception e){
      }
  }
      }
}
public void open(int row,int col){
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col].getText().length() > 0
            || board[row][col].isEnabled() == false) {

        ;
        return;
    } else if (board[row][col].getCount() != 0) {
        board[row][col].setText(board[row][col].getCount() + "");
        board[row][col].setEnabled(false);
        /* board[row][col].setBackground(Color.BLUE);
        board[row][col].setOpaque(true);*/

Opennig_Button++;
    } else {

        board[row][col].setEnabled(false);
        Opennig_Button++;
        open(row - 1, col);
        open(row + 1, col);
        open(row, col - 1);
        open(row, col + 1);
    }
}




    @Override
    public void mouseClicked(MouseEvent e) {
        Buton b= (Buton) e.getComponent();
        if (e.getButton()==1){
            System.out.println("sol tık");
            if(b.isMine()){
                JOptionPane.showConfirmDialog(frame,"Mayına bastınız");
                print();
            }

            else {
           open(b.getRow(), b.getCol());
           if(Opennig_Button==90){

               JOptionPane.showConfirmDialog(frame,"You win the game . Congratulations....");
           }
            }
        }
   else if (e.getButton()==3){
            System.out.println("sağ tık");
       if (!b.isFlag()){
       //   b.setIcon(new ImageIcon("flag.png"));
           b.setText("F");
           b.setFlag(true);
       }
       else{
           b.setText("");
           b.setFlag(false);
       }
        }

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
