import logo from '../../assets/UBB.png'
import styles from './Header.module.css'

export function Header() {
  return (
    <div className={styles.header}>
      <img src={logo} alt="" className={styles.logo} />
      <span className={styles.title}>Teoria Transpirației</span>
    </div>
  )
}
