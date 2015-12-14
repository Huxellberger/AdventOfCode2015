// Collection of functions to convert to and from 16 bit unsigned Binary values
public class BinaryConversion
{
  // Function to convert binary to decimal 
  public static int bToD(boolean[] binaryValue)
  {
    int decimal = 0;
    for (int i = 0; i < binaryValue.length; i++)
    {
      if (binaryValue[i] == true)
        decimal += Math.pow(2, i);
    } // for
    return decimal;
  } // bToD function

  // Function to convert decimal to binary
  public static boolean[] dToB(int decimalValue, int bitLength)
  {
    int index = 0;
    boolean[bitLength] binary;
    for (index = 0; index < bitLength; index++)
      binary[bitLength] = false;
    index = 0;
    int newBit;
    while (decimalValue > 0)
    {
      newBit = decimalValue%2;
      if (newBit == 1)
        binary[index] = newBit;
      decimalValue /= 2;
      index++;
    } // while
    return binary;
  } // dToB function 
} // BinaryConversion Class
