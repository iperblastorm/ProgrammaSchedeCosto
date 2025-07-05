package interfaccia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import personalClass.GBCH;
import personalData.IngredientiData;
import personalData.ProcedimentoData;
import resourceData.Resource;

public class FinestraPrincipale extends JFrame {
	
	private static final long serialVersionUID = 1L;
	String titolo = "Inserimento Scheda Costo Ricette";
	
	
	
	public FinestraPrincipale() {
		this.setTitle(titolo);;
		this.setSize(700,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new pannelloAlto(),BorderLayout.NORTH);
		this.add(new PannelloDestro(),BorderLayout.CENTER);
		this.add(pannelloSinistroScroll(),BorderLayout.WEST);
		this.setVisible(true);
	}
	
	public Component pannelloSinistroScroll() {
		JScrollPane scrollPannelloSinistro = new JScrollPane(new PannelloSinistro());
		return scrollPannelloSinistro;
	}

}

class pannelloAlto extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public pannelloAlto() {
		this.setLayout(new GridBagLayout());
		this.setBorder(new LineBorder(Color.BLUE, 2));
		
		titolo();
	}
	
	private void titolo() {
		JLabel titolo = new JLabel("Ricetta: ");
		GBCH layoutTitolo = new GBCH(0, 0);
		layoutTitolo.modifyfill(GridBagConstraints.HORIZONTAL);
		layoutTitolo.add(this, titolo);
	}
}



