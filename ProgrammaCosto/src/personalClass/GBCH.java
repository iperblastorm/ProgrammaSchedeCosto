package personalClass;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

public class GBCH extends GridBagConstraints {

	private static final long serialVersionUID = 1L;
	
	public GBCH(int gridx,int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
		
		this.gridheight = 1;
		this.gridwidth = 1;
		
		this.weightx = 0.0;
		this.weighty = 0.0;
		
		this.anchor = NORTHWEST;
		this.fill = NONE;
		
		this.insets = new Insets(0, 0, 0, 0);
				
	}
	
	public GBCH modifiyxy(int x, int y) {
		this.gridx = x;
		this.gridy = y;
		return this;
	}
	
	public 	GBCH modifygrid(int width, int height) {
		this.gridwidth = width;
		this.gridheight = height;
		return this;
	}
	
	public GBCH modifyweight(double weightx,double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	
	public GBCH modifyanchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	
	public GBCH modifyfill(int fill) {
		this.fill = fill;
		return this;
	}
	
	public GBCH modifyinsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	public void add(JPanel panel,Component component) {
		panel.add(component, this);
	}
	
	public GridBagConstraints get() {
		return this;
	}
	
}
