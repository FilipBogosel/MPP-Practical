import { ArticleNavItem } from '../ArticleNavItem/ArticleNavItem.jsx'
import { useLanguage } from '../../context/LanguageContext.jsx'
import styles from './ArticleNav.module.css'

export function ArticleNav({ articles, activeId, onSelect }) {
  const { t } = useLanguage()
  return (
    <nav className={styles.nav} aria-label={t('nav.articles')}>
      <p className={styles.label}>{t('nav.articles')}</p>
      <ul className={styles.list}>
        {articles.map(article => (
          <li key={article.id}>
            <ArticleNavItem
              article={article}
              isActive={article.id === activeId}
              onClick={onSelect}
            />
          </li>
        ))}
      </ul>
    </nav>
  )
}
