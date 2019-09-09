package com.how.achtech;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Frame implements ActionListener {
	

	private static final Color FOREGROUND_LABELS = Color.WHITE;



	JFrame f = new JFrame();// creating instance of JFrame

	static int widthData = 700;
	static int widthWindows = 1500;
	static int height =250;
	int y = 150;
	int y2=150;
	int xStartRadio=150,yStartRadio = y;
	
	boolean maxAccesed = false;
	boolean isUriChange;
	
	static String articleName = "";
	String url = "";
	String categoriesS = TraiterData.getPropoerties().get(TraiterData.CATEGORIES);
	String selectedCategorie;
	String [] categories = categoriesS.split(",");
	
	Container contain = new Container();
	
	JLabel labeltitre = new JLabel("Project creator");
	JLabel labelLink = new JLabel("Wiki how link :");
	JLabel labelLogo = new JLabel("Logo :");
	JLabel labelDestination = new JLabel("Destination :");
	JLabel lblUri = new JLabel(TraiterData.getPropoerties().get(TraiterData.URI));
	JLabel labelBackground = new JLabel("Background :");
	JLabel labelColumn = new JLabel();
	JLabel lblImageLogo  = new JLabel();
	JLabel lblImagebBackground  = new JLabel();
	

	JLabel labelRTitle = new JLabel("Titre : ");
	JLabel labelRPackage = new JLabel("Package : ");
	JLabel labelRDescription = new JLabel("Description : ");
	JLabel labelRKeyword = new JLabel("Key Word");
	JLabel labelRPrivacyLaw = new JLabel("Privacy");
	JLabel labelRLicence = new JLabel("Licence");
	JLabel labelRPub1 = new JLabel("Ads 1");
	JLabel labelRPub2 = new JLabel("Ads 2");


	JButton btRTitle = new JButton();
	JButton btRPackage = new JButton();
	JButton btRDescription = new JButton();
	JButton btRKeyword = new JButton();
	JButton btRPrivacyLaw = new JButton();
	JButton btRLicence = new JButton();
	JButton btRPub1 = new JButton();
	JButton btRPub2 = new JButton();

	JCheckBox chResize = new JCheckBox("Resize 512*512");
	JCheckBox chRoundedCorner = new JCheckBox("Rounded corner");
	JCheckBox chBorder = new JCheckBox("Border");

	JCheckBox chResizeBack = new JCheckBox("Resize 1024*500");
	JCheckBox chRoundedCornerBack = new JCheckBox("Rounded corner");
	JCheckBox chBorderBack = new JCheckBox("Border");

	JTextField txtLinkLogo = new JTextField("");
	JTextField txtLinkWikiHow = new JTextField(url);
	JTextField txtLinkBackground = new JTextField("");
	
	ButtonGroup group = new ButtonGroup();
	JButton bDestination = new JButton("Parcourir ...");
	JButton bEditCategorie = new JButton("Categories");
	JButton bColor = new JButton("Color");
	JButton bColor2 = new JButton("Color");
	JButton bExtraire = new JButton("Extraire");// creating instance of JButton
	JButton bCancel = new JButton("Cancel");// creating instance of JButton

	private Color color1 = Color.red;
	private Color color2 = Color.red;
	
	List<JButton> btTitres = new ArrayList<JButton>();
	List<JButton> btTxt = new ArrayList<JButton>();
	List<JRadioButton> radios = new ArrayList<JRadioButton>();

	public Frame(ExcelStrecture data) {
		contain = f.getContentPane();

		// First column 
		txtLinkWikiHow.setText(data.getWikihowUrl());
		txtLinkLogo.setText(data.getLogoUrl());
		txtLinkBackground.setText(data.getBackgroundUrl());
		
		labeltitre.setBounds(5, 5, widthWindows-15, 90);
		labelLink.setBounds(50, y, 100, 30);
		txtLinkWikiHow.setBounds(150, y, widthData - 200, 30);
		y+=50;
		labelDestination.setBounds(50, y, 100, 30);
		bDestination.setBounds(150, y, 150, 30);
		lblUri.setBounds(320, y, widthData - 370, 30);
		y+=50;
		labelLogo.setBounds(50, y, 100, 30);
		txtLinkLogo.setBounds(150, y, widthData - 200, 30);
		y+=50;
		chResize.setBounds(150, y, 150, 30);
		chRoundedCorner.setBounds(300, y, 200, 30);
		chBorder.setBounds(500, y, 70, 30);
		bColor.setBounds(580, y, 70, 30);
		
		chResize.setForeground(FOREGROUND_LABELS);
		chResize.setBackground(Color.DARK_GRAY);

		chRoundedCorner.setForeground(FOREGROUND_LABELS);
		chRoundedCorner.setBackground(Color.DARK_GRAY);

		chBorder.setForeground(FOREGROUND_LABELS);
		chBorder.setBackground(Color.DARK_GRAY);

		chResizeBack.setForeground(FOREGROUND_LABELS);
		chResizeBack.setBackground(Color.DARK_GRAY);

		chRoundedCornerBack.setForeground(FOREGROUND_LABELS);
		chRoundedCornerBack.setBackground(Color.DARK_GRAY);

		chBorderBack.setForeground(FOREGROUND_LABELS);
		chBorderBack.setBackground(Color.DARK_GRAY);

		y+=50;
		labelBackground.setBounds(50, y, 100, 30);
		txtLinkBackground.setBounds(150, y, widthData - 200, 30);
		y+=50;
		chResizeBack.setBounds(150, y, 150, 30);
		chRoundedCornerBack.setBounds(300, y, 200, 30);
		chBorderBack.setBounds(500, y, 70, 30);
		bColor2.setBounds(580, y, 70, 30);
		
		y+=50;
		bEditCategorie.setBounds(50, y, 100, 30);

		selectedCategorie = categories.length>0 ? categories[categories.length-1] :"";
		yStartRadio = y;
		for(int i=0;i<categories.length;i++){
			JRadioButton radio = new JRadioButton(categories[i]);
			radio.setBounds(xStartRadio, yStartRadio, 100, 40);
			yStartRadio += 40;
			radio.setForeground(FOREGROUND_LABELS);
			radio.setBackground(Color.DARK_GRAY);

			radios.add(radio);
			contain.add(radios.get(i));
			group.add(radios.get(i));
			radios.get(i).addActionListener(this);
			if(yStartRadio>650){
				maxAccesed = true;
				yStartRadio = y;
				xStartRadio += 150;
			}
		}
		height = yStartRadio + 100 > height || maxAccesed? 850 : height;
		bExtraire.setBounds(widthData/2 -120, height - 100, 100, 40);
		bCancel.setBounds(widthData / 2 + 20, height - 100, 100, 40);
		labelColumn.setBounds(widthData-10, 100, 10, height-145);
		
		//SECOND COLUMN
		String privacyPolicy = "We care about your privacy and data security. We keep this app free by showing ads. We�ll partner with Google and use a unique identifier on your device to serve only non-personalized ads.";
		privacyPolicy += "\nFor information about how Google uses your mobile identifier please visit:";
		privacyPolicy += "\nhttps://policies.google.com/technologies/partner-sites";
		
		btRTitle.setText(data.getTitre());
		btRPrivacyLaw.setText(privacyPolicy);
		btRPackage.setText(data.getPackageName());
		btRDescription.setText(data.getDescription());
		btRKeyword.setText(data.getKeyword());
		btRLicence.setText(data.getLicence());
		btRPub1.setText(data.getPub1());
		btRPub2.setText(data.getPub2());
		
		
		int x2 = widthData+10,wLabel = 100,wButton = 200,x3=x2+3*wLabel+20;
		labelRTitle.setBounds(x2,y2,wLabel,30);
		btRTitle.setBounds(x2+wLabel+10,y2,wButton,30);
		
		labelRPrivacyLaw.setBounds(x3,y2,wLabel,30);
		btRPrivacyLaw.setBounds(x3+wLabel+10,y2,wButton,30);
		
		y2+=50;
		labelRPackage.setBounds(x2,y2,wLabel,30);
		btRPackage.setBounds(x2+wLabel+10,y2,wButton,30);
		
		labelRDescription.setBounds(x3,y2,wLabel,30);
		btRDescription.setBounds(x3+wLabel+10,y2,wButton,30);

		y2+=50;
		labelRKeyword.setBounds(x2,y2,wLabel,30);
		btRKeyword.setBounds(x2+110,y2,wButton,30);
		
		labelRLicence.setBounds(x3,y2,wLabel,30);
		btRLicence.setBounds(x3+wLabel+10,y2,wButton,30);

		y2+=50;
		labelRPub1.setBounds(x2,y2,wLabel,30);
		btRPub1.setBounds(x2+110,y2,wButton,30);
		
		labelRPub2.setBounds(x3,y2,wLabel,30);
		btRPub2.setBounds(x3+wLabel+10,y2,wButton,30);

		y2+=50;
		
		labelRTitle.setForeground(Color.white);
		labelRPrivacyLaw.setForeground(Color.white);
		labelRPackage.setForeground(Color.white);
		labelRDescription.setForeground(Color.white);
		labelRKeyword.setForeground(Color.white);
		labelRLicence.setForeground(Color.white);
		labelRPub1.setForeground(Color.white);
		labelRPub2.setForeground(Color.white);
		
		lblImageLogo.setBounds(x2+514,height-270, 256, 256);
		lblImagebBackground.setBounds(x2,height-270 , 512, 250);
		
		
		if(radios.size()>0) radios.get(radios.size()-1).setSelected(true);
		chBorder.setSelected(false);
		chResize.setSelected(true);
		chRoundedCorner.setSelected(true);

		chBorderBack.setSelected(false);
		chResizeBack.setSelected(true);
		
		
//		lblUri.setOpaque(true);
//		lblUri.setBackground(BACKGROUND_LABELS);

		labeltitre.setOpaque(true);
		labeltitre.setForeground(Color.BLUE);
		labeltitre.setBackground(Color.WHITE);
		labeltitre.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitre.setVerticalAlignment(SwingConstants.CENTER);
		labeltitre.setFont(new Font("Serif", Font.PLAIN, 36));

		labeltitre.setForeground(Color.BLUE);
		labeltitre.setBackground(Color.WHITE);
		
		lblUri.setForeground(FOREGROUND_LABELS);
		labelBackground.setForeground(FOREGROUND_LABELS);
		labelDestination.setForeground(FOREGROUND_LABELS);
		labelLink.setForeground(FOREGROUND_LABELS);
		labelLogo.setForeground(FOREGROUND_LABELS);
		labelColumn.setOpaque(true);
		labelColumn.setBackground(FOREGROUND_LABELS);
		

		lblUri.setOpaque(true);
		lblUri.setBackground(Color.white);
		lblUri.setForeground(Color.black);
				
		bExtraire.addActionListener(this);
		bCancel.addActionListener(this);
		bDestination.addActionListener(this);
		bEditCategorie.addActionListener(this);
		bColor.addActionListener(this);
		bColor2.addActionListener(this);
		
		btRDescription.addActionListener(this);
		btRKeyword.addActionListener(this);
		btRPackage.addActionListener(this);
		btRPrivacyLaw.addActionListener(this);
		btRTitle.addActionListener(this);
		btRLicence.addActionListener(this);
		btRPub1.addActionListener(this);
		btRPub2.addActionListener(this);
		
		btRLicence.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRLicence.getText()), null);
			  }
		});
		
		btRPub1.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRPub1.getText()), null);
			  }
		});
		
		btRPub2.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRPub2.getText()), null);
			  }
		});
		
		btRDescription.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRDescription.getText()), null);
			  }
		});

		btRKeyword.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRKeyword.getText()), null);
			  }
		});
		
		btRPackage.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRPackage.getText()), null);
			  }
		});
		
		btRPrivacyLaw.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRPrivacyLaw.getText()), null);
			  }
		});
		
		btRTitle.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  Clipboard clipboard = getSystemClipboard();
			        clipboard.setContents(new StringSelection(btRTitle.getText()), null);
			  }
		});
		
		
		contain.add(bColor);
		contain.add(bColor2);
		contain.add(lblImageLogo);
		contain.add(lblImagebBackground);
		contain.add(labeltitre);
		contain.add(chBorder);
		contain.add(chRoundedCorner);
		contain.add(chResize);
		contain.add(chBorderBack);
		contain.add(chRoundedCornerBack);
		contain.add(chResizeBack);
		contain.add(labelColumn);
		contain.add(labelLink);
		contain.add(txtLinkWikiHow);
		contain.add(labelDestination);
		contain.add(bDestination);
		contain.add(bEditCategorie);
		contain.add(lblUri);
		contain.add(labelLogo);
		contain.add(txtLinkLogo);
		contain.add(labelBackground);
		contain.add(txtLinkBackground);
		contain.add(bExtraire);
		contain.add(bCancel);

		contain.add(btRDescription);
		contain.add(btRKeyword);
		contain.add(btRPackage);
		contain.add(btRPrivacyLaw);
		contain.add(btRTitle);
		contain.add(btRLicence);
		contain.add(btRPub1);
		contain.add(btRPub2);
		
		contain.add(labelRDescription);
		contain.add(labelRKeyword);
		contain.add(labelRPackage);
		contain.add(labelRPrivacyLaw);
		contain.add(labelRTitle);
		contain.add(labelRLicence);
		contain.add(labelRPub1);
		contain.add(labelRPub2);
		
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setSize(widthWindows, height);
		f.setLayout(null);
		f.setResizable(true);
		f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
        return systemClipboard;
    }
	
	private void traiterData(DataUser data) {
		List<Articles> list = TraiterData.getDataFromUrl(data.getLinkWikiHow());
		final List<Articles> articles = TraiterData.createHtmlFileFromList(list,articleName);
		int x1 = widthData + 10;
		int x2 = widthData + 80;
		for(int index = 0;index <articles.size();index++){
			final int ct = index;
			btTitres.add(new JButton(index+1+". "+articles.get(index).getTitle()));
			btTitres.get(index).setBounds(x2, y2, 400, 30);
			btTitres.get(index).addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
					  Clipboard clipboard = getSystemClipboard();
				        clipboard.setContents(new StringSelection(ct+1+". "+articles.get(ct).getTitle()), null);
				  }
			});
			contain.add(btTitres.get(index));
			contain.validate();contain.repaint();
			btTxt.add(new JButton("txt"));
			btTxt.get(index).setBounds(x1, y2, 60, 30);
			btTxt.get(index).addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
					  Clipboard clipboard = getSystemClipboard();
				        clipboard.setContents(new StringSelection(articles.get(ct).getTxt()), null);
				  }
			});
			contain.add(btTxt.get(index));
			contain.validate();contain.repaint();
			y2=y2+50;
		}
	}

	private BufferedImage traiterImage(String imagePath, String src, boolean isLogo,boolean resize,boolean rounded,boolean border) {
		BufferedImage image = null;
		BufferedImage image2 = null;
		try {	
            final URL url = new URL(src);
            final HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
            final BufferedImage imageOriginal = ImageIO.read(connection.getInputStream());
//            Image imageOriginal = ImageIO.read(url.openStream());
            if(resize){
            	image = TraiterImage.resizeImage(TraiterImage.toBufferedImage(imageOriginal), isLogo,false);
            	image2 = TraiterImage.resizeImage(TraiterImage.toBufferedImage(imageOriginal), isLogo,true);
            }
            if(border){
            	image = TraiterImage.makeBorder(image,  isLogo ? color1 : color2);
            	image2 = TraiterImage.makeBorder(image2,  isLogo ? color1 : color2);
            }
            if(rounded){
            	image = TraiterImage.makeRoundedCorner(image, 200);
            	image2 = TraiterImage.makeRoundedCorner(image2, 200);
            }
            
        	if(isLogo)
        	lblImageLogo.setIcon(new ImageIcon(image2));
        	else
        	lblImagebBackground.setIcon(new ImageIcon(image2));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Error de creation du photo");
        }
        return image;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<categories.length;i++){
			if (e.getActionCommand().equals(categories[i])) {
				selectedCategorie = categories[i];
			}
		}

		if (e.getActionCommand().equals("Categories")) {
			String oldCat = TraiterData.getPropoerties().get(TraiterData.CATEGORIES);
			String cats = JOptionPane.showInputDialog("Categorie", "");
			TraiterData.changeValueOfProperties(TraiterData.CATEGORIES, oldCat+","+cats);
			JRadioButton rad = new JRadioButton(cats);
			rad.setBounds(xStartRadio, yStartRadio, 100, 40);
			rad.setForeground(FOREGROUND_LABELS);
			rad.setBackground(Color.DARK_GRAY);
			radios.add(rad);
			contain.add(radios.get(radios.size()-1));
			group.add(radios.get(radios.size()-1));
			radios.get(radios.size()-1).addActionListener(this);
			if(yStartRadio>650){
				maxAccesed = true;
				yStartRadio = y;
				xStartRadio += 150;
			}
			contain.validate();contain.repaint();
			}

		if (e.getActionCommand().equals("Cancel")) {
			f.setVisible(false);
		}
		if (e.getActionCommand().equals("Extraire")) {
			
			articleName = lblUri.getText().trim()+"/"+selectedCategorie+"/"+txtLinkWikiHow.getText().substring(24).replace("-", " ").trim();
			new File(articleName).mkdirs();
		
			DataUser data = new DataUser();
			data.setLinkWikiHow(txtLinkWikiHow.getText());
			data.setFolderDestination(lblUri.getText());
			data.setLinkBackground(txtLinkBackground.getText());
			data.setLinkLogo(txtLinkLogo.getText());
			traiterData(data);

			//LOGO
			File outputfile = new File(articleName+"\\logo.png");
			if(txtLinkLogo.getText().length()>0){
				BufferedImage image = traiterImage(articleName+"\\logo.png", txtLinkLogo.getText(),true,chResize.isSelected(),chRoundedCorner.isSelected(),chBorder.isSelected());
				try {
					ImageIO.write(image, "png", outputfile);
				} catch (Exception e1) {
					
				}
			}
			
			//BACKGROUND
			if(txtLinkLogo.getText().length()>0){
				BufferedImage image2 = traiterImage(articleName+"\\logo.png",txtLinkBackground.getText(),false,chResizeBack.isSelected(),chRoundedCornerBack.isSelected(),chBorderBack.isSelected());
				File outputfile2 = new File(articleName+"\\back.png");
				try {
					ImageIO.write(image2, "png", outputfile2);
				} catch (Exception e1) {}
			}
			
			//URI/
			if (isUriChange) {
				TraiterData.changeValueOfProperties(TraiterData.URI, lblUri.getText());
			}
			
			JOptionPane.showMessageDialog(null, "DONE");
		}
		if(e.getActionCommand().equals("Parcourir ...")) {
			JFileChooser f = new JFileChooser();
	        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
	        f.showSaveDialog(null);
	        if(f!=null && f.getSelectedFile()!=null){
	        	lblUri.setText(f.getSelectedFile().getAbsolutePath());
	        	isUriChange = true;	        	
	        }
		}

		if(e.getSource().equals(bColor)) {
			Color initialcolor=Color.RED;    
			color1=JColorChooser.showDialog(null,"Select a color",initialcolor);    
		}
		if(e.getSource().equals(bColor2)) {
			Color initialcolor=Color.RED;    
			color2=JColorChooser.showDialog(null,"Select a color",initialcolor);    
		}
	}
}
