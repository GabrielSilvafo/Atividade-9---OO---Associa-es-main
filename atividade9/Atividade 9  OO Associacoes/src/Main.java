public class Main {
    public static void main(String[] args) {
        Campeonato campeonato = new Campeonato();
        int opcao;

        do {
            opcao = InterfaceUsuario.mostrarMenu();

            switch(opcao) {
                case 1:
                    campeonato.cadastrarTime();
                    break;
                case 2:
                    campeonato.simularJogo();
                    break;
                case 3:
                    InterfaceUsuario.mostrarMensagem("Programa encerrado.");
                    break;
                default:
                    InterfaceUsuario.mostrarMensagem("Opção inválida!");
            }
        } while(opcao != 3);
    }
}