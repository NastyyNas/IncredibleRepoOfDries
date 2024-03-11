![image](https://github.com/DriesMelottePXL/IncredibleRepoOfDries/assets/75361303/dc43beac-bfeb-4cff-99ee-6a9e2ddb3fec)# Documentatie Labs Cloud

# Introduction Lab

### Install AWS CLI

```bash
msiexec.exe /i [https://awscli.amazonaws.com/AWSCLIV2.msi](https://awscli.amazonaws.com/AWSCLIV2.msi)
```

### Configure AWS CLI

```
aws configure
AWS Access Key ID [None]: AKIAIOSFODNN7EXAMPLE
AWS Secret Access Key [None]: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
Default region name [None]: us-east-1
Default output format [None]: json
```

~/.aws/credentials
voeg de credentials toe die te vinden zijn bij lab details op canvas

# Compute Lab

### Create security group

```bash
aws ec2 create-security-group --group-name ssh-access-cli --description "sec group for ssh access from anywhere”
```

### Create  rule for security group

```bash
aws ec2 authorize-security-group-ingress --group-name ssh-access-cli --protocol tcp --port 22 --cidr 0.0.0.0/0
```
inbound = ingress

outbound = egress


### Create private key file (herbekijken)
```bash
aws ec2 create-key-pair --key-name vockey --query 'KeyMaterial' --output text > vockey.pem
```

### Create ec2 instance

```bash
aws ec2 run-instances --image-id **<ami-id>** --instance-type t2.micro --key-name vockey --security-group-ids **<security-group-id>** --tag-specifications 'ResourceType=instance, Tags=[{Key=Name, Value=my-first-cli-ec2}]' --count 1
```

zoek de image id van amazon linux plaats deze in de plaats van \<ami-id>:

```bash
aws ec2 describe-images --owners self amazon
```

zoek de security group id plaats deze in de plaats van <security-group-id>:

```bash
aws ec2 describe-security-groups
```

### Create AMI of another instance

```bash
aws ec2 create-image --instance-id <value> --name <name>
```

# Storage Lab

### Make bucket

```bash
aws s3api create-bucket --bucket my-bucket --region us-east-1
```

### make a directory in the bucket

```bash
aws s3api put-object --bucket bucket-name --key foldername/
```

### Do a ls in the bucket

```bash
aws s3 ls s3://bucket-name
```

### Copy file to directory in bucket

```bash
aws s3 cp file-name s3://bucket-name/directory-name/
```

### Create a new dir and sync this folder with docker mounted volume

```bash
aws s3api put-object --bucket my-bucket --key backend-data/
```

```bash
aws s3 sync ~/init.sql/ s3://my-bucket/backend-data --acl public-read
```

# Networking 1 Lab

### Create VPC

```bash
aws ec2 create-vpc --cidr-block 10.0.0.0/16 --tag-specifications 'ResourceType=vpc,Tags=[{Key=Name,Value=awsgen-cli-vpc}]'
```

### Create subnet

```bash
aws ec2 create-subnet --vpc-id <VPC_ID> --cidr-block 10.0.1.0/24 --tag-specifications 'ResourceType=subnet,Tags=[{Key=Name,Value=Subnet-1}]'
```

### Create internet gateway

```bash
aws ec2 create-internet-gateway --tag-specifications 'ResourceType=internet-gateway,Tags=[{Key=Name,Value=MyIGW}]'
```

### Attach internet gateway

```bash
aws ec2 attach-internet-gateway --vpc-id <VPC_ID> --internet-gateway-id <IGW_ID>
```

### Create route table for public subnet

```bash
aws ec2 create-route-table --vpc-id <VPC_ID> --tag-specifications 'ResourceType=route-table,Tags=[{Key=Name,Value=Public-Route-Table}]' --query 'RouteTable.RouteTableId' --output text
```

### Create route to internet gateway in public route table

```bash
aws ec2 create-route --route-table-id $PUBLIC_ROUTE_TABLE_ID --destination-cidr-block 0.0.0.0/0 --gateway-id <IGW_ID>
```

### Associate public subnet with public route table

```bash
aws ec2 associate-route-table --subnet-id <PUBLIC_SUBNET_ID> --route-table-id $PUBLIC_ROUTE_TABLE_ID
```
