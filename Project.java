// FILE:    Project.java
// AUTHOR:  Daehwan Yeo 19448288
// PURPOSE: It is one of the model classes.
//          Aggregation relationship with Location class.
// REFERENCE: None.
// COMMENTS: String values such as province and stage can 
//           be validated, but the rest are too broad, so 
//           I just check for nulls.
//
// Last Modified: 21st / 5 / 2023

import java.util.*;

public class Project
{
  // CLASS FIELDS:
  private String province;
  private String beneficiary;
  private int beneficiaryNum;
  private String assetClass;
  private String name;
  private String stage;
  private Location location;

  // CONSTRUCTOR with Parameters
  // IMPORT: pProv(String), pBenef(String), pBenefNum(int),
  //         pAsset(String), pName(String), pStage(String),
  //         pLat(double), pLong(double), pCoordSys(String)
  // EXPORT: none Constructors never export
  // ASSERTION: Created object with imported values
  public Project(String pProv, String pBenef, int pBenefNum, String pAsset, String pName, String pStage, double pLat, double pLong, String pCoordSys)
  {
    province = pProv;
    beneficiary = pBenef;
    beneficiaryNum = pBenefNum;
    assetClass = pAsset;
    name = pName;
    stage = pStage;
    location = new Location(pLat, pLong, pCoordSys);
  }

  // COPY CONSTRUCTOR
  // IMPORT: pProject (Project)
  // EXPORT: none Constructors never export
  // ASSERTION: Created a Copy of the IMPORTed object
  public Project(Project pProject)
  {
    province = pProject.getProvince();
    beneficiary = pProject.getBeneficiary();
    beneficiaryNum = pProject.getBeneficiaryNum();
    assetClass = pProject.getAssetClass();
    name = pProject.getName();
    stage = pProject.getStage();
    location = new Location(pProject.location.getLatitude(), pProject.location.getLongitude(), pProject.location.getCoordinateSystem());
  }

  // DEFAULT CONSTRUCTOR
  // IMPORT: none Default constructors do not import
  // EXPORT: none Constructors never export
  // ASSERTION: Creates an object with the default values
  public Project()
  {
    province = "Quebec";
    beneficiary = "First Nations of Quebec and Labrador Sustainable Development Institute";
    beneficiaryNum = 9999;
    assetClass = "Energy sustainability and connectivity";
    name = "Comprehensive Community Planning";
    stage = "Completed";
    location = new Location();
  }

  // MUTATOR: setProvince
  // IMPORT: pProv(String)
  // EXPORT: none
  // ASSERTION: State of province is updated to pProv value
  public void setProvince(String pProv)
  {
    province = validateProvince(pProv);
  }

  // MUTATOR: setBeneficiary
  // IMPORT: pBenef(String)
  // EXPORT: none
  // ASSERTION: State of beneficiary is updated to pBenef value
  public void setBeneficiary(String pBenef)
  {
    String info = "community";
    beneficiary = validateString(pBenef, info);
  }

  // MUTATOR: setBeneficiaryNum
  // IMPORT: pBenefNum(int)
  // EXPORT: none
  // ASSERTION: State of beneficiaryNum is updated to pBenefNum value
  public void setBeneficiaryNum(int pBenefNum)
  {
    beneficiaryNum = validateBeneficiaryNum(pBenefNum);
  }

  // MUTATOR: setAssetClass
  // IMPORT: pAsset(String)
  // EXPORT: none
  // ASSERTION: State of assetClass is updated to pAsset value
  public void setAssetClass(String pAsset)
  {
    String info = "infrastructure category";
    assetClass = validateString(pAsset, info);
  }

  // MUTATOR: setName
  // IMPORT: pName(String)
  // EXPORT: none
  // ASSERTION: State of name is updated to pName value
  public void setName(String pName)
  {
    String info = "project name";
    name = validateString(pName, info);
  }

  // MUTATOR: setStage
  // IMPORT: pStage(String)
  // EXPORT: none
  // ASSERTION: State of stage is updated to pStage value
  public void setStage(String pStage)
  {
    stage = validateStage(pStage);
  }

  // MUTATOR: setLocation
  // IMPORT: pLocation(Location object)
  // EXPORT: none
  // ASSERTION: State of location is updated to pLocation value
  public void setLocation(Location pLocation)
  {
    location = pLocation;
  }

  // ACCESSOR: getProvince
  // IMPORT: none
  // EXPORT: province(String)
  // ASSERTION: returns the province
  public String getProvince()
  {
    return province;
  }

  // ACCESSOR: getBeneficiary
  // IMPORT: none
  // EXPORT: beneficiary(String)
  // ASSERTION: returns the beneficiary
  public String getBeneficiary()
  {
    return beneficiary;
  }

  // ACCESSOR: getBeneficiaryNum
  // IMPORT: none
  // EXPORT: beneficiaryNum(int)
  // ASSERTION: returns the beneficiaryNum
  public int getBeneficiaryNum()
  {
    return beneficiaryNum;
  }

  // ACCESSOR: getAssetClass
  // IMPORT: none
  // EXPORT: assetClass(String)
  // ASSERTION: returns the assetClass
  public String getAssetClass()
  {
    return assetClass;
  }

  // ACCESSOR: getName
  // IMPORT: none
  // EXPORT: name(String)
  // ASSERTION: returns the name
  public String getName()
  {
    return name;
  }

