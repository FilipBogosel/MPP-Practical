import { useState, useRef, useEffect } from 'react'
import { useLanguage } from '../../context/LanguageContext.jsx'
import { LANGUAGES } from '../../i18n/translations.js'
import styles from './LanguageSelector.module.css'

function GlobeIcon() {
  return (
    <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
      <circle cx="12" cy="12" r="10"/>
      <line x1="2" y1="12" x2="22" y2="12"/>
      <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"/>
    </svg>
  )
}

function ChevronIcon({ open }) {
  return (
    <svg
      width="10" height="10" viewBox="0 0 24 24" fill="none"
      stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"
      aria-hidden="true"
      style={{ transform: open ? 'rotate(180deg)' : 'none', transition: 'transform 0.15s ease' }}
    >
      <polyline points="6 9 12 15 18 9"/>
    </svg>
  )
}

function CheckIcon() {
  return (
    <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round" aria-hidden="true">
      <polyline points="20 6 9 17 4 12"/>
    </svg>
  )
}

export function LanguageSelector() {
  const { language, setLanguage } = useLanguage()
  const [open, setOpen] = useState(false)
  const ref = useRef(null)

  useEffect(() => {
    const handler = (e) => {
      if (ref.current && !ref.current.contains(e.target)) setOpen(false)
    }
    document.addEventListener('mousedown', handler)
    return () => document.removeEventListener('mousedown', handler)
  }, [])

  return (
    <div className={styles.root} ref={ref}>
      <button
        className={styles.trigger}
        onClick={() => setOpen(o => !o)}
        aria-haspopup="listbox"
        aria-expanded={open}
        aria-label="Select language"
      >
        <GlobeIcon />
        <span>{language.toUpperCase()}</span>
        <ChevronIcon open={open} />
      </button>

      {open && (
        <ul className={styles.dropdown} role="listbox">
          {LANGUAGES.map(lang => (
            <li key={lang.code} role="option" aria-selected={lang.code === language}>
              <button
                className={`${styles.option} ${lang.code === language ? styles.selected : ''}`}
                onClick={() => { setLanguage(lang.code); setOpen(false) }}
              >
                <span>{lang.label}</span>
                {lang.code === language && <CheckIcon />}
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}
