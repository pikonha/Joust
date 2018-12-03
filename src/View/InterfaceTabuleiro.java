/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.AtorJoust;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import rede.Lance;

/**
 *
 * @author Lucas
 */
public class InterfaceTabuleiro extends javax.swing.JFrame {
    

    public InterfaceTabuleiro(AtorJoust joust) {
        initComponents();
        
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
                break;
            }
        }
                    
        bNovojogo.addActionListener(new NovoJogoListener(joust));
        bConectar.addActionListener(new ConexaoListener(joust));
        bDesconectar.addActionListener(new DesconexaoListener(joust));
        bEncerrar.addActionListener(new EncerrarListener(joust));
        
        for (Component component : jPanel3.getComponents()) {
            if (component.getClass().equals(JButton.class))
                ((JButton)component).addActionListener(new ClickButtonListener(joust));
        }
    }   

    
    public class ClickButtonListener implements ActionListener {
        private AtorJoust joust;
        
        public ClickButtonListener(AtorJoust joust) {
            this.joust = joust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getName();
        
            int linha = Integer.parseInt(name.substring(1, 2));
            int coluna = Integer.parseInt(name.substring(2, 3));

            joust.novoLance(linha, coluna);
        }
    }
    
    public class ConexaoListener implements ActionListener {
        private AtorJoust joust;
        
        public ConexaoListener(AtorJoust joust) {
            this.joust = joust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            joust.conectar();
        }
    }
    
    public class DesconexaoListener implements ActionListener {
        private AtorJoust joust;
        
        public DesconexaoListener(AtorJoust joust) {
            this.joust = joust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            joust.desconectar();
        }
    }
    
    public class NovoJogoListener implements ActionListener {
        private AtorJoust joust;
        
        public NovoJogoListener(AtorJoust joust) {
            this.joust = joust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            joust.iniciarPartida();
        }
     }
    
    public class EncerrarListener implements ActionListener {
        private AtorJoust joust;
        
        public EncerrarListener(AtorJoust joust) {
            this.joust = joust;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            joust.encerrar();
        }
    }
    
	public void setStatus(String status) {
		tStatus.setText(status);		
	}
    
	public void setNomeJogador1(String idJogador) {
		tj1.setText(idJogador);		
	}
	
	public void setNomeJogador2(String idJogador) {
		tj2.setText(idJogador);		
	}

	public void setPosicaoJogador(int corJogador, int linha, int coluna) {
		JButton posicao = getButton(linha, coluna);
		
		try {
			Image img = ImageIO.read(this.getClass().getResource(corJogador == 1 ? "bcavalo.png" : "pcavalo.png"));
			posicao.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


    public JButton getButton(int linha, int coluna) {
         for (Component component : jPanel3.getComponents()) {
            if (component.getClass().equals(JButton.class)) {
                if (Integer.parseInt(component.getName().substring(1, 2)) == linha
                        && Integer.parseInt(component.getName().substring(2, 3)) == coluna)
                    return (JButton)component;
            }
        }
         
        return null;
    }
    
    public void desativaPosicao(int linha, int coluna) {
    	JButton posicao = getButton(linha, coluna);
    	posicao.setIcon(null);
    	posicao.setVisible(false);
    }
    
	public String getStatus() {
		return tStatus.getText();
	}  

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenu1 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        b00 = new javax.swing.JButton();
        b03 = new javax.swing.JButton();
        b04 = new javax.swing.JButton();
        b05 = new javax.swing.JButton();
        b06 = new javax.swing.JButton();
        b07 = new javax.swing.JButton();
        b01 = new javax.swing.JButton();
        b02 = new javax.swing.JButton();
        b67 = new javax.swing.JButton();
        b52 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        b43 = new javax.swing.JButton();
        b33 = new javax.swing.JButton();
        b73 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b53 = new javax.swing.JButton();
        b63 = new javax.swing.JButton();
        b34 = new javax.swing.JButton();
        b74 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b44 = new javax.swing.JButton();
        b54 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b64 = new javax.swing.JButton();
        b20 = new javax.swing.JButton();
        b30 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b40 = new javax.swing.JButton();
        b55 = new javax.swing.JButton();
        b50 = new javax.swing.JButton();
        b35 = new javax.swing.JButton();
        b60 = new javax.swing.JButton();
        b65 = new javax.swing.JButton();
        b70 = new javax.swing.JButton();
        b75 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b45 = new javax.swing.JButton();
        b25 = new javax.swing.JButton();
        b56 = new javax.swing.JButton();
        b36 = new javax.swing.JButton();
        b66 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b76 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b31 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        b51 = new javax.swing.JButton();
        b26 = new javax.swing.JButton();
        b46 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b47 = new javax.swing.JButton();
        b61 = new javax.swing.JButton();
        b77 = new javax.swing.JButton();
        b71 = new javax.swing.JButton();
        b27 = new javax.swing.JButton();
        b62 = new javax.swing.JButton();
        b57 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b37 = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        b32 = new javax.swing.JButton();
        b72 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        bConectar = new javax.swing.JButton();
        bDesconectar = new javax.swing.JButton();
        bNovojogo = new javax.swing.JButton();
        bEncerrar = new javax.swing.JButton();
        tStatus = new javax.swing.JTextField();
        tj1 = new javax.swing.JTextField();
        tj2 = new javax.swing.JTextField();

        b00.setName("b00");
        b01.setName("b01");
        b02.setName("b02");
        b03.setName("b03");
        b04.setName("b04");
        b05.setName("b05");
        b06.setName("b06");
        b07.setName("b07");
        
        b10.setName("b10");
        b11.setName("b11");
        b12.setName("b12");
        b13.setName("b13");
        b14.setName("b14");
        b15.setName("b15");
        b16.setName("b16");
        b17.setName("b17");
        
        b20.setName("b20");
        b21.setName("b21");
        b22.setName("b22");
        b23.setName("b23");
        b24.setName("b24");
        b25.setName("b25");
        b26.setName("b26");
        b27.setName("b27");
        
        b30.setName("b30");
        b31.setName("b31");
        b32.setName("b32");
        b33.setName("b33");
        b34.setName("b34");
        b35.setName("b35");
        b36.setName("b36");
        b37.setName("b37");
        
        b40.setName("b40");
        b41.setName("b41");
        b42.setName("b42");
        b43.setName("b43");
        b44.setName("b44");
        b45.setName("b45");
        b46.setName("b46");
        b47.setName("b47");
        
        b50.setName("b50");
        b51.setName("b51");
        b52.setName("b52");
        b53.setName("b53");
        b54.setName("b54");
        b55.setName("b55");
        b56.setName("b56");
        b57.setName("b57");
        
        b60.setName("b60");
        b61.setName("b61");
        b62.setName("b62");
        b63.setName("b63");
        b64.setName("b64");
        b65.setName("b65");
        b66.setName("b66");
        b67.setName("b67");
        
        b70.setName("b70");
        b71.setName("b71");
        b72.setName("b72");
        b73.setName("b73");
        b74.setName("b74");
        b75.setName("b75");
        b76.setName("b76");
        b77.setName("b77"); 
        
        jMenu1.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu Joust"));
        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Joust");
        setBackground(new java.awt.Color(255, 102, 102));
        setResizable(false);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        b00.setMaximumSize(new java.awt.Dimension(45, 45));
        b00.setMinimumSize(new java.awt.Dimension(45, 45));
        b00.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 0);
        jPanel3.add(b00, gridBagConstraints);

        b03.setBackground(new java.awt.Color(0, 0, 0));
        b03.setMaximumSize(new java.awt.Dimension(45, 45));
        b03.setMinimumSize(new java.awt.Dimension(45, 45));
        b03.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b03, gridBagConstraints);

        b04.setMaximumSize(new java.awt.Dimension(45, 45));
        b04.setMinimumSize(new java.awt.Dimension(45, 45));
        b04.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b04, gridBagConstraints);

        b05.setBackground(new java.awt.Color(0, 0, 0));
        b05.setMaximumSize(new java.awt.Dimension(45, 45));
        b05.setMinimumSize(new java.awt.Dimension(45, 45));
        b05.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b05, gridBagConstraints);

        b06.setMaximumSize(new java.awt.Dimension(45, 45));
        b06.setMinimumSize(new java.awt.Dimension(45, 45));
        b06.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b06, gridBagConstraints);

        b07.setBackground(new java.awt.Color(0, 0, 0));
        b07.setMaximumSize(new java.awt.Dimension(45, 45));
        b07.setMinimumSize(new java.awt.Dimension(45, 45));
        b07.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 6);
        jPanel3.add(b07, gridBagConstraints);

        b01.setBackground(new java.awt.Color(0, 0, 0));
        b01.setMaximumSize(new java.awt.Dimension(45, 45));
        b01.setMinimumSize(new java.awt.Dimension(45, 45));
        b01.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b01, gridBagConstraints);

        b02.setMaximumSize(new java.awt.Dimension(45, 45));
        b02.setMinimumSize(new java.awt.Dimension(45, 45));
        b02.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 0);
        jPanel3.add(b02, gridBagConstraints);

        b67.setBackground(new java.awt.Color(0, 0, 0));
        b67.setMaximumSize(new java.awt.Dimension(45, 45));
        b67.setMinimumSize(new java.awt.Dimension(45, 45));
        b67.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b67, gridBagConstraints);

        b52.setBackground(new java.awt.Color(0, 0, 0));
        b52.setMaximumSize(new java.awt.Dimension(45, 45));
        b52.setMinimumSize(new java.awt.Dimension(45, 45));
        b52.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b52, gridBagConstraints);

        b23.setBackground(new java.awt.Color(0, 0, 0));
        b23.setMaximumSize(new java.awt.Dimension(45, 45));
        b23.setMinimumSize(new java.awt.Dimension(45, 45));
        b23.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b23, gridBagConstraints);

        b43.setBackground(new java.awt.Color(0, 0, 0));
        b43.setMaximumSize(new java.awt.Dimension(45, 45));
        b43.setMinimumSize(new java.awt.Dimension(45, 45));
        b43.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b43, gridBagConstraints);

        b33.setMaximumSize(new java.awt.Dimension(45, 45));
        b33.setMinimumSize(new java.awt.Dimension(45, 45));
        b33.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b33, gridBagConstraints);

        b73.setMaximumSize(new java.awt.Dimension(45, 45));
        b73.setMinimumSize(new java.awt.Dimension(45, 45));
        b73.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b73, gridBagConstraints);

        b13.setMaximumSize(new java.awt.Dimension(45, 45));
        b13.setMinimumSize(new java.awt.Dimension(45, 45));
        b13.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b13, gridBagConstraints);

        b53.setMaximumSize(new java.awt.Dimension(45, 45));
        b53.setMinimumSize(new java.awt.Dimension(45, 45));
        b53.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b53, gridBagConstraints);

        b63.setBackground(new java.awt.Color(0, 0, 0));
        b63.setMaximumSize(new java.awt.Dimension(45, 45));
        b63.setMinimumSize(new java.awt.Dimension(45, 45));
        b63.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b63, gridBagConstraints);

        b34.setBackground(new java.awt.Color(0, 0, 0));
        b34.setMaximumSize(new java.awt.Dimension(45, 45));
        b34.setMinimumSize(new java.awt.Dimension(45, 45));
        b34.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b34, gridBagConstraints);

        b74.setBackground(new java.awt.Color(0, 0, 0));
        b74.setMaximumSize(new java.awt.Dimension(45, 45));
        b74.setMinimumSize(new java.awt.Dimension(45, 45));
        b74.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b74, gridBagConstraints);

        b14.setBackground(new java.awt.Color(0, 0, 0));
        b14.setMaximumSize(new java.awt.Dimension(45, 45));
        b14.setMinimumSize(new java.awt.Dimension(45, 45));
        b14.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b14, gridBagConstraints);

        b44.setMaximumSize(new java.awt.Dimension(45, 45));
        b44.setMinimumSize(new java.awt.Dimension(45, 45));
        b44.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b44, gridBagConstraints);

        b54.setBackground(new java.awt.Color(0, 0, 0));
        b54.setMaximumSize(new java.awt.Dimension(45, 45));
        b54.setMinimumSize(new java.awt.Dimension(45, 45));
        b54.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b54, gridBagConstraints);

        b10.setBackground(new java.awt.Color(0, 0, 0));
        b10.setMaximumSize(new java.awt.Dimension(45, 45));
        b10.setMinimumSize(new java.awt.Dimension(45, 45));
        b10.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b10, gridBagConstraints);

        b64.setMaximumSize(new java.awt.Dimension(45, 45));
        b64.setMinimumSize(new java.awt.Dimension(45, 45));
        b64.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b64, gridBagConstraints);

        b20.setMaximumSize(new java.awt.Dimension(45, 45));
        b20.setMinimumSize(new java.awt.Dimension(45, 45));
        b20.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b20, gridBagConstraints);

        b30.setBackground(new java.awt.Color(0, 0, 0));
        b30.setMaximumSize(new java.awt.Dimension(45, 45));
        b30.setMinimumSize(new java.awt.Dimension(45, 45));
        b30.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b30, gridBagConstraints);

        b24.setMaximumSize(new java.awt.Dimension(45, 45));
        b24.setMinimumSize(new java.awt.Dimension(45, 45));
        b24.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b24, gridBagConstraints);

        b40.setMaximumSize(new java.awt.Dimension(45, 45));
        b40.setMinimumSize(new java.awt.Dimension(45, 45));
        b40.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b40, gridBagConstraints);

        b55.setMaximumSize(new java.awt.Dimension(45, 45));
        b55.setMinimumSize(new java.awt.Dimension(45, 45));
        b55.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b55, gridBagConstraints);

        b50.setBackground(new java.awt.Color(0, 0, 0));
        b50.setMaximumSize(new java.awt.Dimension(45, 45));
        b50.setMinimumSize(new java.awt.Dimension(45, 45));
        b50.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b50, gridBagConstraints);

        b35.setMaximumSize(new java.awt.Dimension(45, 45));
        b35.setMinimumSize(new java.awt.Dimension(45, 45));
        b35.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b35, gridBagConstraints);

        b60.setMaximumSize(new java.awt.Dimension(45, 45));
        b60.setMinimumSize(new java.awt.Dimension(45, 45));
        b60.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        jPanel3.add(b60, gridBagConstraints);

        b65.setBackground(new java.awt.Color(0, 0, 0));
        b65.setMaximumSize(new java.awt.Dimension(45, 45));
        b65.setMinimumSize(new java.awt.Dimension(45, 45));
        b65.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b65, gridBagConstraints);

        b70.setBackground(new java.awt.Color(0, 0, 0));
        b70.setMaximumSize(new java.awt.Dimension(45, 45));
        b70.setMinimumSize(new java.awt.Dimension(45, 45));
        b70.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 4, 0);
        jPanel3.add(b70, gridBagConstraints);

        b75.setMaximumSize(new java.awt.Dimension(45, 45));
        b75.setMinimumSize(new java.awt.Dimension(45, 45));
        b75.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b75, gridBagConstraints);

        b15.setMaximumSize(new java.awt.Dimension(45, 45));
        b15.setMinimumSize(new java.awt.Dimension(45, 45));
        b15.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b15, gridBagConstraints);

        b45.setBackground(new java.awt.Color(0, 0, 0));
        b45.setMaximumSize(new java.awt.Dimension(45, 45));
        b45.setMinimumSize(new java.awt.Dimension(45, 45));
        b45.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b45, gridBagConstraints);

        b25.setBackground(new java.awt.Color(0, 0, 0));
        b25.setMaximumSize(new java.awt.Dimension(45, 45));
        b25.setMinimumSize(new java.awt.Dimension(45, 45));
        b25.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b25, gridBagConstraints);

        b56.setBackground(new java.awt.Color(0, 0, 0));
        b56.setMaximumSize(new java.awt.Dimension(45, 45));
        b56.setMinimumSize(new java.awt.Dimension(45, 45));
        b56.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b56, gridBagConstraints);

        b36.setBackground(new java.awt.Color(0, 0, 0));
        b36.setMaximumSize(new java.awt.Dimension(45, 45));
        b36.setMinimumSize(new java.awt.Dimension(45, 45));
        b36.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b36, gridBagConstraints);

        b66.setMaximumSize(new java.awt.Dimension(45, 45));
        b66.setMinimumSize(new java.awt.Dimension(45, 45));
        b66.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b66, gridBagConstraints);

        b11.setMaximumSize(new java.awt.Dimension(45, 45));
        b11.setMinimumSize(new java.awt.Dimension(45, 45));
        b11.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b11, gridBagConstraints);

        b76.setBackground(new java.awt.Color(0, 0, 0));
        b76.setMaximumSize(new java.awt.Dimension(45, 45));
        b76.setMinimumSize(new java.awt.Dimension(45, 45));
        b76.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b76, gridBagConstraints);

        b21.setBackground(new java.awt.Color(0, 0, 0));
        b21.setMaximumSize(new java.awt.Dimension(45, 45));
        b21.setMinimumSize(new java.awt.Dimension(45, 45));
        b21.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b21, gridBagConstraints);

        b31.setMaximumSize(new java.awt.Dimension(45, 45));
        b31.setMinimumSize(new java.awt.Dimension(45, 45));
        b31.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b31, gridBagConstraints);

        b41.setBackground(new java.awt.Color(0, 0, 0));
        b41.setMaximumSize(new java.awt.Dimension(45, 45));
        b41.setMinimumSize(new java.awt.Dimension(45, 45));
        b41.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b41, gridBagConstraints);

        b51.setMaximumSize(new java.awt.Dimension(45, 45));
        b51.setMinimumSize(new java.awt.Dimension(45, 45));
        b51.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b51, gridBagConstraints);

        b26.setMaximumSize(new java.awt.Dimension(45, 45));
        b26.setMinimumSize(new java.awt.Dimension(45, 45));
        b26.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b26, gridBagConstraints);

        b46.setMaximumSize(new java.awt.Dimension(45, 45));
        b46.setMinimumSize(new java.awt.Dimension(45, 45));
        b46.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b46, gridBagConstraints);

        b16.setBackground(new java.awt.Color(0, 0, 0));
        b16.setMaximumSize(new java.awt.Dimension(45, 45));
        b16.setMinimumSize(new java.awt.Dimension(45, 45));
        b16.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b16, gridBagConstraints);

        b47.setBackground(new java.awt.Color(0, 0, 0));
        b47.setMaximumSize(new java.awt.Dimension(45, 45));
        b47.setMinimumSize(new java.awt.Dimension(45, 45));
        b47.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b47, gridBagConstraints);

        b61.setBackground(new java.awt.Color(0, 0, 0));
        b61.setMaximumSize(new java.awt.Dimension(45, 45));
        b61.setMinimumSize(new java.awt.Dimension(45, 45));
        b61.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b61, gridBagConstraints);

        b77.setMaximumSize(new java.awt.Dimension(45, 45));
        b77.setMinimumSize(new java.awt.Dimension(45, 45));
        b77.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 6);
        jPanel3.add(b77, gridBagConstraints);

        b71.setMaximumSize(new java.awt.Dimension(45, 45));
        b71.setMinimumSize(new java.awt.Dimension(45, 45));
        b71.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b71, gridBagConstraints);

        b27.setBackground(new java.awt.Color(0, 0, 0));
        b27.setMaximumSize(new java.awt.Dimension(45, 45));
        b27.setMinimumSize(new java.awt.Dimension(45, 45));
        b27.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b27, gridBagConstraints);

        b62.setMaximumSize(new java.awt.Dimension(45, 45));
        b62.setMinimumSize(new java.awt.Dimension(45, 45));
        b62.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b62, gridBagConstraints);

        b57.setMaximumSize(new java.awt.Dimension(45, 45));
        b57.setMinimumSize(new java.awt.Dimension(45, 45));
        b57.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b57, gridBagConstraints);

        b17.setMaximumSize(new java.awt.Dimension(45, 45));
        b17.setMinimumSize(new java.awt.Dimension(45, 45));
        b17.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b17, gridBagConstraints);

        b22.setMaximumSize(new java.awt.Dimension(45, 45));
        b22.setMinimumSize(new java.awt.Dimension(45, 45));
        b22.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b22, gridBagConstraints);

        b37.setMaximumSize(new java.awt.Dimension(45, 45));
        b37.setMinimumSize(new java.awt.Dimension(45, 45));
        b37.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 6);
        jPanel3.add(b37, gridBagConstraints);

        b42.setMaximumSize(new java.awt.Dimension(45, 45));
        b42.setMinimumSize(new java.awt.Dimension(45, 45));
        b42.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b42, gridBagConstraints);

        b32.setBackground(new java.awt.Color(0, 0, 0));
        b32.setMaximumSize(new java.awt.Dimension(45, 45));
        b32.setMinimumSize(new java.awt.Dimension(45, 45));
        b32.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b32, gridBagConstraints);

        b72.setBackground(new java.awt.Color(0, 0, 0));
        b72.setMaximumSize(new java.awt.Dimension(45, 45));
        b72.setMinimumSize(new java.awt.Dimension(45, 45));
        b72.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 4, 0);
        jPanel3.add(b72, gridBagConstraints);

        b12.setBackground(new java.awt.Color(0, 0, 0));
        b12.setMaximumSize(new java.awt.Dimension(45, 45));
        b12.setMinimumSize(new java.awt.Dimension(45, 45));
        b12.setPreferredSize(new java.awt.Dimension(45, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel3.add(b12, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu Joust"));

        jLabel1.setText("Status");

        jLabel2.setText("Jogador 1");

        jLabel3.setText("Jogador 2");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/joustLogo.png"))); // NOI18N

        bConectar.setText("Conectar");

        bDesconectar.setText("Desconectar");

        bNovojogo.setText("Novo Jogo");

        bEncerrar.setText("Encerrar");

        tStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tStatus.setFocusable(false);

        tj1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tj1.setFocusable(false);

        tj2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tj2.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bEncerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bNovojogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bDesconectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bConectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tj2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tStatus)
                                    .addComponent(tj1))))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tj2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(bConectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDesconectar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bNovojogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEncerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
    private javax.swing.JButton b00;
    private javax.swing.JButton b01;
    private javax.swing.JButton b02;
    private javax.swing.JButton b03;
    private javax.swing.JButton b04;
    private javax.swing.JButton b05;
    private javax.swing.JButton b06;
    private javax.swing.JButton b07;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b30;
    private javax.swing.JButton b31;
    private javax.swing.JButton b32;
    private javax.swing.JButton b33;
    private javax.swing.JButton b34;
    private javax.swing.JButton b35;
    private javax.swing.JButton b36;
    private javax.swing.JButton b37;
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private javax.swing.JButton b42;
    private javax.swing.JButton b43;
    private javax.swing.JButton b44;
    private javax.swing.JButton b45;
    private javax.swing.JButton b46;
    private javax.swing.JButton b47;
    private javax.swing.JButton b50;
    private javax.swing.JButton b51;
    private javax.swing.JButton b52;
    private javax.swing.JButton b53;
    private javax.swing.JButton b54;
    private javax.swing.JButton b55;
    private javax.swing.JButton b56;
    private javax.swing.JButton b57;
    private javax.swing.JButton b60;
    private javax.swing.JButton b61;
    private javax.swing.JButton b62;
    private javax.swing.JButton b63;
    private javax.swing.JButton b64;
    private javax.swing.JButton b65;
    private javax.swing.JButton b66;
    private javax.swing.JButton b67;
    private javax.swing.JButton b70;
    private javax.swing.JButton b71;
    private javax.swing.JButton b72;
    private javax.swing.JButton b73;
    private javax.swing.JButton b74;
    private javax.swing.JButton b75;
    private javax.swing.JButton b76;
    private javax.swing.JButton b77;
    private javax.swing.JButton bConectar;
    private javax.swing.JButton bDesconectar;
    private javax.swing.JButton bEncerrar;
    private javax.swing.JButton bNovojogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tStatus;
    private javax.swing.JTextField tj1;
    private javax.swing.JTextField tj2;
              
}