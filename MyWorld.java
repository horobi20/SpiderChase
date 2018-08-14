import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
 Player player = new Player();
 Enemy enemy = new Enemy();
 
 Label scoreLabel = new Label("Jumps: 0");
 Label foodLabel = new Label("Food: 0");
 Label endLabel = new Label ("      ");
 
 private boolean isWinner = false;
 private boolean isSurvivor = false;
 private boolean gameOver = false;

 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //add platforms. 
        Block block = new Block();
        addObject(block,0,550);
        Block block2 = new Block();
        addObject(block2,30,550);
        Block block3 = new Block();
        addObject(block3,60,550);
        Block block4 = new Block();
        addObject(block4,90,550);
        Block block5 = new Block();
        addObject(block5,110,550);
        Block block6 = new Block();
        addObject(block6,140,550);
        Block block7 = new Block();
        addObject(block7,170,550);
        Block block8 = new Block();
        addObject(block8,200,550);
        Block block9 = new Block();
        addObject(block9,230,550);
        Block block10 = new Block();
        addObject(block10,260,550);
        Block block11 = new Block();
        addObject(block11,290,550);

        Block block12 = new Block();
        addObject(block12,390,500);
        Block block13 = new Block();
        addObject(block13,410,500);
        Block block14 = new Block();
        addObject(block14,440,500);
        Block block15 = new Block();
        addObject(block15,470,500);
        Block block16 = new Block();
        addObject(block16,500,500);

        Block block18 = new Block();
        addObject(block18,500,400);
        Block block19 = new Block();
        addObject(block19,530,400);
        Block block20 = new Block();
        addObject(block20,560,400);
        Block block21 = new Block();
        addObject(block21,590,400);
        Block block22 = new Block();
        addObject(block22,620,400);
        Block block23 = new Block();
        addObject(block23,650,400);
        Block block24 = new Block();
        addObject(block24,415,300);
        Block block25 = new Block();
        addObject(block25,385,300);
        Block block26 = new Block();
        addObject(block26,355,300);
        Block block27 = new Block();
        addObject(block27,325,300);
        Block block28 = new Block();
        addObject(block28,295,300);
        Block block29 = new Block();
        addObject(block29,265,300);

        Block block17 = new Block();
        addObject(block17,740,575);
        Block block30 = new Block();
        addObject(block30,770,575);
        Block block31 = new Block();
        addObject(block31,800,575);
        Block block32 = new Block();
        addObject(block32,830,575);
        Block block33 = new Block();
        addObject(block33,860,575);
        Block block34 = new Block();
        addObject(block34,890,575);
        Block block35 = new Block();
        addObject(block35,920,575);
        Block block36 = new Block();
        addObject(block36,950,575);

        Block block37 = new Block();
        addObject(block37,750,310);
        Block block38 = new Block();
        addObject(block38,780,310);
        Block block39 = new Block();
        addObject(block39,810,310);
        Block block40 = new Block();
        addObject(block40,840,310);

        Block block41 = new Block();
        addObject(block41,660,200);
        Block block42 = new Block();
        addObject(block42,630,200);
        Block block43 = new Block();
        addObject(block43,600,200);
        Block block44 = new Block();
        addObject(block44,570,200);
        Block block45 = new Block();
        addObject(block45,540,200);
        Block block46 = new Block();
        addObject(block46,510,200);

        Block block47 = new Block();
        addObject(block47,390,80);
        Block block48 = new Block();
        addObject(block48,360,80);
        Block block49 = new Block();
        addObject(block49,330,80);
        Block block50 = new Block();
        addObject(block50,300,80);

        Block block51 = new Block();
        addObject(block51,80,180);
        Block block52 = new Block();
        addObject(block52,50,180);

        //add player
        addObject(player, 33, 532);

        //add label GUI elements
        addObject(scoreLabel, 980, 30);
        addObject(foodLabel, 140, 30);
        addObject(endLabel, 550, 300);

        //add spiderlings
        Food food = new Food();
        addObject(food,388,474);
        Food food2 = new Food();
        addObject(food2,504,372);
        Food food3 = new Food();
        addObject(food3,303,276);
        Food food5 = new Food();
        addObject(food5,854,554);
        Food food6 = new Food();
        addObject(food6,804,290);
        Food food7 = new Food();
        addObject(food7,623,180);
        Food food8 = new Food();
        addObject(food8,570,180);
        Food food9 = new Food();
        addObject(food9,362,59);
        Food food10 = new Food();
        addObject(food10,65,161);
        Food food11 = new Food();
        addObject(food11,901,555);

        
        addObject(enemy,822,148);
        
    }
    //allows accessibility from other classes/actors.
    public Actor getPlayer()
    {
        return player;
    }
    
    private void gameOver()
    {
        
        
        removeObjects(getObjects(Block.class));
        removeObjects(getObjects(Player.class));
        removeObjects(getObjects(Enemy.class));
        removeObjects(getObjects(Food.class));
        
        setBackground(new GreenfootImage("game over.png"));
        if(isWinner)
        {
          
         endLabel.setText("You have won!");
         Greenfoot.stop();
        }
        else if(isSurvivor)
        {
         endLabel.setText("You survived!");
         Greenfoot.stop();
        }
        else
        {
           
         endLabel.setText("You have lost!");
         Greenfoot.stop();
        }
        
    }
    //checks for game end conditions.
    private void checkGameOver()
    {       
        int remainingFood = getObjects(Food.class).size();
 
        if(remainingFood == 0)
        {
            gameOver = true;
            
            if(player.currentFood == 10)
            {
            isWinner = true;
            gameOver();
            }
            else
            {
                isSurvivor = true;
                gameOver();
            }
        }
        else if(enemy.playerCaught == true)
        {
            gameOver = true;
            isWinner = false;
            gameOver();
        }
    }
    //runs updates for labels and tests game end.
    public void act()
    {
        scoreLabel.setText("Score: "+ player.currentScore);
        foodLabel.setText("Food: "+ player.currentFood);
        checkGameOver();
    }
    
}
