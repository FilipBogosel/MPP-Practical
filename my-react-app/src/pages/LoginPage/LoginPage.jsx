import { useState } from 'react'
import { useAuth } from '../../context/AuthContext.jsx'
import logo from '../../assets/UBB.png'
import styles from './LoginPage.module.css'

export function LoginPage({ onNavigate }) {
  const { login } = useAuth()
  const [form, setForm] = useState({ username: '', password: '' })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const set = (field) => (e) => setForm(f => ({ ...f, [field]: e.target.value }))

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      await login(form.username, form.password)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className={styles.page}>
      <div className={styles.brand}>
        <img src={logo} alt="" className={styles.logo} />
        <h1 className={styles.name}>Teoria Transpirației</h1>
        <p className={styles.tagline}>Publicație studențească · UBB</p>
      </div>

      <div className={styles.panel}>
        <form className={styles.form} onSubmit={handleSubmit} noValidate>
          <h2 className={styles.heading}>Autentificare</h2>

          <label className={styles.label}>
            Utilizator
            <input
              className={styles.input}
              type="text"
              value={form.username}
              onChange={set('username')}
              autoComplete="username"
              required
            />
          </label>

          <label className={styles.label}>
            Parolă
            <input
              className={styles.input}
              type="password"
              value={form.password}
              onChange={set('password')}
              autoComplete="current-password"
              required
            />
          </label>

          {error && <p className={styles.error}>{error}</p>}

          <button className={styles.submit} type="submit" disabled={loading}>
            {loading ? 'Se conectează…' : 'Intră în cont'}
          </button>

          <p className={styles.switch}>
            Nu ai cont?{' '}
            <button type="button" className={styles.link} onClick={() => onNavigate('register')}>
              Înregistrează-te
            </button>
          </p>
        </form>
      </div>
    </div>
  )
}
