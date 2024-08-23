// FILE:    Canada.java
// AUTHOR:  Daehwan Yeo 19448288
// PURPOSE: It contains the main and utilises two model classes
//          Location and Project.
// REFERENCE: None. It does rely on the lectures and my lab codings.
// COMMENTS: Easy read and understand in main, shows the flow of the Program
//           first read the file to get valid csv row, then Project array is made.
//           With the array, calculates and shows statistics to users. 
//
// Last Modified: 21st / 5 / 2023

import java.util.*;
import java.io.*;               // some fileIO stuff
import java.text.DecimalFormat; // for percetange format

public class Canada
{
  public static void main(String[] args)
    {
      int rowCount = readFile(); // read file to get valid row number

      if(rowCount == 0) // file do not have valid csv row 
      {
        System.out.println("ERROR: file has no valid csv row.");
      }
      else // file has some valid csv row, make project array
      {
        Project[] projectArr = new Project[rowCount]; // make Project array with compact size
        readFile(projectArr); // read file again to store data into the array
        userInterface(projectArr, rowCount); // play around with the array
      } // END IF
    } // END MAIN

  // METHOD: userInterface
  // IMPORT: projectArr(Project array), rowCount(int)
  // EXPORT: none
  // ASSERTION: interacts with users and loops the menu
  public static void userInterface(Project[] projectArr, int rowCount)
  {
    Scanner sc = new Scanner(System.in);
    boolean exit = false;
    boolean goMainMenu = false;
    String prompt1 = "Please make a choice from the Menu.";
    String prompt2 = "Please make a choice from the statistics.";

    printString("Welcome to the Investments in Indigenous community infrastructure Program.");
    printString("There are a total of " + rowCount + " projects throughout Canada.");

    int choice = 0;
    int statChoice = 0;
    while (!exit)
    {
      printMainMenu();
      choice = validateIntInput(prompt1, 1, 15); // min 1, max 15 
      switch (choice)
      { 
        case 1:
          calcAllCanada(projectArr, rowCount);
          break;

        case 2: case 3: case 4: case 5: case 6: case 7: case 8:
        case 9: case 10: case 11: case 12: case 13: case 14:
          printSubMenu();
          while(!goMainMenu)
          {
            statChoice = validateIntInput(prompt2, 1, 6); // min 1, max 6
            calcProvince(projectArr, rowCount, choice, statChoice);
            if(statChoice == 6)
            {
              goMainMenu = true; // sub menu 6 is return to main menu
            }
          } // END WHILE
          break;

        case 15:
          exit = true;
          break;
        // No default, input validated already 1 - 15
      } // END CASE 
      goMainMenu = false; // default is false for sub menu looping
    } // END WHILE

    printString("Program exited. Please save your logfile.txt");
    sc.close();
  } // END userInterface

  // METHOD: calcAllCanada
  // IMPORT: projectArr(Project array), rowCount(int)
  // EXPORT: none
  // ASSERTION: calculates statistics for all of Canada menu
  public static void calcAllCanada(Project[] projectArr, int rowCount)
  {
    DecimalFormat df = new DecimalFormat("#.##"); // to show the percentage, decimal format used
    String str1, str2, str3, str4, formatNum;
    double percentage;
    int ongoing = 0;
    int completed = 0;

    for(int i = 0; i < rowCount; i++)
    {
      if(projectArr[i].getStage().equals("Ongoing"))
      {
        ongoing++; // count the "Ongoing" stage of each project
      }
    } // END FOR
    completed = rowCount - ongoing; // No need to loop again, Total - ongoing = completed
    
    percentage = ((double) completed / rowCount) * 100; // division with float arithmetic
    formatNum = df.format(percentage);

    str1 = "The total number of projects in Canada: " + rowCount;
    printString(str1);

    str2 = "The total number of ongoing projects: " + ongoing;
    printString(str2);

    str3 = "The total number of completed projects: " + completed;
    printString(str3);

    str4 = "The percentage of completed projects: " + formatNum + "%";
    printString(str4);
  } // end calcAllCanada

