import { formatShortDate } from '../../utils/formatDate.js'
import { useLanguage } from '../../context/LanguageContext.jsx'
import styles from './ArticleNavItem.module.css'

export function ArticleNavItem({ article, isActive, onClick }) {
  const { t, locale } = useLanguage()
  const category = t(`categories.${article.category}`)
  return (
    <button
      className={`${styles.item} ${isActive ? styles.active : ''}`}
      onClick={() => onClick(article.id)}
      aria-current={isActive ? 'page' : undefined}
    >
      <span className={styles.category}>{category}</span>
      <span className={styles.title}>{article.title}</span>
      <span className={styles.meta}>
        {article.author.name} · {formatShortDate(article.publishedAt, locale)}
      </span>
    </button>
  )
}
