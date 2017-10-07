
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhoopal
 */
public class ImageConvolution {
    
    
    
    
   
    public static void main(String[] args) throws IOException {
        
    String path="C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\Images\\lenna.jpg";
    BufferedImage img = null;
    img = ImageIO.read(new File(path));
    
  /*  //sharp
    float[] SHARPEN3x3 = {
                            0.f, -1.f, 0.f,
                            -1.f, 5.0f, -1.f,
                            0.f, -1.f, 0.f};
    //edge Kernel
    float[] edgeKernel = {
                             0.0f, -1.0f, 0.0f,
                            -1.0f, 4.0f, -1.0f,
                             0.0f, -1.0f, 0.0f};
    //blur kernel
    float ninth = 1.0f / 9.0f;
    float[] blurKernel = {
     ninth, ninth, ninth,
     ninth, ninth, ninth,
     ninth, ninth, ninth};
     
//BufferedImage srcimg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYP);
    
BufferedImage dstbimg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);

    Kernel kernel = new Kernel(3,3,blurKernel);
ConvolveOp cop = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);
BufferedImage output= cop.filter(img, null);

    File outputfile = new File("D:\\C or C++ Programs\\ImageConvolution\\src\\images\\savedmapsmall.jpg");
    ImageIO.write(output, "jpg", outputfile); // Write the Buffered Image into an output file
   
  
    
int x=10,y=20;    
int color =img.getRGB(x,y);
int blue =  color & 0xff;
int green = (color & 0xff00) >> 8;
int red = (color & 0xff0000) >> 16;
        System.out.println("\nRed="+red+"\nGreen="+green+"\nBlue="+blue);
  */      
    //file processing
        File folder = new File("C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\Kernels");
File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
    
    Scanner sc =new Scanner(System.in);
        System.out.println("Enter choice \n1.Blur\n2.Sharp=");
        int choice=sc.nextInt();
                String textFilePath="C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\Kernels\\"+listOfFiles[choice-1].getName();
    
    String wholeFileInLine="";    
    int kernelSize=0;    
    try (BufferedReader br = new BufferedReader(new FileReader(new File(textFilePath)))) {
    String line;
    int lineCounter=0;
    
    while ((line = br.readLine()) != null) {
        lineCounter++;
       // System.out.println(lineCounter+" "+line);
        if(lineCounter>1)
       wholeFileInLine=wholeFileInLine+line;
        else
            kernelSize=Integer.parseInt(line);
    }
    }  
    String[] strArray = wholeFileInLine.split(",");
float[] fileKernelArray = new float[strArray.length];
for(int i = 0; i < strArray.length; i++) {
    fileKernelArray[i] = Float.parseFloat(strArray[i]);
}

            
            /*for (int i = 0; i < fileKernelArray.length; i++) {
                System.out.println("fileKernelArray["+i+"]"+"="+fileKernelArray[i]);
            }*/

  
    Kernel kernel = new Kernel(kernelSize,kernelSize,fileKernelArray);
ConvolveOp cop = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);
BufferedImage output= cop.filter(img, null);

    File outputfile = new File("C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\output\\outputOf"+listOfFiles[choice-1].getName().replace(".txt","")+".jpg");
    ImageIO.write(output, "jpg", outputfile); // Write the Buffered Image into an output file
   
    
    
int x=10,y=20;    
int color =img.getRGB(x,y);
int blue =  color & 0xff;
int green = (color & 0xff00) >> 8;
int red = (color & 0xff0000) >> 16;
        System.out.println("\nRed="+red+"\nGreen="+green+"\nBlue="+blue);
  
        
    }
}

