package org.springboot.angular.demo.usermanagment.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Created by rdas on 9/8/2016.
 */


@Entity
@Table(name = "T_GROUP")
@NamedQuery(name="group.findGroupByName",  query="SELECT c FROM UserGroup c  join  c.userProfiles  user WHERE user.loginName = :name")
public class UserGroup {

    @Id  
    @Column(name = "ID",unique = true, nullable = false)
    private String groupId;


    @Column(name = "REV")
    private int revision;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;
    
    
    @ManyToMany(mappedBy = "userGroups")
    Set<UserProfile> userProfiles;
    
    
    
    public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	
    

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
