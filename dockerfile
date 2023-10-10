# This copies the Frontend to the image.
# It needs to be ran seperately using
# docker build -t paperless .
# docker run -d -p 8080:80 paperless

FROM nginx
COPY ../Frontend /usr/share/nginx/html

