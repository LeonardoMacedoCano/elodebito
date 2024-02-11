import GlobalStyles from './GlobalStyles';
import { ThemeProvider } from 'styled-components';
import dark from './theme/dark';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainHeader from './components/menu/MainHeader';
import { Home } from './pages/Home';
import { MensagemProvider } from './providers/MensagemProvider';
import ConsultaDebitos from './pages/Debito/ConsultaDebitos';

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
          </Routes>
        </BrowserRouter>
      </MensagemProvider>
    </ThemeProvider>
  )
}

export default App
