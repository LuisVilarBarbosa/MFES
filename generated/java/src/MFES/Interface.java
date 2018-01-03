package Jyve;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Interface {
	
	public static Scanner scan;
	public static boolean reg = false;
	public static PrivateUser firstU;

	public Interface() {}
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Welcome to Jyve!\n");
		mainMenu();
	}
	
	public static void mainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1 - Registration");
		System.out.println("2 - Login");
		System.out.println("3 - Exit");
		System.out.println("> ");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			registrationMenu();
			break;
		case "2":
			loginMenu();
			break;
		case "3":
			System.out.println("Thanks for using our app");
			break;			
		}
	}
	
	public static void registrationMenu() {
		System.out.println("Enter your name:");
		String n = scan.nextLine();
		System.out.println("Enter your email:");
		String e = scan.nextLine();
		System.out.println("Enter your password:");
		String p = scan.nextLine();
		PrivateUser pu = new PrivateUser(n, e, p);
		reg = true;
		firstU = pu;
		System.out.println("Thanks for registration, " + n + "!");
		privateUserMenu(pu);
	}
	
	public static void loginMenu() {
		if (!reg) {
			System.out.println("No registrated users");
			mainMenu();
		}
		System.out.println("Enter your email:");
		String e = scan.nextLine();
		System.out.println("Enter your password:");
		String p = scan.nextLine();
		PrivateUser puser = firstU.GetPrivateUser(e, p);
		privateUserMenu(puser);
	}
	
	public static void privateUserMenu(PrivateUser pu) {
		System.out.println("Choose an option:");
		System.out.println("1 - Edit profile");
		System.out.println("2 - Create new...");
		System.out.println("3 - Conversations");
		System.out.println("4 - Back to main menu");
		String resp = scan.nextLine();
		/*while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}*/
		switch(resp) {
		case "1":
			editPrivateUserMenu(pu);
			break;
		case "2":
			createNewMenu(pu);
			break;
		case "3":
			conversationsMenu(pu);
			break;
		case "4":
			mainMenu();

		}
	}

	public static void editPrivateUserMenu(PrivateUser pu) {
		System.out.println("Choose an option:");
		System.out.println("1 - Change name");
		System.out.println("2 - Change password");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("Enter new name");
			String n = scan.nextLine();
			pu.SetName(n);
			System.out.println("Your name was changed to " + n);
			privateUserMenu(pu);
			break;
		case "2":
			System.out.println("Enter new password");
			String p = scan.nextLine();
			pu.SetPassword(p);;
			System.out.println("Your password was changed");
			privateUserMenu(pu);
			break;
		case "3":
			privateUserMenu(pu);
			break;
		}
	}
	
	public static void createNewMenu(PrivateUser pu) {
		String n, s, num, c, z;
		System.out.println("Choose an option:");
		System.out.println("1 - Create new artist");
		System.out.println("2 - Create new band");
		System.out.println("3 - Create new place");
		System.out.println("4 - Create new show");
		System.out.println("5 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("The name of an artist:");
			n = scan.nextLine();
			System.out.println("Address:");
			System.out.println("Street:");
			s = scan.nextLine();
			System.out.println("Number:");
			num = scan.nextLine();
			System.out.println("City:");
			c = scan.nextLine();
			System.out.println("Zip code:");
			z = scan.nextLine();
			Common.Address addr = new Common.Address(s, num, c, z);
			pu.CreateArtist(n, addr);
			System.out.println("The artist " + n + " was created");
			createNewMenu(pu);
			break;
		case "2":
			System.out.println("The name of a band:");
			n = scan.nextLine();
			System.out.println("Address:");
			System.out.println("Street:");
			s = scan.nextLine();
			System.out.println("Number:");
			num = scan.nextLine();
			System.out.println("City:");
			c = scan.nextLine();
			System.out.println("Zip code:");
			z = scan.nextLine();
			Common.Address addr1 = new Common.Address(s, num, c, z);
			VDMSet memb = new VDMSet();
			memb.add(pu);
			VDMSet members = getMembers(pu, memb);
			pu.CreateBand(n, addr1, members);
			System.out.println("The band " + n + " was created");
			createNewMenu(pu);
			break;
		case "3":
			System.out.println("The name of place:");
			n = scan.nextLine();
			System.out.println("Short description of place:");
			String descr = scan.nextLine();
			System.out.println("Address:");
			System.out.println("Street:");
			s = scan.nextLine();
			System.out.println("Number:");
			num = scan.nextLine();
			System.out.println("City:");
			c = scan.nextLine();
			System.out.println("Zip code:");
			z = scan.nextLine();
			Common.Address addr2 = new Common.Address(s, num, c, z);
			pu.CreatePlace(n, descr, addr2);
			System.out.println("The place " + n + " was created");
			createNewMenu(pu);
			break;
		case "4":
			System.out.println("The name of show:");
			n = scan.nextLine();
			System.out.println("Short description of show:");
			String desc = scan.nextLine();
			System.out.println("Date of beginning (year-month-day-hours-minutes):");
			String sdate = scan.nextLine();
			String[] sparts = sdate.split("-");
			Number sy = (Number) Integer.parseInt(sparts[0]);
			Number sm = (Number) Integer.parseInt(sparts[1]);
			Number sd = (Number) Integer.parseInt(sparts[2]);
			Number sh = (Number) Integer.parseInt(sparts[3]);
			Number smi = (Number) Integer.parseInt(sparts[4]);
			Common.Date stdate = new Common.Date(sy, sm, sd, sh, smi);
			System.out.println("Date of end (year-month-day-hours-minutes):");
			String edate = scan.nextLine();
			String[] eparts = edate.split("-");
			Number ey = (Number) Integer.parseInt(eparts[0]);
			Number em = (Number) Integer.parseInt(eparts[1]);
			Number ed = (Number) Integer.parseInt(eparts[2]);
			Number eh = (Number) Integer.parseInt(eparts[3]);
			Number emi = (Number) Integer.parseInt(eparts[4]);
			Common.Date endate = new Common.Date(ey, em, ed, eh, emi);
			Place pl = choosePlace();
			VDMSet mem = new VDMSet();
			VDMSet mems = getMembers(pu, mem);
			pu.CreateShow(n, mems, stdate, endate, pl, desc);
			System.out.println("The show " + n + " was created");
			createNewMenu(pu);
			break;
		case "5":
			privateUserMenu(pu);
			break;
		}
	}
	
	public static VDMSet getMembers(PrivateUser pu, VDMSet memb) {
		System.out.println("Who do you want to add?");
		System.out.println("1 - Private user");
		System.out.println("2 - Artist");
		System.out.println("3 - Band");
		System.out.println("4 - Back");
		String resp = scan.nextLine();
		switch(resp){
		case "1":
			Iterator it = pu.privateUsers.iterator();
			while (it.hasNext()) {
				PrivateUser u = (PrivateUser)it.next();
				System.out.println(u.name);
			}
			System.out.println("Choose the User (enter the name)");
			resp = scan.nextLine();
			Iterator it1 = pu.privateUsers.iterator();
			while (it1.hasNext()) {
				PrivateUser us = (PrivateUser)it1.next();
				if (us.name.equals(resp)) {
					memb.add(us);
					getMembers(pu, memb);
				}
			}
			System.out.println("No User with this name");
			getMembers(pu, memb);
			break;
		case "2":
			Iterator ait = Artist.artists.iterator();
			while (ait.hasNext()) {
				Artist a = (Artist)ait.next();
				System.out.println(a.name);
			}
			System.out.println("Choose the Artist (enter the name)");
			resp = scan.nextLine();
			Iterator ait1 = Artist.artists.iterator();
			while (ait1.hasNext()) {
				Artist art = (Artist)ait1.next();
				if (art.name.equals(resp)) {
					memb.add(art);
					getMembers(pu, memb);
				}
			}
			System.out.println("No Artist with this name");
			getMembers(pu, memb);
			break;
		case "3":
			Iterator bit = Band.bands.iterator();
			while (bit.hasNext()) {
				Band b = (Band)bit.next();
				System.out.println(b.name);
			}
			System.out.println("Choose the Band (enter the name)");
			resp = scan.nextLine();
			Iterator bit1 = Band.bands.iterator();
			while (bit1.hasNext()) {
				Band ban = (Band)bit1.next();
				if (ban.name.equals(resp)) {
					memb.add(ban);
					getMembers(pu, memb);
				}
			}
			System.out.println("No Band with this name");
			getMembers(pu, memb);
			break;
		case "4":
			return memb;			
		}
		return memb;		
	}
	
	public static void conversationsMenu(PrivateUser pu) {
		System.out.println("1 - Create new conversation");
		System.out.println("2 - See all conversations");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		switch(resp) {
		case "1":
			System.out.println("Enter the topic of conversation:");
			String topic = scan.nextLine();
			//TODO add members
			
		case "2":
			System.out.println("Your conversations:");
			Iterator it = pu.conversations.iterator();
			while (it.hasNext()) {
				Conversation conv = (Conversation)it.next();
				System.out.println(conv.topic);
			}
			System.out.println("Enter the topic to choose the conversation or type 'back' to go back");
			String top = scan.nextLine();
			if (top.equals("back"))
				privateUserMenu(pu);
			Iterator it1 = pu.conversations.iterator();
			while (it1.hasNext()) {
				Conversation conv1 = (Conversation)it1.next();
				if (conv1.topic.equals(top)) {
					convMenu(conv1);
					break;
				}
			}
			System.out.println("No conversation with this topic");
			conversationsMenu(pu);
			
		case "3":
			privateUserMenu(pu);
			break;
		}
	}
	
	public static Place choosePlace() {
		System.out.println("All places:\n");
		Iterator it = Place.places.iterator();
		Place pl = (Place)it.next();
		System.out.println(pl.name);
		while (it.hasNext()) {
			Place p = (Place)it.next();
			System.out.println(p.name);
		}
		System.out.println("Choose the place (enter the name)");
		String resp = scan.nextLine();
		Iterator it1 = Place.places.iterator();
		while (it1.hasNext()) {
			pl = (Place)it1.next();
			if (pl.name.equals(resp)) {
				return pl;
			}
		}
		return pl;
	}

	public static void convMenu(Conversation c) {}
}
