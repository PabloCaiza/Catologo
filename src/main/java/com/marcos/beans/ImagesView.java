/**
 * 
 */
package com.marcos.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author vicen 
 */
@Named("ima")
@RequestScoped
public class ImagesView {
	
	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			images.add("model" + i + ".jpg");

		}
	}

	public List<String> getImages() {
		return images;
	}

}
