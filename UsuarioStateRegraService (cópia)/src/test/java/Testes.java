import br.usuario.modelo.TipoUsuario;
import br.usuario.modelo.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Testes {
    public static String nome;
    public static String senha;
    
    @BeforeAll
    public static void InstanciarNomeSenha()
    {
        nome = "Tigas";
        senha = "Tigas1234";
    }
    
    @Test
    public void TesteCriarUsuarioInvalido()
    {   
        IllegalArgumentException excecaoNegativa = assertThrows(IllegalArgumentException.class, () -> {
                Usuario u = new Usuario(null, TipoUsuario.NORMAL, senha);
            });
        assertEquals("O nome do usuário não pode ser vazio ou nulo", excecaoNegativa.getMessage());
    }
}
