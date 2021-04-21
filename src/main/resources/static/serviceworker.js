var cacheName = 'v1';

var staticAssets = [
    "/css/main.css",
    "/img/icons/icon-192x192.png",
    "/img/icons/icon-512x512.png",
    "/js/app.js",
    "/favicon.ico",
    "/index.html",
    "/addCall.html",
    "/stats.html",
    "/manifest.json",
    "/serviceworker.js",
    ];


//Cache async
self.addEventListener('install', async event => { //Cache async
    const cache = await caches.open(cacheName);
    await cache.addAll(staticAssets);
});

//https://developers.google.com/web/ilt/pwa/caching-files-with-service-worker

//Caso trocar o Service Worker verificar o cache e eliminar os não necessários
self.addEventListener('activate', async event => {
    console.log('activated');
    event.waitUntil(
        caches.keys().then(function(cacheNames) {
          return Promise.all(
            cacheNames.filter(function(cacheName) {
              // Return true if you want to remove this cache,
              // but remember that caches are shared across
              // the whole origin
            }).map(function(cacheName) {
              return caches.delete(cacheName);
            })
          );
        })
      );
});
//Cache first & not found go network

self.addEventListener('fetch', function(event) {
  event.respondWith(
    caches.open(cacheName).then(function(cache) {
      return cache.match(event.request).then(function (response) {
        return response || fetch(event.request).then(function(response) {
          cache.put(event.request, response.clone());
          return response;
        });
      });
    })
  );
});