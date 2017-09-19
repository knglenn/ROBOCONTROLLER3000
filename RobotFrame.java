//Kevin Glenn

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;

public class RobotFrame extends JFrame {
	public RobotFrame() {
		setTitle("ROBOCONTROLLER 3000");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(screenSize.width/2,screenSize.height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RobotPanel panel = new RobotPanel();
		add(panel);
		pack();
		setVisible(true);
	}
}