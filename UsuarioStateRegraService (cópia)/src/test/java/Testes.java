import br.usuario.modelo.RegraUsuarioService;
import br.usuario.modelo.TipoUsuario;
import br.usuario.modelo.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Testes {
    public Usuario normal;
    public Usuario adm;
    
    @BeforeEach
    public void InstanciarUsuarios()
    {
        normal = new Usuario("Tigas", TipoUsuario.NORMAL, "Tigas1234");
        adm = new Usuario("Tigas", TipoUsuario.ADMINISTRADOR, "Tigas1234");
    }
    
    @Test
    public void TesteCriarUsuarioInvalido()
    {   
        IllegalArgumentException excecaoNome = assertThrows(IllegalArgumentException.class, () -> {
                Usuario u = new Usuario(null, TipoUsuario.NORMAL, "1234");
            });
        assertEquals("O nome do usuário não pode ser vazio ou nulo", excecaoNome.getMessage());
        
        IllegalArgumentException excecaoSenha = assertThrows(IllegalArgumentException.class, () -> {
                Usuario u = new Usuario("Tigas", TipoUsuario.NORMAL, null);
            });
        assertEquals("A senha do usuário não pode ser vazia ou nula", excecaoSenha.getMessage());
    }
    
    @Test
    public void ExplorarAcoesDoAdministrador(){
        SecurityException sException = assertThrows(SecurityException.class, () ->{
            RegraUsuarioService.ativar(normal, normal);
        });
        assertEquals("Ação permitida apenas para administradores", sException.getMessage(), "O usuario que esta fazendo a acao tem que ser ADMINISTRADOR");
    }
    
    @Test
    public void ExplorarAtivarUsuarioNovo(){
        RegraUsuarioService.ativar(normal, adm);
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deveria ser definido para ativo");
        
        RegraUsuarioService.desativar(normal, adm);
        assertEquals("Desativado", normal.getNomeEstado(), "O estado do usuario deveria ser definido para desativado");
        
        RegraUsuarioService.ativar(normal, adm);
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deveria ser definido para ativo");
        
        RegraUsuarioService.advertir(normal, adm); 
        assertEquals(1, normal.getNumeroDeAdvertencias(), "O usuario deveria ter 1 adivertencia");
        
        RegraUsuarioService.advertir(normal, adm); 
        assertEquals(2, normal.getNumeroDeAdvertencias(), "O usuario deveria ter 2 adivertencia");
        assertEquals("BanidoTemporario", normal.getNomeEstado(), "O estado do usuario deveria ser definido para banido temporario");
        //TODO: terminar os assert que precisa
        RegraUsuarioService.advertir(normal, adm);
        assertEquals(3, normal.getNumeroDeAdvertencias(), "O usuario deveria ter 3 adivertencia");
        assertEquals("BanidoDefinitivo", normal.getNomeEstado(), "O estado do usuario deveria ser definido para banido definitivo");
        
    }
}
