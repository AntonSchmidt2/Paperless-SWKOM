FROM nginx
COPY Frontend /usr/share/nginx/html
COPY Frontend/cors.conf /etc/nginx/conf.d/cors.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
