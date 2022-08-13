export function saveItem(key:any, value:any) {
  localStorage.setItem(key, value);
}

export function loadItem(key:any) {
  return localStorage.getItem(key);
}
