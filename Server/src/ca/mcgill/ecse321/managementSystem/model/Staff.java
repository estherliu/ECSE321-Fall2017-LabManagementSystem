/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.managementSystem.model;
import java.util.*;

// line 9 "../../../../../main.ump"
public class Staff
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private String name;

  //Autounique Attributes
  private int id;

  //Staff State Machines
  public enum Role { researcher, researchAssistant }
  private Role role;

  //Staff Associations
  private List<Progress> progresses;
  private Lab lab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aName, Lab aLab)
  {
    name = aName;
    id = nextId++;
    progresses = new ArrayList<Progress>();
    boolean didAddLab = setLab(aLab);
    if (!didAddLab)
    {
      throw new RuntimeException("Unable to create staff due to lab");
    }
    setRole(Role.researcher);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getId()
  {
    return id;
  }

  public String getRoleFullName()
  {
    String answer = role.toString();
    return answer;
  }

  public Role getRole()
  {
    return role;
  }

  public boolean setRole(Role aRole)
  {
    role = aRole;
    return true;
  }

  public Progress getProgress(int index)
  {
    Progress aProgress = progresses.get(index);
    return aProgress;
  }

  public List<Progress> getProgresses()
  {
    List<Progress> newProgresses = Collections.unmodifiableList(progresses);
    return newProgresses;
  }

  public int numberOfProgresses()
  {
    int number = progresses.size();
    return number;
  }

  public boolean hasProgresses()
  {
    boolean has = progresses.size() > 0;
    return has;
  }

  public int indexOfProgress(Progress aProgress)
  {
    int index = progresses.indexOf(aProgress);
    return index;
  }

  public Lab getLab()
  {
    return lab;
  }

  public static int minimumNumberOfProgresses()
  {
    return 0;
  }

  public Progress addProgress(String aDetail)
  {
    return new Progress(aDetail, this);
  }

  public boolean addProgress(Progress aProgress)
  {
    boolean wasAdded = false;
    if (progresses.contains(aProgress)) { return false; }
    Staff existingStaff = aProgress.getStaff();
    boolean isNewStaff = existingStaff != null && !this.equals(existingStaff);
    if (isNewStaff)
    {
      aProgress.setStaff(this);
    }
    else
    {
      progresses.add(aProgress);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProgress(Progress aProgress)
  {
    boolean wasRemoved = false;
    //Unable to remove aProgress, as it must always have a staff
    if (!this.equals(aProgress.getStaff()))
    {
      progresses.remove(aProgress);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addProgressAt(Progress aProgress, int index)
  {  
    boolean wasAdded = false;
    if(addProgress(aProgress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgresses()) { index = numberOfProgresses() - 1; }
      progresses.remove(aProgress);
      progresses.add(index, aProgress);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProgressAt(Progress aProgress, int index)
  {
    boolean wasAdded = false;
    if(progresses.contains(aProgress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgresses()) { index = numberOfProgresses() - 1; }
      progresses.remove(aProgress);
      progresses.add(index, aProgress);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProgressAt(aProgress, index);
    }
    return wasAdded;
  }

  public boolean setLab(Lab aLab)
  {
    boolean wasSet = false;
    if (aLab == null)
    {
      return wasSet;
    }

    Lab existingLab = lab;
    lab = aLab;
    if (existingLab != null && !existingLab.equals(aLab))
    {
      existingLab.removeStaff(this);
    }
    lab.addStaff(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (progresses.size() > 0)
    {
      Progress aProgress = progresses.get(progresses.size() - 1);
      aProgress.delete();
      progresses.remove(aProgress);
    }
    
    Lab placeholderLab = lab;
    this.lab = null;
    placeholderLab.removeStaff(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lab = "+(getLab()!=null?Integer.toHexString(System.identityHashCode(getLab())):"null");
  }
}