import "./App.css";
import MainLayout from "./components/layout/MainLayout";
import MainRouter from "./roters/MainRouter";
import { GlobalStyle } from "./styles/global";


function App() {
  return (
  <>
    <GlobalStyle />
    <MainLayout>
      <MainRouter />
    </MainLayout>
  </>
  );
}

export default App;
