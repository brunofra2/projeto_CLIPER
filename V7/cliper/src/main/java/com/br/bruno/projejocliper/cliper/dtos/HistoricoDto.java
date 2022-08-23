package com.br.bruno.projejocliper.cliper.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class HistoricoDto implements Serializable {
    private final Long idHistorico;
    private final Date periodoInicio;
    private final Date periodoFim;

    public HistoricoDto(Long idHistorico, Date periodoInicio, Date periodoFim) {
        this.idHistorico = idHistorico;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
    }

    public Long getIdHistorico() {
        return idHistorico;
    }

    public Date getPeriodoInicio() {
        return periodoInicio;
    }

    public Date getPeriodoFim() {
        return periodoFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoDto entity = (HistoricoDto) o;
        return Objects.equals(this.idHistorico, entity.idHistorico) &&
                Objects.equals(this.periodoInicio, entity.periodoInicio) &&
                Objects.equals(this.periodoFim, entity.periodoFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, periodoInicio, periodoFim);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idHistorico = " + idHistorico + ", " +
                "periodoInicio = " + periodoInicio + ", " +
                "periodoFim = " + periodoFim + ")";
    }
}
