package org.springboot.angular.demo.usermanagment.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * Class that represent custom security entity that represent system user.
 * 
 * @author rdas
 *
 */

@Entity
@Table(name = "T_USER")
@NamedQueries( { @NamedQuery(name="user.findAll",   query="SELECT c FROM UserProfile c"),
@NamedQuery(name="user.findByName",  query="SELECT c FROM UserProfile c WHERE c.loginName = :name"),
@NamedQuery(name="findGroupByName",  query="SELECT c FROM UserProfile c  join  c.userGroups  user WHERE c.loginName = :name")
}     
)



public class UserProfile {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String loginName;

    @Column(name = "REV")
    private int revision;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String emailAddress;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PWD")
    private String password;
    
    
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "T_MEMBERSHIP", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID" ), 
    inverseJoinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"))
    private Set<UserGroup> userGroups;
    
    

    public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


   
}
