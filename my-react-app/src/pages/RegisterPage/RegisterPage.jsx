import { useState } from 'react'
import { useAuth } from '../../context/AuthContext.jsx'
import logo from '../../assets/UBB.png'
import styles from './RegisterPage.module.css'

const ROLES = [
  { value: 'ADMIN',      label: 'Admin',      color: '#dc2626' },
  { value: 'EDITOR',     label: 'Editor',     color: '#d97706' },
  { value: 'JOURNALIST', label: 'Journalist', color: '#2563eb' },
  { value: 'USER',       label: 'User',       color: '#16a34a' },
]

export function RegisterPage({ onNavigate }) {
  const { register } = useAuth()
  const [form, setForm] = useState({
    username: '', email: '', password: '', confirm: '', role: 'USER',
  })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const set = (field) => (e) => setForm(f => ({ ...f, [field]: e.target.value }))

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    if (form.password !== form.confirm) {
      setError('Parolele nu coincid.')
      return
    }
    setLoading(true)
    try {
      await register(form.username, form.email, form.password, form.role)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const selectedRole = ROLES.find(r => r.value === form.role)

  return (
    <div className={styles.page}>
      <div className={styles.brand}>
        <img src={logo} alt="" className={styles.logo} />
        <h1 className={styles.name}>Teoria Transpirației</h1>
        <p className={styles.tagline}>Publicație studențească · UBB</p>
      </div>

      <div className={styles.panel}>
        <form className={styles.form} onSubmit={handleSubmit} noValidate>
          <h2 className={styles.heading}>Cont nou</h2>

          <label className={styles.label}>
            Utilizator
            <input className={styles.input} type="text" value={form.username}
              onChange={set('username')} autoComplete="username" required />
          </label>

          <label className={styles.label}>
            Email
            <input className={styles.input} type="email" value={form.email}
              onChange={set('email')} autoComplete="email" required />
          </label>

          <label className={styles.label}>
            Parolă
            <input className={styles.input} type="password" value={form.password}
              onChange={set('password')} autoComplete="new-password" required />
          </label>

          <label className={styles.label}>
            Confirmă parola
            <input className={styles.input} type="password" value={form.confirm}
              onChange={set('confirm')} autoComplete="new-password" required />
          </label>

          <div className={styles.roleGroup}>
            <span className={styles.roleLabel}>Rol</span>
            <div className={styles.roles}>
              {ROLES.map(r => (
                <label
                  key={r.value}
                  className={styles.roleOption}
                  style={{ '--role-color': r.color }}
                  data-active={form.role === r.value}
                >
                  <input
                    type="radio"
                    name="role"
                    value={r.value}
                    checked={form.role === r.value}
                    onChange={set('role')}
                    className={styles.roleRadio}
                  />
                  <span className={styles.roleDot} />
                  {r.label}
                </label>
              ))}
            </div>
          </div>

          {error && <p className={styles.error}>{error}</p>}

          <button
            className={styles.submit}
            type="submit"
            disabled={loading}
            style={{ '--role-color': selectedRole.color }}
          >
            {loading ? 'Se creează contul…' : 'Creează cont'}
          </button>

          <p className={styles.switch}>
            Ai deja cont?{' '}
            <button type="button" className={styles.link} onClick={() => onNavigate('login')}>
              Autentifică-te
            </button>
          </p>
        </form>
      </div>
    </div>
  )
}
