# Web Layer Dockerfile
FROM python:3.9

RUN apt-get update && apt-get install -y nginx gettext-base && apt-get install -y python3-pip && apt-get clean
RUN pip3 install jinja2-cli
WORKDIR /app

COPY requirements.txt /app/
RUN pip install --no-cache-dir -r requirements.txt gunicorn

COPY . /app

RUN mkdir /etc/nginx/templates
COPY default.j2 /etc/nginx/templates/default.j2

COPY startup.sh /app/startup.sh
RUN chmod +x /app/startup.sh

EXPOSE 80

ENTRYPOINT ["/app/startup.sh"]