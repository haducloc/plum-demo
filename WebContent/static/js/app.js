function fetchApi(apiUrl, queryParams) {
  const url = new URL(apiUrl);

  if (queryParams) {
    for (const key in queryParams) {
      if (queryParams[key] !== null) {
        url.searchParams.append(key, queryParams[key]);
      }
    }
  }

  return fetch(url)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error(`HTTP Error: ${response.status} ${response.statusText}`);
      }
    })
    .then(data => {
      return data;
    })
    .catch(error => {
      throw new Error(`Error: ${error.message}`);
    });
}
