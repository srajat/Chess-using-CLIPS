/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expertchess;

import CLIPSJNI.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;  
/**
 *
 * @author Lakshay , Naveen, Satyam, Rajat  
 */
public class Expertchess {

    /**
     * @param args the command line arguments
     */
    public static int x1,y1,x2,y2;
    public static String content;
    public static boolean first = true,flag = false;
    public static JFrame f;
    public static JButton[][] b = new JButton[8][8];
    public static ImageIcon whiteking = new ImageIcon(new ImageIcon("whiteking.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
    public static ImageIcon whiterook = new ImageIcon(new ImageIcon("whiterook.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
    public static ImageIcon blackking = new ImageIcon(new ImageIcon("blackking.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
    
    public static void main(String[] args) throws IOException 
    {
        // TODO code application logic here
        
        f=new JFrame("Classic King-Rook Chess Expert System");
        f.setSize(600,600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout gl = new GridLayout(8,8);
        f.setLayout(gl);
        for(int i=0; i<8; i++)
        {
            for(int j=0; j< 8; j++)
            {
                b[i][j] = new JButton();
                b[i][j].setBounds(600*i/8,600*j/8,600/8,600/8);
                if((i+j)%2==0)
                {
                    b[i][j].setBackground(Color.darkGray);
                }
                else
                {
                    b[i][j].setBackground(Color.white);
                }
                b[i][j].addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                       JButton jb = (JButton) e.getSource();
                       for(int i=0;i<8;i++)
                        {
                             for(int j=0;j<8;j++)
                             {
                                 if(b[i][j] == jb)
                                 {
                                     x2 = i;
                                     y2 = j;
                                 }
                             }
                         }
                       String prevy = "", prevx = "";
                       String rookx = "", rooky = "";
                       String kingx = "", kingy = "";
                        try {
                            prevy = new String(Files.readAllBytes(Paths.get("5.txt")));
                            prevx = new String(Files.readAllBytes(Paths.get("6.txt")));
                            kingy = new String(Files.readAllBytes(Paths.get("1.txt")));
                            kingx = new String(Files.readAllBytes(Paths.get("2.txt")));
                            rooky = new String(Files.readAllBytes(Paths.get("3.txt")));
                            rookx = new String(Files.readAllBytes(Paths.get("4.txt")));
                        } catch (IOException ex) {
                            Logger.getLogger(Expertchess.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        if(((abs(Integer.parseInt(prevx)-1 - x2) <=1 )&&(abs(Integer.parseInt(prevy)-1 - y2) <= 1))&&
                                ((abs(Integer.parseInt(prevx)-1 - x2) != 0)||(abs(Integer.parseInt(prevy)-1 - y2) != 0))
                               &&((abs(Integer.parseInt(kingx)-1 - x2) >1 )||(abs(Integer.parseInt(kingy)-1 - y2) >1))
                                &&((abs(Integer.parseInt(rookx)-1 - x2) != 0)&&(abs(Integer.parseInt(rooky)-1 - y2) != 0)) ){
                            move(Integer.parseInt(prevx)-1,Integer.parseInt(prevy)-1,x2,y2);
                            try {
                               //System.out.println("Enter black king vertical : ");
                               //bky = in.nextLine();
                               Files.write(Paths.get("5.txt"),Integer.toString(y2+1).getBytes(), StandardOpenOption.WRITE);
                           } catch (IOException ex) {
                               Logger.getLogger(Expertchess.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           try {
                               // System.out.println("Enter black king horizontal : ");
                               // bkx = in.nextLine();
                               Files.write(Paths.get("6.txt"), Integer.toString(x2+1).getBytes(), StandardOpenOption.WRITE);
                           } catch (IOException ex) {
                               Logger.getLogger(Expertchess.class.getName()).log(Level.SEVERE, null, ex);
                           }
                            try {
                               
                                runclip();
                                if(!issafe()){
                                    System.out.println("Winner");
                                    //JOptionPane.showMessageDialog(new JFrame("Game Over"), "Computer A.I. successfully beated you!!");
                                    int result = JOptionPane.showConfirmDialog(new JFrame("Game Over"),
                                         "Computer A.I. successfully beated you!!",
                                         "Confirm Quit", JOptionPane.YES_NO_OPTION);
                                    if (result == JOptionPane.YES_OPTION) System.exit(0);
                                }
                                System.out.println("safe");
                              
                               
                           } catch (IOException ex) {
                               Logger.getLogger(Expertchess.class.getName()).log(Level.SEVERE, null, ex);
                           }
                        }else{
                            x2 = Integer.parseInt(prevx);
                            y2 = Integer.parseInt(prevy);
                        }
                    }

                    private boolean issafe() {
                       String prevy = "", prevx = "";
                       String rookx = "", rooky = "";
                       String kingx = "", kingy = "";
                        try {
                            prevy = new String(Files.readAllBytes(Paths.get("5.txt")));
                            prevx = new String(Files.readAllBytes(Paths.get("6.txt")));
                            kingy = new String(Files.readAllBytes(Paths.get("1.txt")));
                            kingx = new String(Files.readAllBytes(Paths.get("2.txt")));
                            rooky = new String(Files.readAllBytes(Paths.get("3.txt")));
                            rookx = new String(Files.readAllBytes(Paths.get("4.txt")));
                        } catch (IOException ex) {
                            Logger.getLogger(Expertchess.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(prevx + " " + prevy + " " + kingx + " " + kingy + " " + rookx + " " + rooky + " " );
                        int movex[] = {0, 1, 1, 1,  0, -1, -1, -1};
                        int movey[] = {1, 1, 0, -1,-1, -1,  0,  1};
                        
                        for(int i=0;i<8;i++){
                            if(((Integer.parseInt(prevx) -1 + movex[i]) >= 0)&&((Integer.parseInt(prevy) -1 + movey[i]) >= 0)&&
                                    ((Integer.parseInt(prevx) -1 + movex[i]) < 8)&&((Integer.parseInt(prevy) -1 + movey[i]) < 8)){
                                int x2 = (Integer.parseInt(prevx) -1 + movex[i]);
                                int y2 = (Integer.parseInt(prevy) -1 + movey[i]);
                               if( (abs(Integer.parseInt(kingx)-1 - x2) > 1 ) || (abs(Integer.parseInt(kingy)-1 - y2) > 1)){                                   
                                 if((abs(Integer.parseInt(rookx)-1 - x2) != 0) && (abs(Integer.parseInt(rooky)-1 - y2) != 0)){
                                   System.out.println( Integer.toString(x2) +" "+ Integer.toString(y2));
                                   return true;
                                 }
                               } 
                            }
                        }
                        return false;
                    }
                });
                f.add(b[i][j]);
                
            }
        }
        b[0][4].setIcon(whiteking);
        b[7][4].setIcon(blackking);
        b[0][0].setIcon(whiterook);
        
        f.setVisible(true);//making the frame visible  
        
        System.out.println("Game Started.......\n");
        //System.out.println("Enter white king vertical : ");
        String wky = "5";
        Files.write(Paths.get("1.txt"), wky.getBytes(), StandardOpenOption.WRITE);
        //System.out.println("Enter white king horizontal : ");
        String wkx = "1";
        Files.write(Paths.get("2.txt"), wkx.getBytes(), StandardOpenOption.WRITE);

        //System.out.println("Enter white rook vertical : ");
        String wry = "1";
        Files.write(Paths.get("3.txt"), wry.getBytes(), StandardOpenOption.WRITE);
        //System.out.println("Enter white rook horizontal : ");
        String wrx = "1";
        Files.write(Paths.get("4.txt"), wrx.getBytes(), StandardOpenOption.WRITE);


        //System.out.println("Enter black king vertical : ");
        String bky = "5";
        Files.write(Paths.get("5.txt"), bky.getBytes(), StandardOpenOption.WRITE);
        //System.out.println("Enter black king horizontal : ");
        String bkx = "8";
        Files.write(Paths.get("6.txt"), bkx.getBytes(), StandardOpenOption.WRITE);
        
        runclip();
    }
    static void move(int x1, int y1, int x2, int y2)
    {
        ImageIcon icon = (ImageIcon) b[x1][y1].getIcon();
        b[x2][y2].setIcon(icon);
        b[x1][y1].setIcon(null);
    }
        
    static void runclip() throws IOException{
        Environment clips = new Environment();
        clips = new Environment();
        clips.load("chess.clp");
        clips.reset();
        clips.run();
        clips.eval("(start-game)");
        clips.destroy();
        content = new String(Files.readAllBytes(Paths.get("output.txt")));
        //System.out.println(content);
       
       if(content.contains("K")){
            int ypos = content.charAt(3) - 'a' + 1;
            int xpos = content.charAt(4) - '0';
            String vert = Integer.toString(ypos);
            String hor = Integer.toString(xpos);
            String prevy = new String(Files.readAllBytes(Paths.get("1.txt")));
            String prevx = new String(Files.readAllBytes(Paths.get("2.txt")));
            move(Integer.parseInt(prevx)-1,Integer.parseInt(prevy)-1,xpos - 1,ypos - 1);
            Files.write(Paths.get("1.txt"), vert.getBytes(), StandardOpenOption.WRITE);
            Files.write(Paths.get("2.txt"), hor.getBytes(), StandardOpenOption.WRITE);
        }
        else{
            int ypos = content.charAt(3) - 'a' + 1;
            int xpos = content.charAt(4) - '0';
            String vert = Integer.toString(ypos);
            String hor = Integer.toString(xpos);
            String prevy = new String(Files.readAllBytes(Paths.get("3.txt")));
            String prevx = new String(Files.readAllBytes(Paths.get("4.txt")));
            move(Integer.parseInt(prevx)-1,Integer.parseInt(prevy)-1,xpos - 1,ypos - 1);
            Files.write(Paths.get("3.txt"), vert.getBytes(), StandardOpenOption.WRITE);
            Files.write(Paths.get("4.txt"), hor.getBytes(), StandardOpenOption.WRITE);
        }
    }
}
