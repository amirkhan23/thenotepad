package thenotepad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NotepadController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	TextArea textArea;
	@FXML
	BorderPane bp;

	private FileChooser fileChooser = new FileChooser();
	private File file;

	public void newFile(ActionEvent event) {
		textArea.clear();
		Stage stage = (Stage) textArea.getScene().getWindow();
		stage.setTitle("New - Notepad");
		file = null;
	}

	public void openFile(ActionEvent event) {

		file = fileChooser.showOpenDialog(null);
		if (file != null) {
			Stage stage = (Stage) textArea.getScene().getWindow();
			stage.setTitle(file.getName() + " - Notepad");

			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(file));
				textArea.clear();
				while ((sCurrentLine = br.readLine()) != null) {
					textArea.appendText(sCurrentLine + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFile(ActionEvent e) {

		String content = textArea.getText();
		if (file != null) {
			try {
				// if file does not exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			// open a file dialog box
			file = fileChooser.showSaveDialog(null);
			if (file != null) {
				Stage stage = (Stage) textArea.getScene().getWindow();
				stage.setTitle(file.getName() + " - Notepad");

				try {
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	public void saveasFile(ActionEvent event) {
		file = fileChooser.showSaveDialog(null);

		String content = textArea.getText();
		if (file != null) {
			Stage stage = (Stage) textArea.getScene().getWindow();
			stage.setTitle(file.getName() + " - Notepad");
			try {
				// if file does not exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close(ActionEvent event) {
		Stage stage = (Stage) bp.getScene().getWindow();
		stage.close();
	}
}