  // METHOD: calcProvince
  // IMPORT: projectArr(Project array), rowCount(int), choice(int), statChoice(int)
  // EXPORT: none
  // ASSERTION: calculates statistics for province menu
  public static void calcProvince(Project[] projectArr, int rowCount, int choice, int statChoice)
  {
    DecimalFormat df = new DecimalFormat("#.##"); // to show the percentage, decimal format used
    String[] provArr = {"none", "none", "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland And Labrador", "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Northwest Territories", "Nunavut", "Yukon"};
    // province array, added two items at the beginning so choice equals directly to each province

    String str1, str2, str3, str4, str5, formatNum;
    double percentage;
    int numProj = 0; // number of projects for specific province 
    int ongoing = 0;
    int completed = 0;

    for(int i = 0; i < rowCount; i++)
    { // searching whole project array, if province matches,
      if(projectArr[i].getProvince().equals(provArr[choice]))
      { 
        numProj++; // count the number of projects for this province 
        if(projectArr[i].getStage().equals("Ongoing"))
        { // count ongoing projects
          ongoing++;
        } // END IF
      } // END IF
    } // END FOR
    completed = numProj - ongoing; // No need to loop again, Total - ongoing = completed

    switch (statChoice) // statistics switch case
    {
      case 1:
        str1 = "There are " + numProj + " projects in " + provArr[choice] + ".";
        printString(str1);
        break;
      case 2:
        percentage = ((double) numProj / rowCount) * 100;
        formatNum = df.format(percentage);
        str2 = "Percentage of " + provArr[choice] + " over all projects in Canada: " + formatNum + "%";
        printString(str2);
        break;
      case 3:
        percentage = ((double) ongoing / numProj) * 100;
        formatNum = df.format(percentage);
        str3 = "Total number of ongoing projects and percentage : " + ongoing + ", " + formatNum + "%";
        printString(str3);
        break;
      case 4:
        percentage = ((double) completed / numProj) * 100;
        formatNum = df.format(percentage);
        str4 = "Total number of completed projects and percentage : " + completed + ", " + formatNum + "%";
        printString(str4);
        break;  
      case 5:
        str1 = "There are " + numProj + " projects in " + provArr[choice] + ".";
        printString(str1);
        
        percentage = ((double) numProj / rowCount) * 100;
        formatNum = df.format(percentage);
        str2 = "Percentage of " + provArr[choice] + " over all projects in Canada: " + formatNum + "%";
        printString(str2);

        percentage = ((double) ongoing / numProj) * 100;
        formatNum = df.format(percentage);
        str3 = "Total number of ongoing projects and percentage : " + ongoing + ", " + formatNum + "%";
        printString(str3);

        percentage = ((double) completed / numProj) * 100;
        formatNum = df.format(percentage);
        str4 = "Total number of completed projects and percentage : " + completed + ", " + formatNum + "%";
        printString(str4);
        break;
      case 6:
        // do nothing, return to main menu
        break;
    } // END CASE
  } // end calcProvince

  // METHOD: printMainMenu
  // IMPORT: none
  // EXPORT: none
  // ASSERTION: none
  public static void printMainMenu()
  {
    System.out.println(">  1. All of Canada");
    System.out.println(">  2. Alberta");
    System.out.println(">  3. British Columbia");
    System.out.println(">  4. Manitoba");
    System.out.println(">  5. New Brunswick");
    System.out.println(">  6. Newfoundland and Labrador");
    System.out.println(">  7. Nova Scotia");
    System.out.println(">  8. Ontario");
    System.out.println(">  9. Prince Edward Island");
    System.out.println("> 10. Quebec");
    System.out.println("> 11. Saskatchewan");
    System.out.println("> 12. Northwest Territories");
    System.out.println("> 13. Nunavut");
    System.out.println("> 14. Yukon");
    System.out.println("> 15. Exit Program");
  } // end printMainMenu

  // METHOD: printSubMenu
  // IMPORT: none
  // EXPORT: none
  // ASSERTION: none
  public static void printSubMenu()
  {
    System.out.println(">  1. Number of projects");
    System.out.println(">  2. Percentage of all projects located in this province/territory");
    System.out.println(">  3. Total number and percentage of Ongoing projects");
    System.out.println(">  4. Total number and percentage of Completed projects");
    System.out.println(">  5. All of the above statistics");
    System.out.println(">  6. Return to main menu");
  } // end printSubMenu

  // METHOD: printString
  // IMPORT: message(String)
  // EXPORT: none
  // ASSERTION: print out message to the screen, and to the logfile.txt
  public static void printString(String message)
  {
    FileOutputStream fileStrm = null;
    PrintWriter pw;

    System.out.println(message); // print to the console
    
    try
    {
      fileStrm = new FileOutputStream("logfile.txt", true);
      pw = new PrintWriter(fileStrm);
      pw.println(message); // print to the log file
      pw.close();
    } // END TRY
    catch (IOException e)
    {
      System.out.println("ERROR: failed in writing to file. " + e.getMessage());
    } // END CATCH
  } // end printString

