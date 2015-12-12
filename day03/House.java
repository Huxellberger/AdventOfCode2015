// Class to create house objects that will make up a street
public class House 
{
  private String name;
  private boolean hasPresents;

  // Constructor 
  public House(String chosenName)
  {
    name = chosenName;
    hasPresents = true;
  } // House Constructor

  // Method to return if house has presents or not
  public boolean checkPresents()
  {
    return hasPresents;
  } // checkPresents method

  // Method to print house as its key
  public String toString()
  {
    return name;
  } // toString method 
} // House class
