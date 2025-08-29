# IaC + Terraform Core Ideas
	•	IaC benefits: repeatability, versioning, automation, drift detection.
	•	Terraform value: provider-agnostic, declarative model, state tracks reality.
	•	Providers: downloaded on init from the Registry; versions pinned in .terraform.lock.hcl.

⸻

# Core Workflow (+ Flags You’ll See on the Exam)

| Command            | What it does                                                                        | Common options / gotchas                                                                             |
|--------------------|-------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| terraform init     | Initializes a working dir, downloads providers/modules, writes  .terraform.lock.hcl | -upgrade (update provider/module versions),  -reconfigure (re-init backend),  -backend-config=...    |
| terraform fmt      | Canonical formatting for HCL files                                                  | -check (CI) -recursive                                                                               |
| terraform validate | Static checks for config correctness                                                | -json (machine output)                                                                               |
| terraform plan     | Shows execution plan (no changes yet)                                               | -var -var-file=FILE  -out=planfile -refresh-only -target=addr  (use sparingly)                       |
| terraform apply    | Applies a plan to reach desired state                                               | planfile (from -out),  -auto-approve -refresh-only  -replace=addr (replacement of tainted resources) |
| terraform destroy  | Destroys managed infra                                                              | -target=addr (use sparingly) -auto-approve                                                           |
| terraform show     | Renders state or a saved plan                                                       | terraform show -json plan.out (for tooling)                                                          |
| terraform graph    | DOT graph of resource deps                                                          | Pipe into Graphviz for images                                                                        |
| terraform output   | Prints output values                                                                | -json terraform output name                                                                          |
| terraform console  | REPL to evaluate HCL expressions                                                    | Great for functions/types debugging                                                                  |

Exam tips
-	Preferred approach to “taint” is now apply -replace=....
- 	-target is for exceptional cases (bootstrap, debugging), not daily use.

⸻

# Variables, Values, and Precedence

Declare inputs in variables.tf:

```hcl
variable "region" {
  type        = string
  description = "AWS region"
  default     = "us-east-1"
}
```

Provide values (several ways):
- terraform.tfvars and *.auto.tfvars (auto-loaded)
- CLI: -var 'region=eu-west-1' or -var-file=prod.tfvars
- Env vars: TF_VAR_region=eu-west-1

Precedence (highest → lowest):
1.	-var
2.	-var-file
3.	*.auto.tfvars & terraform.tfvars
4.	TF_VAR_... environment variables
5.	default in variable block

Other files
- locals {} for computed values within the module.
- outputs.tf exposes values; sensitive = true to hide in CLI.

# Resources, Data, Dependencies, & Meta-Args
- resource: creates/updates/destroys infra.
- data: reads existing info (read-only).
- Dependencies:
    - Implicit: references (e.g., aws_vpc.main.id).
    - Explicit: depends_on = [resource.addr] for non-obvious ordering.

Meta-arguments you should recognize
- count (scalar iteration)
- for_each (map/set iteration, stable addressing)
- provider (choose a specific provider config)
- lifecycle:
    - create_before_destroy
    - prevent_destroy
    - ignore_changes = [attr, ...]
    - replace_triggered_by = [expr, ...] (forces replace when deps change)

Dynamic blocks & expressions
- dynamic "ingress" { for_each = var.rules ... }
- for expressions, if conditionals, splats (*), and functions (e.g., merge, concat, coalesce, regex, cidrsubnet).


# Modules (Using & Authoring)
Call a module:
```hcl
module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 5.0"
  name    = "main"
  cidr    = "10.0.0.0/16"
}
```
- Pin versions (version = "x.y.z" or ~> x.y).
- Outputs from a child module become attributes on module.<name>.

Commands you may see
- Most module ops happen via init, plan, apply. (Legacy terraform get is handled by init.)

# State, Backends, and Drift

State basics
- terraform.tfstate maps config to real resources.
- Contains sensitive data → never commit to VCS.
- Default backend is local. Use remote for teams.

Common remote backends
- S3 (+ DynamoDB for locking), GCS, AzureRM, Terraform Cloud (HCP).

State CLI (know these subcommands)

| Command                   | Purpose                           |
|---------------------------|-----------------------------------|
| terraform state list      | List addresses in state           |
| terraform state show addr | Show a resource’s state           |
| terraform state mv A B    | Move/rename addresses (refactors) |
| terraform state rm addr   | Remove from state (stop managing) |
| terraform state pull      | Download raw state JSON           |
| terraform state push file | Upload state (rare; careful!)     |

