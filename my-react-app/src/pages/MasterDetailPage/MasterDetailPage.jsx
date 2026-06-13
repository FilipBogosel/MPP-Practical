import { useState, useEffect } from 'react'
import { Header } from '../../components/Header/Header.jsx'
import { ArticleNav } from '../../components/ArticleNav/ArticleNav.jsx'
import { ArticleView } from '../../components/ArticleView/ArticleView.jsx'
import { LanguageSelector } from '../../components/LanguageSelector/LanguageSelector.jsx'
import { useArticles } from '../../hooks/useArticles.js'
import { useLanguage } from '../../context/LanguageContext.jsx'
import styles from './MasterDetailPage.module.css'

export function MasterDetailPage() {
  const { articles, loading, error } = useArticles()
  const { t } = useLanguage()
  const [selectedId, setSelectedId] = useState(null)

  useEffect(() => {
    if (articles.length > 0 && selectedId === null) {
      setSelectedId(articles[0].id)
    }
  }, [articles, selectedId])

  const selectedArticle = articles.find(a => a.id === selectedId) ?? null

  return (
    <div className={styles.layout}>
      <Header />

      <div className={styles.body}>
        <aside className={styles.sidebar}>
          {loading && <p className={styles.sidebarMessage}>{t('nav.loading')}</p>}
          {error   && <p className={styles.sidebarMessage}>{t('nav.error')}</p>}
          {!loading && !error && (
            <ArticleNav
              articles={articles}
              activeId={selectedId}
              onSelect={setSelectedId}
            />
          )}
        </aside>

        <main className={styles.main}>
          <div className={styles.topBar}>
            <LanguageSelector />
          </div>
          {loading
            ? <div className={styles.loading}>{t('article.loading')}</div>
            : <ArticleView article={selectedArticle} />
          }
        </main>
      </div>
    </div>
  )
}
