package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PagamentoCadastroDto(
        @NotNull
        @Positive
        BigDecimal valor,

        @Size(max = 100)
        String nome,

        @Size(max = 19)
        String numero,

        @Size(max = 7)
        String expiracao,

        @Size(min = 3, max = 3)
        String codigo,
        @NotNull
        Status status,
        @NotNull
        Long formaDePagamentoId,
        @NotNull
        Long pedidoId
) {
}
