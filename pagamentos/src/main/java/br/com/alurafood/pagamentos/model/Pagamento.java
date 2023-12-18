package br.com.alurafood.pagamentos.model;

import br.com.alurafood.pagamentos.dto.PagamentoCadastroDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Pagamento")
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;

    public Pagamento(PagamentoCadastroDto dto) {
        this.valor = dto.valor();
        this.nome = dto.nome();
        this.numero = dto.numero();
        this.expiracao = dto.expiracao();
        this.codigo = dto.codigo();
        this.status = dto.status();
        this.pedidoId = dto.pedidoId();
        this.formaDePagamentoId = dto.formaDePagamentoId();
    }
}
