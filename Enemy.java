import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy spider. Can sense your vibrations and eat you if you get close enough. Will avoid platforms, but can demolish them if they get between you.
 * 
 * @author Harry Robinson 22787039
 * @version 1.0
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int moveSeed = Greenfoot.getRandomNumber(100);
    private double deltaX = 0;
    private double deltaY = 0;
    
    private int groundHeight = getImage().getHeight()/2;
    private int sideWidth = getImage().getWidth()/2;
    
    public boolean playerCaught = false;
    
    private World myWorld;
    int worldHeight;
    int worldWidth;
    
    private int spiderTimer = 0;
    private int jitterSeed = Greenfoot.getRandomNumber(10);
    
    public void addedToWorld(World myWorld)
    {
        this.myWorld = myWorld;
        this.worldHeight = myWorld.getHeight();
        this.worldWidth = myWorld.getWidth();
    }
    
    public void act() 
    {
        spiderTimer += 1;
        attack();
        move();
        demolish();
    }  
    
    public void demolish()
    {
        if (isTouching(Block.class))
        {
            removeTouching(Block.class);
        }
    }
    
    public void attack()
    {
        if(isTouching(Player.class))
        {
            playerCaught = true;
        }
    }
    
    public void move()
    {
       double newX = getX() + deltaX;
       double newY = getY() - deltaY;
       
        Actor platformBelow = getOneObjectAtOffset(0, groundHeight + 55, Block.class);
        Actor platformAbove = getOneObjectAtOffset(0, -(groundHeight + 55), Block.class);
        Actor platformToRight = getOneObjectAtOffset(sideWidth + 55, 0, Block.class);
        Actor platformToLeft = getOneObjectAtOffset(-(sideWidth + 55), 0, Block.class);
                
        if(platformAbove!=null || platformBelow!=null || platformToRight!=null || platformToLeft!=null)
        {
            if(platformBelow!=null)
            {             
                    turn(80 + (jitterSeed));
                    move(4);                                
            }   
            if(platformAbove!=null)
            {                                  
                    turn(-(80 + (jitterSeed)));
                    move(4);                             
            }
             if(platformToRight!=null)
            {
             turn (-(80 + (jitterSeed)));
             move(4);
            }
             if(platformToLeft!=null)
            {    
             turn(80 + (jitterSeed));
             move(4);
            }
      
        }
    
        else if(isAtEdge())
        {
            turn(160 + (jitterSeed));
            move(5);
        }
        
        else if(!getObjectsInRange(200, Player.class).isEmpty())
        {
            turnTowards(((MyWorld)getWorld()).getPlayer().getX(), ((MyWorld)getWorld()).getPlayer().getY());
            Greenfoot.playSound("spider.wav");
            move(2);
        }

        else
        
        {
        
        move(2);
        moveSeed =  Greenfoot.getRandomNumber(100);
        
            if(spiderTimer >= (10 +(jitterSeed)))
            {
                if(moveSeed < 25)
                {
                    turn (-(moveSeed));
                    move ((int)jitterSeed/2);
                    jitterSeed = Greenfoot.getRandomNumber(10);
                    spiderTimer = 0;
                }
                else
                {
                    turn((int)moveSeed/2);
                    move((int)jitterSeed/2);
                    jitterSeed = Greenfoot.getRandomNumber(10);
                    spiderTimer = 0;
                }
            }
        }
 
    }
}
