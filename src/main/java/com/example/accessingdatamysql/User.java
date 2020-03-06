package com.example.accessingdatamysql;

import javax.persistence.*;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String email;

	private String title;


	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(name, user.name) &&
				Objects.equals(email, user.email) &&
				Objects.equals(title, user.title) &&
				Objects.equals(address, user.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, email, title, address);
	}
}
