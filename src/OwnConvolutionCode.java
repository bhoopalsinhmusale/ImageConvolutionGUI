import com.sun.corba.se.impl.orbutil.ORBUtility;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.DataBufferByte;
import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OwnConvolutionCode
{
    int[][] outputArray;
     int[][] outputArrayOfExistingCode;
    int[][] redOutputArray;
    int[][] blueOutputArray;
    int[][] greenOutputArray;
    int[][] redArray;
    int[][] greenArray;
    int[][] blueArray;
    float[] kernelArray;
    int kernelSize;
    BufferedImage imageForIcon=null;
  File outputFileForIcon;
  
    void getRGBValues( BufferedImage img){
    
    redArray=new int[img.getWidth()][img.getHeight()];
    greenArray=new int[img.getWidth()][img.getHeight()];
    blueArray=new int[img.getWidth()][img.getHeight()];
        for (int i = 0; i < img.getWidth(); i++) {
        for (int j = 0; j < img.getHeight(); j++) {
        
        int c=img.getRGB(i,j);
      
        redArray[i][j]=(c & 0xff0000) >> 16; 
         greenArray[i][j]=(c & 0xff00) >> 8; 
        blueArray[i][j]= c & 0xff; 
        }     
        }
    }
    
    public void initializeOutputArray(BufferedImage img){
        outputArray=new int[img.getWidth()][img.getHeight()];
        //System.out.println("***Output Array***");
        for (int i = 0; i < img.getWidth(); i++) {
        for (int j = 0; j < img.getHeight(); j++) {
        outputArray[i][j]=0;
              }}}
    
    public float[] kernelRead(String Operation) throws Exception
    {   String textFilePath="D:\\ImageConvolutionGUI\\Kernels\\"+Operation+".txt";
        float[] fileKernelArray=null;			
        BufferedReader br = new BufferedReader(new FileReader(new File(textFilePath)));
        String line="";
        String wholeFileInLine="";
            int lineCounter=0;
            System.out.println("***Kernel File****");
        while ((line = br.readLine()) != null) {
            lineCounter++;
            System.out.println(lineCounter+" "+line);
        if(lineCounter>1)
            wholeFileInLine=wholeFileInLine+line;
        
        else
            kernelSize=Integer.parseInt(line);
    }
    String[] strArray=null;
    strArray = wholeFileInLine.split(",");
    fileKernelArray = new float[strArray.length];
    for(int i = 0; i < strArray.length; i++) {
     fileKernelArray[i] = Float.parseFloat(strArray[i]);
    }
        return fileKernelArray;
    }
    
    public void computOutputArray(BufferedImage img){
        redOutputArray=new int[img.getWidth()][img.getHeight()];
        greenOutputArray=new int[img.getWidth()][img.getHeight()];
        blueOutputArray=new int[img.getWidth()][img.getHeight()];
        for(int x=0;x<img.getHeight()-2;x++){
          for(int y=0;y<img.getWidth()-2;y++){    
                int kernelArrayCounter=8;
                float resultRed=0;
                float resultGreen=0;
                float resultBlue=0;

        for (int i = x; i < (x+3); i++) {
          for (int j = y; j < (y+3); j++) {
                resultRed=resultRed+(redArray[i][j]*kernelArray[kernelArrayCounter]);
                resultGreen=resultGreen+(greenArray[i][j]*kernelArray[kernelArrayCounter]);
                resultBlue=resultBlue+(blueArray[i][j]*kernelArray[kernelArrayCounter]);
                    kernelArrayCounter--;
            }}
        if(resultRed<0)
            resultRed=0;
        else if(resultRed>255)
            resultRed=255;
        redOutputArray[x+1][y+1]=(int) resultRed;
 
        if(resultBlue<0)
            resultBlue=0;
        else if(resultBlue>255)
            resultBlue=255;
        blueOutputArray[x+1][y+1]=(int) resultBlue;
        
        if(resultGreen<0)
            resultGreen=0;
        else if(resultGreen>255)
            resultGreen=255;
        greenOutputArray[x+1][y+1]=(int) resultGreen; 
        
        }}}
    
    public void writeImage(BufferedImage img,float[] fileKernelArray,String operation,String fileName) throws IOException{

        BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB); 

    outputArrayOfExistingCode=new int[img.getWidth()][img.getHeight()];
  
    for (int y = 0; y < img.getHeight(); y++) {
     for (int x = 0; x < img.getWidth(); x++) {
        int rgb = redOutputArray[y][x];
        rgb = (rgb << 8) + greenOutputArray[y][x]; 
        rgb = (rgb << 8) + blueOutputArray[y][x];
        outputArray[y][x]=rgb;
        image.setRGB(y, x, rgb);
     }
  }
  File outputFile = new File("D:\\ImageConvolutionGUI\\output\\output of "+operation+" for "+fileName+".jpg");
  ImageIO.write(image, "jpg", outputFile);
  outputFileForIcon=outputFile;
    }}