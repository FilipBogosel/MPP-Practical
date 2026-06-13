import { formatLongDate } from '../../utils/formatDate.js'
import { useLanguage } from '../../context/LanguageContext.jsx'
import styles from './ArticleView.module.css'

export function ArticleView({ article }) {
  const { t, locale } = useLanguage()

  if (!article) {
    return (
      <div className={styles.empty}>
        <p>{t('article.selectPrompt')}</p>
      </div>
    )
  }

  const paragraphs = article.content.split('\n\n').filter(Boolean)
  const category = t(`categories.${article.category}`)

  return (
    <article className={styles.article}>
      <header className={styles.header}>
        <span className={styles.category}>{category}</span>
        <h1 className={styles.title}>{article.title}</h1>
        <p className={styles.summary}>{article.summary}</p>
        <div className={styles.meta}>
          <span className={styles.author}>
            <strong>{article.author.name}</strong>
            <span className={styles.role}>{article.author.role}</span>
          </span>
          <span className={styles.divider} aria-hidden="true">·</span>
          <time dateTime={article.publishedAt}>
            {formatLongDate(article.publishedAt, locale)}
          </time>
          <span className={styles.divider} aria-hidden="true">·</span>
          <span>{article.readTimeMinutes} {t('article.minRead')}</span>
          {article.updatedAt && (
            <>
              <span className={styles.divider} aria-hidden="true">·</span>
              <span className={styles.updated}>
                {t('article.updated')} {formatLongDate(article.updatedAt, locale)}
              </span>
            </>
          )}
        </div>
      </header>

      {article.imageUrl && (
        <img src={article.imageUrl} alt="" className={styles.image} />
      )}

      <div className={styles.body}>
        {paragraphs.map((para, i) => (
          <p key={i}>{para}</p>
        ))}
      </div>

      {article.tags.length > 0 && (
        <footer className={styles.footer}>
          {article.tags.map(tag => (
            <span key={tag} className={styles.tag}>{tag}</span>
          ))}
        </footer>
      )}
    </article>
  )
}
