const BASE_URL = 'http://localhost:8080/api/articles'

export const articleService = {
  getAll: () => fetch(BASE_URL).then(r => {
    if (!r.ok) throw new Error(`HTTP error! Status: ${r.status}`)
    return r.json()
  }),
  getById: (id) => fetch(`${BASE_URL}/${id}`).then(r => {
    if (r.status === 404) return null
    if (!r.ok) throw new Error(`HTTP error! Status: ${r.status}`)
    return r.json()
  }),
}

