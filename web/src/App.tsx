import GlobalStyles from './GlobalStyles';
import { ThemeProvider } from 'styled-components';
import dark from './theme/dark';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainHeader from './components/menu/MainHeader';
import { Home } from './pages/Home';
import { MensagemProvider } from './providers/MensagemProvider';
import ConsultaDebitos from './pages/Debito/ConsultaDebitos';
import DebitoDetalhe from './pages/Debito/DebitoDetalhe';

function App() {
  return (
    <ThemeProvider theme={dark}>
      <MensagemProvider>
        <BrowserRouter>
          <GlobalStyles/>
          <MainHeader />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/consulta-debitos" element={<ConsultaDebitos />} />
            <Route path="/debito-detalhe/:idDebito" element={<DebitoDetalhe />} />
          </Routes>
        </BrowserRouter>
      </MensagemProvider>
    </ThemeProvider>
  )
}

export default App
