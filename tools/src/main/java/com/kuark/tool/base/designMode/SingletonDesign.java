package com.kuark.tool.base.designMode;

import javax.swing.*;
import java.awt.*;

/**
 * @author rock
 * @detail java图形展示
 * @date 2020/8/18 10:44
 */
public class SingletonDesign {
    public static void main(String[] args)
    {
        JFrame jf=new JFrame("test");
        jf.setLayout(new GridLayout(1,2));
        Container contentPane=jf.getContentPane();
        Bajie obj1=Bajie.getInstance();
        contentPane.add(obj1);
        Bajie obj2=Bajie.getInstance();
        contentPane.add(obj2);
        if(obj1==obj2)
        {
            System.out.println("他们是同一人！");
        }
        else
        {
            System.out.println("他们不是同一人！");
        }
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class Bajie extends JPanel
    {
        private static Bajie instance=new Bajie();
        private Bajie()
        {
            JLabel l1=new JLabel(new ImageIcon("D:\\myProjects\\tools\\src\\main\\java\\com\\kuark\\tool\\base\\designMode\\bajie.jpg"));
            this.add(l1);
        }
        public static Bajie getInstance()
        {
            return instance;
        }
    }
}
