package br.com.fiap.domain.repository;
import br.com.fiap.domain.entity.Gravida;
import br.com.fiap.infra.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GravidaRepository implements Repository <Gravida, Long> {

    private ConnectionFactory factory;
    private static final AtomicReference<GravidaRepository> instance = new AtomicReference<>();
    private GravidaRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static GravidaRepository build() {
        instance.compareAndSet(null, new GravidaRepository());
        return instance.get();
    }

    @Override
    public Gravida persist(Gravida gravida) {
        var sql = "INSERT INTO TB_GRAVIDA (ID_GRAVIDA, NM_GRAVIDA, NR_IDADE_GRAVIDA, DS_ENDERECO_GRAVIDA, SQ_CPF_GRAVIDA, NR_RG_GRAVIDA, SQ_CEP_GRAVIDA, TP_SANGUINEO) VALUES (0,?,?,?,?,?,?,?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( sql, new String[]{"ID_GRAVIDA"} );
            ps.setString(1, gravida.getNome());
            ps.setInt(2, gravida.getIdade());
            ps.setString(3, gravida.getEndereco());
            ps.setString(4, gravida.getCpf());
            ps.setString(5, gravida.getRg());
            ps.setString(6, gravida.getCep());
            ps.setString(7, gravida.getTipo_sanguineo());

            ps.executeUpdate();
            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                gravida.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi possivel salvar a gravida no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return gravida;
    }

    @Override
    public List<Gravida> findAll() {
        List<Gravida> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_GRAVIDA";
        try {
            ps = con.prepareStatement( sql, new String[]{"ID_GRAVIDA"} );
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                GravidaRepository gravidaRepo = GravidaRepository.build();
                while (rs.next()){
                    Long id = rs.getLong("ID_GRAVIDA");
                    String nome = rs.getString("NM_GRAVIDA");
                    Integer idade = rs.getInt("NR_IDADE");
                    String endereco = rs.getString("DS_ENDERECO_GRAVIDA");
                    String cpf = rs.getString("SQ_CPF_GRAVIDA");
                    String rg = rs.getString("NR_RG_GRAVIDA");
                    String cep = rs.getString("SQ_CEP_GRAVIDA");
                    String tipo_sanguineo = rs.getString("TP_SANGUINEO");
                    list.add(new Gravida(id, nome, idade, endereco, cpf, rg, cep, tipo_sanguineo));
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar a gravida\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Gravida findById(Long id) {
        Gravida gravida = null;
        var sql = "SELECT * FROM TB_GRAVIDA where ID_GRAVIDA=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                GravidaRepository gravidaRepo = GravidaRepository.build();
                while (rs.next()){
                    String nome = rs.getString("NM_GRAVIDA");
                    Integer idade = rs.getInt("NR_IDADE");
                    String endereco = rs.getString("DS_ENDERECO_GRAVIDA");
                    String cpf = rs.getString("SQ_CPF_GRAVIDA");
                    String rg = rs.getString("NR_RG_GRAVIDA");
                    String cep = rs.getString("SQ_CEP_GRAVIDA");
                    String tipo_sanguineo = rs.getString("TP_SANGUINEO");
                    gravida = new Gravida(null,nome, idade, endereco, cpf, rg, cep, tipo_sanguineo);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID da gravida");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return gravida;
    }

    @Override
    public void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        Repository.super.fecharObjetos(rs, st, con);
    }
}
