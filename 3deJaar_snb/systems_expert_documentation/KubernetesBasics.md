# Deploy ad App

## kubectl basics

### View the nodes in the cluster

```bash
kubectl get nodes
```

## Deploy an app

### Deploy the app

```bash
kubectl create deployment kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1
```

### list your deploynments

```bash
kubectl get deployments
```

## View the app

### proxy

The kubectl proxy command can create a proxy that will forward communications into the cluster-wide, private network.

**You need to open a second terminal window to run the proxy.**

```bash
kubectl proxy
```

### store the pod name in an environment variable POD_NAME

```bash
export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
echo Name of the Pod: $POD_NAME
```

### You can access the Pod through the proxied API

```bash
curl http://localhost:8001/api/v1/namespaces/default/pods/$POD_NAME:8080/proxy/
```

# Explore your App

## Troubleshooting with kubectl

### list resources

```bash
kubectl get
```

### show detailed information about a resource

```bash
kubectl describe
```

### print the logs from a container in a pod

```bash
kubectl logs
```

### execute a command on a container in a pod

```bash
kubectl exec
```

# Expose your app publicly

## Step 1: Creating a new Service

### Create a new service and expose it to external traffic

```bash
kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080
```

### get the minikube ip and node port

```bash
minikube ip
kubectl describe services/kubernetes-bootcamp
```

### we can test if the app is exposed outside of the cluster using curl

```bash
curl http://[minikube ip]:[node port]
```

## Step 2: Using labels

### The deployment created automatically a label for our Pod. you can see the name of that label

```bash
kubectl describe deployment
```

### use this label to query our list of Pods.

```bash
kubectl get pods -l app=kubernetes-bootcamp
```

### you can do the same to list the existing Services

```bash
kubectl get services -l app=kubernetes-bootcamp
```

### To apply a new label.

```bash
kubectl label pods [Pod name] version=v1
```

## Step 3: Deleting a service

### delete services. Labels can also be used here

```bash
kubectl delete service -l app=kubernetes-bootcamp
```

# Scale your app

## Scaling a deployment

### show the ReplicaSet created by the deployment

```bash
kubectl get rs
```

### scale the deployment

```bash
kubectl scale deployments/kubernetes-bootcamp --replicas=4
```

### see all replicas of the deployment

```bash
kubectl get pods -o wide
```

# Update your app

## Update the version of the app

### update the image of the application to version 2

```bash
kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=docker.io/jocatalin/kubernetes-bootcamp:v2
```

### confirm the update

```bash
kubectl rollout status deployment/kubernetes-bootcamp
```

### roll back the deployment to your last working version

```bash
kubectl rollout undo deployments/kubernetes-bootcamp
```
