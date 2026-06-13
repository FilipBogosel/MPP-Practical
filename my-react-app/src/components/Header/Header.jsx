import { useAuth } from '../../context/AuthContext.jsx'
import logo from '../../assets/UBB.png'
import styles from './Header.module.css'

const ROLE_COLORS = {
  ADMIN:      '#dc2626',
  EDITOR:     '#d97706',
  JOURNALIST: '#2563eb',
  USER:       '#16a34a',
}

export function Header() {
  const { user, logout } = useAuth()
  const roleColor = user ? (ROLE_COLORS[user.role] ?? '#888') : null

  return (
    <div className={styles.header}>
      <img src={logo} alt="" className={styles.logo} />
      <span className={styles.title}>Teoria Transpirației</span>

      {user && (
        <div className={styles.userArea}>
          <span className={styles.badge} style={{ background: roleColor }}>
            {user.role.charAt(0) + user.role.slice(1).toLowerCase()}
          </span>
          <span className={styles.username}>{user.username}</span>
          <button className={styles.logout} onClick={logout} type="button">
            Ieșire
          </button>
        </div>
      )}
    </div>
  )
}
