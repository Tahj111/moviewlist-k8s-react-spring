Submission summary for moviewlist-k8s-react-spring

Challenges and fixes
- YAML indentation and incorrect nginx config initially prevented the React deployment from starting and proxying API requests correctly. Fixed indentation in `k8s/react-deployment.yaml` and added a `react-nginx-configmap.yaml` to proxy `/api` to `spring-service:8080`.
- One Spring pod got stuck terminating; I force-deleted the stuck pod so the deployment could stabilize.
- Docker Desktop NodePort wasn't reachable from macOS host. I used `kubectl port-forward` to make the frontend available at `http://localhost:30000` for testing and screenshots.

AI tools used
- I used AI coding assistants (GitHub Copilot and other AI helpers) to suggest YAML fixes, generate the nginx ConfigMap, and craft the README and submission summary text. The AI helped speed up debugging by suggesting likely causes and commands; all code changes were reviewed and tested locally.

How to reproduce
1. Clone the repo: `git clone https://github.com/Tahj111/moviewlist-k8s-react-spring.git`
2. Apply manifests: `kubectl apply -f k8s/`
3. Port-forward React: `kubectl port-forward pod/$(kubectl get pods -l app=react-frontend -o jsonpath='{.items[0].metadata.name}') 30000:80 --address 127.0.0.1`
4. Open `http://localhost:30000` and exercise CRUD endpoints in the UI.

Files included
- Spring and React source code
- Dockerfiles for both services
- Kubernetes YAML manifests (2 Deployments, 2 Services, 1 ConfigMap)
- README.md and this SUBMISSION_SUMMARY.md
