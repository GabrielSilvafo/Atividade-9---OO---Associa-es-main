import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Campeonato {
    private ArrayList<Time> times = new ArrayList<>();
    private ArrayList<Jogo> jogos = new ArrayList<>();

    public void cadastrarTime() {
        if(times.size() >= 10) {
            InterfaceUsuario.mostrarMensagem("Limite máximo de 10 times atingido!");
            return;
        }

        String nomeTime = InterfaceUsuario.lerString("Digite o nome do time:");
        times.add(new Time(nomeTime));
        InterfaceUsuario.mostrarMensagem("Time cadastrado com sucesso!");
    }

    public void simularJogo() {
        if(times.size() < 2) {
            InterfaceUsuario.mostrarMensagem("Cadastre pelo menos 2 times!");
            return;
        }

        StringBuilder listaTimes = new StringBuilder("Times disponíveis:\n");
        for(int i = 0; i < times.size(); i++) {
            listaTimes.append(i+1).append(" - ").append(times.get(i).getNome()).append("\n");
        }

        try {
            int time1Idx = InterfaceUsuario.lerInt(listaTimes.toString() + "Escolha o primeiro time:") - 1;
            int time2Idx = InterfaceUsuario.lerInt("Escolha o segundo time:") - 1;

            if(time1Idx < 0 || time2Idx < 0 || time1Idx >= times.size() || time2Idx >= times.size()) {
                InterfaceUsuario.mostrarMensagem("Índice inválido!");
                return;
            }

            if(time1Idx == time2Idx) {
                InterfaceUsuario.mostrarMensagem("Um time não pode jogar contra ele mesmo!");
                return;
            }

            Time time1 = times.get(time1Idx);
            Time time2 = times.get(time2Idx);

            if(jogoJaRealizado(time1, time2)) {
                InterfaceUsuario.mostrarMensagem("Este jogo já foi realizado! Escolha outra dupla de times.");
                return;
            }

            Jogo jogo = new Jogo(time1, time2);
            jogo.simular();
            jogos.add(jogo);

            atualizarEstatisticas(jogo);

            InterfaceUsuario.mostrarMensagem("Resultado: " + jogo.getResultado());
            mostrarTabela();

            if(todosJogosRealizados()) {
                anunciarCampeoes();
            }

        } catch(Exception e) {
            InterfaceUsuario.mostrarMensagem("Erro: " + e.getMessage());
        }
    }

    private boolean jogoJaRealizado(Time time1, Time time2) {
        for(Jogo jogo : jogos) {
            if((jogo.getTime1().equals(time1) && jogo.getTime2().equals(time2)) ||
                    (jogo.getTime1().equals(time2) && jogo.getTime2().equals(time1))) {
                return true;
            }
        }
        return false;
    }

    private void atualizarEstatisticas(Jogo jogo) {
        Time time1 = jogo.getTime1();
        Time time2 = jogo.getTime2();
        int gols1 = jogo.getGolsTime1();
        int gols2 = jogo.getGolsTime2();

        time1.adicionarJogo();
        time1.adicionarGolsMarcados(gols1);
        time1.adicionarGolsSofridos(gols2);

        time2.adicionarJogo();
        time2.adicionarGolsMarcados(gols2);
        time2.adicionarGolsSofridos(gols1);

        if(gols1 > gols2) {
            time1.adicionarVitoria();
            time2.adicionarDerrota();
        } else if(gols1 < gols2) {
            time2.adicionarVitoria();
            time1.adicionarDerrota();
        } else {
            time1.adicionarEmpate();
            time2.adicionarEmpate();
        }
    }

    private void mostrarTabela() {
        Collections.sort(times, Comparator.comparingInt(Time::getPontos).reversed());

        System.out.println("\nTabela do Campeonato:");
        System.out.println("============================================================================================================");
        System.out.printf("%-15s | %-4s | %-4s | %-4s | %-4s | %-4s | %-4s | %-4s | %-4s | %-4s\n",
                "Time", "J", "V", "E", "D", "Pts", "GM", "GS", "SG", "Aproveitamento");
        System.out.println("============================================================================================================");

        for(Time time : times) {
            System.out.printf("%-15s | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4d | %-4.1f%%\n",
                    time.getNome(),
                    time.getJogos(),
                    time.getVitorias(),
                    time.getEmpates(),
                    time.getDerrotas(),
                    time.getPontos(),
                    time.getGolsMarcados(),
                    time.getGolsSofridos(),
                    time.getSaldoGols(),
                    time.getAproveitamento());
        }
        System.out.println("============================================================================================================\n");
    }

    private boolean todosJogosRealizados() {
        int totalJogosPossiveis = times.size() * (times.size() - 1) / 2;
        return jogos.size() >= totalJogosPossiveis;
    }

    private void anunciarCampeoes() {
        if(times.isEmpty()) return;

        int maiorPontuacao = times.get(0).getPontos();
        ArrayList<Time> campeoes = new ArrayList<>();

        for(Time time : times) {
            if(time.getPontos() == maiorPontuacao) {
                campeoes.add(time);
            }
        }

        if(campeoes.size() == 1) {
            InterfaceUsuario.mostrarMensagem("O campeão é: " + campeoes.get(0).getNome());
        } else {
            StringBuilder mensagem = new StringBuilder("Os times campeões são:\n");
            for(Time time : campeoes) {
                mensagem.append(time.getNome()).append("\n");
            }
            InterfaceUsuario.mostrarMensagem(mensagem.toString());
        }
    }
}