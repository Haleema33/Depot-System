import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class User {
	private String username; // stores the user's username
	private String password; // stores the user's password
	private int id; // stores the user's ID
	private String role; // stores the user's role, which can be "driver" or "manager"
	private String name; // stores the user's name
	private String address; // stores the user's address
	private String phone; // stores the user's phone number

	// Constructor for the User class
	public User(String username, String password, int id, String role, String name, String address, String phone) {
		// Assign the provided values to the corresponding instance variables
		this.username = username;
		this.password = password;
		this.id = id;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	// Getter method for the username
	public String getUsername() {
		return username;
	}

	// Setter method for the username
	public void setUsername(String username) {
		this.username = username;
	}

	// Getter method for the password
	public String getPassword() {
		return password;
	}

	// Setter method for the password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter method for the ID
	public int getId() {
		return id;
	}

	// Setter method for the ID
	public void setId(int id) {
		this.id = id;
	}

	// Getter method for the role
	public String getRole() {
		return role;
	}

	// Setter method for the role
	public void setRole(String role) {
		this.role = role;
	}

	// Getter method for the name
	public String getName() {
		return name;
	}

	// Setter method for the name
	public void setName(String name) {
		this.name = name;
	}

	// Getter method for the address
	public String getAddress() {
		return address;
	}

	// Setter method for the address
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter method for the phone
	public String getPhone() {
		return phone;
	}

	// Setter method for the phone
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
