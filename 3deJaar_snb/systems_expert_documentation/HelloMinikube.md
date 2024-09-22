# Hello Minikube Workshop

## Create a minikube cluster

```bash
minikube start
```

## Open the dashboard

open a new terminal, and run:

```bash
minikube dashboard
```

## Create a deployment

### Use the kubectl create command to create that manages a Pod. The Pod runs a Container based on the provided Docker image.

```bash
kubectl create deployment [deployment name] --image=registry.k8s.io/e2e-test-images/agnhost:2.39 -- /agnhost netexec --http-port=8080
```

### View the deployment

```bash
kubectl get deployments
```

### View the Pod

```bash
kubectl get pods
```

### View cluster events

```bash
kubectl get events
```

### View the kubectl configuration

```bash
kubectl config view
```

### View application logs for a container in a pod

```bash
kubectl logs [pod name]
```

## Create a service

### Expose the Pod tothe public internet using kubectl expose command

```bash
kubectl expose deployment [deployment name] --type=LoadBalancer --port=8080
```

### View the service you created

```bash
kubectl get services
```

### serve the app and show the app's response

```bash
minikube service hello-node
```

## Enable addons

### List the currently supported addons

```bash
minikube addons list
```

### Enable an addon, for example, metrics-server

```bash
minikube addons enable metrics-server
```

### View the Pod and Service you created by installing that addon

```bash
kubectl get pod,svc -n kube-system
```

### check the output form metrics-server

```bash
kubectl top pods
```

### Disable the addon

```bash
minikube addons disable metrics-server
```

## Clean up

```bash
kubectl delete service hello-node
kubectl delete deployment hello-node
#Stop the cluster
minikube stop
#Optionally delete the minikube VM
minikube delete
```
