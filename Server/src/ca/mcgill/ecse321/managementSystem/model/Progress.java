/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.managementSystem.model;

// line 5 "../../../../../main.ump"
public class Progress
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Progress Attributes
  private String detail;

  //Progress Associations
  private Staff staff;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Progress(String aDetail, Staff aStaff)
  {
    detail = aDetail;
    boolean didAddStaff = setStaff(aStaff);
    if (!didAddStaff)
    {
      throw new RuntimeException("Unable to create progress due to staff");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDetail(String aDetail)
  {
    boolean wasSet = false;
    detail = aDetail;
    wasSet = true;
    return wasSet;
  }

  public String getDetail()
  {
    return detail;
  }

  public Staff getStaff()
  {
    return staff;
  }

  public boolean setStaff(Staff aStaff)
  {
    boolean wasSet = false;
    if (aStaff == null)
    {
      return wasSet;
    }

    Staff existingStaff = staff;
    staff = aStaff;
    if (existingStaff != null && !existingStaff.equals(aStaff))
    {
      existingStaff.removeProgress(this);
    }
    staff.addProgress(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Staff placeholderStaff = staff;
    this.staff = null;
    placeholderStaff.removeProgress(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "detail" + ":" + getDetail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staff = "+(getStaff()!=null?Integer.toHexString(System.identityHashCode(getStaff())):"null");
  }
}