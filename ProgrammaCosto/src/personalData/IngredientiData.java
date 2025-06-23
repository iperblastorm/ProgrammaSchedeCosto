package personalData;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class IngredientiData{
	private JComboBox<String> combobox ;
	private JTextField textfield;
	
	public IngredientiData(JComboBox<String> combo,JTextField text) {
		this.combobox = combo;
		this.textfield = text;
	}
	
	public JComboBox<String> getCombobox () {
		return combobox;
	}
	
	public JTextField getTextfield() {
		return textfield;
	}
	
	public void setCombobox (JComboBox<String> combo){
		this.combobox = combo;
	}
	
	public void setTextField (JTextField text) {
		this.textfield = text;
	}
}
