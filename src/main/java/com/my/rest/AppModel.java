package com.my.rest;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter

public class AppModel implements Serializable {
    private static final long serialVersionUID = 876688928410084519L;
    @Id
    private long id;
    private String company;
    private String message;
    
    @Override
	public String toString() {
		return "AppModel [id=" + id + ", message=" + message + ", company=" + company + "]";
	}
    
    
}