
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Code {
    public int ReadKernelSize(String Operation) throws Exception
    {
       
        int kernelSize=0;
        	String textFilePath="C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\Kernels\\"+Operation+".txt";
    float[] fileKernelArray=null;			
     BufferedReader br = new BufferedReader(new FileReader(new File(textFilePath)));
    String line="";
    String wholeFileInLine="";
    int lineCounter=0;
   
    line = br.readLine();
       kernelSize=Integer.parseInt(line);
       // System.out.println(lineCounter+" "+line);
    return kernelSize;
    }
    
    
    public float[] kernelRead(String Operation) throws Exception
    {
       

        
        
        	String textFilePath="C:\\Users\\HP\\Documents\\NetBeansProjects\\convo_gui\\Kernels\\"+Operation+".txt";
    float[] fileKernelArray=null;			
     BufferedReader br = new BufferedReader(new FileReader(new File(textFilePath)));
    String line="";
    String wholeFileInLine="";
    int lineCounter=0;
   
    while ((line = br.readLine()) != null) {
        lineCounter++;
       // System.out.println(lineCounter+" "+line);
        if(lineCounter>1)
       wholeFileInLine=wholeFileInLine+line;
    }
    String[] strArray=null;
    strArray = wholeFileInLine.split(",");
fileKernelArray = new float[strArray.length];
for(int i = 0; i < strArray.length; i++) {
    fileKernelArray[i] = Float.parseFloat(strArray[i]);
}
         

        return fileKernelArray;
    }

    
    
    
public BufferedImage execute(int kernelSize,float[] fileKernelArray,File f) throws Exception{
        
    BufferedImage img = null;
    
        img = ImageIO.read(new File(f.getAbsolutePath()));
    
    
 Kernel kernel = new Kernel(kernelSize,kernelSize,fileKernelArray);
ConvolveOp cop = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP,null);
BufferedImage output= cop.filter(img, null);
return output;
    } 


 public void RGB(){
/*int x=10,y=20;    
//int color =img.getRGB(x,y);
int blue =  color & 0xff;
int green = (color & 0xff00) >> 8;
int red = (color & 0xff0000) >> 16;
        System.out.println("\nRed="+red+"\nGreen="+green+"\nBlue="+blue);
*/} 
}