// Class to represent a computer circuit
public class Circuit
{
  private Gate gates[];
  private int currentGates = 0;
  private int gateLimit = 0;

  // Circuit constructor 
  public Circuit(int gateNo)
  {
    gateLimit = gateNo;
    gates = new Gate[gateNo];
  } // Circuit constructor 

  // Method to add a gate to the circuit 
  public void addGate(Gate chosenGate)
  {
    if (gateLimit == currentGates)
      System.out.println("Unable to add gate, limit reached");
    else
    {
      gates[currentGates] = new Gate(chosenGate);
      currentGates++;
    } // else
  } // addGate method

  // Method to return a gate from the circuit
  public Gate getGate(int gateIndex)
  {
    return gates[gateIndex];
  } // getGate method
} // Circuit class 
