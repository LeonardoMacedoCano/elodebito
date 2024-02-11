import GlobalStyles from './GlobalStyles';
import { ThemeProvider } from 'styled-components';
import dark from './theme/dark';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainHeader from './components/menu/MainHeader';
import { Home } from './pages/Home';

function App() {
  return (
    <ThemeProvider theme={dark}>
      <BrowserRouter>
        <GlobalStyles/>
        <MainHeader />
        <Routes>
          <Route path="/" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  )
}

export default App
