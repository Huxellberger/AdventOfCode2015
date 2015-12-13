# Program to catch the bad eggs out
# Written by Jake Huxell 2015 for Advent Of Code
# *TRUE MEANS PASS AND NICE, FALSE MEANS VERY NAUGHTY*  

# Rule 1 is pairs appear with no overlaps eg "aabaa" not "aaa". Letters
# DO NOT HAVE TO MATCH YOU CRETIN
# Rule 2 is exactly one letter that repeats with a letter between it
# Function to check if word has at least 3 vowels 

# Going to use regular expressions for this one, had trouble without them
# Honestly had trouble with them but I'll just have to keep practicing
import re 

OVERLAPS = r'(..).*\1' # Pair of characters that don't share a common letter
                       # The (..) signifies any 2 sequence. . means it's alright
                       # unless (*) it's in group 1 (\1) 

SUCCESSION = r'(.).\1' # Repeating letter with exactly one seperator
                       # The . signifies 1 letter, any. The .\1 is the same letter
                       # of group 1, with any in between at .

# Function to check no double overlaps
def checkOverlaps(inputString):
  return re.search(OVERLAPS, inputString)
        
# Function to make sure inputString has only 1 alternating letter 
def checkSuccession(inputString):
  return re.search(SUCCESSION, inputString)

# Function to check all 3 rules and return if string is naughty or nice
def isNice(inputString):
  return (checkSuccession(inputString) and checkOverlaps(inputString))

# Main function to read in the file and count number of naughty and nice
def main():
  niceCount = 0 # Hopefully this will go up! What a world!
  file = open("tst.txt", "r")
  for line in file:
    if (isNice(line)):
      niceCount += 1
  print "This year ", niceCount, " children have behaved. Pitiful...\n"

main()
  
    
    
  


