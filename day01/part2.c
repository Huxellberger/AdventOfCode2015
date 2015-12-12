// Program to read in a stream of data, incrementing by one on open bracket, 
// decrementing on close
// Created By Jake Huxell for advent of code 2015. 

#include <stdio.h>
#include <stdlib.h>

// Function that works out if going up or down
// Returns 1 for up, -1 for down, 0 for possible garbage input
int upOrDown(int instruction)
{
  if (instruction == 40)
    return 1;
  else if (instruction == 41)
    return -1;
  else 
    return 0;
} // upOrDown function

// Function to check if Father Christmas got locked in the cellar 
int checkCellar(int floor)
{
  if (floor != -1)
    return 0;
  else
    return 1;
} // checkCellar function

// Main function to run the program 
int main(int argc, char* argv[])
{
  int floor = 0;
  int instructionIndex = 0;
  int instruction; 
  int isCellar = 0;
  FILE *inputFile;
  inputFile = fopen(argv[1], "r");

  // Check for bad inputs
  if (inputFile == NULL)
  {
    printf(stderr, "File not valid for reading you christmas toad.\n");
    exit(-1);
  } //if

  // While not at end of file
  while ((instruction = fgetc(inputFile)) != EOF && isCellar == 0)
  {
    instructionIndex ++;
    floor += upOrDown(instruction);
    isCellar = checkCellar(floor);
  } // while

  // Tidy up after yourself!
  fclose(inputFile);

  // Print the result to standard output
  if (isCellar == 1)
    printf("\nThe instruction that traps him in the basement is:  %i\n", 
           instructionIndex);
  else 
    printf("\nAfter %i instructions Crimbo never tickled the underbelly.\n", 
           instructionIndex);
} // main function
