import { MasterDetailPage } from './pages/MasterDetailPage/MasterDetailPage.jsx'
import { LanguageProvider } from './context/LanguageContext.jsx'
import './App.css'

function App() {
  return (
    <LanguageProvider>
      <MasterDetailPage />
    </LanguageProvider>
  )
}

export default App
