#!/bin/bash

k3d cluster create pe-cluster -p "8088:30080@agent:0" --agents 1
kubectl apply -f .