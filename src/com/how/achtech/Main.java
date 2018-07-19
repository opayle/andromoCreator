package com.how.achtech;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main implements ActionListener {
	private String excelUri;
	JFrame f = new JFrame();// creating instance of JFrame

	static int width = 1500;
	static int height = 300;

	Container contain = new Container();

	JLabel labeltitre = new JLabel("Project creator");
	JLabel labelDestination = new JLabel("Destination :");
	JLabel labelUri = new JLabel();
	JButton bDestination = new JButton("Parcourir ...");
	JButton bExtraire = new JButton("Extraire");// creating instance of JButton
	JButton bCancel = new JButton("Cancel");// creating instance of JButton

	int y = 150;

	public Main() {

		contain = f.getContentPane();
		labeltitre.setBounds(5, 5, width - 15, 90);

		labelDestination.setBounds(50, y, 100, 30);
		bDestination.setBounds(150, y, 150, 30);
		labelUri.setBounds(350, y, 300, 30);

		y += 50;
		bExtraire.setBounds(width / 2 - 120, y, 100, 40);
		bCancel.setBounds(width / 2 + 20, y, 100, 40);

		labeltitre.setOpaque(true);
		labeltitre.setForeground(Color.BLUE);
		labeltitre.setBackground(Color.WHITE);
		labeltitre.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitre.setVerticalAlignment(SwingConstants.CENTER);
		labeltitre.setFont(new Font("Serif", Font.PLAIN, 36));

		labeltitre.setForeground(Color.BLUE);
		labeltitre.setBackground(Color.WHITE);

		labelDestination.setForeground(Color.WHITE);
		labelUri.setForeground(Color.white);

		bExtraire.addActionListener(this);
		bCancel.addActionListener(this);
		bDestination.addActionListener(this);

		contain.add(labeltitre);
		contain.add(labelUri);
		contain.add(labelDestination);
		contain.add(bDestination);
		contain.add(bExtraire);
		contain.add(bCancel);

		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setSize(width, height);
		f.setLayout(null);
		f.setResizable(true);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String... strings) {
		new Main();
		// for(int i = 0;i<3;i++){
		// Frame frame = new Frame("wiki"+i, "logoUrl"+i, "backgroundUrl"+i);
		// frame.f.setVisible(true);
		//
		// }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Parcourir ...")) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			filechooser.showSaveDialog(null);
			if (filechooser != null && filechooser.getSelectedFile() != null) {
				excelUri = filechooser.getSelectedFile().getAbsolutePath();
				labelUri.setText(excelUri);
			}
		}

		if (e.getActionCommand().equals("Cancel")) {
			System.exit(0);
		}

		if (e.getActionCommand().equals("Extraire")) {
			try {
	        // Get the workbook instance for XLS file
				XSSFWorkbook workbook = new XSSFWorkbook(excelUri);
	 
	        // Get first sheet from the workbook
	        XSSFSheet sheet = workbook.getSheetAt(0);
	 
	        // Get iterator to all the rows in current sheet
	        Iterator<Row> rowIterator = sheet.iterator();
	 
	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            // Get iterator to all cells of current row
	            Iterator<Cell> cellIterator = row.cellIterator();
	 
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();	 
	                System.out.print(cell.getStringCellValue());
	                System.out.print("\t");
	 
	            }
	            System.out.println("");
	        }			
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
