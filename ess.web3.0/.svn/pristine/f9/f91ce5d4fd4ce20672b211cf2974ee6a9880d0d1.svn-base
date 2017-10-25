package com.protocol;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	private JTextField textField_1;

	private static byte[] buffer;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		buffer = new byte[8];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInput = new JLabel("input");
		lblInput.setBounds(10, 10, 54, 15);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(10, 135, 54, 15);
		contentPane.add(lblOutput);
		
		textField = new JTextField();
		textField.setBounds(20, 35, 554, 70);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(20, 160, 554, 70);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				String[] strs=null;
				if(!str.isEmpty()){
					strs=str.split(" ");
					if(strs.length!=0){
						buffer=new byte[strs.length];
						for(int i=0;i<strs.length;i++){
							buffer[i]= Byte.parseByte(strs[i]);
							System.out.println("++++++"+i);
						}
						strs=Convert.ArrByteToHex(buffer);
						str="";
						for(int i=0;i<strs.length;i++){
							str+=strs[i]+" ";
						}
						textField_1.setText(str);
						String crcStr="";
						CRC crc = new CRC();
						crc.CrcMake(buffer.length-2, buffer);
						buffer = new byte[2];
						buffer[0]=crc.getBYCRCHi();
						buffer[1]=crc.getBYCRCLo();
						String[] crcStrs=Convert.ArrByteToHex(buffer);
						for(int i =0;i<crcStrs.length;i++){
							crcStr+=crcStrs[i]+" ";
						}
						textField_2.setText(crcStr);
					}
				}
			}
		});
		btnSubmit.setBounds(481, 115, 93, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblCrc = new JLabel("CRC16");
		lblCrc.setBounds(10, 240, 54, 15);
		contentPane.add(lblCrc);
		
		textField_2 = new JTextField();
		textField_2.setBounds(20, 265, 554, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblXslName = new JLabel("xsl name");
		lblXslName.setBounds(10, 310, 54, 15);
		contentPane.add(lblXslName);
		
		textField_3 = new JTextField();
		textField_3.setBounds(20, 335, 438, 23);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSubmit_1 = new JButton("submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String name = textField_3.getText();
				//new DoExcel(name);
			}
		});
		btnSubmit_1.setBounds(481, 335, 93, 23);
		contentPane.add(btnSubmit_1);
	}
}
