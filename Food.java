import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Spiderlings to snack on and increase player food score. Can't survive if their platform is gone.
 * @author Harry Robinson 22787039
 * @version 1.0
 */
public class Food extends Actor


{
    

    private boolean isReversing = false;
    
    private int moveSeed = Greenfoot.getRandomNumber(100);
    private int groundHeight = getImage().getHeight()/2;
    private int deltaX = 0;
    private int deltaY = 0; 
    private int reverseTimer = 0;

    
    public void act() 
    {
        move();
    } 
    
    //move randomly  to the left or right, then reverse direction once the edge of the platform is reached. If the platform blocks have been destroyed by the spider, destroy this piece of food. 
    private void move()
    {
    int newX = getX() + deltaX;
    int newY = getY() - deltaY;
    
    Actor platformBelowRight = getOneObjectAtOffset(1, groundHeight + 5, Block.class);
    Actor platformBelowLeft = getOneObjectAtOffset(-1, groundHeight + 5, Block.class);

   
        if((getObjectsInRange(50, Block.class).isEmpty()))
        {
            getWorld().removeObject(this);
        }

        if (deltaX == 0)
        {
            if (platformBelowRight !=null && platformBelowLeft != null)
            {
                if(moveSeed > 80)
               {

                   deltaX = 2;

               
                }
                else if (moveSeed < 20)
                {
                    getImage().mirrorHorizontally();
                    deltaX = -2;
   
               
                }
                else
                {
                    deltaX = 0;
                }
            }
        }
        
        if (isReversing)
        {
            reverseTimer += 1;
            
            if(reverseTimer >= 2)
            {
                reverseTimer = 0;
                isReversing = false;
                
            }
        
        }
        else if (isReversing == false && platformBelowRight == null || platformBelowLeft == null)
        {
        
              deltaX = -(deltaX);
              isReversing = true;
              getImage().mirrorHorizontally();
        }    

        
        setLocation(newX,newY);
        moveSeed = Greenfoot.getRandomNumber(100);
        }
  

}

