[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/aBt_Z3_I)
# Yet another To-Do Application for the world

## Description
In today's digital era, web applications have become the backbone of countless businesses and services. Developing a well-structured and scalable web application is essential for providing a seamless user experience.

To further our journey towards a seamless cloud environment, we would like to migrate this application to a containerized version that runs in the cloud. We would like you to do this migration for us.

## Architecture

![Architecture Diagram](/images/Architecture.png)
This is the current application architecture. Use this as a benchmark for security, high-availability and performance.

**health check:** The backend contains a health endpoint that can be used as a health check if necessary.

**Information about deployment:** / configuration can always be found in the README.md file of the various components.

## Technology

The assignment is to _automatically_ deploy the application to an AWS environment. We would like you to use AWS services for this.
Consider the buildpipeline for the applications as well, the application- & weblayer need to be dockerized and built, this can for instance be done using GHA.

It is important that the application (both the front and backend!) is always highly available & fault tolerant. Consider things such as autoscaling at extra load / peak times.

The application must be reachable via **one IP address / hostname**.

## Security & compliance

To set up, you will need to keep internal & external traffic separate if possible. Consider the necessary subnets, IP addresses, NAT gateways, etc.

We also use the principle of least privilege. Use sufficient security groups and, if necessary, ACLs. Within the learner lab environment, an IAM role “LabAccess” has been created that you can use to link policies if necessary.

Created virtual machines are managed using a bastion host. Only this machine gets SSH access to all other machines (if need be).

Provide the necessary encryption for data at rest where possible, and also be careful with hardcoding API keys and other sensitive information in your IaC! There are AWS services that can help you with this. 


## Monitoring
Implement Datadog on your application and infrastructure, ensure that both the installation of the DD agent and configuration per environment are deployed with your Cloudformation.

Aggregate relevant metrics on a useful dashboard per environment (Test/Acceptance/Production)

# Evaluation (60p)

## **Minimum requirements:**

### cloud formation(16p)
Considering the purpose of this PE is cloud formation, the expectation is that from 1 (one) command, provided the correct parameter is provided, the application can be deployed in 3 types of environments:

- **Test(1p)**: The application deployed with following constraints:
<br>this is the test environment, cost effeciency is important, therefor not all infrastructure features need to be available:
    - the front- & backend do not need to be high-available for this environment. 
    - The back-end can be accessed by an API that runs on the backend. 
    - The DB may be a standard tst/dev template MySQL/Aurora RDS.
- **Acceptance(5p)**: The application deployed with following constraints:
<br>this is the acceptance environment, therefor all infrastructure features need to be available:
    - the front- & backend need to be high-available for this environment. 
    - the front-end is internet-facing so should be separated from the rest of the infrastructure.
    - The back-end is private, so should be separated from the rest of the infrastructure.
    - Scaling options should be present for both back- and frontend.    
    - The DB may be a standard tst/dev template MySQL/Aurora RDS.

- **Production(10p)**:  The application deployed with following constraints:
<br>this is the acceptance environment, therefor all infrastructure features need to be available:
    - the front- & backend need to be high-available for this environment. 
    - the front-end is internet-facing so should be separated from the rest of the infrastructure.
    - The back-end is private, so should be separated from the rest of the infrastructure.
    - Scaling options should be present for both back- and frontend.
    - The DB may be a standard tst/dev template MySQL/Aurora RDS.

### Application
* A working application where all components are separated (front & backend, and DB) and communicate with each other.
* The application uses a MySQL RDS or Aurora RDS instance as DB
* The application is set up to be highly available.
* Ensure that the “scaling” of all components is optimized in the production deploy.

* All steps taken to achieve the above are clearly documented in 1 document on GitHub, step by step with screenshots where necessary.

### Monitoring(14p)
Implement Datadog on your application and infrastructure, ensure that both the installation of the DD agent and configuration per environment are deployed with your Cloudformation.

Aggregate relevant metrics on a useful dashboard per environment (Test/Acceptance/Production):

* APM metrics
* DB metrics
* RUM metrics
* Autoscaling health metrics

**The above requirements are the **minimum** to come to the evaluation presentation and be graded on extras and documentation.**

## **Extra points** (15p)
insist on the proper application of automation concepts. These will only be assessed if the minimum level has been reached. 


The following factors are taken into account for **automation**:

* Correct use of packaging & deployments (+6p)
* Correct use of layered stacks and modules (+5p)
* Correct use of pseudo-parameters and dynamic references (+2p)
* Correct use Conditions (+2p)


<br><br>

## **Evaluation steps:**
--- 


### **10/12 at 11:00 PM: Deadline for submitting cloud formation, application & documentation:**

This deadline is for both the documentation and submission of the assignment (cloud formation, application), this documentation contains **EVERYTHING** that you have created and want to discuss during your defense.

### **Documentation (5p)**
This documentation is 1 document in your GitHub repository in a human-readable format (markdown preferred). This document provides a clear overview of your solution, after which it zooms in on each component and explains how you built it and how it works together with the rest. Where necessary, use code snippets or screenshots to clarify.

If during the defense your solution is not the same as the one at the time of the deadline, this will be explicitly stated at the start of the defense, with an exhaustive list of changes. 
* Failure to mention results in a '0' for the PE. 
* You will be quoted on the submission as it was at the time of the deadline.
<br><br>

### **Lesson of 11/12 evaluation presentation: (10p)**

The timetable for the defenses will be available on Blackboard the week before the presentations.


**Course:**
* The application works and is quickly demonstrated, both functional and high-availability.
* 1 environment has already been deployed (you can choose)
* 1 environment will be deployed live during the demo (lecturer's choice)
* The demo takes a maximum of 15 minutes.
* The AWS Console is open and all components of the application can be explained if requested.

	

**Expectations**


* It is expected that the cloud concepts and components used can be explained.
* If you use additional technology, you understand and can explain it both conceptually and practically.
<br><br>

# Important
* Plagiarism is not allowed. (see PXL exam regulations)
There are strict penalties for this, up to and including exclusion from all exams.
* The person who passes on any solutions is also guilty of plagiarism.
* Only individual work is done.
* Commitments are made to GitHub at regular intervals (at least once for every 1 hour of work). Failure to comply with this obligation will result in a '0'.
* There is NO communication about the PE with other students! That is considered plagiarism.

## License

This library is licensed under the MIT-0 License. See the [LICENSE](LICENSE) file.
