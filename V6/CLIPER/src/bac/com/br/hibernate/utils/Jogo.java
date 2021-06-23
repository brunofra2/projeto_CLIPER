package bac.com.br.hibernate.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Jogo extends JFrame {
	private String escolhau;
	private int escolham;
	private String convercao;
	private int total = 0;
	private double valorusuario = 0;
	private double valormaquina = 0;
	private double valorempate = 0;
	private String txttotal;
	private String txtusuario;
	private String txtmaquina;
	private String txtempate;

	public Jogo() {
		
		super("jogo jokempo");
		
		setSize(600,600);
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		JPanel painel1 = new JPanel(new BorderLayout());
		JPanel painel2 = new JPanel(new BorderLayout(50, 50));
		JPanel painel3 = new JPanel(new GridLayout(4, 2));
		JPanel painel5 = new JPanel(new BorderLayout(50, 50));
		JPanel painel6 = new JPanel(new BorderLayout());

		JLabel usuario = new JLabel("escolhas do usuario");
		JLabel resultado = new JLabel("resultado do jogo");
		resultado.setForeground(Color.green);
		JLabel qtd = new JLabel("quantide de rodadas");
		JLabel lpu = new JLabel("porcentagem do usuario");
		JLabel lpm = new JLabel("porcentagem do adversario");
		JLabel lpe = new JLabel("porcentagem de empate");

		JButton pedra = new JButton("pedra");
		pedra.setRolloverEnabled(true);
		pedra.setBackground(Color.green);
		JButton papel = new JButton("papel");
		papel.setRolloverEnabled(true);
		papel.setBackground(Color.green);
		JButton tesoura = new JButton("tesoura");
		tesoura.setRolloverEnabled(true);
		tesoura.setBackground(Color.green);
		final JButton jogar = new JButton("jogar");
		jogar.setRolloverEnabled(true);
		jogar.setBackground(Color.green);
		JButton sair = new JButton("sair");
		sair.setRolloverEnabled(true);
		sair.setBackground(Color.green);

		final JTextField aguardando = new JTextField();
		final JTextArea  resultado1 = new JTextArea();
		final JTextField pu = new JTextField();
		final JTextField pm = new JTextField();
		final JTextField pe = new JTextField();

		painel1.add(painel6,BorderLayout.SOUTH);
		painel5.add(painel1, BorderLayout.CENTER);
		painel5.add(painel3, BorderLayout.EAST);

		painel1.add(resultado,BorderLayout.NORTH);
		painel1.add(resultado1,BorderLayout.CENTER);


		painel6.add(usuario,BorderLayout.NORTH);
		painel6.add(painel2,BorderLayout.CENTER);

		painel2.add(pedra, BorderLayout.NORTH);
		painel2.add(papel, BorderLayout.WEST);
		painel2.add(tesoura, BorderLayout.SOUTH);
		painel2.add(jogar, BorderLayout.EAST);
		painel2.add(sair, BorderLayout.CENTER);

		painel3.add(qtd);
		painel3.add(aguardando);
		painel3.add(lpu);
		painel3.add(pu);
		painel3.add(lpm);
		painel3.add(pm);
		painel3.add(lpe);
		painel3.add(pe);
		
		painel1.setBackground(Color.gray);
		painel2.setBackground(Color.gray);
		painel3.setBackground(Color.gray);
		painel5.setBackground(Color.gray);
		painel6.setBackground(Color.gray);
		this.add(painel5);
		itexto();

		pedra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				escolhau = "pedra";
				JOptionPane.showMessageDialog(null, "voce escolheu pedra \n agora clique em jogar");
				jogar.setEnabled(true);
			}

		});
		papel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				escolhau = "papel";
				JOptionPane.showMessageDialog(null, "voce escolheu papel \n agora clique em jogar");
				jogar.setEnabled(true);
			}

		});
		tesoura.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				escolhau = "tesoura";
				JOptionPane.showMessageDialog(null, "voce escolheu tesoura \n agora clique em jogar");
				jogar.setEnabled(true);
			}

		});

		jogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random r = new Random();
				escolham = r.nextInt(3);
				total++;
				txttotal = String.valueOf(total);
				aguardando.setText(txttotal);
				switch (escolham) {
				case 0:
					convercao = "pedra";
					JOptionPane.showMessageDialog(null, "escohas realizadas");
					break;
				case 1:
					convercao = "papel";

					JOptionPane.showMessageDialog(null, "escohas realizadas");
					break;
				case 2:
					convercao = "tesoura";

					JOptionPane.showMessageDialog(null, "escohas realizadas");
					break;
				default:
					convercao = "";
					JOptionPane.showMessageDialog(null, "erro ocorrido");
					break;
				}

				if (convercao == escolhau) {
					resultado1.setText("empate");
					valorempate++;
					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);

					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);
				}

				if ((convercao == "pedra") && (escolhau == "tesoura")) {
					resultado1.setText("o adversario ganhou");
					valormaquina++;
					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);

				}
				if ((convercao == "tesoura") && (escolhau == "pedra")) {
					resultado1.setText("voc� ganhou");
					valorusuario++;
					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);

				}
				if ((convercao == "papel") && (escolhau == "pedra")) {
					resultado1.setText("o adversario ganhou");
					valormaquina++;
					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);
				}
				if ((convercao == "pedra") && (escolhau == "papel")) {
					resultado1.setText("voc� ganhou");
					valorusuario++;
					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);
				}
				if ((convercao == "tesoura") && (escolhau == "papel")) {
					resultado1.setText("o adversario ganhou");
					valormaquina++;

					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);
				}
				if ((convercao == "papel") && (escolhau == "tesoura")) {
					resultado1.setText("o adversario ganhou");
					valorusuario++;
					txtusuario = String.valueOf((valorusuario / total) * 100);
					pu.setText(txtusuario);
					System.out.println("usuario" + txtusuario);

					txtmaquina = String.valueOf((valormaquina / total) * 100);
					pm.setText(txtmaquina);
					System.out.println(txtmaquina);

					txtempate = String.valueOf((valorempate / total) * 100);
					pe.setText(txtempate);
					System.out.println("empate" + txtempate);
				}
				jogar.setEnabled(false);
			}

			private String getText() {
				return getText();
			}
		});
		sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});
		
		

	}
	private void itexto(){
		JOptionPane.showMessageDialog(this,"Instru��es \n Para jogar contra a maquina voc� deve clicar na op��o que deseja jogar EX: pedra, papel e tesoura. clique no bot�o jogar para a maquina fazer sua escolha. \n clique no bot�o sair para finalizar o programa");
	}
	public static void main(String[] args) {

		Jogo j = new Jogo();
		
	}

}
