package br.com.fiap.domain.repository;
import br.com.fiap.domain.entity.Filho;
import br.com.fiap.domain.entity.Gravida;
import br.com.fiap.infra.ConnectionFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FilhoRepository implements Repository <Filho, Long> {

    private ConnectionFactory factory;
    private static final AtomicReference<FilhoRepository> instance = new AtomicReference<>();
    private FilhoRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static FilhoRepository build() {
        instance.compareAndSet(null, new FilhoRepository());
        return instance.get();
    }

    @Override
    public Filho persist(Filho filho) {
        var sql = "INSERT INTO TB_FILHO (ID_FILHO, NM_FILHO,NR_RG_FILHO, TP_SANGUINEO_FILHO) VALUES (0,?,?,?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( sql, new String[]{"ID_FILHO"} );
            ps.setString(1, filho.getNome());
            ps.setString(2, filho.getRg());
            ps.setString(3, filho.getTipo_sanguineo());

            ps.executeUpdate();
            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                filho.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi possivel salvar o filho no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return filho;
    }

    @Override
    public List<Filho> findAll() {
        List<Filho> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_FILHO";
        try {
            ps = con.prepareStatement( sql, new String[]{"ID_FILHO"} );
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                FilhoRepository filhoRepo = FilhoRepository.build();
                while (rs.next()){
                    Long id = rs.getLong("ID_FILHO");
                    String nome = rs.getString("NM_FILHO");
                    String rg = rs.getString("NR_RG_FILHO");
                    String tipo_sanguineo = rs.getString("TP_SANGUINEO_FILHO");
                    list.add(new Filho(id, nome, rg, tipo_sanguineo));
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar o filho\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Filho findById(Long id) {
        Filho filho = null;
        var sql = "SELECT * FROM TB_FILHO where ID_FILHO=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                FilhoRepository filhoRepo = FilhoRepository.build();
                while (rs.next()){
                    String nome = rs.getString("NM_FILHO");
                    String rg = rs.getString("NR_RG_FILHO");
                    String tipo_sanguineo = rs.getString("TP_SANGUINEO_FILHO");
                    filho = new Filho(null,nome, rg, tipo_sanguineo);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID do Filho");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return filho;
    }

    @Override
    public void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        Repository.super.fecharObjetos(rs, st, con);
    }
}