package net.sourceforge.fenixedu.domain.phd;

import java.io.Serializable;

import net.sourceforge.fenixedu.domain.DomainReference;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.person.PersonName;

public class PhdProgramGuidingBean implements Serializable {

    static private final long serialVersionUID = -5481393284887457872L;

    private PhdProgramGuidingType guidingType;
    private DomainReference<PersonName> personName;

    private String name;
    private String qualification;
    private String category;
    private String workLocation;
    private String address;
    private String email;
    private String phone;

    public PhdProgramGuidingBean() {
    }

    public PhdProgramGuidingType getGuidingType() {
	return guidingType;
    }

    public void setGuidingType(PhdProgramGuidingType guidingType) {
	this.guidingType = guidingType;
    }

    public PersonName getPersonName() {
	return (this.personName != null) ? this.personName.getObject() : null;
    }

    public void setPersonName(PersonName personName) {
	this.personName = (personName != null) ? new DomainReference<PersonName>(personName) : null;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getQualification() {
	return qualification;
    }

    public void setQualification(String qualification) {
	this.qualification = qualification;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getWorkLocation() {
	return workLocation;
    }

    public void setWorkLocation(String workLocation) {
	this.workLocation = workLocation;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public boolean isInternal() {
	return getGuidingType() == PhdProgramGuidingType.INTERNAL;
    }

    public Person getPerson() {
	return getPersonName().getPerson();
    }

    static public enum PhdProgramGuidingType {
	INTERNAL, EXTERNAL;

	public String getName() {
	    return name();
	}
    }
}