Refreshing & drift
- Use terraform plan (default refreshes) or refresh-only mode:
terraform plan -refresh-only → terraform apply -refresh-only

Backends in config
```hcl
terraform {
  backend "s3" {
    bucket         = "tf-state-bucket"
    key            = "env/prod/terraform.tfstate"
    region         = "eu-west-1"
    dynamodb_table = "tf-state-locks"
    encrypt        = true
  }
}
```

# Workspaces (When & When Not)
- Separate state instances within the same config: default, dev, prod, etc.
- Commands:
    - terraform workspace list
    - terraform workspace new dev
    - terraform workspace select dev
    - terraform workspace delete dev
- Use case: lightweight env separation.
Not a substitute for separate accounts/projects where strong isolation is needed.

# Terraform Cloud / HCP Terraform (Know the Value)
- Remote state & locking
- VCS-driven runs (GitHub/GitLab/Bitbucket)
- RBAC, Sentinel (policy as code), run tasks, audit logs
- Private Registry for internal modules/providers
- CLI auth:
    - terraform login (create/store token)
    - terraform logout
- Configuration (cloud or remote block):
```hcl
terraform {
  cloud {
    organization = "acme"
    workspaces { name = "prod" }
  }
}
```

# Providers & Versions
Pin provider versions in required_providers; lockfile .terraform.lock.hcl is updated on init.
```hcl
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.60"
    }
  }
  required_version = ">= 1.3.0"
}
```
Commands you might see:
- terraform providers (what’s in use)
- terraform providers lock -platform=linux_amd64 (multi-platform lockfile)
- terraform version / -json

# Secrets & Sensitive Handling
- Prefer env vars or secret managers; avoid plain text.
- variable "db_password" { sensitive = true } (flag input as sensitive)
- output "password" { value = var.db_password, sensitive = true }
- Don’t expose secrets via logs or outputs unless required.

Logging for troubleshooting
- TF_LOG=TRACE|DEBUG|INFO|WARN|ERROR
- TF_LOG_PATH=./tf.log to capture logs.

# Importing Existing Resources
Map real infra to config without recreating it:
1.	Write the resource block (no create yet).
2.	terraform import <addr> <id>
3.	Run plan to reconcile arguments (sometimes need to fill missing attributes).

Example
```shell
terraform import aws_s3_bucket.logs my-logs-bucket
```

# Targeting & Replacements (Use with Care)
Replace on next apply:
```shell
terraform apply -replace=aws_instance.web
```
Target a subset (debug/bootstrap only):
```shell
terraform plan -target=module.db -target=aws_iam_role.app
```


# JSON Variants & File Types (Know they exist)
- Any *.tf file can be authored as JSON (*.tf.json).
- Vars files support JSON: terraform.tfvars.json.



# Common HCL Patterns You’ll Recognize

for_each vs count
```hcl
resource "aws_iam_user" "u" {
  for_each = toset(var.usernames)
  name     = each.key
}
```
for expressions
```hcl
locals {
  public_subnets = [for s in var.subnets : s.cidr if s.public]
}
```
conditional
```hcl
instance_type = var.is_prod ? "m6i.large" : "t3.medium"
```
dynamic block
```hcl
dynamic "ingress" {
  for_each = var.ports
  content {
    from_port = ingress.value
    to_port   = ingress.value
    protocol  = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
```

Rapid “What Tool When?” Table

| Need                      | Use                                      |
|---------------------------|------------------------------------------|
| Install deps / backend    | init(-upgrade, -reconfigure)             |
| Format & validate         | fmt, validate                            |
| Preview changes           | plan(-out, -target, -refresh-only)       |
| Apply changes             | apply(planfile, -auto-approve, -replace) |
| Tear down                 | destroy                                  |
| Inspect plan/state        | show, state list/show                    |
| Move or remove state addr | state mv, state rm                       |
| Import existing resource  | import                                   |
| Evaluate expressions      | console                                  |
| Visualize deps            | graph                                    |
| Outputs                   | output, -json                            |
| Workspaces                | workspace new/select/list/delete         |
| Cloud auth                | login, logout                            |




# Last-mile Exam Reminders
- Never commit terraform.tfstate. Use a remote backend for teams.
- for_each gives stable addressing vs count (changing size shuffles indices).
- Prefer module & provider version pinning.
- Workspaces = separate state, not separate code or accounts.
- Use -replace instead of legacy taint.
- -target is exceptional; understand why it’s discouraged.