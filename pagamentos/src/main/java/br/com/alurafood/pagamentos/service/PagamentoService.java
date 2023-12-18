package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoCadastroDto;
import br.com.alurafood.pagamentos.dto.PagamentoListagemDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Page<PagamentoListagemDto> obterTodos(Pageable paginacao) {
        return repository.findAll(paginacao).map(PagamentoListagemDto::new);
    }

    public PagamentoListagemDto obterPorId(Long id) {
        var pagamento = repository.getReferenceById(id);
        return new PagamentoListagemDto(pagamento);
    }

    public PagamentoListagemDto criarPagamento(PagamentoCadastroDto dto) {
        var pagamento = new Pagamento(dto);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return new PagamentoListagemDto(pagamento);
    }

    public PagamentoListagemDto atualizarPagamento(Long id, PagamentoListagemDto dto) {
        Pagamento pagamento = repository.getReferenceById(id);
        //pagamento.atualizarInformacoes();
        pagamento = repository.save(pagamento);
        return new PagamentoListagemDto(pagamento);
    }

    public void excluirPagamento(Long id) {
        var medico = repository.getReferenceById(id);
        //medico.excluir();
    }
}
