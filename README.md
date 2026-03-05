# SISTEMA DE TRAZABILIDAD DE PRODUCTOS 📄 

Sistema API REST que permite gestionar la trazabilidad de productos a lo largo de su ciclo de vida.
Podemos registrar productos, lotes asociados y además los eventos que describen el estado del lote.

## Inicio Rápido 🚀
Sigue estos pasos para poner en marcha el proyecto

### EC2 🛠️

- Arrancar el contenedor MariaDB:  
```bash
podman start mariadb_api
```

- Entrar en el proyecto:
```bash
cd ProductosApiPractica2026/build/libs

java -jar ProductosApiPractica-0.0.1-SNAPSHOT.jar 
```

### Desde Insomnia 🔐

1. Registrar un usuario nuevo:
http://44.216.7.114:8080/auth/register

```bash
{
	"username": "",
	"email": "",
	"password": "",
	"nombre": ""
}
```

2. Login con el usuario creado: 
http://44.216.7.114:8080/auth/login

```bash
{
	"username": "",
	"password": ""
}
```

2.1 Quédate con el token devuelto para introducirlo en todos los endpoints:

```bash
{
    "username": "",
    "authorities": [
        "READ"
    ],
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzcyNzMxMTUwLCJleHAiOjE3NzI4MTc1NTAsInVzZXJuYW1lIjoiSnVhbiIsImVtYWlsIjoianVhbkBnbWFpbC5jb20ifQ.M0JboxK9kn2eRpuLZZZNpgbjtkBAnSq-bLeVm8-5u_A"
}
```

### Endpoints principales 📖

#### Auth  

| Método             | Endpoint               | Descripción                    |
| ------------------ | ---------------------- | :----------------------------- |
| POST               | /auth/register         | Registro de usuarios           |
| POST               | /auth/login            | Login y obtención de token     |

#### Productos  

| Método             | Endpoint               | Descripción                    |
| ------------------ | ---------------------- | :----------------------------- |
| GET                | /api/productos         | Listar todos los productos     |
| POST               | /api/productos         | Crear producto                 |
| GET                | /api/productos/{id}    | Detalle de un producto         |
| PUT                | /api/productos/{id}    | Actualizar producto            |
| DELETE             | /api/productos/{id}    | Eliminar producto              |

#### Lotes  

| Método             | Endpoint               | Descripción                    |
| ------------------ | ---------------------- | :----------------------------- |
| GET                | /api/productos/{id}/lotes | Listar lotes de un producto |
| POST               | /api/productos/{id}/lotes | Crear lote                  |
| GET                | /api/lotes/{id}        | Detalle de un lote             |
| PATCH              | /api/lotes/{id}/estado | Actualizar estado de un lote   |

#### Eventos  

| Método             | Endpoint               | Descripción                    |
| ------------------ | ---------------------- | :----------------------------- |
| POST               | /api/lotes/{id}/eventos | Registrar evento en un lote   |
| GET                | /api/lotes/{id}/eventos | Historial completo de eventos |
| GET                | /api/lotes/{id}/eventos?inicio=...&fin=... | Filtrar por rango de fechas |
| GET                | /api/lotes/{id}/eventos?tipoEvento=...&inicio=...&fin=... | Filtrar por tipo y fechas |


### Tecnologías utilizadas 🎯
- Java 25
- Spring Boot
- MariaDB
- Podman
- AWS
















