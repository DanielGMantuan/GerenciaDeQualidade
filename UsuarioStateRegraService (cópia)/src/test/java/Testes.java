import br.usuario.modelo.RegraUsuarioService;
import br.usuario.modelo.TipoUsuario;
import br.usuario.modelo.Usuario;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        //Explorando se as acoes dos administradores sao exclusivas
        SecurityException aException = assertThrows(SecurityException.class, () ->{
            RegraUsuarioService.ativar(normal, normal);
        });
        assertEquals("Ação permitida apenas para administradores", aException.getMessage(), "O usuario que esta fazendo a acao tem que ser ADMINISTRADOR");
        
        SecurityException sException = assertThrows(SecurityException.class, () ->{
            RegraUsuarioService.desativar(normal, normal);
        });
        assertEquals("Ação permitida apenas para administradores", sException.getMessage(), "O usuario que esta fazendo a acao tem que ser ADMINISTRADOR");
    
        SecurityException adException = assertThrows(SecurityException.class, () ->{
            RegraUsuarioService.advertir(normal, normal);
        });
        assertEquals("Ação permitida apenas para administradores", adException.getMessage(), "O usuario que esta fazendo a acao tem que ser ADMINISTRADOR");
    }
    
    @Test
    public void ExplorarUsuarioAtivo(){
        RegraUsuarioService.ativar(normal, adm);
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deve ser definido para ativo");

        //Explorando todos os estados que o usuario ativo pode passar
        IllegalStateException ativarException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.ativar(normal, adm);
        });        
        assertEquals("O usuário já está ativo", ativarException.getMessage());
        
        RegraUsuarioService.desativar(normal, adm);
        assertEquals("Desativado", normal.getNomeEstado(), "O estado do usuario deve ser definido para desativado");
        
        RegraUsuarioService.ativar(normal, adm);
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deve ser definido para ativo");
        
        RegraUsuarioService.advertir(normal, adm); 
        assertEquals(1, normal.getNumeroDeAdvertencias(), "O usuario deveria ter 1 adivertencia");
        
        RegraUsuarioService.advertir(normal, adm); 
        assertEquals(2, normal.getNumeroDeAdvertencias(), "O usuario deve ter 2 adivertencia");
        assertEquals(Duration.ofSeconds(30).getSeconds(), normal.getTempoBanimento().getSeconds(), 1);
        assertEquals("BanidoTemporario", normal.getNomeEstado(), "O estado do usuario deve ser definido para banido temporario");
        
        try { //Esperando o tempo que falta para terminar o banimento temporario
            Thread.sleep(Duration.ofSeconds(2).plus(normal.getTempoBanimento()));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deve ser definido para ativo");
        assertEquals(2, normal.getNumeroDeAdvertencias(), "O usuario deve ter 2 adivertencia");
        
        RegraUsuarioService.advertir(normal, adm);
        assertEquals(3, normal.getNumeroDeAdvertencias(), "O usuario deve ter 3 adivertencia");
        assertEquals("BanidoDefinitivo", normal.getNomeEstado(), "O estado do usuario deve ser definido para banido definitivo");
    }
    
    @Test
    public void ExplorarUsuarioBanidoTemporariamente(){
        RegraUsuarioService.ativar(normal, adm);
        RegraUsuarioService.advertir(normal, adm);
        RegraUsuarioService.advertir(normal, adm);
        
        //Explorando todas as operacoes no usuario banido
        IllegalStateException ativarException = assertThrows(IllegalStateException.class, () -> {
                RegraUsuarioService.ativar(normal, adm);    
            });
        assertEquals("Usuário ainda está banido temporariamente", ativarException.getMessage());
        
        IllegalStateException desativarException = assertThrows(IllegalStateException.class, () -> {
                RegraUsuarioService.desativar(normal, adm);    
            });
        assertEquals("Usuário banido temporariamente não pode ser desativado", desativarException.getMessage());
        
        IllegalStateException advertirException = assertThrows(IllegalStateException.class, () -> {
                RegraUsuarioService.advertir(normal, adm);    
            });
        assertEquals("Usuário banido temporariamente não pode ser advertido", advertirException.getMessage());
    }
    
    @Test
    public void ExplorarUsuarioDesativado(){
        RegraUsuarioService.ativar(normal, adm);
        
        //Testando todas as operacoes no usuario desativado
        RegraUsuarioService.desativar(normal, adm);
        assertEquals("Desativado", normal.getNomeEstado(), "O estado do usuario deve ser definido para desativado");

        IllegalStateException desException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.desativar(normal, adm);
        });
        assertEquals("O usuário já está desativado", desException.getMessage());
        
        IllegalStateException adException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.advertir(normal, adm);
        });
        assertEquals("Usuário desativado não pode ser advertido", adException.getMessage());
        
        RegraUsuarioService.ativar(normal, adm);
        assertEquals("Ativo", normal.getNomeEstado(), "O estado do usuario deve ser atualizado para Ativado");
    }
    
    @Test
    public void ExplorarUsuarioBanido(){
        RegraUsuarioService.ativar(normal, adm);
        RegraUsuarioService.advertir(normal, adm);
        RegraUsuarioService.advertir(normal, adm);
        
        try { //Esperando o tempo que falta para terminar o banimento temporario
            Thread.sleep(Duration.ofSeconds(2).plus(normal.getTempoBanimento()));
        } catch (InterruptedException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RegraUsuarioService.advertir(normal, adm);
        
        assertEquals(3, normal.getNumeroDeAdvertencias(), "O usuario deve ter 3 advertencias");
        assertEquals("BanidoDefinitivo", normal.getNomeEstado(), "Com 3 advertencias o usuario deve estar no estado de banidoDefinitivo");
        
        //Explorando todas as operacoes em usuario banido definitivamente
        IllegalStateException adException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.advertir(normal, adm);
        });
        assertEquals("Usuário banido definitivamente não pode ser advertido", adException.getMessage());
        
        IllegalStateException desException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.desativar(normal, adm);
        });
        assertEquals("Usuário banido definitivamente não pode ser desativado", desException.getMessage());
        
        IllegalStateException atException = assertThrows(IllegalStateException.class, () -> {
            RegraUsuarioService.ativar(normal, adm);
        });
        assertEquals("Usuário banido definitivamente não pode ser ativado", atException.getMessage());
    }
}   
