import { useParams } from 'react-router-dom';
import useApi from '../../../hooks/useApi';
import { useEffect, useState } from 'react';
import { Debito } from '../../../types/Debito';
import * as C from './styles';
import { formatarData } from '../../../utils/DateUtils';

const DebitoDetalhe = () => {
  const { idDebito: idDebitoParam } = useParams<{ idDebito: string }>();
  const idDebitoSelecionado = idDebitoParam ? parseInt(idDebitoParam) : null;
  const [debito, setDebito] = useState<Debito>(); 
  const api = useApi();

  useEffect(() => {
    const fetchDebito = async () => {
      try {
        if (idDebitoSelecionado !== null) {
          const result = await api.findDebitoById(idDebitoSelecionado);
          setDebito(result);
        }
      } catch (error) {
        console.error("Erro ao carregar o débito:", error);
      }
    };

    fetchDebito();
  }, [idDebitoSelecionado]); 

  const valorTotalParcelas = debito?.parcelas.reduce((total, parcela) => {
    const valorParcela = parcela.valor;
    return isNaN(valorParcela) ? total : total + valorParcela;
  }, 0);
  
  return (
    <C.Container>
      <C.Body>
        <C.DebitoArea>
          <C.Column>
              <C.FirstRow>
                  ID
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                  type={'text'}
                  readOnly
                  defaultValue={debito?.id}
                />
              </C.SecondRow>
          </C.Column>

          <C.Column>
              <C.FirstRow>
                Data Lançamento
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                  type={'text'}
                  readOnly
                  defaultValue={debito?.dataLancamento && formatarData(debito?.dataLancamento.toString())}
                />
              </C.SecondRow>
          </C.Column>

          <C.Column>
              <C.FirstRow>
                Nome Pessoa
              </C.FirstRow>
              <C.SecondRow>
                <C.Input
                  type={'text'}
                  readOnly
                  defaultValue={debito?.pessoa.nome}
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
                  defaultValue={debito?.parcelas.length}
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
                    defaultValue={valorTotalParcelas?.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                />
              </C.SecondRow>
          </C.Column>

        </C.DebitoArea>
      </C.Body>
    </C.Container>
  );
};

export default DebitoDetalhe;
