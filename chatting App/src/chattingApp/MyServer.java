package chattingApp;

import java.net.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
public class MyServer extends JFrame{
	ServerSocket ss;
	Socket s;
	DataOutputStream dos;
	DataInputStream dis;
	String msgin = "";
	
	JTextArea ta;
	JTextField tf ;
	JButton  b;
	
	MyServer(){
		
		try {
			
			
			ta = new JTextArea();
			ta.setBounds(0,0,385,320);
			add(ta);
			ta.setEditable(false);
			
			tf = new JTextField();
			tf.setBounds(0,320,320,42);
			add(tf);
			
			b = new JButton("send");
			b.setBounds(322,320,65,40);
			add(b);
			
			b.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					String msg = tf.getText();
					try {
						dos.writeUTF(msg);
						tf.setText("");
						dos.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			
			tf.addKeyListener(new KeyListener() {
				
				public void keyTyped(KeyEvent e) {
					
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
					if(e.getKeyCode()==10) {
						String msg = tf.getText();
						try {
							dos.writeUTF(msg);
							tf.setText("");
							dos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
				}
				
			});
			setTitle("Server");
			setSize(400,400);
			setResizable(false);
			setLayout(null);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			
			
			
			System.out.println("server is waiting.....");
			ss = new ServerSocket(1111);
			s = ss.accept();
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			
			while(!msgin.equals("exit")) {
				
				msgin = dis.readUTF();
				ta.setText(ta.getText()+ "\n Client : "+msgin);
				
			}
//			ta.setText("cliend end the chat"+"\n"+ta.getText());
			JOptionPane.showMessageDialog(this, "Client terminated the chat ");
			dis.close();
			dos.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		new MyServer();
	}
	
}
