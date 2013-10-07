package window;

import web.MainWeb;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.String;
import java.awt.image.BufferedImage;



public class MyWindow extends JFrame{

    public String filePath;
    public JTable table;
    public int [][] pixelArray;
    public MainWeb web1;
    DefaultListModel listModel;



    public MyWindow() throws HeadlessException {

        pixelArray = new int[5][3];

        web1 = new MainWeb(3, 5, pixelArray);

        this.setDefaultCloseOperation(MyWindow.EXIT_ON_CLOSE);
        this.setSize(440, 280);
        this.setTitle("neuro_web");
        this.setVisible(true);
        this.setBackground(Color.WHITE);

        setLayout(null);

        showLabel();

        JButton btn = new JButton();
        btn.setText("Open");
        btn.setBounds(10, 200, 80, 30);
        btn.setSize(70, 30);
        ActionListener fOpenActionListener = new FileOpenAction();
        btn.addActionListener(fOpenActionListener);
        this.add(btn);

        JButton btn2 = new JButton();
        btn2.setText("False");
        btn2.setBounds(100, 200, 80, 30);
        btn2.setSize(70, 30);
        ActionListener FalseButtonActionListener = new FalseButtonAction();
        btn2.addActionListener(FalseButtonActionListener);
        this.add(btn2);

    }


    public class FileOpenAction implements ActionListener {
        public int ret;
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser("C:\\Users\\flammel\\Documents\\neyrweb");
            ret = fileopen.showDialog(null, "Open file");
            filePath = fileopen.getSelectedFile().getAbsolutePath();

            try{
                BufferedImage img = ImageIO.read(new File(filePath));
                pixelArray = ImgToPixelArray(img);
            }catch(IOException ex){
                System.out.println(ex);
            }

            NewTable();
            recognize();

        }
    }

  public class FalseButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent t) {
            try{
                if(!web1.rez()){
                    web1.incW(pixelArray);
                }
                else{
                    web1.decW(pixelArray);
                }
            }catch (NullPointerException ew){System.out.println(ew);}
        }
    }



    public void NewTable(){

        if(table != null){
            this.remove(table);
        }

        Object[][] mat = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 3; j++){
                mat[i][j] = pixelArray[i][j];
            }
        }

        Object[] column = {"A", "B", "C"};
        table = new JTable(mat, column);
        table.setBounds(10, 10, 100, 100);
        table.setSize(60, 80);
        this.add(table);
        this.setVisible(true);
    }


    private static int[][] ImgToPixelArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (image.getRGB(col, row) == -1){
                    result[row][col] = 0;
                }
                else{
                    result[row][col] = 1;
                }
            }
        }
        return result;
    }

    public void showLabel(){

        listModel = new DefaultListModel();
        JList txtArea = new JList(listModel);;
        txtArea.setBounds(200, 10, 10, 10);
        txtArea.setSize(220, 220);
        txtArea.setVisible(true);
        this.add(txtArea);

        this.repaint();
    }

    public void printRezult(String str){
        System.out.println(str);
        listModel.removeAllElements();
        listModel.addElement(str + ": " + web1.sum);
    }

    public void recognize(){
        web1.mul_w(pixelArray);
        web1.sum();
        if (web1.rez() == true){
            printRezult("True");
        }
        else{
            printRezult("False");
        }


    }

}