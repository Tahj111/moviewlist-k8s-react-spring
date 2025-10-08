# Item Management System - Full Stack Application

A complete full-stack application with Spring Boot REST API backend and React frontend, deployed on Kubernetes.

## Project Structure

```
moviewlist/
├── assignment-2-service-layer/     # Spring Boot microservice
├── react-app/                      # React frontend application
│   ├── src/
│   ├── public/
│   ├── Dockerfile
│   └── package.json
├── k8s/                           # Kubernetes manifests
│   ├── spring-deployment.yaml
│   ├── spring-service.yaml
│   ├── react-deployment.yaml
│   └── react-service.yaml
└── README.md
```

## Prerequisites

- Docker Desktop with Kubernetes enabled
- kubectl configured to use Docker Desktop cluster
- Docker Hub account
- Java 17+ (for local development)
- Node.js 18+ (for local development)

## Architecture

- **Backend**: Spring Boot REST API with CRUD operations for Item management
- **Frontend**: React application with clean UI for all CRUD operations
- **Containerization**: Both services containerized with Docker
- **Orchestration**: Kubernetes with 2 replicas each for high availability
- **Networking**: Service discovery within Kubernetes cluster

## Features

### Backend (Spring Boot)
- Full CRUD operations for Items
- RESTful API endpoints
- In-memory data storage
- Swagger UI documentation
- Health checks and monitoring

### Frontend (React)
# moviewlist-k8s-react-spring

Small project: React frontend + Spring Boot backend, containerized and deployed to Kubernetes.

## Quick start (Kubernetes / Docker Desktop)

1. Apply manifests:

```bash
kubectl apply -f k8s/
```

2. Port-forward the React frontend to localhost:30000 (for local testing):

```bash
nohup kubectl port-forward pod/$(kubectl get pods -l app=react-frontend -o jsonpath='{.items[0].metadata.name}') 30000:80 --address 127.0.0.1 > /tmp/portforward-react.log 2>&1 & echo $! > /tmp/portforward-react.pid
```

3. Open the UI:

http://localhost:30000

## Files of interest

- `react-app/` — React source, `Dockerfile`, `nginx.conf`
- `assignment-2-service-layer/` — Spring Boot app
- `k8s/` — Kubernetes manifests (deployments, services, ConfigMap)

## Notes
- The React app proxies `/api` to the spring service using an nginx ConfigMap mounted into the container.
- If NodePort is not directly reachable on Docker Desktop, use the port-forward above.
