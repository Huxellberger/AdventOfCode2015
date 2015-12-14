// Program to set up the circuit and send a current through it

import java.util.*;
import java.io.*;

public class Part1
{

  final int GATE_NUM = 338;
  // ArrayList to hold all the gates
  Gate[GATE_NUM] gates;
  // Hashmap to hold all the wires
  HashMap <String, Wire> wires = new HashMap();

  // Regular expression to check if string is an int value in hiding
  Pattern findInt = Pattern.compile("^[0-9]*$");

  // Function to check if a string contains an int
  // If it does parses the int and returns it
  // -1 means no int found
  public static int extractInt(string extractee)
  {
    Matcher intChecker = findInt.matcher(extractee);
    if (intChecker.find())
      return Integer.parseInt(extractee);
    else
      return -1;
  } // extractInt function 

  // Function to process an instruction and find the appropriate
  // course of action
  public static void processInstruction(string[] instruction)
  {
    int currentValue = 0;
    int input1 = -1;
    int input2 = -1;

    // First input always in index 0 no matter the instruction
    if ((currentValue = extractInt(instruction[0])) != -1)
      input1 = currentValue;
    else
    {
      Wire wireIn1 = new Wire(instruction[0]);
      wires.put(instruction[0], wireIn1);  
    } // else

    // Next steps based off instruction length
    if (instruction.length == 5)
    {
      if ((currentValue = extractInt(instruction[2])) != -1)
        input2 = currentValue;
      else
      {
        Wire wireIn2 = new Wire(instruction[2]);
        wires.put(instruction[2], wireIn2);  
      } // else
      Wire wireOut = new Wire(instruction[4]);
      wires.put(instruction[4], wireOut);
      if (instruction[1] == "AND")
 
      else if (instruction[1] == "OR")
       
      else if (instruction[1] == "RSHIFT")

      else if (instruction[1] == "LSHIFT")

      else 
        System.out.println("Invalid instruction.");
    } // if
    else if (instruction.length == 3)
    { 
    } // else if
    else
      System.out.println("Invalid instruction length.");
  } // processInstruction method

  // Main function
  public static void main(String args[])
  {
    // Put in try for possible IOException
    try
    {
      File file = new File(args[0]);
      if (!file.exists())
      {
        System.out.println("\nFile not valid you elf feeler.\n");
        return;
      } // if

      FileInputStream fis = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      string line;
      while (line = br.readLIne() != null)
      {
        string[] instruction =  line.split(" ");
        processInstruction(instruction);
      } // while
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
  } // Main function
} // Part1 class
