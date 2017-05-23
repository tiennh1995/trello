package edu.ttl.ui;

import java.io.File;
import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileFilter;
//import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author iSling
 */
public class FileChooser {

	private File f;
	private String fileName;
	public static final int FILE_OPEN = 0;
	public static final int FILE_SAVE = 1;
	private int choose;
	private JFileChooser chooser;

	public FileChooser(String title, int chooseType, final String fileTypes, final String Description) {
		fileChooser(title, chooseType, fileTypes, Description);
	}

	private int fileChooser(String title, int chooseType, final String fileTypes, final String Description) {
		choose = -1;
		chooser = new JFileChooser();
		//FileFilter fileFilter = new FileNameExtensionFilter(Description, "png", "jpg", "jpeg", "gif", "bmp");
		switch (chooseType) {
		case FILE_OPEN:
			chooser.setDialogTitle(title);
			choose = chooser.showOpenDialog(null);
			break;
		case FILE_SAVE:
			chooser.setDialogTitle(title);
			choose = chooser.showSaveDialog(null);
			break;
		}
		if (choose == JFileChooser.APPROVE_OPTION) {
			switch (chooseType) {
			case FILE_OPEN:
				f = chooser.getSelectedFile();
				// fileName = chooser.getSelectedFile().toString();
				fileName = chooser.getName(f);
				break;
			case FILE_SAVE:
				fileName = chooser.getSelectedFile().toString();
				break;
			}
		}
		return choose;
	}

	public File getFile() {
		return f;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isSuccess() {
		if (choose == JFileChooser.APPROVE_OPTION) {
			return true;
		}
		return false;
	}
}
