FROM nginx
COPY ./static /usr/share/nginx/html
COPY ./cors.conf /etc/nginx/conf.d/cors.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
