// Program to set up the circuit and send a current through it
//TODO FIX TURNING GATES ON

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1
{

  public static final int GATE_NUM = 339;
  // ArrayList to hold all the gates
  static Circuit circuit = new Circuit(GATE_NUM);
  // Hashmap to hold all the wires
  static HashMap <String, Wire> wires = new HashMap();

  // Regular expression to check if string is an int value in hiding
  static Pattern findInt = Pattern.compile("^[0-9]*$");

  // Function to check if wire already exists in the hashmap
  public static boolean checkWireMatch(String wireName)
  {
    System.out.println("\nChecking if wire matches");
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
    System.out.println(extractee);
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
    Wire wireIn1 = null;
    Wire wireIn2 = null;
    Wire wireOut = null;
    Gate newGate = null;

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
        System.out.println("Creating new wire");
        System.out.println(instruction[0]);
        wireIn1 = new Wire(instruction[0]);
        System.out.println("New wire created, inserting now");
        wires.put(instruction[0], wireIn1);
        System.out.println("Wire Inserted");
      } // else  
    } // else

    // Next steps based off instruction length
    if (instruction.length == 5)
    {
      System.out.println("Instruction is length 5");
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
      if (instruction[1].equals("AND"))
      {
        if (input1 == -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_AND, wireIn1, wireIn2, wireOut));
        else if (input1 != -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_AND, wireIn2, input1, wireOut));
        else if (input1 == -1 && input2 != -1)
          circuit.addGate( new Gate(Gate.GateType.G_AND, wireIn1, input2, wireOut));
      } // if
      else if (instruction[1].equals("OR"))
      {
       if (input1 == -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_OR, wireIn1, wireIn2, wireOut));
        else if (input1 != -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_OR, wireIn2, input1, wireOut));
        else if (input1 == -1 && input2 != -1)
          circuit.addGate( new Gate(Gate.GateType.G_OR, wireIn1, input2, wireOut));
      } // else if 
      else if (instruction[1].equals("RSHIFT"))
      {
       if (input1 == -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_RSHIFT, wireIn1, wireIn2, wireOut));
        else if (input1 != -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_RSHIFT, wireIn2, input1, wireOut));
        else if (input1 == -1 && input2 != -1)
          circuit.addGate( new Gate(Gate.GateType.G_RSHIFT, wireIn1, input2, wireOut));
      } // else if
      else if (instruction[1].equals("LSHIFT"))
      {
       if (input1 == -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_LSHIFT, wireIn1, wireIn2, wireOut));
        else if (input1 != -1 && input2 == -1)
          circuit.addGate(new Gate(Gate.GateType.G_LSHIFT, wireIn2, input1, wireOut));
        else if (input1 == -1 && input2 != -1)
          circuit.addGate( new Gate(Gate.GateType.G_LSHIFT, wireIn1, input2, wireOut));
      } // else if
      else 
        System.out.println("Invalid instruction.");
    } // if
    else if (instruction.length == 4)
    {
      if (checkWireMatch(instruction[1]))
        wireIn1 = getChosenWire(instruction[1]);
      else
        wireIn1 = new Wire(instruction[1]);

      if (checkWireMatch(instruction[3]))
        wireOut = getChosenWire(instruction[3]);
      else
        wireOut = new Wire(instruction[3]);
      circuit.addGate(new Gate(Gate.GateType.G_NOT, wireIn1, wireOut));
    } // else if
    else if (instruction.length == 3)
    { 
      System.out.println("Length 3 instruction sighted");
      if (checkWireMatch(instruction[2]))
        wireOut = getChosenWire(instruction[2]);
      else
      {
        wireOut = new Wire(instruction[2]);
        wires.put(instruction[2], wireOut);
      } // else  
      if (input1 != -1)
        circuit.addGate(new Gate(Gate.GateType.G_ROUTE, input1, wireOut));
      else
        circuit.addGate(new Gate(Gate.GateType.G_ROUTE, wireIn1, wireOut));
    } // else if
    else
      System.out.println("Invalid instruction length.");
    System.out.println("\n" + wireIn1 + " " + wireIn2 + " " + wireOut);
    System.out.println(input1 + " " + input2);
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
        System.out.println("\nFile not valid you elf mangler.\n");
        return;
      } // if

      FileInputStream fis = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));
      String line;
      int instructionIndex = 0;
      while ((line = br.readLine()) != null)
      {
        System.out.println(instructionIndex);
        String[] instruction =  line.split(" ");
        processInstruction(instruction);
        instructionIndex++;
      } // while
      // Turn on the circuit
      for (int i = 0; i < GATE_NUM; i++)
      {
        if(circuit.getGate(i) != null)
        {
          System.out.println("Printing gate " + i);
          System.out.println(circuit.getGate(i));
          circuit.getGate(i).turnOn();
          System.out.println(circuit.getGate(i));
        } // if
      } // for
     for (int i = 0; i < GATE_NUM; i++)
      {
        if(circuit.getGate(i) != null)
        {
          System.out.println("Printing gate " + i);
          System.out.println(circuit.getGate(i));
          circuit.getGate(i).turnOn();
          System.out.println(circuit.getGate(i));
        } // if
      } // for
      System.out.println("Getting wire a value now");
      Wire wireA = getChosenWire("a");
      System.out.println("\nWire A Value is: " + wireA.getBusDecimal() + "\n");
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
