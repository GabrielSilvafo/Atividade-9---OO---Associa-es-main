import javax.swing.JOptionPane;

public class InterfaceUsuario {
    public static int mostrarMenu() {
        String menu = "CAMPEONATO DE FUTEBOL\n\n" +
                "1 - Cadastrar Times\n" +
                "2 - Simular Jogos\n" +
                "3 - Sair\n\n" +
                "Digite sua opção:";
        return lerInt(menu);
    }

    public static String lerString(String mensagem) {
        return JOptionPane.showInputDialog(null, mensagem);
    }

    public static int lerInt(String mensagem) {
        while(true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensagem);
                if(input == null) return -1;
                return Integer.parseInt(input);
            } catch(NumberFormatException e) {
                mostrarMensagem("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    public static void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}