  // METHOD: readFile
  // IMPORT: projectArr(Project array)
  // EXPORT: none
  // ASSERTION: read file to store data into the Project array
  public static void readFile(Project[] projectArr) 
  {
    String fileName = "First_Nation_Infrastructure_Investment.csv";
    // Hardcoded file name for the assignment purpose

    FileInputStream fileStream = null;
    InputStreamReader isr;
    BufferedReader bufRdr;
    int lineNum;
    String line;
    boolean isValid = false;
    int rowCount = 0;

    try
    { 
      fileStream = new FileInputStream(fileName);
      isr = new InputStreamReader(fileStream);
      bufRdr = new BufferedReader(isr);
      lineNum = 1;
      line = bufRdr.readLine(); // First row is header
      line = bufRdr.readLine();

      while(line != null)
      {
        lineNum++;
        isValid = validateLine(line);
        if(isValid == true) // valid csv row, increase rowCount
        {
          processLine(projectArr, line, rowCount);
          rowCount++;
          line = bufRdr.readLine();
        } // END IF
        else // invalid csv row, ignore and continue to read
        {
          line = bufRdr.readLine();
        }
      } // END WHILE
      fileStream.close(); // end of file, close it
    } // END TRY
    catch(IOException e) // if there is an error during file processing
    {
      if(fileStream != null)
      {
        try
        {
          fileStream.close(); // close the file
        } // END TRY
        catch(IOException ex2) 
        { // nothing while closing the fileStream
        } // END CATCH
      } // END IF
      System.out.println("ERROR: " + e.getMessage());
    } // END CATCH
  } // END readFile

  // METHOD: processLine
  // IMPORT: projectArr(Project array), line(String), rowCount(int)
  // EXPORT: none
  // ASSERTION: process csv line, store each row data into Project array element
  public static void processLine(Project[] projectArr, String line, int rowCount)
  {
    String[] splitLine;
    splitLine = line.split(","); // delimiter ","
 
    String prov, benef, asset, name, stage, coSys;
    int bNum;
    double lati, longi;

    // the csv line is already validated, just need to be stored
    prov = splitLine[0];
    benef = splitLine[1];
    bNum = Integer.parseInt(splitLine[2]);
    asset = splitLine[3];
    name = splitLine[4];
    stage = splitLine[6];
    lati = Double.parseDouble(splitLine[7]);
    longi = Double.parseDouble(splitLine[8]);
    coSys = splitLine[9];

    // use CONSTRUCTOR with Parameters
    projectArr[rowCount] = new Project(prov, benef, bNum, asset, name, stage, lati, longi, coSys);
  } // END processLine

  // METHOD: readFile
  // IMPORT: none
  // EXPORT: rowCount(int)
  // ASSERTION: reads csv file and returns valid csv row count number 
  public static int readFile() 
  {
    String fileName = "First_Nation_Infrastructure_Investment.csv";
    // Hardcoded file name for the assignment purpose

    FileInputStream fileStream = null;
    InputStreamReader isr;
    BufferedReader bufRdr;
    int lineNum;
    String line;
    boolean isValid = false;
    int rowCount = 0;

    try
    { 
      fileStream = new FileInputStream(fileName);
      isr = new InputStreamReader(fileStream);
      bufRdr = new BufferedReader(isr);
      lineNum = 1;
      line = bufRdr.readLine(); // First row is header
      line = bufRdr.readLine();

      while(line != null)
      {
        lineNum++;
        isValid = validateLine(line);
        if(isValid == true) // valid csv row, increase rowCount
        {
          rowCount++;
          line = bufRdr.readLine();
        }
        else // invalid csv row, do not count
        {
          System.out.println("ERROR: invalid row data, discarding line " + lineNum + " in csv file.");
          line = bufRdr.readLine();
        } // END IF
      } // END WHILE
      fileStream.close(); // end of file, close it
    } // END TRY
    catch (FileNotFoundException e)
    {
      System.out.println("ERROR: could not find the csv file.");
    }
    catch(IOException e) // if there is an error during file processing
    {
      if(fileStream != null)
      {
        try
        {
          fileStream.close(); // close the file
        } // END TRY
        catch(IOException ex2) 
        { // nothing while closing the fileStream
        } // END CATCH
      } // END IF
      System.out.println("ERROR: " + e.getMessage());
    } // END CATCH

    return rowCount; // this will be the project array size
  } // END readFile

