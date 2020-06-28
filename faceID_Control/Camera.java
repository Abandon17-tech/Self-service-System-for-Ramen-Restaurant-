package faceID_Control;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import impl_Control.UserDAOImpl;
import user_Boundary.LoginUI;
import user_Boundary.MainUI;

/** 
 * This class is used to set face recognition property
 * @author Wendi Zhu
 * @version 1.5
 * 
 */
public class Camera {
	public static String userName = "none";
	MainUI main;
 
	public Camera(MainUI main){
		this.main = main;
		
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);


		JFrame window = new JFrame("FaceID Login");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.pack();
		window.setVisible(true);


		final JButton button = new JButton("Verify!");
		window.add(panel, BorderLayout.CENTER);
		window.add(button, BorderLayout.SOUTH);
		window.setResizable(true);
		window.pack();
		window.setVisible(true);
		
		button.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){
			button.setEnabled(false);  
			String fileName = "src/face_images/1.png";      
			WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run(){
					try{
					String str = FaceSearch.faceSearch();
					String result[] = str.split(",");
					userName = result[7].substring(10);
					userName = userName.replace("\"", "");
					String score = result[9].substring(8,13);
					System.out.println(userName +"\n"+ score); 
					if(Double.parseDouble(score)>90.0){
						JOptionPane.showMessageDialog(null, "login successfully!");
						button.setEnabled(true);  
						
						main.id = userName;
						main.setInvisible();
						UserDAOImpl dao = new UserDAOImpl();
						try {
							main.userInfo = dao.checkInformation(main.id);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						main.setPara(main.userInfo.getfName());
						window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
						
					}
					else{
						JOptionPane.showMessageDialog(null, "There is no such faceID!");
						button.setEnabled(true);   
						return;
					}
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, "Oops!Something went wrong...");
						button.setEnabled(true);   
						return;
					}
					
				}
			});
		}
		});
		
		window.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				main.frame.setEnabled(true);
				webcam.close();
			}
		});
		
	}
}
