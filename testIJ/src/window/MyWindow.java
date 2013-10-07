package window;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.String;


public class MyWindow extends JFrame{

    public File file;

    public class FileOpenAction implements ActionListener {
        public int ret;
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            ret = fileopen.showDialog(null, "Open file");
            file = fileopen.getSelectedFile();
        }
    }


    public MyWindow() throws HeadlessException {
        this.setDefaultCloseOperation(MyWindow.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("neuro_web");
        this.setVisible(true);

        setLayout(null);

        JButton btn = new JButton();
        btn.setText("Open");
        btn.setBounds(50, 500, 80, 30);
        btn.setSize(70, 30);
        ActionListener fOpenActionListener = new FileOpenAction();
        btn.addActionListener(fOpenActionListener);
        this.add(btn);

        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"));
        imgLabel.setText("Hello cats!");
        imgLabel.setBounds(50, 400, 80, 30);
        this.add(imgLabel);


    }

}