  // METHOD: validateLine
  // IMPORT: line(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: returns true if the csv line is valid
  public static boolean validateLine(String line)
  {
    String[] splitLine;
    splitLine = line.split(","); // delimiter ","
    int lineLength = splitLine.length;
    boolean isValid = false;

    if(lineLength == 10) // csv row has 10 items in order.
    { // province
      if(validateProvince(splitLine[0]) == true)
      { // beneficiaryNum
        if(validateBeneficiaryNum(splitLine[2]) == true)
        { // stage
          if(validateStage(splitLine[6]) == true)
          { // latitude
            if(validateLatitude(splitLine[7]) == true)
            { // longitude
              if(validateLongitude(splitLine[8]) == true)
              { 
                isValid = true; // csv row is valid
              }
            }
          } 
        }  
      }
    } // END IF
    return isValid;
  } // END validateLine

  // METHOD: validateProvince
  // IMPORT: token(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: isValid is true when token is valid province
  public static boolean validateProvince(String token) 
  {
    String[] provArr = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland And Labrador", "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Northwest Territories", "Nunavut", "Yukon"};
    int provNum = provArr.length; // 13 at the moment as per the menu
    boolean isValid = false;
    for(int i = 0; i < provNum; i++)
    {
      if(token.equals(provArr[i]))
      {
        isValid = true;
      } // END IF
    } // END FOR
    return isValid;
  } // END validateProvince

  // METHOD: validateBeneficiaryNum
  // IMPORT: token(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: isValid is true when token is valid beneficiaryNum
  public static boolean validateBeneficiaryNum(String token)
  {
    boolean isValid = false;
    try
    { // token has to be parsed, beneficiaryNum is an integer.
      int parsedInt = Integer.parseInt(token);
      if((parsedInt >= 1) && (parsedInt <= 9999))
      {
        isValid = true;
      } // END IF
    } // END TRY
    catch (NumberFormatException e)
    {
      System.out.println("ERROR: beneficiaryNum has to be an integer.");
    } // END CATCH
    return isValid;
  } // END validateBeneficiaryNum

  // METHOD: validateStage
  // IMPORT: token(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: isValid is true when token is valid stage
  public static boolean validateStage(String token)
  {
    String[] stageArr = {"Completed", "Ongoing"};
    int stageNum = stageArr.length;
    boolean isValid = false;
    for(int i = 0; i < stageNum; i++)
    {
      if(token.equals(stageArr[i]))
      {
        isValid = true;
      }
    } // END FOR
    return isValid;
  } // END validateStage

  // METHOD: validateLatitude
  // IMPORT: token(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: isValid is true when token is valid latitude
  public static boolean validateLatitude(String token)
  {
    boolean isValid = false;
    try
    { // token has to be parsed, latitude is a double.
      double parsedDouble = Double.parseDouble(token);
      if((parsedDouble >= 42.0) && (parsedDouble <= 83.0))
      {
        isValid = true;
      } // END IF
    } // END TRY
    catch (NumberFormatException e)
    {
      System.out.println("ERROR: latitude has to be a rational number.");
    } // END CATCH
    return isValid;
  } // END validateLatitude

  // METHOD: validateLongitude
  // IMPORT: token(String)
  // EXPORT: isValid(boolean)
  // ASSERTION: isValid is true when token is valid longitude
  public static boolean validateLongitude(String token)
  {
    boolean isValid = false;
    try
    { // token has to be parsed, longitude is a double.
      double parsedDouble = Double.parseDouble(token);
      if((parsedDouble >= -141.0) && (parsedDouble <= -53.0))
      {
        isValid = true;
      } // END IF
    } // END TRY
    catch (NumberFormatException e)
    {
      System.out.println("ERROR: longitude has to be a rational number.");
    } // END CATCH
    return isValid;
  } // END validateLongitude

  // METHOD: validateIntInput
  // IMPORT: prompt(String), min(int), max(int)
  // EXPORT: value(int)
  // ASSERTION: min <= value <= max and value is integer 
  public static int validateIntInput(String prompt, int min, int max)
  {
    int value = min - 1;
    Scanner sc = new Scanner(System.in);

    do
    {
      try
      {
        System.out.println(prompt);
        value = sc.nextInt();
        if((value < min) || (value > max))
        {
          throw new IllegalArgumentException("ERROR: select a number between " + min + " and " + max + ".");
        }
      } // END TRY
      catch (IllegalArgumentException e) // catch when outside of integer range
      {
        System.out.println(e.getMessage());
      }
      catch (InputMismatchException e) // catch when it is not int type
      {
        System.out.println("ERROR: enter a whole number only.");
        sc.next(); // discard the invalid token, avoid infinite looping
      } // END CATCH
    } while ((value < min)||(value > max));
    // Assertion: min <= value <= max
    return value;
  } // END validateIntInput

} // END CLASS