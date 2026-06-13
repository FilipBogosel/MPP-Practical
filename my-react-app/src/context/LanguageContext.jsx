import { createContext, useContext, useState, useCallback } from 'react'
import { translations, LANGUAGES } from '../i18n/translations.js'

const LanguageContext = createContext(null)

export function LanguageProvider({ children }) {
  const [language, setLanguage] = useState('ro')

  const locale = LANGUAGES.find(l => l.code === language)?.locale ?? 'ro-RO'

  const t = useCallback((key) =>
    key.split('.').reduce((obj, k) => obj?.[k], translations[language]) ?? key,
    [language]
  )

  return (
    <LanguageContext.Provider value={{ language, setLanguage, locale, t }}>
      {children}
    </LanguageContext.Provider>
  )
}

export function useLanguage() {
  const ctx = useContext(LanguageContext)
  if (!ctx) throw new Error('useLanguage must be used within LanguageProvider')
  return ctx
}
