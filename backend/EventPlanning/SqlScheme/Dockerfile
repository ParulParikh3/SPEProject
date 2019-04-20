FROM mysql

ENV MYSQL_DATABASE techevent

COPY ./subschema/ /docker-entrypoint-initdb.d/



docker run --name sql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -d newimage1
docker run -d -p 3250:3306 newimage1
