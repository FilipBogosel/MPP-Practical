import { useState } from 'react'
import { AuthProvider, useAuth } from './context/AuthContext.jsx'
import { LanguageProvider } from './context/LanguageContext.jsx'
import { MasterDetailPage } from './pages/MasterDetailPage/MasterDetailPage.jsx'
import { LoginPage } from './pages/LoginPage/LoginPage.jsx'
import { RegisterPage } from './pages/RegisterPage/RegisterPage.jsx'
import './App.css'

function AppRouter() {
  const { user } = useAuth()
  const [page, setPage] = useState('login')

  if (!user) {
    return page === 'login'
      ? <LoginPage onNavigate={setPage} />
      : <RegisterPage onNavigate={setPage} />
  }

  return (
    <LanguageProvider>
      <MasterDetailPage />
    </LanguageProvider>
  )
}

function App() {
  return (
    <AuthProvider>
      <AppRouter />
    </AuthProvider>
  )
}

export default App
