/**
 * Flattens a raw article's localized fields into a single language.
 * Falls back to 'ro' if the requested language is missing.
 *
 * When connecting to a real API, move this logic to a query param (?lang=en)
 * and remove this helper — the server will return already-flat objects.
 */
export function localizeArticle(article, language) {
  const pick = (field) => field?.[language] ?? field?.ro ?? field

  return {
    ...article,
    title:   pick(article.title),
    summary: pick(article.summary),
    tags:    pick(article.tags),
    author: {
      ...article.author,
      role: pick(article.author.role),
    },
  }
}
