import { useParams } from 'react-router-dom';
import useApi from '../../../hooks/useApi';
import { useEffect, useState } from 'react';
import { Debito } from '../../../types/Debito';
import * as C from './styles';
import { dateToString, getDataAtual } from '../../../utils/DateUtils';
import { TableArea } from '../../../components/Table/TableArea';
import { ParcelaColunasFormat } from '../TableConfig/ParcelaColunasFormat';
import { ParcelaColunasConfig } from '../TableConfig/ParcelaColunasConfig';
import { FormParcela } from '../../../types/FormParcela';
import { FormDebito } from '../../../types/FormDebito';
import { Pessoa } from '../../../types/Pessoa';

const DebitoDetalhe = () => {
  const { idDebito: idDebitoParam } = useParams<{ idDebito: string }>();
  const idDebitoSelecionado = idDebitoParam ? parseInt(idDebitoParam) : null;
  const [debito, setDebito] = useState<Debito>();
  const [pessoas, setPessoas] = useState<Pessoa[]>([]);
  const [idParcelaSelecionada, setIdParcelaSelecionada] = useState<number | null>(null);
  const [formDebito, setFormDebito] = useState<FormDebito>({
    idPessoa: 0,
    dataLancamento: getDataAtual(),
    parcelas: []
  }); 
  const api = useApi();

  useEffect(() => {
    const fetchDebito = async () => {
      try {
        if (idDebitoSelecionado !== null) {
          const debito = await api.findDebitoById(idDebitoSelecionado);
          setDebito(debito);
        } else {
          const pessoas = await api.findPessoas(0, 10);
          if (pessoas) {
            setPessoas(pessoas);
          }
        }
      } catch (error) {
        console.error("Erro ao carregar o débito:", error);
      }
    };

    fetchDebito();
  }, [idDebitoSelecionado]); 

  const valorTotalParcelas = idDebitoSelecionado !== null
    ? debito?.parcelas.reduce((total, parcela) => {
        const valorParcela = parcela.valor;
        return isNaN(valorParcela) ? total : total + valorParcela;
      }, 0)
    : formDebito?.parcelas.reduce((total, parcela) => {
        const valorParcela = parcela.valor;
        return isNaN(valorParcela) ? total : total + valorParcela;
      }, 0);

  const dataLancamento = idDebitoSelecionado !== null
    ? debito?.dataLancamento && dateToString(debito?.dataLancamento)
    : formDebito?.dataLancamento && dateToString(formDebito?.dataLancamento);

  const handleItemClick = (id: number | null) => {
    setIdParcelaSelecionada(id);
  };
  
  const handleNovaParcela = () => {
    const ultimoNumeroParcela = formDebito.parcelas.reduce((max, parcela) => {
      return parcela.numero > max ? parcela.numero : max;
    }, 0);

    const numeroNovaParcela = ultimoNumeroParcela + 1;

    const novaParcela: FormParcela = {
        numero:  numeroNovaParcela,
        valor: 10.0,
        dataVencimento: getDataAtual(),
    };

    setFormDebito(prevFormDebito => ({
      ...prevFormDebito,
      parcelas: [...formDebito.parcelas, novaParcela],
    }));
  };

  const handleGerarDebito = async () => {
    await api.gerarDebito(formDebito); 
  };

  const handleChangeFormDebito = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormDebito(prevFormDebito => ({
      ...prevFormDebito,
      [name]: value,
    }));
  };

  const handlePessoaFormDebitoChange = (value: string) => {
    setFormDebito(prevFormDebito => ({
      ...prevFormDebito,
      idPessoa: Number(value),
    }));
  }; 
  
  return (
    <C.Container>
      <C.Body>
        <C.DebitoArea>
          <C.Column>
              <C.FirstRow>
                Pessoa
              </C.FirstRow>
              <C.SecondRow>
                { idDebitoSelecionado !== null
                  ? <C.Input
                      type={'text'}
                      readOnly={idDebitoSelecionado !== null}
                      value={debito?.pessoa.nome}
                  />
                  : <C.Select
                      value={formDebito?.idPessoa}
                      onChange={(e) => handlePessoaFormDebitoChange(e.target.value)}
                    >
                      <option value="">Selecione...</option>
                      {pessoas.map((pessoa) => (
                        <option key={pessoa.id} value={pessoa.id}>
                          {pessoa.nome}
                        </option>
                      ))}
                  </C.Select>
                }
                
              </C.SecondRow>
          </C.Column>

          <C.Column>
              <C.FirstRow>
                Data Lanç.
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                  type={'date'}
                  readOnly={idDebitoSelecionado !== null}
                  value={dataLancamento}
                  onChange={handleChangeFormDebito}
                  name={"dataLancamento"}
                />
              </C.SecondRow>
          </C.Column>

          <C.Column>
              <C.FirstRow>
                Qt Parcelas
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                  type={'text'}
                  readOnly
                  value={idDebitoSelecionado !== null 
                    ? debito?.parcelas.length
                    : formDebito?.parcelas.length
                  }
                /> 
              </C.SecondRow>
          </C.Column>

          <C.Column>
              <C.FirstRow>
                Valor Total
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                    type={'text'}
                    readOnly
                    value={valorTotalParcelas?.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                />
              </C.SecondRow>
          </C.Column>

          <C.Column>
                <C.FirstRow>
                    &nbsp;  
                </C.FirstRow>
                <C.SecondRow>
                    {idParcelaSelecionada === null ? (
                        <>
                          <C.Button onClick={handleNovaParcela}>Nova Parcela</C.Button>
                          <C.Button onClick={handleGerarDebito}>Gerar debito</C.Button>
                        </>
                    ) : (
                        <C.Button>Visualizar Parcela</C.Button>
                    )}
                </C.SecondRow>
            </C.Column>
        </C.DebitoArea>
        
        <TableArea
          lista={idDebitoSelecionado !== null 
            ? debito?.parcelas ?? []
            : formDebito?.parcelas ?? []
          }
          colunasConfig={ParcelaColunasConfig}
          colunasFormat={ParcelaColunasFormat}
          onItemClick={handleItemClick}
          itemIdSelecionado={idParcelaSelecionada}
        />
      </C.Body>
    </C.Container>
  );
};

export default DebitoDetalhe;
