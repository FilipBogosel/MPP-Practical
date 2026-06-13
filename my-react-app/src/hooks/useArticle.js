import { useState, useEffect, useMemo } from 'react'
import { articleService } from '../services/articleService.js'
import { useLanguage } from '../context/LanguageContext.jsx'
import { localizeArticle } from '../utils/localizeArticle.js'

export function useArticle(id) {
  const { language } = useLanguage()
  const [rawArticle, setRawArticle] = useState(null)
  const [loading, setLoading] = useState(!!id)
  const [error, setError] = useState(null)

  useEffect(() => {
    if (!id) { setLoading(false); return }
    setLoading(true)
    articleService
      .getById(id)
      .then(setRawArticle)
      .catch(setError)
      .finally(() => setLoading(false))
  }, [id])

  const article = useMemo(
    () => rawArticle ? localizeArticle(rawArticle, language) : null,
    [rawArticle, language]
  )

  return { article, loading, error }
}
