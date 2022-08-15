package produto;

public enum TipoTamanho {
    P(1.0), M(1.3), G(1.5);

    public final double multiplicador;

    TipoTamanho(final double multiplicador) {
        this.multiplicador = multiplicador;
    }

}
