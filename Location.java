// FILE:    Location.java
// AUTHOR:  Daehwan Yeo 19448288
// PURPOSE: It is one of the model classes.
//          Aggregation relationship with Project class.
// REFERENCE: None.
// COMMENTS: According to the decimal degrees system, the fifth decimal place is worth up to 1.1 m.
//           Therefore, Math.abs( ) < 0.00001 is used to compare latitudes and longitudes.
//           For the coordinate system of Canada, latitudes range from about 42 - 83N, and
//           longitudes range from about 53 - 141W. These values are used to validating methods.
// Last Modified: 21st / 5 / 2023

import java.util.*;

public class Location
{
  // CLASS FIELDS:
  private double latitude;
  private double longitude;
  private String coordinateSystem;

  // CONSTRUCTOR with Parameters
  // IMPORT: pLat(double), pLong(double), pCoordSys(String)
  // EXPORT: none Constructors never export
  // ASSERTION: Created object with imported values
  public Location(double pLat, double pLong, String pCoordSys)
  {
    latitude = pLat;
    longitude = pLong;
    coordinateSystem = pCoordSys;
  }

  // COPY CONSTRUCTOR
  // IMPORT: pLocation(Location)
  // EXPORT: none Constructors never export
  // ASSERTION: Created a Copy of the IMPORTed object
  public Location(Location pLocation)
  {
    latitude = pLocation.getLatitude();
    longitude = pLocation.getLongitude();
    coordinateSystem = pLocation.getCoordinateSystem();
  }

  // DEFAULT CONSTRUCTOR
  // IMPORT: none Default constructors do not import
  // EXPORT: none Constructors never export
  // ASSERTION: Creates an object with the default values
  public Location()
  {
    latitude = 46.81228;
    longitude = -71.21454;
    coordinateSystem = "GCS_North_American_1983_CSRS";
  }

  // MUTATOR: setLatitude
  // IMPORT: pLat(double)
  // EXPORT: none
  // ASSERTION: State of latitude is updated to pLat value
  public void setLatitude(double pLat)
  {
    latitude = validateLatitude(pLat);
  }

  // MUTATOR: setLongitude
  // IMPORT: pLong(double)
  // EXPORT: none
  // ASSERTION: State of longitude is updated to pLong value
  public void setLongitude(double pLong)
  {
    longitude = validateLongitude(pLong);
  }

  // MUTATOR: setCoordinateSystem
  // IMPORT: pCoordSys(String)
  // EXPORT: none
  // ASSERTION: State of coordinateSystem is updated to pCoordSys value
  public void setCoordinateSystem(String pCoordSys)
  {
    coordinateSystem = pCoordSys;
  }

  // ACCESSOR: getLatitude
  // IMPORT: none
  // EXPORT: latitude(double)
  // ASSERTION: returns the latitude
  public double getLatitude()
  {
    return latitude;
  }

  // ACCESSOR: getLongitude
  // IMPORT: none
  // EXPORT: longitude(double)
  public double getLongitude()
  {
    return longitude;
  }

  // ACCESSOR: getCoordinateSystem
  // IMPORT: none
  // EXPORT: coordinateSystem(String)
  // ASSERTION: returns the coordinateSystem
  public String getCoordinateSystem()
  {
    return coordinateSystem;
  }

  /* METHOD: toString
   * IMPORT: none
   * EXPORT: locationString(String)
   * ASSERTION: returns a String representation of the object */
  public String toString()
  {
    String locationString;
    locationString = "Latitude: " + latitude + " Longitude: " + longitude + " Coordinate System: " + coordinateSystem;
    return locationString;
  }

  /* METHOD: equals
   * IMPORT: inObject(Object)
   * EXPORT: isEqual(boolean)
   * ASSERTION: returns true if the two objects are equivalent */
  public boolean equals(Object inObject)
  {
    boolean isEqual = false; 
    Location inLocation = null;
    if(inObject instanceof Location)
    {
      inLocation = (Location)inObject;
      if(Math.abs(latitude - inLocation.getLatitude()) < 0.00001)
        if(Math.abs(longitude - inLocation.getLongitude()) < 0.00001)
          if(coordinateSystem.equals(inLocation.getCoordinateSystem()))
            isEqual = true;
    }
    return isEqual;
  }

  /* METHOD: validateLatitude
   * IMPORT: pLat(double)
   * EXPORT: latitue(double)
   * ASSERTION: latitude sits 42 - 83N range */
  public double validateLatitude(double pLat)
  {
    double latitude = 42.0;
    try
    {
      if((pLat < 42.0) || (pLat > 83.0))
      {
        throw new IllegalArgumentException("ERROR: latitude is outside of Canada.");
      } // END IF 
      latitude = pLat;
    } // END TRY
    catch (IllegalArgumentException e)
    {
      System.out.println(e.getMessage());
    } // END CATCH
    return latitude;
  }

  /* METHOD: validateLongitude
   * IMPORT: pLong(double)
   * EXPORT: longitude(double)
   * ASSERTION: longitude sits 53 - 141W range */
  public double validateLongitude(double pLong)
  {
    double longitude = -53.0; // W is negative in decimal degrees
    try
    {
      if((pLong < -141.0) || (pLong > -53.0))
      {
        throw new IllegalArgumentException("ERROR: longitude is outside of Canada.");
      } // END IF 
      longitude = pLong;
    } // END TRY
    catch (IllegalArgumentException e)
    {
      System.out.println(e.getMessage());
    } // END CATCH
    return longitude;
  }

  /* METHOD: validateCoordinateSystem
   * IMPORT: pCoordSys(String)
   * EXPORT: coordinateSystem(String)
   * ASSERTION: coordinateSystem is not null */
  public double validateCoordinateSystem(String pCoordSys)
  {
    String coordinateSystem = "GCS_North_American_1983_CSRS";
    try
    {
      if(pCoordSys == null)
      {
        throw new NullPointerException("ERROR: coordinate system is empty.");
      } // END IF 
      coordinateSystem = pCoordSys;
    } // END TRY
    catch (NullPointerException e)
    {
      System.out.println(e.getMessage());
    } // END CATCH
    return longitude;
  }

} // END CLASS 