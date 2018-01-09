package Jyve;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.overture.codegen.runtime.*;

import Jyve.Conversation.Message;

@SuppressWarnings("all")
public class Interface {
	
	public static Scanner scan;
	public static boolean reg = false;
	public static PrivateUser firstU;

	public Interface() {}
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("** Welcome to Jyve! **\n");
		mainMenu();
	}
	
	public static void mainMenu() {
		System.out.println("Choose an option:");
		System.out.println("1 - Registration");
		System.out.println("2 - Login");
		System.out.println("3 - Exit");
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
			System.out.println("** Thanks for using our app **");
			System.exit(0);
			break;			
		}
	}
	
	public static void registrationMenu() {
		System.out.println("\n** Registration **\n");
		System.out.println("Enter your name:");
		String n = scan.nextLine();
		System.out.println("Enter your email:");
		String e = scan.nextLine();
		System.out.println("Enter your password:");
		String p = scan.nextLine();
		PrivateUser pu = new PrivateUser(n, e, p);
		reg = true;
		firstU = pu;
		System.out.println("** Thanks for registration, " + n + "! **");
		privateUserMenu(pu);
	}
	
	public static void loginMenu() {
		System.out.println("\n** Login **\n");
		if (!reg) {
			System.out.println("No registrated users\n");
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
		System.out.println("\n** Private user " + pu.name + " **\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Edit profile");
		System.out.println("2 - Conversations");
		System.out.println("3 - Artists");
		System.out.println("4 - Bands");
		System.out.println("5 - Places");
		System.out.println("6 - Shows");
		System.out.println("7 - Back to main menu");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5") && !resp.equals("6") && !resp.equals("7")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			editPrivateUserMenu(pu);
			break;
		case "2":
			conversationsMenu(pu);
			break;
		case "3":
			artistsMenu(pu);
			break;
		case "4":
			bandsMenu(pu);
			break;
		case "5":
			placesMenu(pu);
			break;
		case "6":
			showsMenu(pu);
		case "7":
			mainMenu();

		}
	}

	public static void editPrivateUserMenu(PrivateUser pu) {
		System.out.println("\n** Edit user **\n");
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
	
	public static void conversationsMenu(PrivateUser pu) {
		System.out.println("\n** Conversation Menu **\n");
		System.out.println("1 - Create new conversation");
		System.out.println("2 - See all conversations");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		switch(resp) {
		case "1":
			System.out.println("\n** New conversation **\n");
			System.out.println("Enter the topic of conversation:");
			String topic = scan.nextLine();
			VDMSet memb = new VDMSet();
			memb.add(pu);
			VDMSet members = getMembers(pu, memb);
			Conversation con = new Conversation(topic, members);
			pu.AddConversation(con);
			convMenu(pu, con);
			
		case "2":
			System.out.println("** Your conversations **");
			Iterator it = pu.conversations.iterator();
			while (it.hasNext()) {
				Conversation conv = (Conversation)it.next();
				System.out.println(conv.topic);
			}
			System.out.println("Enter the topic to choose the conversation or type 'back' to go back");
			String top = scan.nextLine();
			if (top.equals("back"))
				conversationsMenu(pu);
			Iterator it1 = pu.conversations.iterator();
			while (it1.hasNext()) {
				Conversation conv1 = (Conversation)it1.next();
				if (conv1.topic.equals(top)) {
					convMenu(pu, conv1);
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
	
	public static void artistsMenu(PrivateUser pu) {
		System.out.println("\n** Artists Menu **\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Create new artist");
		System.out.println("2 - See my artists");
		System.out.println("3 - See all artists");
		System.out.println("4 - Search for artist");
		System.out.println("5 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			createArtist(pu);
			break;
		case "2":
			System.out.println("** Your artists **\n");
			VDMSet arts = pu.myArtists;
			boolean a = true;
			seeAll(pu, arts, a);
			break;
		case "3":
			System.out.println("** All artists **\n");
			VDMSet art = Artist.artists;
			boolean b = true;
			seeAll(pu, art, b);
			break;
		case "4":
			VDMSet allA = Artist.artists;
			VDMSet foundA = searchByName(allA);
			if (foundA.isEmpty()) {
				System.out.println("No artist with this name");
				artistsMenu(pu);
			}
			else {
				boolean c = true;
				seeAll(pu, foundA, c);
			}
			break;
		case "5":
			privateUserMenu(pu);
			break;
		}
	} 
	
	public static void bandsMenu(PrivateUser pu) {
		System.out.println("\n** Bands Menu **\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Create new band");
		System.out.println("2 - See my bands");
		System.out.println("3 - See all bands");
		System.out.println("4 - Search for band");
		System.out.println("5 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			createBand(pu);
			break;
		case "2":
			System.out.println("** Your bands **\n");
			VDMSet arts = pu.myBands;
			boolean a = false;
			seeAll(pu, arts, a);
			break;
		case "3":
			System.out.println("** All bands **\n");
			VDMSet art = Band.bands;
			boolean b = false;
			seeAll(pu, art, b);
			break;
		case "4":
			VDMSet allB = Band.bands;
			VDMSet foundB = searchByName(allB);
			if (foundB.isEmpty()) {
				System.out.println("No band with this name");
				bandsMenu(pu);
			}
			else {
				boolean c = false;
				seeAll(pu, foundB, c);
			}
			break;
		case "5":
			privateUserMenu(pu);
			break;
		}
	} 
	
	public static void placesMenu(PrivateUser pu) {
		System.out.println("\n** Places Menu **\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Create new place");
		System.out.println("2 - See my places");
		System.out.println("3 - See all places");
		System.out.println("4 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			createPlace(pu);
			break;
		case "2":
			System.out.println("** Your places **\n");
			seePlaces(pu, pu.myPlaces);			
			break;
		case "3":
			System.out.println("** All places **\n");
			seePlaces(pu, Place.places);
			break;
		case "4":
			privateUserMenu(pu);
			break;
		}
	}
	
	
	public static void showsMenu(PrivateUser pu) {
		System.out.println("\n** Shows Menu **\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Create new show");
		System.out.println("2 - See my shows");
		System.out.println("3 - See all shows");
		System.out.println("4 - See calendar of shows");
		System.out.println("5 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			createShow(pu);
			break;
		case "2":
			System.out.println("** Your shows **");
			seeShows(pu, pu.myShows);
			break;
		case "3":
			System.out.println("** All shows **");
			seeShows(pu, Calendar.shows);
			break;
		case "4":
			System.out.println("** Calendar of shows **");
			Iterator it = Calendar.GetShowsSortedByStartDate().iterator();
			while(it.hasNext()) {
				Calendar.Show s = (Calendar.Show)it.next();
				System.out.println(s.startDate.day + "/" + s.startDate.month + "/" + s.startDate.year + "  -  '" + s.name + "'  (" + s.place.name + ")");
			}
			System.out.println("Choose show (enter the name) or type 'back' to go back:");
			String res = scan.nextLine();
			if (res.equals("back")) {
				showsMenu(pu);
			}
			Iterator it1 = Calendar.GetShowsSortedByStartDate().iterator();
			while (it1.hasNext()) {
				Calendar.Show sh = (Calendar.Show)it1.next();
				if (sh.name.equals(res)) {
					shMenu(pu, sh);
				}
			}
			System.out.println("No show with this name");
			showsMenu(pu);
			break;
		case "5":
			privateUserMenu(pu);
			break;
		}
	}

	public static VDMSet searchByName(VDMSet arts) {
		System.out.println("\n** Search **\n");
		System.out.println("Enter the name or the part of name to search:");
		String n = scan.nextLine();
		VDMSet res = new VDMSet();
		Iterator it = arts.iterator();
		while(it.hasNext()) {
			PublicUser a = (PublicUser)it.next();
			if (Common.ContainsStr2(a.name, n)) {
				res.add(a);
			}
		}
		return res;
	}
	
	public static void seeAll(PrivateUser pu, VDMSet arts, boolean a) {
		Iterator it = arts.iterator();
		while(it.hasNext()) {
			PublicUser a1 = (PublicUser)it.next();
			System.out.println(a1.name);
		}
		System.out.println("\nEnter the name to choose or 'back' to go back:");
		String n = scan.nextLine();
		if (n.equals("back")) {
			if (a)
				artistsMenu(pu);
			else bandsMenu(pu);
		}
		Iterator ite = arts.iterator();
		while(ite.hasNext()) {
			PublicUser ar = (PublicUser)ite.next();
			if (ar.name.equals(n)) {
				artMenu(pu, ar);
			}
		}
		System.out.println("Nobody is found with this name");
		seeAll(pu, arts, a);
	}
	
	public static void artMenu(PrivateUser pu, PublicUser art) {
		System.out.println("\n** " + art.name + " **\n");
		System.out.println("Discription: " + art.description);
		System.out.println("Address: " + art.address.street + " " + art.address.number + ", " + art.address.city + ", " + art.address.zipcode + "\n");
		System.out.println("Choose an option:");
		System.out.println("1 - See songs");
		System.out.println("2 - See genres");
		System.out.println("3 - See instruments");
		System.out.println("4 - Send message");
		System.out.println("5 - Set name");
		System.out.println("6 - Set description");
		System.out.println("7 - Set address");
		System.out.println("8 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5") && !resp.equals("6") && !resp.equals("7") && !resp.equals("8")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			songsMenu(pu, art);
			break;
		case "2":
			genresMenu(pu, art);
			break;
		case "3":
			instrMenu(pu, art);
			break;
		case "4":
			System.out.println("\n** New conversation **\n");
			System.out.println("Enter the topic of conversation:");
			String topic = scan.nextLine();
			VDMSet memb = new VDMSet();
			memb.add(pu);
			memb.add(art);
			Conversation con = new Conversation(topic, memb);
			String tDate = null;
			Date todaysDate = new Date();
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		    tDate = df.format(todaysDate);   
		    String[] dparts = tDate.split("-");
			Number ey = (Number) Integer.parseInt(dparts[0]);
			Number em = (Number) Integer.parseInt(dparts[1]);
			Number ed = (Number) Integer.parseInt(dparts[2]);
			Number eh = (Number) Integer.parseInt(dparts[3]);
			Number emi = (Number) Integer.parseInt(dparts[4]);
			Common.Date mdate = Common.CreateDate(ey, em, ed, eh, emi);
			System.out.println("Enter your message:");
			String mess = scan.nextLine();
			con.AddMessage(mdate, mess, pu);
			pu.AddConversation(con);
			System.out.println("Your message is sent");
			artMenu(pu, art);
			break;
		case "5":
			System.out.println("Enter new name:");
			String name = scan.nextLine();
			art.SetName(name);
			artMenu(pu, art);
			break;
		case "6":
			System.out.println("Enter new description:");
			String desc = scan.nextLine();
			art.SetDescription(desc);;
			artMenu(pu, art);
			break;
		case "7":
			System.out.println("New address:");
			System.out.println("Street:");
			String s = scan.nextLine();
			System.out.println("Number:");
			String num = scan.nextLine();
			System.out.println("City:");
			String c = scan.nextLine();
			System.out.println("Zip code:");
			String z = scan.nextLine();
			Common.Address addr = Common.CreateAddress(s, num, c, z);
			art.SetAddress(addr);
			artMenu(pu, art);
			break;
		case "8":
			privateUserMenu(pu);
			break;
		}
	}
	
	public static void songsMenu(PrivateUser pu, PublicUser art) {
		System.out.println("All songs of " + art.name + ":\n");
		Iterator it = art.songs.iterator();
		while(it.hasNext()) {
			PublicUser.Song s = (PublicUser.Song)it.next();
			System.out.println(s.name + " - " + s.link);
		}
		System.out.println("\nChoose an option:");
		System.out.println("1 - Add song");
		System.out.println("2 - Remove song");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("Name of song:");
			String n = scan.nextLine();
			System.out.println("Link:");
			String l = scan.nextLine();
			art.AddSong(n, l);
			songsMenu(pu, art);
			break;
		case "2":
			System.out.println("Name of song:");
			String na = scan.nextLine();
			Iterator ite = art.songs.iterator();
			while(ite.hasNext()) {
				PublicUser.Song so = (PublicUser.Song)ite.next();
				if(so.name.equals(na)) {
					art.RemoveSong(so);
					songsMenu(pu, art);
				}
			}
			System.out.println("No song with this name");
			songsMenu(pu, art);
			break;
		case "3":
			artMenu(pu, art);
			break;
		}
	}
	
	public static void genresMenu(PrivateUser pu, PublicUser art) {
		System.out.println("All genres of " + art.name + ":\n");
		Iterator it = art.genres.iterator();
		while(it.hasNext()) {
			String g = (String)it.next();
			System.out.println(g);
		}
		System.out.println("\nChoose an option:");
		System.out.println("1 - Add genre");
		System.out.println("2 - Remove genre");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("Name of genre:");
			String n = scan.nextLine();
			art.AddGenre(n);
			genresMenu(pu, art);
			break;
		case "2":
			System.out.println("Name of genre:");
			String ge = scan.nextLine();
			art.RemoveGenre(ge);
			genresMenu(pu, art);
			break;
		case "3":
			artMenu(pu, art);
			break;
		}
	}
	
	public static void instrMenu(PrivateUser pu, PublicUser art) {
		System.out.println("All instruments of " + art.name + ":\n");
		Iterator it = art.instruments.iterator();
		while(it.hasNext()) {
			String g = (String)it.next();
			System.out.println(g);
		}
		System.out.println("\nChoose an option:");
		System.out.println("1 - Add instrument");
		System.out.println("2 - Remove instrument");
		System.out.println("3 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("Name of instrument:");
			String n = scan.nextLine();
			art.AddInstrument(n);;
			instrMenu(pu, art);
			break;
		case "2":
			System.out.println("Name of instrument:");
			String ge = scan.nextLine();
			art.RemoveInstrument(ge);;
			instrMenu(pu, art);
			break;
		case "3":
			artMenu(pu, art);
			break;
		}
	}
	
	public static void createArtist(PrivateUser pu) {
		System.out.println("\n** New artist **\n");
		System.out.println("The name of an artist:");
		String n = scan.nextLine();
		System.out.println("Address:");
		System.out.println("Street:");
		String s = scan.nextLine();
		System.out.println("Number:");
		String num = scan.nextLine();
		System.out.println("City:");
		String c = scan.nextLine();
		System.out.println("Zip code:");
		String z = scan.nextLine();
		Common.Address addr = Common.CreateAddress(s, num, c, z);
		pu.CreateArtist(n, addr);
		System.out.println("The artist " + n + " was created");
		artistsMenu(pu);
	}
	
	public static void createBand(PrivateUser pu) {
		System.out.println("\n** New band **\n");
		System.out.println("The name of a band:");
		String n = scan.nextLine();
		System.out.println("Address:");
		System.out.println("Street:");
		String s = scan.nextLine();
		System.out.println("Number:");
		String num = scan.nextLine();
		System.out.println("City:");
		String c = scan.nextLine();
		System.out.println("Zip code:");
		String z = scan.nextLine();
		Common.Address addr1 = Common.CreateAddress(s, num, c, z);
		VDMSet memb = new VDMSet();
		memb.add(pu);
		VDMSet members = getMembers(pu, memb);
		pu.CreateBand(n, addr1, members);
		System.out.println("The band " + n + " was created");
		bandsMenu(pu);
	}
	

	public static void createPlace(PrivateUser pu) {
		System.out.println("The name of place:");
		String n = scan.nextLine();
		System.out.println("Short description of place:");
		String descr = scan.nextLine();
		System.out.println("Address:");
		System.out.println("Street:");
		String s = scan.nextLine();
		System.out.println("Number:");
		String num = scan.nextLine();
		System.out.println("City:");
		String c = scan.nextLine();
		System.out.println("Zip code:");
		String z = scan.nextLine();
		Common.Address addr2 = Common.CreateAddress(s, num, c, z);
		pu.CreatePlace(n, descr, addr2);
		System.out.println("The place " + n + " was created");
		placesMenu(pu);
	}
	public static void createShow(PrivateUser pu) {
		System.out.println("\n** New show **\n");
		System.out.println("The name of show:");
		String n = scan.nextLine();
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
		Common.Date stdate = Common.CreateDate(sy, sm, sd, sh, smi);			
		System.out.println("Date of end (year-month-day-hours-minutes):");
		String edate = scan.nextLine();
		String[] eparts = edate.split("-");
		Number ey = (Number) Integer.parseInt(eparts[0]);
		Number em = (Number) Integer.parseInt(eparts[1]);
		Number ed = (Number) Integer.parseInt(eparts[2]);
		Number eh = (Number) Integer.parseInt(eparts[3]);
		Number emi = (Number) Integer.parseInt(eparts[4]);
		Common.Date endate = Common.CreateDate(ey, em, ed, eh, emi);
		System.out.println("Available places:\n");
		Place pl = choosePlace(Place.places);
		VDMSet mem = new VDMSet();
		VDMSet mems = getMembers(pu, mem);
		pu.CreateShow(n, mems, stdate, endate, pl, desc);
		System.out.println("The show " + n + " was created");
		showsMenu(pu);
	}
	public static void seePlaces(PrivateUser pu, VDMSet places) {
		Iterator it = places.iterator();
		while (it.hasNext()) {
			Place p = (Place)it.next();
			System.out.println(p.name);
		}
		System.out.println("Choose the place (enter the name) or type 'back' to go back:");
		String resp = scan.nextLine();
		if (resp.equals("back")) {
			placesMenu(pu);
		}
		Iterator it1 = places.iterator();
		while (it1.hasNext()) {
			Place pl = (Place)it1.next();
			if (pl.name.equals(resp)) {
				plMenu(pu, pl);
			}
		}
		System.out.println("No place with this name");
		placesMenu(pu);
	}
	
	public static VDMSet getMembers(PrivateUser pu, VDMSet memb) {
		String resp = new String();
		while(!resp.equals("4")) {
			System.out.println("Who do you want to add?");
		System.out.println("1 - Private user");
		System.out.println("2 - Artist");
		System.out.println("3 - Band");
		System.out.println("4 - Back");
		resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp){
		case "1":
			Iterator it = pu.privateUsers.iterator();
			while (it.hasNext()) {
				PrivateUser u = (PrivateUser)it.next();
				System.out.println(u.name);
			}
			System.out.println("Choose the User (enter the name)");
			String res = scan.nextLine();
			Iterator it1 = pu.privateUsers.iterator();
			while (it1.hasNext()) {
				PrivateUser us = (PrivateUser)it1.next();
				if (us.name.equals(res)) {
					memb.add(us);
					//getMembers(pu, memb);
					//return memb;
				}
			}
			System.out.println("No User with this name");
			//getMembers(pu, memb);
			break;
		case "2":
			Iterator ait = Artist.artists.iterator();
			while (ait.hasNext()) {
				Artist a = (Artist)ait.next();
				System.out.println(a.name);
			}
			System.out.println("Choose the Artist (enter the name)");
			String re = scan.nextLine();
			Iterator ait1 = Artist.artists.iterator();
			while (ait1.hasNext()) {
				Artist art = (Artist)ait1.next();
				if (art.name.equals(re)) {
					memb.add(art);
					//getMembers(pu, memb);
					//return memb;
				}
			}
			System.out.println("No Artist with this name");
			//getMembers(pu, memb);
			break;
		case "3":
			Iterator bit = Band.bands.iterator();
			while (bit.hasNext()) {
				Band b = (Band)bit.next();
				System.out.println(b.name);
			}
			System.out.println("Choose the Band (enter the name):");
			String r = scan.nextLine();
			Iterator bit1 = Band.bands.iterator();
			while (bit1.hasNext()) {
				Band ban = (Band)bit1.next();
				if (ban.name.equals(r)) {
					memb.add(ban);
					//getMembers(pu, memb);
					//return memb;
				}
			}
			System.out.println("No Band with this name");
			//getMembers(pu, memb);
			break;			
		}
		}
		return memb;		
	}

	public static User getMember(Conversation c) {
		System.out.println("Who do you want to add?");
		System.out.println("1 - Private user");
		System.out.println("2 - Artist");
		System.out.println("3 - Band");
		System.out.println("4 - Back");
		String resp = scan.nextLine();
		switch(resp){
		case "1":
			Iterator it = PrivateUser.privateUsers.iterator();
			while (it.hasNext()) {
				PrivateUser u = (PrivateUser)it.next();
				System.out.println(u.name);
			}
			System.out.println("Choose the User (enter the name)");
			resp = scan.nextLine();
			Iterator it1 = PrivateUser.privateUsers.iterator();
			while (it1.hasNext()) {
				PrivateUser us = (PrivateUser)it1.next();
				if (us.name.equals(resp)) {
					return us;
				}
			}
			System.out.println("No User with this name");
			getMember(c);
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
					return art;
				}
			}
			System.out.println("No Artist with this name");
			getMember(c);
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
					return ban;
				}
			}
			System.out.println("No Band with this name");
			getMember(c);
			break;
		case "4":
			getMember(c);			
		}
		return new User("null");		
	}
	
	public static Place choosePlace(VDMSet places) {
		Iterator it = places.iterator();
		Place pl = (Place)it.next();
		System.out.println(pl.name);
		while (it.hasNext()) {
			Place p = (Place)it.next();
			System.out.println(p.name);
		}
		System.out.println("Choose the place (enter the name):");
		String resp = scan.nextLine();
		Iterator it1 = places.iterator();
		while (it1.hasNext()) {
			pl = (Place)it1.next();
			if (pl.name.equals(resp)) {
				return pl;
			}
		}
		return pl;
	}
	
	public static void shMenu(PrivateUser pu, Calendar.Show sh) {
		System.out.println("\n** " + sh.name + " **\n");
		System.out.println("Description: " + sh.description);
		System.out.println("Place: " + sh.place.name + "(" + sh.place.address.street + " " + sh.place.address.number + ", " + sh.place.address.city + ", " + sh.place.address.zipcode + ")");
		System.out.println("Start date:" + sh.startDate.day + "/" + sh.startDate.month + "/" + sh.startDate.year + " " + sh.startDate.hour + ":" + sh.startDate.minute);
		System.out.println("End date:" + sh.endDate.day + "/" + sh.endDate.month + "/" + sh.endDate.year + " " + sh.endDate.hour + ":" + sh.endDate.minute);
		System.out.println("Performers:");
		Iterator it = sh.performers.iterator();
		while(it.hasNext()) {
			PublicUser a = (PublicUser)it.next();
			System.out.println("     - " + a.name);
		}
		System.out.println("Choose an option:");
		System.out.println("1 - Remove show");
		System.out.println("2 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			pu.RemoveShow(sh);
			showsMenu(pu);
			break;
		case "2":
			showsMenu(pu);
			break;
		}

	}
	public static void seeShows(PrivateUser pu, VDMSet shows) {
		Iterator it = shows.iterator();
		while (it.hasNext()) {
			Calendar.Show s = (Calendar.Show)it.next();
			System.out.println(s.name);
		}
		System.out.println("Choose show (enter the name) or type 'back' to go back:");
		String resp = scan.nextLine();
		if (resp.equals("back")) {
			showsMenu(pu);
		}
		Iterator it1 = shows.iterator();
		while (it1.hasNext()) {
			Calendar.Show sh = (Calendar.Show)it1.next();
			if (sh.name.equals(resp)) {
				shMenu(pu, sh);
			}
		}
		System.out.println("No show with this name");
		showsMenu(pu);
	}

	public static void plMenu(PrivateUser pu, Place pl) {
		System.out.println("\n** " + pl.name + " **\n");
		System.out.println("Description: " + pl.description);
		System.out.println("Address: " + pl.address.street + " " + pl.address.number + ", " + pl.address.city + ", " + pl.address.zipcode + "\n");
		System.out.println("Choose an option:");
		System.out.println("1 - Set name");
		System.out.println("2 - Set description");
		System.out.println("3 - Remove place");
		System.out.println("4 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			System.out.println("Enter new name:");
			String name = scan.nextLine();
			pl.SetName(name);
			plMenu(pu, pl);
			break;
		case "2":
			System.out.println("Enter new description:");
			String desc = scan.nextLine();
			pl.SetDescription(desc);
			plMenu(pu, pl);
			break;
		case "3":
			pu.RemovePlace(pl);
			placesMenu(pu);
			break;
		case "4":
			placesMenu(pu);
			break;
		}
	}
	

	public static void convMenu(PrivateUser pu, Conversation c) {
		System.out.println("Topic: " + c.topic + "\n");
		System.out.println("Members:");
		Iterator it = c.members.iterator();
		while (it.hasNext()) {
			User u = (User)it.next();
			System.out.println(u.name);
		}
		System.out.println("\nChoose an option:");
		System.out.println("1 - Add member");
		System.out.println("2 - Remove member");
		System.out.println("3 - Add message");
		System.out.println("4 - Set topic");
		System.out.println("5 - See messages");
		System.out.println("6 - Back");
		String resp = scan.nextLine();
		while (!resp.equals("1") && !resp.equals("2") && !resp.equals("3") && !resp.equals("4") && !resp.equals("5") && !resp.equals("6")) {
			System.out.println("Invalid input. Try again");
			resp = scan.nextLine();
		}
		switch(resp) {
		case "1":
			User m = getMember(c);
			c.AddMember(m);
			convMenu(pu, c);
			break;
		case "2":
			System.out.println("Enter the name of member to delete:");
			String del = scan.nextLine();
			Iterator it1 = c.members.iterator();
			while(it1.hasNext()) {
				User us = (User)it1.next();
				if(us.name.equals(del)) {
					it1.remove();
					convMenu(pu, c);
				}
			}
			System.out.println("No member with this name");
			convMenu(pu, c);
			break;
		case "3":
			String tDate = null;
			Date todaysDate = new Date();
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		    tDate = df.format(todaysDate);   
		    String[] dparts = tDate.split("-");
			Number ey = (Number) Integer.parseInt(dparts[0]);
			Number em = (Number) Integer.parseInt(dparts[1]);
			Number ed = (Number) Integer.parseInt(dparts[2]);
			Number eh = (Number) Integer.parseInt(dparts[3]);
			Number emi = (Number) Integer.parseInt(dparts[4]);
			Common.Date mdate = Common.CreateDate(ey, em, ed, eh, emi);
			System.out.println("Enter your message:");
			String mess = scan.nextLine();
			c.AddMessage(mdate, mess, pu);
			convMenu(pu, c);
			break;
		case "4":
			System.out.println("Enter new topic:");
			String topic = scan.nextLine();
			c.SetTopic(topic);
			convMenu(pu, c);
			break;
		case "5":
			int i = c.messages.size();
			for (Message messs : (Collection<Conversation.Message>)c.messages.values()) {
				System.out.println(messs);
			}
			convMenu(pu, c);
			break;
		case "6":
			conversationsMenu(pu);
		}
	}
}
