package com.how.achtech;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main implements ActionListener {
	private static final String PACK_SUFFIX = "com.";
	private static final String PACK_PREFIX = ".achtech";
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
			List<ExcelStrecture> list = getDataFromExcel(excelUri);
			for(ExcelStrecture data : list){
				Frame f = new Frame(data);
			
			}
		}
	}

	public List<ExcelStrecture>  getDataFromExcel(String excelUri){
		DataFormatter dataFormatter = new DataFormatter();
		List<ExcelStrecture> list = new ArrayList<ExcelStrecture>();
		List<List<String>> rows = new ArrayList<List<String>>(); 
		int indexRow = 0;
		try {
			FileInputStream fichier = new FileInputStream(new File(excelUri));
		       //créer une instance workbook qui fait référence au fichier xlsx 
		       XSSFWorkbook wb = new XSSFWorkbook(fichier);
		       XSSFSheet sheet = wb.getSheetAt(0);
		       FormulaEvaluator formulaEvaluator = 
	                     wb.getCreationHelper().createFormulaEvaluator();
		       
		    // Create a DataFormatter to format and get each cell's value as String
		       for (Row ligne : sheet) {//parcourir les lignes
		    	   // Now let's iterate over the columns of the current row
		            Iterator<Cell> cellIterator = ligne.cellIterator();
		            List<String> row = new ArrayList<>(); 
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();
		                String cellValue = dataFormatter.formatCellValue(cell);
		                row.add(cellValue);
		            }
		            if(indexRow!=0) rows.add(row);
		            indexRow++;
		       }
		          for(List<String> ligne : rows)  {
		            ExcelStrecture excel = new ExcelStrecture();
		    	   if(ligne.get(0) != null ){
		    		   String wiki = ligne.get(0);
		    		   excel.setWikihowUrl(wiki);
		    		   String pack = PACK_SUFFIX+wiki.substring(24).replace("-", ".").replace("?", "").trim().replace(" ", ".")+PACK_PREFIX;
		    		   excel.setPackageName(pack.toLowerCase());
		    		   String title = wiki.substring(24).replace("-", " ").replace("?", "");
		    		   excel.setTitre(title);
		    		   System.out.println(title);
		    	   }
		    	   if(ligne.get(1) != null ) excel.setLogoUrl(ligne.get(1));
		    	   if(ligne.get(2) != null ) excel.setBackgroundUrl(ligne.get(2));
		    	   if(ligne.get(3) != null ) excel.setDescription(ligne.get(3));
		    	   if(ligne.get(4) != null ) excel.setKeyword(ligne.get(4).length()>80?ligne.get(4).substring(0, 80):ligne.get(4));
		    	   list.add(excel);
		       }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}

}
