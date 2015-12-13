// Program to burninate christmas
// Created By Jake Huxell for Advent Of Code 2015

#include <iostream> 
#include <fstream>
#include <string>
#include <sstream>

using namespace std;

// Instructions as strings 
const string TOGGLE = "toggle";
const string TURN_ON = "turn on";
const string TURN_OFF = "turn off";

// Enum type to rep christmas lights
struct ChristmasLight {
  int brightness;
}; // ChristmasLight struct

// Size of array for christmas lights
int SQUARE_SIDE = 1000;
ChristmasLight lights[1000][1000];

// Function to switch lights to default all off state
void resetLights()
{
  for (int i = 0; i < SQUARE_SIDE; i++)
  {
    for (int j = 0; j < SQUARE_SIDE; j++)
    {
      lights[i][j].brightness = 0;
    } // for
  } // for
} // resetLights function

// Function to count how many lights are on
// returns an int representing the lights switched on
int countBrightness()
{
  int totalBrightness = 0;
  for (int i = 0; i < SQUARE_SIDE; i++)
  {
    for (int j = 0; j < SQUARE_SIDE; j++)
    {
      totalBrightness += lights[i][j].brightness;
    } // for
  } // for
  return totalBrightness; 
} // countLights function

// Function to get just one corner index of a diagonal
// Takes instruction and start index as input
// Returns number for coordinate as output
int getCornerIndex(string instruction, int startIndex)
{
  int currentIndex = startIndex;
  char currentChar = instruction[startIndex];
  string fullNum;
  while (currentChar != ' ' && currentChar != ',' && 
         currentIndex < instruction.length())
  {
    fullNum += currentChar;
    currentIndex++;
    currentChar = instruction[currentIndex];
  } // while
  istringstream convert(fullNum);
  convert >> currentIndex;
  return currentIndex;
} // getCornerIndex function

// Function to get numbers from instruction
// Takes start index of first and uses it to calculate location of next
void getCorners(int corners[], string instruction, int startIndex)
{
  int currentIndex = startIndex;
  for (int i = 0; i < 4; i++)
  {
    corners[i] = getCornerIndex(instruction, currentIndex);
    if (corners[i] < 9)
      currentIndex += 2;
    else if (corners[i] < 99)
      currentIndex += 3;
    else
      currentIndex += 4;
    if (i == 1)
      currentIndex += 8;
  } // for
} // getCorners function

// Function to toggle lights, swaps current setting
// Takes light array as input
void toggleLights(int corners[])
{
  for (int i = corners[0]; i <= corners[2]; i++)
  {
    for (int j = corners[1]; j <= corners[3]; j++)
    {
      lights[i][j].brightness += 2;
    } // for

  } // for
  
} // toggleLights function


// Function to turn on lights
// Takes light array as input
void turnOnLights(int corners[])
{
  for (int i = corners[0]; i <= corners[2]; i++)
  {
    for (int j = corners[1]; j <= corners[3]; j++)
    {
      lights[i][j].brightness += 1;
    } // for
  } // for

} // turnOnLights function

// Function to turn off lights
// Takes light array as input
void turnOffLights(int corners[])
{
  for (int i = corners[0]; i <= corners[2]; i++)
  {
    for (int j = corners[1]; j <= corners[3]; j++)
    {
      if (lights[i][j].brightness == 0)
      {}
      else
        lights[i][j].brightness -= 1;
    } // for
  } // for
} // turnOffLights function


// Function to work out what instruction we're doing with lights
// 3 possible, turn off, turn on, and toggle
void fiddleLights(string instruction)
{
  // Array to hold 4 coordinates of rectangle 
  int corners[4];

  if (instruction.find(TOGGLE) != string::npos)
  {
    getCorners(corners, instruction, 7);
    toggleLights(corners);
  } // if
  else if (instruction.find(TURN_ON)!= string::npos)
  {
    getCorners(corners, instruction, 8);
    turnOnLights(corners);
  } // else if
  else 
  {
    getCorners(corners, instruction, 9);
    turnOffLights(corners);
  } // else
} // fiddleLights function

// Main function
int main()
{
  string line;
  int totalBrightness = 0;   // Lights start turned off

  ifstream fileReader ("tst.txt");
  if (! fileReader.is_open())
  {
    cout << "\nFile not opened! What's going on?\n";
    return 0;
  } // if

  resetLights();

  while (getline(fileReader, line))
  {
    fiddleLights(line);
  } // while

  // Count up active lights and print result
  totalBrightness = countBrightness();

  cout << "There is a total of " << totalBrightness << " brightness\n";
  
} // main function
