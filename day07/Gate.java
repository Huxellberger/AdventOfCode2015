// Program to represent a gate object 
public class Gate
{
  // Enum to represent the different kinds of gates
  public enum GateType 
  {
    G_AND,
    G_OR,
    G_RSHIFT,
    G_LSHIFT,
    G_NOT
  } // GateType enum

  // Variables to represent the wire connectors, wire output 
  // All wires have 1 wire input and 1 output, except AND and OR which have
  // two inputs.
  Wire wireIn1;
  Wire wireIn2;
  Wire wireOut;
  
  int literalValue = -1;
  GateType gate;

  // Gate constructor method for two inputs
  public Gate(GateType chosenGate, Wire chosenWire1, Wire chosenWire2, 
             Wire chosenOut)
  {
    gate = chosenGate;
    wireIn1 = chosenWire1;
    wireIn2 = chosenWire2;
    wireOut = chosenOut;
  } // Gate constructor

  // Gate constructor for one literal and one wire value, one output
  public Gate(GateType chosenGate, Wire chosenWire1, int chosenLiteral, 
             Wire chosenOut)
  {
    gate = chosenGate;
    wireIn1 = chosenWire1;
    literalValue = chosenLiteral;
    wireOut = chosenOut;
  } // Gate constructor

  // Gate constructor for 1 input, 1 out no literal
  public Gate(GateType chosenGate, Wire chosenWire1, Wire chosenOut)
  {
    gate = chosenGate;
    wireIn1 = chosenWire1;
    wireOut = chosenOut;
  } // Gate constructor 

  // Gate constructor for 1 literal and one out
  public Gate(GateType chosenGate, int chosenLiteral, Wire chosenOut)
  {
    gate = chosenGate;
    literalValue = chosenLiteral;
    wireOut = chosenOut;
  } // Gate constructor

  // Function to perform an operation on a wire's inputs
  // Will use a switch statement to distinguish between them
  public void turnOn()
  {
    switch(gate)
    {
      case G_AND:  and();
                   break;
      case G_OR:  or();
                  break;
      case G_RSHIFT:  rshift();
                      break;
      case G_LSHIFT:  lshift();
                      break;
      case G_NOT:     not();
                      break;
      default:  System.out.println("\nInvalid gate switched on..\n");
    } // switch
  } // turnOn method

  // What follows is private methods that implement the logic of the gates
  // First is bitwise and
  private void and()
  {
    // Work out if hooked to literal or not
    boolean[] newOut = new boolean[15];
    boolean[] literalWire = new boolean[15];
    if (literalValue >= 0)
      literalWire = BinaryConversion.dToB(literalValue, 16);
    else 
      literalWire = wireIn2.getBusBinary();
    for (int i = 0; i < 16; i++)
    {
      if(literalWire[i] == true && (wireIn1.getBusBinary())[i] == true)
        newOut[i] = true;
      else 
        newOut[i] = false;
    } // for
    wireOut.changeBus(newOut);
  } // and method

  // bitwise or operation
  private void or()
  {
    boolean[] newOut = new boolean[15];
    boolean[] literalWire = new boolean[15];
    if (literalValue >= 0)
      literalWire = BinaryConversion.dToB(literalValue, 16);
    else 
      literalWire = wireIn2.getBusBinary();
    for (int i = 0; i < 16; i++)
    {
      if(literalWire[i] == false && (wireIn1.getBusBinary())[i] == false)
        newOut[i] = false;
      else 
        newOut[i] = true;
    } // for
    wireOut.changeBus(newOut);    
  } // or method

  // logical right shift operation
  private void rshift()
  {
    int iterations = literalValue;
    boolean[] newOut = wireIn1.getBusBinary();
    while (iterations != 0)
    {
      for (int i = 0; i < 15; i++)
      {
        newOut[i+1] = newOut[i];
      } // for
      newOut[0] = false;
      iterations--;
    } // while
    wireOut.changeBus(newOut);
  } // rshift method

  // logical left shift operation
  private void lshift()
  {
    int iterations = literalValue;
    boolean[] newOut = wireIn1.getBusBinary();
    while (iterations != 0)
    {
      for (int i = 15; i > 1; i++)
      {
        newOut[i-1] = newOut[i];
      } // for
      newOut[15] = false;
      iterations--;
    } // while
    wireOut.changeBus(newOut);
  } // lshift method

  // not operation
  private void not()
  {
    boolean[] newOut = wireIn1.getBusBinary();
    for(int i = 0; i < 15; i++)
    {
      if (newOut[i] == true)
        newOut[i] = false;
      else
        newOut[i] = true;
    } // for
    wireOut.changeBus(newOut);
  } // not method
  
} // Gate class
