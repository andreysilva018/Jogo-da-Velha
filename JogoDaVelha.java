package Jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaVelha extends JFrame implements ActionListener {
    private JButton[][] botoes = new JButton[3][3];
    private String jogadorAtual = "X";
    private JLabel statusLabel = new JLabel("Vez do jogador: X");
    private int jogadas = 0;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelTabuleiro = new JPanel(new GridLayout(3, 3));
        
        // Criar botões
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                botoes[i][j].addActionListener(this);
                painelTabuleiro.add(botoes[i][j]);
            }
        }

        // Status e reiniciar
        JPanel painelInferior = new JPanel(new BorderLayout());
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        painelInferior.add(statusLabel, BorderLayout.CENTER);
        painelInferior.add(botaoReiniciar, BorderLayout.EAST);

        add(painelTabuleiro, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();

        if (!botaoClicado.getText().equals("")) {
            return; // já clicado
        }

        botaoClicado.setText(jogadorAtual);
        jogadas++;

        if (verificarVencedor()) {
            statusLabel.setText("Jogador " + jogadorAtual + " venceu!");
            desativarBotoes();
        } else if (jogadas == 9) {
            statusLabel.setText("Empate!");
        } else {
            trocarJogador();
            statusLabel.setText("Vez do jogador: " + jogadorAtual);
        }
    }

    private void trocarJogador() {
        jogadorAtual = jogadorAtual.equals("X") ? "O" : "X";
    }

    private boolean verificarVencedor() {
        for (int i = 0; i < 3; i++) {
            // Linhas
            if (botoes[i][0].getText().equals(jogadorAtual) &&
                botoes[i][1].getText().equals(jogadorAtual) &&
                botoes[i][2].getText().equals(jogadorAtual)) {
                return true;
            }
            // Colunas
            if (botoes[0][i].getText().equals(jogadorAtual) &&
                botoes[1][i].getText().equals(jogadorAtual) &&
                botoes[2][i].getText().equals(jogadorAtual)) {
                return true;
            }
        }

        // Diagonais
        if (botoes[0][0].getText().equals(jogadorAtual) &&
            botoes[1][1].getText().equals(jogadorAtual) &&
            botoes[2][2].getText().equals(jogadorAtual)) {
            return true;
        }

        if (botoes[0][2].getText().equals(jogadorAtual) &&
            botoes[1][1].getText().equals(jogadorAtual) &&
            botoes[2][0].getText().equals(jogadorAtual)) {
            return true;
        }

        return false;
    }

    private void desativarBotoes() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                botoes[i][j].setEnabled(false);
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
            }
        jogadorAtual = "X";
        jogadas = 0;
        statusLabel.setText("Vez do jogador: X");
    }

    public static void main(String[] args) {
        new JogoDaVelha();
    }
}
