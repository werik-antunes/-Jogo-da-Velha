package jogo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JogoDaVelha extends JFrame implements ActionListener{
	
	private JButton[][] botoes;
    private boolean turnoX;
    private JLabel lblStatus;
    public JogoDaVelha() {

        setTitle("Jogo da Velha");

        setSize(300, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel painelTabuleiro = new JPanel(new GridLayout(3, 3));

        botoes = new JButton[3][3];

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                botoes[i][j] = new JButton();

                botoes[i][j].addActionListener(this);

                painelTabuleiro.add(botoes[i][j]);
            }
        }

        lblStatus = new JLabel("Vez do jogador X");

        add(lblStatus, BorderLayout.NORTH);

        add(painelTabuleiro, BorderLayout.CENTER);

        turnoX = true;
        setVisible(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        JButton botaoClicado = (JButton) e.getSource();

        if (botaoClicado.getText().isEmpty()) {

            if (turnoX) {

                botaoClicado.setText("X");

                lblStatus.setText("Vez do jogador O");

            } else {

                botaoClicado.setText("O");

                lblStatus.setText("Vez do jogador X");
            }

            turnoX = !turnoX;

            if (verificarVencedor()) {

                lblStatus.setText("Jogador " + (turnoX ? "O" : "X") + " venceu!");

                desabilitarBotoes();

            } else if (verificarEmpate()) {

                lblStatus.setText("Empate!");

            }
        }
    }

    private boolean verificarVencedor() {

        for (int i = 0; i < 3; i++) {

            if (!botoes[i][0].getText().isEmpty() &&

                    botoes[i][0].getText().equals(botoes[i][1].getText()) &&

                    botoes[i][0].getText().equals(botoes[i][2].getText())) {

                return true; // Verifica linhas
            }

            if (!botoes[0][i].getText().isEmpty() &&

                    botoes[0][i].getText().equals(botoes[1][i].getText()) &&

                    botoes[0][i].getText().equals(botoes[2][i].getText())) {

                return true; // Verifica colunas
            }
        }

        if (!botoes[0][0].getText().isEmpty() &&

                botoes[0][0].getText().equals(botoes[1][1].getText()) &&

                botoes[0][0].getText().equals(botoes[2][2].getText())) {

            return true; // Verifica diagonal principal
        }

        if (!botoes[0][2].getText().isEmpty() &&

                botoes[0][2].getText().equals(botoes[1][1].getText()) &&

                botoes[0][2].getText().equals(botoes[2][0].getText())) {

            return true; // Verifica diagonal secundária
        }

        return false;
    }

    private boolean verificarEmpate() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (botoes[i][j].getText().isEmpty()) {

                    return false; // Ainda há espaços vazios
                }
            }
        }

        return true; // Todos os espaços estão preenchidos
    }

    private void desabilitarBotoes() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                botoes[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	
                new JogoDaVelha();
            }
        });
    }
}

