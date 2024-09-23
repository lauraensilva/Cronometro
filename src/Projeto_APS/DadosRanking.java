package Projeto_APS;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DadosRanking {
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public List<DadosEquipe> buscarDados() {
        List<DadosEquipe> resultados = new ArrayList<>();
        String sql = "SELECT * FROM cronometro";

        conn = new ConectaDB().conectaDB();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	String volta1 = rs.getString("primeira_volta");
                String volta2 = rs.getString("segunda_volta");
                String total = rs.getString("tempo_total");
            	String equipe = rs.getString("nome_equipe");
                String piloto = rs.getString("nome_piloto");
                
                
                DadosEquipe dados = new DadosEquipe(volta1, volta2, total, equipe, piloto);
                resultados.add(dados);
            }
            
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return resultados;
    }
}