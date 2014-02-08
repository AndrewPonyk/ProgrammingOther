package com.ap.structural.proxy;

class ProxyImage implements Image {
	 
    private RealImage image = null;
    private String filename = null;
    /**
     * Constructor
     * @param FILENAME
     */
    public ProxyImage(final String FILENAME) { 
        filename = FILENAME; 
    }
 
    /**
     * Displays the image
     */
    public void displayImage() {
        if (image == null) {
           image = new RealImage(filename);  //RealImage should be a singleton
        } 
        image.displayImage();
    }
 
}