  // ACCESSOR: getStage
  // IMPORT: none
  // EXPORT: stage(String)
  // ASSERTION: returns the stage
  public String getStage()
  {
    return stage;
  }

  // ACCESSOR: getLocation
  // IMPORT: none
  // EXPORT: location(Location object)
  // ASSERTION: returns the location
  public Location getLocation()
  {
    return location;
  }

  /* METHOD: toString
   * IMPORT: none
   * EXPORT: projectString(String)
   * ASSERTION: returns a String representation of the object */
  public String toString()
  {
    String projectString;
    projectString = "Province/Territory: " + province + " Community: " + beneficiary + " Community Number: " + beneficiaryNum + " Infrastructure Category: " + assetClass + " Project Name: " + name + " Project Status: " + stage + " " + location.toString();
    // There is no local variable or method parameter, so location.toString() would work the same as this.location.toString()
    return projectString;
  }

  /* METHOD: equals
   * IMPORT: inObject(Object)
   * EXPORT: isEqual(boolean)
   * ASSERTION: returns true if the two objects are equivalent */
  public boolean equals(Object inObject)
  {
    boolean isEqual = false; 
    Project inProject = null;
    if(inObject instanceof Project)
    {
      inProject = (Project)inObject;
      if(province.equals(inProject.getProvince()))
        if(beneficiary.equals(inProject.getBeneficiary()))
          if(beneficiaryNum == inProject.getBeneficiaryNum())
            if(assetClass.equals(inProject.getAssetClass()))
              if(name.equals(inProject.getName()))
                if(stage.equals(inProject.getStage()))
                  if(location.equals(inProject.getLocation()))
                    isEqual = true;
    }
    return isEqual;
  }

  // METHOD: validateProvince
  // IMPORT: pProv(String)
  // EXPORT: province(String)
  // ASSERTION: province must be one of these: Alberta, British Columbia, 
  //            Manitoba, New Brunswick, Newfoundland And Labrador, Nova Scotia, 
  //            Ontario, Prince Edward Island, Quebec, Saskatchewan, Northwest Territories, Nunavut, Yukon
  public String validateProvince(String pProv)
  {
    String province = "none";
    String[] provArr = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland And Labrador", "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Northwest Territories", "Nunavut", "Yukon"};
    int provNum = provArr.length; // 13 at the moment as per the menu
    boolean isValid = false;
    try
    {
        if(pProv == null)
        {
            throw new NullPointerException("ERROR: province is empty.");
        }

        for(int i = 0; i < provNum; i++)
        {
            if(pProv.equals(provArr[i]))
            {
                isValid = true;
            }
        } // END FOR
    
        if(isValid == false)
        {
          throw new IllegalArgumentException("ERROR: province/territory does not belong to Canada.");
        }
        // pProv is valid, it is located in Canada
        province = pProv; 
    } // END TRY
    catch (IllegalArgumentException e)
    {
        System.out.println(e.getMessage());
    } 
    catch (NullPointerException e)
    {
        System.out.println(e.getMessage());
    } // END CATCH
    return province;
  }

  // METHOD: validateBeneficiaryNum
  // IMPORT: pBenefNum(int)
  // EXPORT: beneficiaryNum(int)
  // ASSERTION: beneficiaryNum sits 1 - 9999 range
  public int validateBeneficiaryNum(int pBenefNum)
  {
    int beneficiaryNum = 0;
    try
    {
      if((pBenefNum < 1) || (pBenefNum > 9999))
      {
        throw new IllegalArgumentException("ERROR: beneficiary number is invalid.");
      } // END IF
      beneficiaryNum = pBenefNum;
    } // END TRY
    catch (IllegalArgumentException e)
    {
      System.out.println(e.getMessage());
    } // END CATCH
    return beneficiaryNum;
  }

  // METHOD: validateStage
  // IMPORT: pStage(String)
  // EXPORT: stage(String)
  // ASSERTION: stage is "Completed" or "Ongoing" 
  public String validateStage(String pStage)
  {
    String stage = "none";
    String[] stageArr = {"Completed", "Ongoing"};
    int stageNum = stageArr.length;
    boolean isValid = false;
    try
    {
        if(pStage == null)
        {
            throw new NullPointerException("ERROR: stage is empty.");
        }

        for(int i = 0; i < stageNum; i++)
        {
            if(pStage.equals(stageArr[i]))
            {
                isValid = true;
            }
        } // END FOR
    
        if(isValid == false)
        {
          throw new IllegalArgumentException("ERROR: stage must be 'Completed' or 'Ongoing'.");
        }
        // pProv is valid, it is located in Canada
        stage = pStage; 
    } // END TRY
    catch (IllegalArgumentException e)
    {
        System.out.println(e.getMessage());
    } 
    catch (NullPointerException e)
    {
        System.out.println(e.getMessage());
    } // END CATCH
    return stage;
  }

  // METHOD: validateString
  // IMPORT: pString(String), info(String)
  // EXPORT: str(String)
  // ASSERTION: str is not null, info is extra information for error message
  public String validateString(String pString, String info)
  {
    String str = "none";
    try
    {
      if(pString == null)
      {
        throw new IllegalArgumentException("ERROR: " + info + " is empty.");
      } // END IF
      str = pString;
    } // END TRY
    catch (IllegalArgumentException e)
    {
      System.out.println(e.getMessage());
    } // END CATCH
    return str;
  }

} // END CLASS