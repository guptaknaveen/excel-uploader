package com.s2p.utility.exceluploader.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends CommonModel {
    private String firstName;
    private String lastName;
    private String email;
    private List<FieldPermission> permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User addPermission(FieldPermission fieldPermission) {
        if (permissions == null) {
            this.permissions = new ArrayList<>();
        }
        this.permissions.add(fieldPermission);
        return this;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		User user = (User) o;
		return id == user.id &&
				firstName.equals(user.firstName) &&
				lastName.equals(user.lastName) &&
				email.equals(user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, firstName, lastName, email);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}