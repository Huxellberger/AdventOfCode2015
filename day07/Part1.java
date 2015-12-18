// Program to set up the circuit and send a current through it
// *TODO: FINISH THE PROCESS INSTRUCTION METHOD*, DON'T FORGET CHECKING IF 
// WIRE ALREADY EXISTS 

import java.util.*;
import java.io.*;

public class Part1
{

  public static final int GATE_NUM = 338;
  static int currentGateNo = 0;
  // ArrayList to hold all the gates
  static Gate[] gates = new Gate[GATE_NUM];
  // Hashmap to hold all the wires
  static HashMap <String, Wire> wires = new HashMap();

  // Regular expression to check if string is an int value in hiding
  static Pattern findInt = Pattern.compile("^[0-9]*$");

  // Function to check if wire already exists in the hashmap
  public static boolean checkWireMatch(String wireName)
  {
    if(wires.containsKey(wireName))
      return true;
    else
      return false;
  } // checkWireMatch function

  // Function to retrieve a  wire of a given key from the hashmap
  public static Wire getChosenWire(String wireName)
  {
    return wires.get(wireName);
  } // getChosenWire function

  // Function to check if a string contains an int
  // If it does parses the int and returns it
  // -1 means no int found
  public static int extractInt(String extractee)
  {
    Matcher intChecker = findInt.matcher(extractee);
    if (intChecker.find())
      return Integer.parseInt(extractee);
    else
      return -1;
  } // extractInt function 

  // Function to process an instruction and find the appropriate
  // course of action
  public static void processInstruction(String[] instruction)
  {
    // Declare everything needed by the process
    Wire wireIn1;
    Wire wireIn2;
    Wire wireOut;
    Gate newGate;

    int currentValue = 0;
    int input1 = -1;
    int input2 = -1;

    // First input always in index 0 no matter the instruction
    if ((currentValue = extractInt(instruction[0])) != -1)
      input1 = currentValue;
    else
    {
      if (checkWireMatch(instruction[0]))
        wireIn1 = (Wire)wires.get(instruction[0]);
      else
      {
        wireIn1 = new Wire(instruction[0]);
        wires.put(instruction[0], wireIn1);
      } // else  
    } // else

    // Next steps based off instruction length
    if (instruction.length == 5)
    {
      if ((currentValue = extractInt(instruction[2])) != -1)
        input2 = currentValue;
      else
      {
        if (checkWireMatch(instruction[2]))
          wireIn2 = getChosenWire(instruction[2]);
        else
        {
          wireIn2 = new Wire(instruction[2]);
          wires.put(instruction[2], wireIn2);
        } // else    
      } // else
      if (checkWireMatch(instruction[4]))
        wireOut = getChosenWire(instruction[4]);
      else
      {
        wireOut = new Wire(instruction[4]);
        wires.put(instruction[4], wireOut);
      } // else  
      if (instruction[1] == "AND")
      {
        if (input1 != -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_AND, wireIn1, wireIn2, wireOut);
        else if (input1 != -1 && input2 == -1)
          newGate = new Gate(Gate.GateType.G_AND, wireIn2, input1, wireOut);
        else if (input1 == -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_AND, wireIn1, input2, wireOut);
      } // if
      else if (instruction[1] == "OR")
      {
        if (input1 != -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_OR, wireIn1, wireIn2, wireOut);
        else if (input1 != -1 && input2 == -1)
          newGate = new Gate(Gate.GateType.G_OR, wireIn2, input1, wireOut);
        else if (input1 == -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_OR, wireIn1, input2, wireOut);
      } // else if 
      else if (instruction[1] == "RSHIFT")
      {
        if (input1 != -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_RSHIFT, wireIn1, wireIn2, wireOut);
        else if (input1 != -1 && input2 == -1)
          newGate = new Gate(Gate.GateType.G_RSHIFT, wireIn2, input1, wireOut);
        else if (input1 == -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_RSHIFT, wireIn1, input2, wireOut);
      } // else if
      else if (instruction[1] == "LSHIFT")
      {
        if (input1 != -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_LSHIFT, wireIn1, wireIn2, wireOut);
        else if (input1 != -1 && input2 == -1)
          newGate = new Gate(Gate.GateType.G_LSHIFT, wireIn2, input1, wireOut);
        else if (input1 == -1 && input2 != -1)
          newGate = new Gate(Gate.GateType.G_LSHIFT, wireIn1, input2, wireOut);
      } // else if
      else 
        System.out.println("Invalid instruction.");
      gates[currentGateNo] = newGate;
      currentGateNo++;
    } // if
    else if (instruction.length == 4)
    {
      if (checkWireMatch(instruction[1]))
        wireIn1 = getChosenWire(instruction[1]);
      else
        wireIn1 = new Wire(instruction[1]);

      if (checkWireMatch(instruction[3]))
        wireIn1 = getChosenWire(instruction[3]);
      else
        wireIn1 = new Wire(instruction[3]);
      newGate = new Gate(Gate.GateType.G_NOT, wireIn1, wireOut);
      gates[currentGateNo] = newGate;
      currentGateNo++;
    } // else if
    else if (instruction.length == 3)
    { 
      if((currentValue = extractInt(instruction[2])) != -1)
        input2 = currentValue;
      else 
      {
        if (checkWireMatch(instruction[2]))
          wireOut = getChosenWire(instruction[2]);
        else
        {
          wireOut = new Wire(instruction[2]);
          wires.put(instruction[2], wireOut);
        } // else  
      } // else
      if (input1 != -1)
        wireOut.changeBus(input1);
      else
        wireOut.changeBus(wireIn1.getBusDecimal());
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
      String line;
      while (line = br.readLine() != null)
      {
        String[] instruction =  line.split(" ");
        processInstruction(instruction);
      } // while
      // Turn on the circuit
      for (int i = 0; i <= currentGateNo; i++)
        gates[i].turnOn();
      Wire wireA = getChosenWire("a");
      System.out.println("\n" + wireA.getBusDecimal() + "\n");
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
