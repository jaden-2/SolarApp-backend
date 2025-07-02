
## 📁 Recommended File Structure

```
project-root/
├── README.md
├── docker-compose.yml
├── src/
└── ...
```

---

## ✅ `README.md` (Project Overview)

````markdown
# 🔆 Solar Backend API

This is the backend service for the Solar Estimation & Recommendation System, built with Spring Boot, PostgreSQL, and Docker. 
It allows authenticated users to estimate solar needs, recommend real-world components from a database, generate reports, and export PDFs.

---

## 🚀 Key Features

- Secure cookie-based authentication with token rotation
- Real-world solar system recommendation logic
- Dynamic report generation with PDF & versioning
- Admin-grade employee and attendance management

---

## ⚙️ Tech Stack

- Java 21, Spring Boot
- PostgreSQL
- Docker + Docker Compose
- Swagger (OAS 3.0)

---

## 🐳 Running Locally

```bash
docker-compose up --build
````

API runs at: `http://localhost:8080`

Swagger Docs: [`/swagger/api-docs`](http://localhost:8080/swagger/api-docs)


## 🧾 Environment Variables

Configured in `application.yml` or `docker-compose.yml`:

| Variable      | Description            |
| ------------- | ---------------------- |
| `DB_USER`     | PostgreSQL username    |
| `DB_PASS`     | PostgreSQL password    |
| `JWT_SECRET`  | JWT signing secret     |
| `SERVER_PORT` | Port (default: `8080`) |

---

## 📄 License

MIT License — use freely, attribution appreciated.

---

## 👤 Author

**Sylvanus Jedidiah**
🌐 [LinkedIn](https://www.linkedin.com/in/sylvanus-jedidiah/)
📧 [Send Email](mailto:jedidiahamonia1@gmail.com)

````

---

## ✅ `API_DOCUMENTATION.md` (Full API Reference)

```markdown
# 📘 API Documentation — Solar Estimation & Recommendation

**Version:** 1.0  
**Spec:** OpenAPI 3.0  
**Docs Endpoint:** [`/swagger/api-docs`](http://localhost:8080/swagger/api-docs)

---

## 🔐 Authentication

| Endpoint        | Method | Description      |
|----------------|--------|------------------|
| `/auth/login`  | POST   | Login with credentials |
| `/auth/refresh`| GET    | Refresh token |
| `/auth/logout` | GET    | Logout and clear session |
| `/account/signup` | POST | Create a new user |
| `/account/update` | PUT | Update account info |

---

## 📊 Report Endpoints

| Endpoint                            | Method | Description |
|-------------------------------------|--------|-------------|
| `/reports`                          | GET    | Get all reports for current user |
| `/reports/{reportId}`              | GET    | Fetch a specific report |
| `/reports/generate`                | POST   | Generate a new report from estimator input |
| `/reports/{reportId}`             | PUT    | Update an existing report |
| `/reports/{reportId}`             | DELETE | Delete a report |
| `/reports/{reportId}/pdf`         | GET    | Download PDF version |
| `/reports/{reportId}/versions`    | GET    | Get version history |
| `/reports/{reportId}/versions/{version}` | GET | Get a specific version |
| `/reports/{reportId}/versions/{version}` | DELETE | Delete a version |

### 🔁 Replace Components in Report
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/reports/{reportId}/inverter`    | PATCH | Replace inverter |
| `/reports/{reportId}/controller`  | PATCH | Replace charge controller |
| `/reports/{reportId}/battery`     | PATCH | Replace battery |
| `/reports/{reportId}/array`       | PATCH | Replace panel array |

---

## 📦 Resource Endpoints

| Type         | List Endpoint               | By ID Endpoint                         |
|--------------|-----------------------------|----------------------------------------|
| Panels       | `/resources/panels`         | `/resources/panels/{panelId}`         |
| Inverters    | `/resources/inverters`      | `/resources/inverters/{inverterId}`   |
| Controllers  | `/resources/controllers`    | `/resources/controllers/{controllerId}`|
| Breakers     | `/resources/breakers`       | `/resources/breakers/{breakerId}`     |
| Batteries    | `/resources/batteries`      | `/resources/batteries/{batteryId}`    |
| Template     | `/resources/template`       | —                                      |

---

## 📑 Models & Schemas

- `ReportDTO`, `EstimatorRequest`, `CreatorRequest`
- `Panel`, `Inverter`, `Battery`, `ChargeController`, `Breaker`
- `ArrayDTO`, `ControllerDTO`, `BatteryDTO`, `InverterDTO`
- `ArraySpecs`, `BatterySpecs`, `BreakerSpecs`, `ControllerSpecs`, `InverterSpecs`
- `Configuration`, `TechSheet`, `MechSheet`, `WireDetails`, `WireSpec`

---

## 📄 Notes

- All protected endpoints require authentication via secure cookie-based JWTs.
- Reports support version control and PDF export for traceability.
- Swagger UI available for interactive testing (if enabled in prod/dev profile).

---
````
## 👤 Author

**Sylvanus Jedidiah**  
🌐 [LinkedIn](https://www.linkedin.com/in/sylvanus-jedidiah/)  
📧 [Send Email](mailto:jedidiahamonia1@gmail.com)
````

