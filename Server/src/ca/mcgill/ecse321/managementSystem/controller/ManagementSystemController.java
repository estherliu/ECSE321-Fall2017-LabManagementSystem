package ca.mcgill.ecse321.managementSystem.controller;

import ca.mcgill.ecse321.managementSystem.model.Staff;
import ca.mcgill.ecse321.managementSystem.model.Staff.Role;
import tcpserver.sockethandler;
import ca.mcgill.ecse321.managementSystem.model.Equipment;
import ca.mcgill.ecse321.managementSystem.model.Expense;
import ca.mcgill.ecse321.managementSystem.model.Lab;
import ca.mcgill.ecse321.managementSystem.model.Progress;
import ca.mcgill.ecse321.managementSystem.model.Supply;
import ca.mcgill.ecse321.managementSystem.model.URLMS;
import ca.mcgill.ecse321.managementSystem.persistence.PersistenceXStream;

import java.util.List;

public class ManagementSystemController extends Thread {
	private Lab currentLab;

	// constructor
	public ManagementSystemController() {
	}

	//translate message to command
	public String exec(String input, sockethandler origin) {

		String[] cmd = input.split("_");
		String out = "";

		try {
			switch (cmd[0]) {
			case "addequipments":
				addEquipments(cmd[1], Integer.parseInt(cmd[2]));
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "addexpense":
				addExpense(cmd[1], Double.parseDouble(cmd[2]));
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "addsupplies":
				addSupplies(cmd[1], Integer.parseInt(cmd[2]));
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "addstaff":
				addStaff(cmd[1], cmd[2]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "addprogress":
				addProgress(cmd[1], cmd[2]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "checkequipment":
				
				for (Equipment var : checkEquipments()) {
					out += var.getName() + "_" + var.getQuantity() + "_";
				}
				break;
			case "checkexpenses":
				for (Expense var : checkExpenses()) {
					out += var.getReason() + "_" + var.getAmountPaid() + "_";
				}
				break;
			case "checkstaffs":
				for (Staff var : checkStaffs()) {
					out += var.getName() + "_" + var.getRoleFullName() + "_";
				}
				break;
			case "checksupplies":
				for (Supply var : checkSupplies()) {
					out += var.getName() + "_" + var.getQuantity() + "_";
				}
				break;
			case "checkprogress":
				for (Progress progress : checkProgress(cmd[1])) {
					out += progress.getDetail() + "_";
				}
				break;
			case "getbalance":
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return Double.toString(getBalance());
			case "login":
				Login(cmd[1], cmd[2]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "logout":
				Logout();
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "register":
				register(cmd[1], cmd[2]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "unregister":
				unregister(cmd[1]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "removestaff":
				removeStaff(cmd[1]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "removesupply":
				removeSupply(cmd[1]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			case "removeequipment":
				removeEquipement(cmd[1]);
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "true";
			default:
				PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "false";
			}
		} catch (NumberFormatException e) {
			PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "the value must be a valid number";
		} catch (Exception e) {
			PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return "false_"+e.getMessage().toString();
		}
		PersistenceXStream.saveToXMLwithXStream(URLMS.getInstance()); return out;
	}

	public void unregister(String labname) throws IllegalArgumentException {
		URLMS systemProgram = URLMS.getInstance();
		List<Lab> lablist = systemProgram.getLab();
		for (Lab lab : lablist) {
			if (lab.getUsername().equals(labname)) {
				try {
					lab.delete();
					return;
				} catch (Exception e) {
					throw new IllegalArgumentException("cant remove lab due to: " + e.getMessage());
				}

			}
		}
	}

	public void register(String username, String password) throws IllegalArgumentException {
		if (username == null || password == null) {
			throw new IllegalArgumentException("null pointer");
		}
		URLMS systemProgram = URLMS.getInstance();
		// check if username is valid
		if (isEmpty(username)) {
			throw new IllegalArgumentException("The username is empty!");
		}
		// check if username is duplicated
		List<Lab> lablist = systemProgram.getLab();
		for (Lab lab : lablist) {
			if ((lab.getUsername()).equals(username)) {
				throw new IllegalArgumentException("The lab username is duplicated!");
			}
		}

		// check if password is valid
		if (isEmpty(password)) {
			throw new IllegalArgumentException("The password is empty!");
		}

		// create a new lab account
		Lab newLab = new Lab(username, 0, password, systemProgram);
		systemProgram.addLab(newLab);
		
	}

	public void Login(String username, String password) {
		URLMS systemProgram = URLMS.getInstance();

		// check if username is valid
		if (isEmpty(username)) {
			throw new IllegalArgumentException("Please enter an Username");
		}

		// check if username exists
		List<Lab> lablist = systemProgram.getLab();
		for (Lab lab : lablist) {
			if ((lab.getUsername()).equals(username)) {
				// check the password
				if ((lab.getPassword()).equals(password)) {
					currentLab = lab;
					return;
				} else
					throw new IllegalArgumentException("Wrong password");
			}
		}
		
		throw new IllegalArgumentException("This lab doesnt exist");
	}

	public void Logout() {
		currentLab = null;
		return;
	}

	public void addStaff(String staffName, String roleType) throws IllegalArgumentException {
		Lab lab = currentLab;

		// check if staffName is valid
		if (isEmpty(staffName)) {
			throw new IllegalArgumentException("The staff name is empty!");
		}

		// check if the roleType is valid
		if ((!roleType.equals("researcher")) && (!roleType.equals("researchAssistant"))) {
			throw new IllegalArgumentException("The role type is not valid!");
		}

		List<Staff> stafflist = lab.getStaffs();
		// check if the staff exist
		for (Staff staff : stafflist) {
			if ((staff.getName()).equals(staffName)) {
				throw new IllegalArgumentException("The staff name is duplicated!");
			}
		}

		// add staff to the system
		Staff newStaff = new Staff(staffName, lab);
		if (roleType.equals("researcher"))
			newStaff.setRole(Role.researcher);
		else
			newStaff.setRole(Role.researchAssistant);

		lab.addStaff(newStaff);
		
		return;

	}

	public void addEquipments(String equipmentName, int quantity) throws IllegalArgumentException {
		Lab lab = currentLab;

		// check if equipmentName is valid
		if (isEmpty(equipmentName)) {
			throw new IllegalArgumentException("The equipment name is empty!");
		}


		List<Equipment> equipmentlist = lab.getEquipments();
		// check if the equipment exist
		for (Equipment equipment : equipmentlist) {
			if ((equipment.getName()).equals(equipmentName)) {
				int count = equipment.getQuantity();
				equipment.setQuantity(count + quantity);
				return;
			}
		}

		// if not exist, add equipment to the system
		if (quantity<=0) {
			throw new IllegalArgumentException("Invalid quantity");
		}
		Equipment newEquip = new Equipment(equipmentName, quantity, lab);
		lab.addEquipment(newEquip);
		
		return;

	}

	public void addSupplies(String supplyname, int quantity) throws IllegalArgumentException {
		Lab lab = currentLab;

		// check if supply name is valid
		if (isEmpty(supplyname)) {
			throw new IllegalArgumentException("The supplyname is empty!");
		}

		

		List<Supply> supplylist = lab.getSupplies();
		// check if the supply exist
		for (Supply supply : supplylist) {
			if ((supply.getName()).equals(supplyname)) {
				int count = supply.getQuantity()+quantity;
				if (count<=0) {
					supply.delete();
					throw new IllegalArgumentException("This item is removed");
				}
				supply.setQuantity(count);
				
				return;
			}
		}
		// if not exist, add supply to the system
		// check if the quantity is valid
		if (quantity <= 0) {
			throw new IllegalArgumentException("Invalid quantity!");
		}
		Supply newSupply = new Supply(supplyname, quantity, lab);
		lab.addSupply(newSupply);
		
		return;
	}

	public void addProgress(String progress, String staffName) throws IllegalArgumentException {
		Lab lab = currentLab;
		
		if (progress.isEmpty()||staffName.isEmpty()) {
			throw new IllegalArgumentException("Invalid Input, Please fill the required fields");
		}
		
		
		List<Staff> stafflist = lab.getStaffs();
		// check if the staff exist
		boolean isExist = false;
		for (Staff staff : stafflist) {
			if ((staff.getName()).equals(staffName)) {
				// add progress to staff
				Progress newProgress = new Progress(progress, staff);
				staff.addProgress(newProgress);
				isExist = true;
				break;
			}
		}
		if (!isExist)
			throw new IllegalArgumentException("The staff doesn't exist!");

		return;
	}

	public List<Progress> checkProgress(String staffName) throws IllegalArgumentException {
		Lab lab = currentLab;

		List<Staff> stafflist = lab.getStaffs();
		// check if the staff exist
		boolean isExist = false;
		List<Progress> list = null;
		for (Staff staff : stafflist) {
			if ((staff.getName()).equals(staffName)) {
				list = staff.getProgresses();
				isExist = true;
				break;
			}
		}
		if (!isExist)
			throw new IllegalArgumentException("The staff doesn't exist!");

		return list;

	}

	public void addExpense(String reason, double amount) throws IllegalArgumentException {
		Lab lab = currentLab;

		if (reason==null || reason.isEmpty()) {
			throw new IllegalArgumentException("please enter a reason");
		}

		// add expense to lab account
		Expense newExpense = new Expense(reason, amount, lab);
		lab.addExpense(newExpense);
		lab.setAccountBalance(lab.getAccountBalance() + amount);

		
		
		return;
	}

	public List<Staff> checkStaffs() {
		List<Staff> staffs = currentLab.getStaffs();
		return staffs;

	}

	public List<Equipment> checkEquipments() {
		List<Equipment> equipments = currentLab.getEquipments();
		return equipments;

	}

	public List<Supply> checkSupplies() {
		List<Supply> supplies = currentLab.getSupplies();
		return supplies;

	}

	public List<Expense> checkExpenses() {
		List<Expense> expenses = currentLab.getExpenses();
		return expenses;
	}

	public double getBalance() {
		return currentLab.getAccountBalance();

	}

	private boolean isEmpty(String text) {

		if (text == null || text.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public void removeEquipement(String name) throws IllegalArgumentException {

		if (isEmpty(name)) {
			throw new IllegalArgumentException("the equipment doesnt exist");
		}

		Equipment target = null;

		for (Equipment item : currentLab.getEquipments()) {
			if (item.getName().equals(name)) {
				target = item;
				break;
			}
		}

		if (target != null) {
			target.delete();
		} else {
			throw new IllegalArgumentException("the equipmenet doesnt exist");
		}

	}
	
	public void removeStaff(String name) {
		if (isEmpty(name)) {
			throw new IllegalArgumentException("the staff doesnt exist");
		}
		Staff target = null;
		
		for (Staff staff : currentLab.getStaffs()) {
			if (staff.getName().equals(name)) {
				target = staff;
				break;
			}
		}
		if (target!=null) {
			target.delete();
		}else {
			throw new IllegalArgumentException("the staff doesnt exist");
		}
	}
	public void removeSupply(String name) {
		if (isEmpty(name)) {
			throw new IllegalArgumentException("the Supply doesnt exist");
		}
		Supply target = null;
		
		for (Supply supply : currentLab.getSupplies()) {
			if (supply.getName().equals(name)) {
				target = supply;
				break;
			}
		}
		
		if (target!=null) {
			target.delete();
		}else {
			throw new IllegalArgumentException("the Supply doesnt exist");
		}
	}
	

}