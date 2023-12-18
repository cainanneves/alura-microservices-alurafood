package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoCadastroDto;
import br.com.alurafood.pagamentos.dto.PagamentoListagemDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public ResponseEntity<Page<PagamentoListagemDto>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.obterTodos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoListagemDto> detalhar(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PagamentoListagemDto> cadastrar(@RequestBody @Valid PagamentoCadastroDto dto, UriComponentsBuilder uriBuilder) {
        var pagamento = service.criarPagamento(dto);

        var uri = uriBuilder.path("/pagamento/{id}").buildAndExpand(pagamento.id()).toUri();

        return ResponseEntity.created(uri).body(pagamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PagamentoListagemDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoListagemDto dto) {
        var atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PagamentoListagemDto> excluir(@PathVariable Long id) {
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }
}
