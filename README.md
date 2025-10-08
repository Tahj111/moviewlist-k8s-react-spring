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
- Clean, responsive UI
- Create, Read, Update, Delete operations
- Real-time data display
- Form validation
- Error handling

## Local Development

### Backend Setup
```bash
cd assignment-2-service-layer
./gradlew bootRun
```
API will be available at: http://localhost:8080

### Frontend Setup
```bash
cd react-app
npm install
npm start
```
Frontend will be available at: http://localhost:3000

## Containerization

### Build Spring Boot Container
```bash
cd assignment-2-service-layer
docker build -t your-dockerhub-username/spring-microservice:1.0.0 .
docker push your-dockerhub-username/spring-microservice:1.0.0
```

### Build React Container
```bash
cd react-app
docker build -t your-dockerhub-username/react-frontend:1.0.0 .
docker push your-dockerhub-username/react-frontend:1.0.0
```

## Kubernetes Deployment

### Prerequisites
- Ensure Docker Desktop Kubernetes is running
- Update image names in YAML files with your Docker Hub username

### Deploy Services
```bash
# Deploy Spring microservice
kubectl apply -f k8s/spring-deployment.yaml
kubectl apply -f k8s/spring-service.yaml

# Deploy React frontend
kubectl apply -f k8s/react-deployment.yaml
kubectl apply -f k8s/react-service.yaml
```

### Verify Deployment
```bash
kubectl get all
```

You should see:
- 2 Deployments (spring-microservice, react-frontend)
- 2 ReplicaSets
- 4 Pods total (2 for each service)
- 2 Services

### Access Application
```bash
# Get the NodePort for React frontend
kubectl get service react-service
```

Open your browser to: `http://localhost:30080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items` | Get all items |
| GET | `/api/items/{id}` | Get item by ID |
| POST | `/api/items` | Create new item |
| PUT | `/api/items/{id}` | Update existing item |
| DELETE | `/api/items/{id}` | Delete item |

### Item Model
```json
{
  "id": "number",
  "title": "string",
  "description": "string",
  "category": "string",
  "status": "ACTIVE|INACTIVE|ARCHIVED",
  "tags": ["string"],
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

## Testing

### CRUD Operations Testing
1. **Create**: Add a new item using the form
2. **Read**: View all items in the grid
3. **Update**: Click "Edit" on an item, modify, and save
4. **Delete**: Click "Delete" on an item and confirm

### Verification
- Data persists after browser refresh
- All operations work correctly
- UI updates immediately after operations

## Cleanup

```bash
# Delete all resources
kubectl delete -f k8s/

# Or delete by labels
kubectl delete deployment spring-microservice react-frontend
kubectl delete service spring-service react-service
```

## Troubleshooting

### Common Issues

1. **Pods not starting**: Check image names and ensure they're pushed to Docker Hub
2. **Services not accessible**: Verify NodePort assignment and firewall settings
3. **API calls failing**: Check service discovery and environment variables

### Debug Commands
```bash
# Check pod status
kubectl get pods

# View pod logs
kubectl logs <pod-name>

# Check service endpoints
kubectl get endpoints

# Port forward for testing
kubectl port-forward svc/spring-service 8080:8080
```

## Screenshots

Include the following screenshots in your submission:

1. `kubectl get all` output showing all resources running
2. Browser screenshot showing the React UI with data
3. Browser screenshot showing a CRUD operation in progress

## Technologies Used

- **Backend**: Spring Boot 3.x, Java 17, Gradle
- **Frontend**: React 18, Axios, CSS3
- **Containerization**: Docker, Docker Hub
- **Orchestration**: Kubernetes, Docker Desktop
- **Development**: VS Code, Git

## Assignment Requirements Met

- ✅ Spring Boot microservice with RESTful CRUD API
- ✅ React frontend with clean UI for all CRUD operations
- ✅ Containerized applications (Spring + NGINX for React)
- ✅ Kubernetes deployment with 2 replicas each
- ✅ Service discovery between frontend and backend
- ✅ NodePort service for external access
- ✅ Complete documentation and deployment instructions

## AI Collaboration

This assignment was completed with assistance from AI tools for:
- React application structure and component design
- Kubernetes manifest creation and best practices
- Docker container optimization
- Documentation formatting and completeness

All code has been reviewed and tested to ensure functionality and adherence to best practices.
