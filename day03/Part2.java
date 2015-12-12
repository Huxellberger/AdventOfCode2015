// Class to deliver presents to the children
// Prints out the houses delivered to at least once at the end
// Created By Jake Huxell For Advent Of Code 2015
// MY EVIL TWIIIN version 

import java.util.*;
import java.io.*;

public class Part2
{
  // Function to convert index to hash map key
  public static String getKey (int index[])
  {
    String keyFirst = "";
    String keySecond = "";
    if (index[0] < 0)
      keyFirst += "minus";
    else
      keyFirst += "positive";
    if (index[1] < 0)
      keySecond += "minus";
    else
      keySecond += "positive";
    return keyFirst + index[0] + keySecond + index[1]; 
  } // getKey function

  // Function to update index based on char instruction
  public static void updateIndex (int index[], char instruction)
  {
    if (instruction == '^')
      index[1] += 1;
    else  if (instruction == 'v')
      index[1] -= 1;
    else if (instruction == '>')
      index[0] += 1;
    else if (instruction == '<')
      index[0] -= 1;
    else
    {}
  } // updateIndex function

  // Main function
  public static void main(String args[])
  {
    // Use a hashmap for the house locations
    int[] indexNormal = {0, 0};
    int[] indexRobo = {0, 0};
    boolean robotMoving = false;    

    String key;
    HashMap <String, House> town = new HashMap();
    key = getKey(indexNormal);
    town.put(key, new House(key));
    int houseCount = 1;

    // Put in try for possible IOException
    try
    {
      File file = new File(args[0]);
      if (!file.exists())
      {
        System.out.println("\nFile not valid you christmas leper.\n");
        return;
      } // if

      FileInputStream fis = new FileInputStream(file);
      char currentInstruction;
      while (fis.available() > 0)
      {
        currentInstruction = (char) fis.read();
        if(!robotMoving)
        {
          updateIndex(indexNormal, currentInstruction);
          key = getKey(indexNormal);
        } // if
        else 
        {
          updateIndex(indexRobo, currentInstruction);
          key = getKey(indexRobo);
        } // else

        House currentHouse = town.get(key);
        if (currentHouse == null)
        {
          town.put(key, new House(key));
          houseCount++;
        } // if
        robotMoving = !robotMoving;
      } // while
      System.out.println(houseCount);
    } // try block

    // Catch for IOExceptions
    catch (IOException exception)
    {
      System.err.println("\nSomething went wrong with the file.\n" + 
                         exception.getMessage());
    } // catch

    // Catch really bad exceptions
    catch (Exception exception)
    {
      System.err.println("\nSomething has gone very wrong indeed.\n" +
                         exception.getMessage());
    }// catch
  } // main function

  

} // Part1 Class


