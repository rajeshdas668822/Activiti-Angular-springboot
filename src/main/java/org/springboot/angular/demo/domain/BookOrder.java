package org.springboot.angular.demo.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service("bookOrder")
public class BookOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	  public Date validate(Long isbn) {
	    System.out.println("received isbn " + isbn);
	    return new Date();
	  }

}
