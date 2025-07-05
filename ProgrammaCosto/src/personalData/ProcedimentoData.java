package personalData;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ProcedimentoData {
	private JLabel step;
	private JTextArea stepText;
	
	public ProcedimentoData(JLabel title, JTextArea procedure) {
		this.step = title;
		this.stepText = procedure;
	}
	
	public JLabel getTitleProcedure() {
		return step;
	}
	
	public void setTitleProcedure(JLabel title) {
		this.step = title;
	}
	
	public JTextArea getProcedure() {
		return stepText;
	}
	
	public void setProcedure(JTextArea procedure) {
		this.stepText = procedure;
	}
	
	
	

}
