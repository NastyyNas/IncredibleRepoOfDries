#!/bin/sh

sed -i "s/env: '<ENV_NAME>',/env: '$RUM_ENV',/" /app/templates/base.html

jinja2 /etc/nginx/templates/default.j2 -o /etc/nginx/sites-available/default -D API_URL="$API_URL"

rm -f /etc/nginx/sites-enabled/default

ln -s /etc/nginx/sites-available/default /etc/nginx/sites-enabled/

service nginx start

gunicorn -b 0.0.0.0:8000 app:app