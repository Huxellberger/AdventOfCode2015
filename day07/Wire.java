// Program to represent wire objects in a makeshift circuit
public class Wire // No this isn't verilog!!
{
  // Variables to rep data bus and wire's unique name
  // It's an unsigned value so we have to create our own data type
  private boolean[15] bus;
  private string name;

  // Wire constructor
  public Wire(string chosenName)
  {
    for (int i =0; i < 16; i++)
      bus[i] = false;
    name = chosenName;
  } // Wire constructor

  // Method to get unsigned value repped by bus as Decimal
  public int getBusDecimal()
  {
    int numValue = BinaryConversion.bToD(bus);
    return numValue;
  } // getBus method

  // Method to get unsigned value repped by bus in binary
  public int getBusBinary()
  {
    return bus;
  } // getBusBinary method

  // Method to change the value of the bus
  public void changeBus(int newValue)
  {
    bus = BinaryConversion.dToB(newValue);
  } // changeBus method

  // Additional method that takes bus as arg
  public void changeBus(boolean[] newValue)
  {
    bus = newValue;
  } // changeBus method

  // Method to get the name of the wire
  public string getName()
  {
    return name;
  } // getName method

  // Method that prints out a wire
  public string toString()
  {
    int num = bToD(bus);
    System.out.println(name + ": " + num + "\n"); 
  } // toString function
} // Wire Class
