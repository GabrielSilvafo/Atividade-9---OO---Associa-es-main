public class Time {
    private String nome;
    private int jogos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;

    public Time(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public int getJogos() { return jogos; }
    public int getVitorias() { return vitorias; }
    public int getEmpates() { return empates; }
    public int getDerrotas() { return derrotas; }
    public int getPontos() { return vitorias * 3 + empates; }
    public int getGolsMarcados() { return golsMarcados; }
    public int getGolsSofridos() { return golsSofridos; }
    public int getSaldoGols() { return golsMarcados - golsSofridos; }
    public double getAproveitamento() {
        return jogos == 0 ? 0.0 : (getPontos() * 100.0) / (jogos * 3);
    }

    public void adicionarJogo() { jogos++; }
    public void adicionarVitoria() { vitorias++; }
    public void adicionarEmpate() { empates++; }
    public void adicionarDerrota() { derrotas++; }
    public void adicionarGolsMarcados(int gols) { golsMarcados += gols; }
    public void adicionarGolsSofridos(int gols) { golsSofridos += gols; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time other = (Time) obj;
        return nome.equals(other.nome);
    }
}