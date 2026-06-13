import { useState, useEffect, useMemo } from 'react'
import { articleService } from '../services/articleService.js'
import { useLanguage } from '../context/LanguageContext.jsx'
import { localizeArticle } from '../utils/localizeArticle.js'

export function useArticles() {
  const { language } = useLanguage()
  const [rawArticles, setRawArticles] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    articleService
      .getAll()
      .then(setRawArticles)
      .catch(setError)
      .finally(() => setLoading(false))
  }, [])

  const articles = useMemo(
    () => rawArticles.map(a => localizeArticle(a, language)),
    [rawArticles, language]
  )

  return { articles, loading, error }
}
