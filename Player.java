import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player character - you're an ant!
 * 
 * @author Harry Robinson 22787039
 * @version 1.0
 */
public class Player extends Actor
{
    private int jumpHeight = 10;
    private int walkSpeed = 5;
    private double fallSpeed = 0.4;
    
    private boolean imageFlipped = false;
    private boolean inTheAir = false;
    private double deltaX = 0;
    private double deltaY = 0; 
    private int groundHeight = getImage().getHeight()/2;
    private int sideWidth = getImage().getWidth()/2;
    private World myWorld;
    int worldHeight;
    int worldWidth;

    public int currentScore = 0;
    public int currentFood = 0;

    public void addedToWorld(World myWorld)
    {
        this.myWorld = myWorld;
        this.worldHeight = myWorld.getHeight();
        this.worldWidth = myWorld.getWidth();
    }

    /**
     * Act - do whatever the PlatformJumper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(inTheAir)
        {
            fall();
        } 
        else 
        {
            getCommand();
            eat();
        }
        move();
    }  
    public void scorecounter()
    {
            currentScore += 1;  
    }

    private void run (String direction)
    {
        if(direction=="left")
            deltaX = walkSpeed*-1;
        else
            deltaX = walkSpeed;
    }
    
    private void drop()
    {
        deltaY -= fallSpeed;
    }

   private void stop ()
    {
        deltaX = 0;
    }

    private void jump()
    {
        deltaY += jumpHeight;
        inTheAir = true;
    }

    private void fall()
    {
        deltaY-=fallSpeed;
    }

    private void move()
    {
        double newX = getX() + deltaX;
        double newY = getY() - deltaY;

        Actor platformBelow = getOneObjectAtOffset(0, groundHeight + 5, Block.class);
        Actor platformAbove = getOneObjectAtOffset(0, -(groundHeight + 5), Block.class);
        Actor platformToRight = getOneObjectAtOffset(sideWidth + 5, 0, Block.class);
        Actor platformToLeft = getOneObjectAtOffset(-(sideWidth + 5), 0, Block.class);
        if(platformBelow!=null)
        {
            if(deltaY<= 0)
            {
               if(!Greenfoot.isKeyDown("down"))
                   {deltaY = 0;
                    inTheAir = false;
                    GreenfootImage platformImage = platformBelow.getImage();
                    int topOfPlatform = platformBelow.getY() - platformImage.getHeight()/2;
                    newY = topOfPlatform - groundHeight;
                   }           
            }
        }
        else if(getY() >= worldHeight - groundHeight) 
        {
            if(deltaY < 0)
            {
                deltaY = 0;
                inTheAir = false;
                newY = worldHeight - groundHeight;
            }
        }
        else 
        {
            inTheAir = true;
        }
        
        if(platformAbove !=null)
        {
            if(deltaY>0)
            {
                deltaY=0;
                
                GreenfootImage platformImage = platformAbove.getImage();
                int bottomOfPlatform = platformAbove.getY() + platformImage.getHeight()/2;
                newY = bottomOfPlatform + groundHeight;
            }
        }
        
        if(getX() <= sideWidth)
        {
            deltaX = Math.abs(deltaX);
        }
        
        if(getX()>=worldWidth-sideWidth)
        {
            deltaX = Math.abs(deltaX) * -1;
        }
        
        if(platformToRight != null)
        {
            deltaX = Math.abs(deltaX) * -1;
        }
        
        if(platformToLeft != null)
        {
            deltaX = Math.abs(deltaX);
        }
        
        orientImage();
        setLocation((int)newX,(int)newY);
    }
    private void eat()
    {
        if (isTouching(Food.class))
        {
            removeTouching(Food.class);
            currentFood += 1;           
        }
    }
    private void getCommand()
    {
        if(Greenfoot.isKeyDown("left")|| Greenfoot.isKeyDown("a"))
        {
            run("left");
        } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            run("right");
        } else 
        {
            stop();
        }

        if(Greenfoot.isKeyDown("up")|| Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w"))
        {
            jump();
            Greenfoot.playSound("ant.wav");
            scorecounter();
        }
        
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
        {            
           drop();
        }
    }
    private void orientImage()
    {
        if(deltaX < 0 & imageFlipped == false)
        {
            getImage().mirrorHorizontally();
            imageFlipped = true;
        }
        if(deltaX > 0 & imageFlipped == true)
        {
            getImage().mirrorHorizontally();
            imageFlipped = false;
        }
    }
}
