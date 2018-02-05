package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestStaff.class,TestSupply.class,TestEquipment.class ,TestSupply.class, TestRegisterAndLogin.class})
public class AllTests {

}
