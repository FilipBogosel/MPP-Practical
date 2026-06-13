/**
 * @typedef {Object} Author
 * @property {string} id
 * @property {string} name
 * @property {string} role
 * @property {string|null} avatarUrl
 */

/**
 * @typedef {Object} Article
 * @property {string} id
 * @property {string} title
 * @property {string} summary           - Short excerpt for the nav list
 * @property {string} content           - Full body; paragraphs separated by \n\n
 * @property {Author} author
 * @property {string} category
 * @property {string[]} tags
 * @property {string} publishedAt       - ISO 8601
 * @property {string|null} updatedAt    - ISO 8601 or null
 * @property {string|null} imageUrl
 * @property {number} readTimeMinutes
 */
