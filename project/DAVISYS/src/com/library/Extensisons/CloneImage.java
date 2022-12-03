/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.extensisons;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static org.bridj.Platform.getResource;

/**
 *
 * @author dangt
 */
public class CloneImage {
    
    BufferedImage image;
    String src;
    public CloneImage(){
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage cloneImage;
        
        try {
            JFileChooser chooser = new JFileChooser("");
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            this.src = file.getName();
            System.out.println(this.image);
            image = ImageIO.read(file);
            //image = ImageIO.read(/*getClass().getClassLoader().*/getResource("C:\\Users\\dangt\\OneDrive\\Máy tính\\nganmayy.png"));
            cloneImage = new BufferedImage(800,500,BufferedImage.TYPE_INT_ARGB);
            Graphics2D  g = cloneImage.createGraphics();
            
            g.drawImage(image, 0,0, null);
            g.dispose();
            
            //JLabel lbl = new JLabel();
            //window.add(lbl);
            //lbl.setIcon(new ImageIcon(cloneImage));
            
            try {
                ImageIO.write(cloneImage, "PNG", new File("src\\com\\library\\images\\"+src+".PNG"));
            } catch (Exception e) {
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        window.pack();
        window.setVisible(true);
        
    }
    
    public static void main(String args[]) {
    /*
      //Loading the OpenCV core library
      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
      
      //Reading the Image from the file and storing it in to a Matrix object
      String file = "C:/opencv/logo.jpg";
      
      Mat matrix = Imgcodecs.imread(file);
      System.out.println("Image Loaded ..........");
      String file2 = "C:/opencv/logoResaved.jpg";

      //Writing the image
      Imgcodecs.imwrite(file2, matrix);
      System.out.println("Image Saved ............");
    */
        new CloneImage();
   }
}
