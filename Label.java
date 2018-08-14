import greenfoot.*; 


/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
   public Label (String text)
   {
     GreenfootImage img = new GreenfootImage(text.length() * 30, 60);
     img.setColor(greenfoot.Color.BLACK);
     img.drawString(text, 4 , 40);
     setImage(img);
   }
   
   public void setText(String text)
   {
       GreenfootImage img = getImage();
       img.clear();
       img.drawString(text, 4 , 40);
   }
    
}
