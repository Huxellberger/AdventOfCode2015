# Program to catch the bad eggs out
# Written by Jake Huxell 2015 for Advent Of Code
# *TRUE MEANS PASS AND NICE, FALSE MEANS VERY NAUGHTY*  

# Make tuples that represent the requirements of the rules
VOWELS = ("a", "e", "i", "o", "u") # 3 vowels at least, Rule 1
# Rule 2 is successive letters, no need to make a tuple for that
NAUGHTY_STRINGS = ("ab", "cd", "pq", "xy") # These strings are the pits

# Function to check if word has at least 3 vowels 
def checkVowels(inputString):
  vowelCount = 0;
  for c in VOWELS:
    vowelCount += inputString.count(c)
  if vowelCount >= 3:
    return True
  else:
    print inputString
    return False

# Function to make sure inputString has succesive letters
def checkSuccession(inputString):
  for index in range(0, len(inputString ) - 1):
    if inputString[index] == inputString[index+1]:
      return True
  return False

# Function to check for the ultimate badmen, those Naughty strings
def checkNaughtiness(inputString):
  for baddy in NAUGHTY_STRINGS:
    if baddy in inputString:
      return False
  return True

# Function to check all 3 rules and return if string is naughty or nice
def isNice(inputString):
  if (checkNaughtiness(inputString) and checkSuccession(inputString) and 
      checkVowels(inputString)):
    return True
  return False

# Main function to read in the file and count number of naughty and nice
def main():
  niceCount = 0 # Hopefully this will go up! What a world!
  file = open("tst.txt", "r")
  for line in file:
    if (isNice(line)):
      niceCount += 1
  print "This year ", niceCount, " children have behaved. Pitiful...\n"

main()
  
    
    
  


