package chattingApp;

import java.net.*;
import java.util.Scanner;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
public class MyClient extends JFrame{
	
	Socket s;
	DataOutputStream dos;
	DataInputStream dis;
	String msgin = "";
	
	JTextArea ta;
	JTextField tf ;
	JButton  b;
	
	MyClient(){
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
			
			
			setTitle("client");
			setSize(400,400);
			setResizable(false);
			setLayout(null);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			
			
			//--------------------------------------------------------------------
			s = new Socket("localhost" , 1111);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			while(!msgin.equals("exit")) {
				
				msgin  = dis.readUTF();
				ta.setText(ta.getText()+"\n Server : "+msgin);
				
			}
//			ta.setText("server end the chat "+" \n"+ta.getText());
			JOptionPane.showMessageDialog(this, "server terminated the chat");
			dis.close();
			dos.close();
			
			//------------------------------------------------------------------
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new MyClient();
	}

}

