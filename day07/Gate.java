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
    G_NOT,
    G_ROUTE
  } // GateType enum

  // Variables to represent the wire connectors, wire output 
  // All wires have 1 wire input and 1 output, except AND and OR which have
  // two inputs.
  Wire wireIn1;
  Wire wireIn2;
  Wire wireOut;
  
  int literalValue = -1;
  GateType gate;

  // Clone gate constructor
  public Gate(Gate anotherGate)
  {
    this.wireIn1 = anotherGate.wireIn1;
    this.wireIn2 = anotherGate.wireIn2;
    this.wireOut = anotherGate.wireOut;
    this.literalValue = anotherGate.literalValue;
    this.gate = anotherGate.gate;
  } // Gate constructor 

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
    System.out.println("Switching gate on");
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
      case G_ROUTE:   route();
                      break;
      default:  System.out.println("\nInvalid gate switched on..\n");
    } // switch
  } // turnOn method

  // What follows is private methods that implement the logic of the gates
  // First is bitwise and
  private void and()
  {
    // Work out if hooked to literal or not
    boolean[] newOut = new boolean[16];
    boolean[] literalWire = new boolean[16];
    if (literalValue >= 0)
      literalWire = BinaryConversion.dToB(literalValue, 16);
    else 
    { 
      for(int i = 0; i < 16; i++)      
        literalWire[i] = wireIn2.getBusBinary()[i];
    } // else
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
    boolean[] newOut = new boolean[16];
    boolean[] literalWire = new boolean[16];
    if (literalValue >= 0)
      literalWire = BinaryConversion.dToB(literalValue, 16);
    else
    { 
      for(int i = 0; i < 16; i++)      
        literalWire[i] = wireIn2.getBusBinary()[i];
    } // else
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
    boolean[] newOut = new boolean[16];
    for(int i = 0; i < 16; i++)
      newOut[i] = wireIn1.getBusBinary()[i];
    boolean[] workingOut = new boolean[16];
    for(int i = 0; i < 16; i++)
      workingOut[i] = wireIn1.getBusBinary()[i];
    while (iterations != 0)
    {
      for (int i = 15; i > 0; i--)
      {
        if(newOut[i] == true)
          workingOut[i-1] = true;
        else
          workingOut[i-1] = false;
      } // for
      for (int i = 0; i < 16; i++)
        newOut[i] = workingOut[i];
      newOut[15] = false;
      iterations--;
    } // while
    wireOut.changeBus(newOut);
  } // rshift method

  // logical left shift operation
  private void lshift()
  {
    int iterations = literalValue;
    boolean[] newOut = new boolean[16];
    for(int i = 0; i < 16; i++)
      newOut[i] = wireIn1.getBusBinary()[i];
    boolean[] workingOut = new boolean[16];
    for(int i = 0; i < 16; i++)
      workingOut[i] = wireIn1.getBusBinary()[i];
    while (iterations != 0)
    {
      for (int i = 0; i < 15; i++)
      {
        if(newOut[i] == true)
          workingOut[i+1] = true;
        else
          workingOut[i+1] = false;
      } // for
      for (int i = 0; i < 16; i++)
        newOut[i] = workingOut[i];
      newOut[0] = false;
      iterations--;
    } // while
    wireOut.changeBus(newOut);
  } // lshift method

  // not operation
  private void not()
  {
    boolean[] newOut = new boolean[16];
    for(int i = 0; i < 16; i++)
      newOut[i] = wireIn1.getBusBinary()[i];
    for(int i = 0; i < 16; i++)
    {
      if (newOut[i] == true)
        newOut[i] = false;
      else
        newOut[i] = true;
    } // for
    wireOut.changeBus(newOut);
  } // not method

  // route operation that connects two wires 
  private void route()
  {
    if (literalValue != -1)
      wireOut.changeBus(literalValue);
    else
      wireOut.changeBus(wireIn1.getBusBinary());
  } // route method

  // Method to print a gate as its wires
  // Used only for debugging purposes 
  public String toString()
  {
    return wireIn1 + " " + wireIn2 + " " + wireOut +  " " + literalValue + "\n";
  } // toString method
  
} // Gate class
