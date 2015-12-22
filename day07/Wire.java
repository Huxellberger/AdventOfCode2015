// Program to represent wire objects in a makeshift circuit
public class Wire // No this isn't verilog!!
{
  // Variables to rep data bus and wire's unique name
  // It's an unsigned value so we have to create our own data type
  private boolean[] bus = new boolean[16];
  private String name;

  // Wire constructor
  public Wire(String chosenName)
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
  public boolean[] getBusBinary()
  {
    return bus;
  } // getBusBinary method

  // Method to change the value of the bus
  public void changeBus(int newValue)
  {
    System.out.println("Converting to binary now");
    bus = BinaryConversion.dToB(newValue, 16);
  } // changeBus method

  // Additional method that takes bus as arg
  public void changeBus(boolean[] newValue)
  {
    for (int i = 0; i < bus.length; i++)
    {
      bus[i] = newValue[i];
    } // for
  } // changeBus method

  // Method to get the name of the wire
  public String getName()
  {
    return name;
  } // getName method

  // Method that prints out a wire
  public String toString()
  {
    int num = BinaryConversion.bToD(bus);
    return name + ": " + num; 
  } // toString function
} // Wire Class
