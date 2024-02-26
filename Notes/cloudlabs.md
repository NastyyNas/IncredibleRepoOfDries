# Documentatie Labs Cloud

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

### Create rule for security group

```bash
aws ec2 authorize-security-group-ingress --group-name ssh-access-cli --protocol tcp --port 22 --cidr 0.0.0.0/0
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
aws ec2 create-image --instance-id <value>
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
