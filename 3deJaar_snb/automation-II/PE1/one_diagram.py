from diagrams import Diagram, Cluster
from diagrams.aws.compute import EC2, AutoScaling
from diagrams.aws.network import ELB, VPC, InternetGateway, NATGateway
from diagrams.aws.network import PublicSubnet, PrivateSubnet

with Diagram("AWS Scalable Web App Infrastructure", show=False):

    # VPC and Internet Gateway
    with Cluster("VPC MijnApp"):
        internet_gateway = InternetGateway("Internet Gateway")

        # ELB in public subnets of both AZ1 and AZ2
        elb = ELB("Elastic Load Balancer")

        # Availability Zone 1 (AZ1)
        with Cluster("Availability Zone 1"):
            public_subnet_1 = PublicSubnet("Public Subnet AZ1")
            private_subnet_1 = PrivateSubnet("Private Subnet AZ1")
            nat_gateway_1 = NATGateway("NAT Gateway AZ1")
            ec2_1 = EC2("EC2 Instance AZ1")

        # Availability Zone 2 (AZ2)
        with Cluster("Availability Zone 2"):
            public_subnet_2 = PublicSubnet("Public Subnet AZ2")
            private_subnet_2 = PrivateSubnet("Private Subnet AZ2")
            nat_gateway_2 = NATGateway("NAT Gateway AZ2")
            ec2_2 = EC2("EC2 Instance AZ2")

        # Auto Scaling Group managing EC2 instances across both AZ1 and AZ2
        auto_scaling = AutoScaling("Auto Scaling Group")

        # Connections
        internet_gateway >> [public_subnet_1, public_subnet_2] >> elb

        # NAT Gateway connections from public subnets to private subnets
        public_subnet_1 >> nat_gateway_1 >> private_subnet_1 >> ec2_1
        public_subnet_2 >> nat_gateway_2 >> private_subnet_2 >> ec2_2

        # ELB distributing traffic directly to EC2 instances managed by ASG
        elb >> [ec2_1, ec2_2]
        elb >> auto_scaling

        # Auto Scaling Group connected to EC2 instances in both AZs
        auto_scaling >> [ec2_1, ec2_2]
