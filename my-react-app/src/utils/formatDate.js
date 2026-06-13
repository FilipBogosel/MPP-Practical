export const formatShortDate = (iso, locale = 'ro-RO') =>
  new Intl.DateTimeFormat(locale, { day: 'numeric', month: 'short', year: 'numeric' }).format(new Date(iso))

export const formatLongDate = (iso, locale = 'ro-RO') =>
  new Intl.DateTimeFormat(locale, { day: 'numeric', month: 'long', year: 'numeric' }).format(new Date(iso))