class PannelloSinistro extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private List<IngredientiData> arrayRiga = new ArrayList<>();
	
	
	//PULSANTE NUOVA RIGA
	ImageIcon icona = new ImageIcon(Resource.immaginePlus());
	JButton inserisciNuovo = new JButton(icona);
	GBCH inserisciNuovoLayout = new GBCH(2,0);
	
	//PULSANTE ELIMINA RIGA
	ImageIcon iconaElimina = new ImageIcon(Resource.immagineMinus());
	JButton eliminaRiga = new JButton(iconaElimina);
	GBCH eliminaRigaLayout = new GBCH(0, 0);
	
	//RIEMPITIVO
	JLabel riempi = new JLabel();
	GBCH riempiLayout = new GBCH(0, 0);
	
	public PannelloSinistro() {
		this.setLayout(new GridBagLayout());
		this.setBorder(new LineBorder(Color.BLACK,2));
		
		
		this.ingredienti();
		this.inserisciRigheDinamico();
		inserisciNuovoLayout.add(this, inserisciNuovo);
		eliminaRigaLayout.add(this, eliminaRiga);
		riempiLayout.add(this, riempi);
	}
	
	private void ingredienti() {
		JLabel etichettaIngredienti = new JLabel("Ingredienti");
		GBCH etichettaILayout = new GBCH(0, 0);
		etichettaILayout.modifyanchor(GridBagConstraints.NORTHWEST);
		etichettaILayout.modifyinsets(5, 5, 5, 5);
		etichettaILayout.modifyfill(GridBagConstraints.NONE);
		etichettaILayout.add(this, etichettaIngredienti);
		
		JLabel etichettaQuantita = new JLabel("Quantità");
		GBCH etichettaQLayout = new GBCH(1, 0);
		etichettaQLayout.modifyanchor(GridBagConstraints.NORTH);
		etichettaQLayout.modifyinsets(5, 5, 5, 5);
		etichettaQLayout.modifyfill(GridBagConstraints.NONE);
		etichettaQLayout.add(this, etichettaQuantita);
		
		inserisciNuovo.setBorderPainted(false);
		inserisciNuovo.setContentAreaFilled(false);
		inserisciNuovo.addActionListener(this);
		
		eliminaRiga.setBorderPainted(false);
		eliminaRiga.setContentAreaFilled(false);
		eliminaRiga.addActionListener(this);

	}
	
	private void inserisciRigheDinamico() { 
		
		//COMBO BOX INGREDIENTE SELEZIONATO
		String[] ingredienti = {"alalalalalala","b","c","d"}; //DA INSERIRE ARRAY DEL DB
		JComboBox<String> comboIngredienti = new JComboBox<>(ingredienti);
		GBCH comboLayout = new GBCH(0,(arrayRiga.size())+1);
		comboLayout.modifyweight(1.0, 0.0);
		comboLayout.modifyinsets(5, 5, 5, 5);
		comboLayout.fill = GridBagConstraints.HORIZONTAL;
		comboLayout.add(this, comboIngredienti);
		
		//TEXT FIELD QUANTITA
		JTextField quantità = new JTextField(5);
		GBCH quantitaLayout = new GBCH(1,(arrayRiga.size())+1);
		quantitaLayout.modifyinsets(5, 5, 5, 5);
		quantitaLayout.modifyfill(GridBagConstraints.VERTICAL);
		quantitaLayout.add(this, quantità);
		
		inserisciNuovoLayout.modifiyxy(2, (arrayRiga.size())+1 );
		inserisciNuovoLayout.modifyanchor(GridBagConstraints.CENTER);
		
		eliminaRigaLayout.modifiyxy(3, (arrayRiga.size())+1);
		eliminaRigaLayout.modifyanchor(GridBagConstraints.CENTER);
		
		//RIEMPIMENTO SPAZIO VUOTO
		riempiLayout.modifiyxy(0, (arrayRiga.size()) + 2);
		riempiLayout.modifyfill(GridBagConstraints.VERTICAL);
		riempiLayout.modifyweight(1.0, 1.0);
		riempi.setBorder(new LineBorder(Color.YELLOW, 2));
		riempiLayout.modifygrid(2, 1);

		IngredientiData rigaAttuale = new IngredientiData(comboIngredienti, quantità);
		arrayRiga.add(rigaAttuale);
		
		this.revalidate();
		this.repaint();
		
	}
	
	private void eliminationLogic() {
		int contatore = 1;
						
		this.remove(riempi);
		this.remove(eliminaRiga);
		this.remove(inserisciNuovo);
		
		for(IngredientiData elemento : arrayRiga) {
			this.remove(elemento.getCombobox());
			this.remove(elemento.getTextfield());
		}
		
		arrayRiga.remove(arrayRiga.getLast());
		
		for(IngredientiData elemento : arrayRiga) {
			GBCH newComboLayout = new GBCH(0, contatore);
			newComboLayout.modifyweight(1.0, 0.0);
			newComboLayout.modifyinsets(5, 5, 5, 5);
			newComboLayout.fill = GridBagConstraints.HORIZONTAL;
			newComboLayout.modifiyxy(0, contatore);
			newComboLayout.add(this, elemento.getCombobox());
			
			GBCH newTextLayout = new GBCH(1, contatore);
			newTextLayout.modifyinsets(5, 5, 5, 5);
			newTextLayout.modifyfill(GridBagConstraints.VERTICAL);
			newTextLayout.modifiyxy(1, contatore);
			newTextLayout.add(this, elemento.getTextfield());
			
			contatore++;
		}
		
		this.inserisciNuovoLayout.modifiyxy(2, arrayRiga.size());
		this.inserisciNuovoLayout.add(this, inserisciNuovo);
		
		this.eliminaRigaLayout.modifiyxy(3, arrayRiga.size());
		this.eliminaRigaLayout.add(this, eliminaRiga);
		
		this.riempiLayout.modifiyxy(0, (arrayRiga.size()+1));
		this.riempiLayout.add(this, riempi);
		
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bottonePremuto = (JButton) e.getSource();
		
		if(bottonePremuto == inserisciNuovo) {
			
			this.remove(riempi);
			this.remove(eliminaRiga);
			this.remove(inserisciNuovo);
			
			inserisciRigheDinamico();
			
			riempiLayout.add(this, riempi);
			eliminaRigaLayout.add(this, eliminaRiga);
			inserisciNuovoLayout.add(this, inserisciNuovo);
			
		}
		if(bottonePremuto == eliminaRiga) {
			if(arrayRiga.size() < 2) {
				FinestraErrore errore = new FinestraErrore((JFrame) SwingUtilities.getWindowAncestor(this));
			}
			else {
				
				eliminationLogic();
				
			}
		}
	}
}


class FinestraErrore extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel messaggioErrore;
	JButton chiudiErrore;
	
	public FinestraErrore(JFrame finestra) {
		super();
		messaggioErrore = new JLabel ("Non puoi Rimuovere Ingredienti.");
		chiudiErrore = new JButton("CHIUDI");
		chiudiErrore.addActionListener(this);
		this.add(messaggioErrore,BorderLayout.CENTER);
		this.add(chiudiErrore,BorderLayout.SOUTH);
		this.pack();
		this.setModal(true);
		this.setLocationRelativeTo(finestra);
		this.setVisible(true);
		}
	//CHIUDE FINESTRA
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		}
	}



