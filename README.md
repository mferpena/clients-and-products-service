# Build project

```bash
chmod +x build_projects.sh
./build_projects.sh
```

# Exec project

```bash
chmod +x exec_projects.sh
./exec_projects.sh
```

# Postgres relational database container

```bash
docker run -d \
  --name postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=postgres \
  -p 5432:5432 \
  postgres
```

# Access to the H2 Console

url: jdbc:h2:mem:testdb

```bash
start http://localhost:8080/h2-console
```

# APIS to Client Project

### Create Client

```bash
curl -X POST http://localhost:8080/api/v1/client \
-H 'Content-Type: application/json' \
-d '{
  "name": "Juan",
  "paternalLastName": "Pérez",
  "maternalLastName": "López"
}'
```

### List Client

```bash
curl -X GET http://localhost:8080/api/v1/client
```

### Update Client

```bash
curl -X PUT http://localhost:8080/api/v1/client \
-H 'Content-Type: application/json' \
-d '{
  "name": "Juan Update",
  "paternalLastName": "Pérez",
  "maternalLastName": "López"
}'
```