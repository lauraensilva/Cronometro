package Projeto_APS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Dados {
    Connection conn;
    PreparedStatement pstm;

    public void enviarDados(Cronometro dados) {
        String sql = "insert into cronometro (primeira_volta, segunda_volta, tempo_total, nome_equipe, nome_piloto) values (?, ?, ?, ?, ?)";

        conn = new ConectaDB().conectaDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, dados.getVolta1());
            pstm.setString(2, dados.getVolta2());
            pstm.setString(3, dados.getTotal());
            pstm.setString(4, dados.getEquipe());
            pstm.setString(5, dados.getPiloto());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Os dados da sua equipe foram salvos no Banco de Dados");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}