class PannelloDestro extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private List<ProcedimentoData> arrayProcedimento = new ArrayList<>();
	
	//PULSANTE INSERISCI PROCEDURA
	ImageIcon icona = new ImageIcon(Resource.immaginePlus());
	JButton inserisciNuovo = new JButton(icona);
	GBCH inserisciNuovoLayout = new GBCH(1,0);
	
	//PULSANTE ELIMINA PROCEDURA
	ImageIcon iconaElimina = new ImageIcon(Resource.immagineMinus());
	JButton eliminaRiga = new JButton(iconaElimina);
	GBCH eliminaRigaLayout = new GBCH(2, 0);
	
	//RIEMPITIVO
	JLabel riempi = new JLabel();
	
	
	public PannelloDestro() {
		this.setLayout(new GridBagLayout());
		this.setBorder(new LineBorder(Color.RED,2));
		title();
		procedureDinamiche();
		
		}
	
	private void title () {
		JLabel title = new JLabel("Procedimento");
		GBCH titleLayout = new GBCH(0,0);
		titleLayout.modifyanchor(GridBagConstraints.CENTER);
		titleLayout.modifyfill(GridBagConstraints.HORIZONTAL);
		titleLayout.add(this,title);
		
		inserisciNuovo.setBorderPainted(false);
		inserisciNuovo.setContentAreaFilled(false);
		inserisciNuovo.addActionListener(this);
		inserisciNuovoLayout.modifyanchor(GridBagConstraints.EAST);
		inserisciNuovoLayout.add(this, inserisciNuovo);
		
		eliminaRiga.setBorderPainted(false);
		eliminaRiga.setContentAreaFilled(false);
		eliminaRiga.addActionListener(this);
		eliminaRigaLayout.modifyanchor(GridBagConstraints.EAST);
		eliminaRigaLayout.add(this,eliminaRiga);
		
		
	}
	
	private int positionY(int array,int elemento) {
			return ((2*array)+elemento);
	}
	
	private void procedureDinamiche() {
		
		JLabel step = new JLabel("Passaggio" + (arrayProcedimento.size()+1) + ":" );
		GBCH stepLayout = new GBCH(0, positionY(arrayProcedimento.size(), 1));
		stepLayout.modifyfill(GridBagConstraints.HORIZONTAL);
		stepLayout.modifyinsets(5, 5, 5, 5);
		
		JTextArea stepText = new JTextArea("Scrivi il procedimento qui");
		stepText.setLineWrap(true);
		GBCH stepTextLayout = new GBCH(0,positionY(arrayProcedimento.size(), 2));
		stepTextLayout.modifygrid(3, 1);
		stepTextLayout.modifyfill(GridBagConstraints.HORIZONTAL);
		stepTextLayout.modifyinsets(0, 5, 0, 5);
		
		riempi.setBorder(new LineBorder(Color.BLUE));
		GBCH riempiLayout = new GBCH(0, positionY(arrayProcedimento.size(), 3));
		riempiLayout.modifyfill(GridBagConstraints.BOTH);
		riempiLayout.modifyweight(1.0, 1.0);
		
		stepLayout.add(this, step);
		stepTextLayout.add(this, stepText);
		riempiLayout.add(this, riempi);
		
		ProcedimentoData nuovoProcedimento = new ProcedimentoData(step, stepText);
		arrayProcedimento.add(nuovoProcedimento);
		
		this.revalidate();
		this.repaint();
	}
	
	private void clearPanel() {
		
		for(ProcedimentoData elemento : arrayProcedimento) {
			this.remove(elemento.getProcedure());
			this.remove(elemento.getTitleProcedure());
		}
		
		this.remove(riempi);
	}
	
	private void remakePanel() {
		int contatore = 0;
		for(ProcedimentoData elemento :arrayProcedimento) {
			
			JLabel step = elemento.getTitleProcedure();
			GBCH stepLayout = new GBCH(0, positionY(contatore, 1));
			stepLayout.modifyfill(GridBagConstraints.HORIZONTAL);
			stepLayout.modifyinsets(5, 5, 5, 5);
			stepLayout.add(this, step);
			
			JTextArea stepText = elemento.getProcedure();
			stepText.setLineWrap(true);
			GBCH stepTextLayout = new GBCH(0,positionY(contatore, 2));
			stepTextLayout.modifygrid(3, 1);
			stepTextLayout.modifyfill(GridBagConstraints.HORIZONTAL);
			stepTextLayout.modifyinsets(0, 5, 0, 5);
			stepTextLayout.add(this, stepText);
			
			contatore++;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton bottonePremuto = (JButton) e.getSource();
		
		if(bottonePremuto == inserisciNuovo) {
			
			clearPanel();
			remakePanel();
			procedureDinamiche();
		}
		else if (bottonePremuto == eliminaRiga) {
			
			arrayProcedimento.removeLast();
			
			for (ProcedimentoData elemento : arrayProcedimento) {
				this.remove(elemento.getProcedure());
				this.remove(elemento.getTitleProcedure());
			}
			
			this.revalidate();
			this.repaint();
			
		}
	}	
}



