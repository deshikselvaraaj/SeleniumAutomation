package com.test.wrappers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**This functionality is under implementation
 */

public class ScreenCapture extends GenericWrappers{
	public static void screenCapture(String fileName) {
		 	           
		try {
			robot = new Robot();
		} catch (AWTException ex) {
			System.out.println("An exception occurred in instantiating Robot class :  " + ex.getMessage());
		}
		
				// Get the size of the screen
	            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

	            // Capture the screen image
	            BufferedImage image = robot.createScreenCapture(screenRect);

	            // Save the captured image to a file
	            File file = new File(fileName+".png");
	            try {
					ImageIO.write(image, "png", file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            System.out.println("Screenshot saved to " + file.getAbsolutePath());
	      
	}

}
