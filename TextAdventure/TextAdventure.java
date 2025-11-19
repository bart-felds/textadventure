import java.util.*;


public class TextAdventure
{
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;


  public TextAdventure()
  {
    console = new FancyConsole("Great Text Adventure!", 600, 600);
    inScanner = new Scanner(System.in);
    ourHero = new Player("Bob", 100, 0);
  }


  public void play()
  {
    String input;
    console.setImage("distantcity.jpg");


    System.out.println("What is your name?\n");
    input = inScanner.nextLine();


    ourHero.changeName(input);
   
    System.out.println("You find yourself in a forest. \nYou see what looks like a city in the distance. \nWhat would you like to do? \ncity: go towards the city\nforest: turn around and re-enter the forest\nnap: go back to sleep\n" + ourHero.getName() + ": ");


    input = inScanner.nextLine();


    if(input.equals("city"))
    {
      enterZone1();
    }
    else if(input.equals("forest"))
    {
      enterZone2();
    }
    else if(input.equals("nap"))
    {
      System.out.println("You fall asleep and never wake up.");
      gameEnd();
    }
    else
    {
      System.out.println("You wait too long and darkness creeps in.");
      gameEnd();
    }
  }


  private void enterZone1()
  {
    console.setImage("mansion.jpg");
    String input = "";


    System.out.println("You reach an old mansion. The front door is partly open.\nenter\ngo back");


    while(!input.equals("enter") && !input.equals("go back"))
    {
      input = inScanner.nextLine();
    }


    if(input.equals("enter"))
    {
      double chance = Math.random();
      if(chance < 0.2)
      {
        System.out.println("A trap triggers! You lose 10 health.");
        ourHero.setHealth(ourHero.getHealth() - 10);
      }
      enterZone3();
    }
    else
    {
      enterZone2();
    }
  }


  private void enterZone2()
  {
    console.setImage("forest.jpg");
    String input = "";


    System.out.println("The forest grows darker. You hear rustling.\ncity\nstay\nwait");


    input = inScanner.nextLine();


    double chance = Math.random();
    if(chance < 0.3)
    {
      System.out.println("A wild shadow monster attacks!");
      ourHero.setHealth(ourHero.getHealth() - 20);
      System.out.println("You escape but lose 20 health. Current health: " + ourHero.getHealth());
    }


    if(input.equals("city"))
    {
      enterZone1();
    }
    else if(input.equals("stay"))
    {
      System.out.println("You stay still as shadows surround you.");
      gameEnd();
    }
    else
    {
      int counter = 0;
      while(counter < 3 && !input.equals("city"))
      {
        System.out.println("Something is moving. Type city to escape:");
        input = inScanner.nextLine();
        counter = counter + 1;
      }
      if(input.equals("city"))
      {
        enterZone1();
      }
      else
      {
        System.out.println("You waited too long.");
        gameEnd();
      }
    }
  }


  private void enterZone3()
  {
    console.setImage("foyer.jpg");
    String input = "";


    System.out.println("Inside the mansion foyer. Two doors stand before you.\nleft\nright");


    while(!(input.equals("left") || input.equals("right")))
    {
      input = inScanner.nextLine();
    }


    if(input.equals("left"))
    {
      enterZone4();
    }
    else
    {
      enterZone5();
    }
  }


  private void enterZone4()
  {
    console.setImage("library.jpg");
    String input = "";


    System.out.println("You enter a library. A key glows on a table.\ntake key\nback");


    while(!input.equals("take key") && !input.equals("back"))
    {
      input = inScanner.nextLine();
    }


    if(input.equals("take key"))
    {
      if(!(ourHero.getGold() == 1))
      {
        ourHero.setGold(1);
      }
      enterZone3();
    }
    else
    {
      enterZone3();
    }
  }


  private void enterZone5()
  {
    console.setImage("door.jpg");
    String input = "";


    System.out.println("A locked wooden door blocks your way.\nopen\nback\nkick door");


    input = inScanner.nextLine();


    if(input.equals("open"))
    {
      if(ourHero.getGold() == 1)
      {
        enterZone6();
      }
      else
      {
        System.out.println("You do not have the key.");
        enterZone3();
      }
    }
    else if(input.equals("back"))
    {
      enterZone3();
    }
    else
    {
      int strength = 0;
      while(strength < 3 && !input.equals("open"))
      {
        System.out.println("You kick the door. Try again? kick or open.");
        input = inScanner.nextLine();
        strength = strength + 1;
      }
      if(input.equals("open") && ourHero.getGold() == 1)
      {
        enterZone6();
      }
      else
      {
        System.out.println("The door does not budge.");
        enterZone3();
      }
    }
  }


  private void enterZone6()
  {
    console.setImage("attic.jpg");
    String input = "";


    System.out.println("You enter the attic. A whisper asks: 'Do you wish to leave?'\nyes\nno");


    while(!input.equals("yes") && !input.equals("no"))
    {
      input = inScanner.nextLine();
    }
      

    if(input.equals("yes"))
    {
      System.out.println("A bright light surrounds you. You escape the mansion!");
      gameEnd();
    }
    else
    {
      System.out.println("You remain in the mansion forever.");
      gameEnd();
    }
  }


  private void gameEnd()
  {
    System.out.println("Thanks for playing!");
    inScanner.close();
  }
}



