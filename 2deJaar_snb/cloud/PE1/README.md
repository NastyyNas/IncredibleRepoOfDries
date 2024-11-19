[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/GilRZFCm)
# PE 1: CLI in AWS (25 punten)

## Overzicht
Voor deze PE werk je met de AWS CLI (versie 2). Onderstaande infrastructuur wordt enkel opgezet met de CLI. Je beschrijft de oplossingen zoals gevraagd in het bestand `oplossing.md` met de nodige commando's en screenshots. Screenshots kan je embedden in je oplossing bestand met `![alt text](./images/image.png)`.

Let goed op de naamgevingen van de elementen die je opzet en de gevraagde tags (key & value). Deze moeten **exact** overeenkomen om punten te kunnen krijgen op de verschillende onderdelen.

Het is enkel toegestaan om gebruik te maken van zelfgemaakte notities in `docx` of `pdf` formaat, de officiële AWS (CLI) documentatie op 
[https://docs.aws.amazon.com/index.html](https://docs.aws.amazon.com/index.html), [https://docs.aws.amazon.com/cli/latest/index.html](https://docs.aws.amazon.com/cli/latest/index.html) en [https://docs.aws.amazon.com/cli/index.html](https://docs.aws.amazon.com/cli/index.html)

_Veel succes!_

## Deel 1
Een voorbeeld van de gevraagde output is te vinden onder _Antwoord A_ in de file `oplossing.md`.

### VPC (B) - 1 punt
Maak een nieuwe VPC aan met als cidr block 10.0.0.0/16. Zorg ervoor dat deze VPC een tag krijgt met als key `Name` en als value `vpc-pe`.

Documenteer je commando(s) en screenshot in `oplossing.md` onder de titel `Antwoord B`.

### Publieke subnets (C) - 3 punten
Voorzie 2 publieke subnets met de cidr blocks 10.0.0.0/24 en 10.0.1.0/24 in de availability zone's us-east-1a en us-east-1b. Beide subnets moeten publieke IPs geven bij het aanmaken van nieuwe EC2 instances. Tip: `--map-public-ip-on-launch`.

 Zorg ervoor dat deze subnets tags krijgen met als key `Name` en als value `vpc-pe-public-1` en `vpc-pe-public-2`

### VPC internet connectie (D) - 2 punten
Zorg ervoor dat de VPC voorzien is van een internetverbinding a.d.h.v. een internet gateway (naam `vpc-pe-igw`) & route-table (naam `vpc-pe-rtb`). Voorzie de nodige routes in je route tabel zodat de publieke subnets bereikbaar zijn.

Documenteer je commando(s) en screenshot in `oplossing.md` onder de titel `Antwoord C`.

### Private subnets (E) - 4 punten
Voorzie 2 private subnets met de cidr blocks 10.0.2.0/24 en 10.0.3.0/24 in de availability zones us-east-1a en us-east-1b. Zorg ook dat elk subnet zijn eigen route table heeft met namen `vpc-pe-rtb-private-1` en `vpc-pe-rtb-private-2`.

Zorg ervoor dat deze subnest tags krijgen met als key `Name` en als value `vpc-pe-private-1` en `vpc-pe-private-2`.

### VPC NAT gateway (F) - 2 punten
Voorzie 1 NAT gateway met als naam `vpc-pe-nat` en voorzie ook de juiste entries in de route tabellen. Je mag de NAT gateway hergebruiken voor beide private subnets.

## Deel 2

### Instance types (G) - 1 punt
Je kan met het commando `aws ec2 describe-instance-types` alle instance types opvragen. Geef ons het aangepaste commando waarmee je alle instance types opvraagt waarbij de `intance-type` begint met `t3`

### EC2 instances (H) - 4 punten
Maak 2 nieuwe instances (beide in een ander publiek subnet) met als naam `ec2-pe-1` en `ec2-pe-2` gebruik makend van de `Amazon linux 2 AMI (HVM) - Kernel 5.10 SSD Volume type`. Je kan de `ami-id` opzoeken via de CLI indien nodig. De instance type is `t2.micro` en je koppelt de `vockey` aan de instance. Zorg ervoor dat beide instances gebruik maken van het userdata script dat je kan terugvinden in de root directory van deze folder (`./userdata`).

Zorg er eveneens voor dat de instances gebruik maken van een  security group met als naam `secgroup-pe` waarin je voorziet dat ze toegankelijk zijn vanaf het internet (poort 80) en via ssh (poort 22).

### Loadbalancer (I) - 4 punten
Zet een application loadbalancer met als naam "lb-pe" op die het verkeer stuurt naar de 2 machines uit de vorige opgave. Voorzie de nodige infrastructuur.

In deze folder, pas de file `./website/index.html` aan. Op regel 29 pas je de url aan naar die van jouw load balancer.

## Deel 3

### Static S3 website (J) - 4 punten
Zet een nieuwe s3 bucket op met als naam `cloud-pe-studentennummer`. Zorg ervoor dat deze bucket werkt als een static website die je kan bezoeken. Voorzie de nodige permissies en koppel onderstaalde policy:
```
{
  "Version": "2012-10-17",
  "Statement": [
      {
          "Sid": "PublicReadGetObject",
          "Effect": "Allow",
          "Principal": "*",
          "Action": "s3:GetObject",
          "Resource": "arn:aws:s3:::bucket-name/*"
      }
  ]
}
```

Upload de file `./website/index.html` naar de root directory van je s3 bucket via de CLI.

Bezoek de website van je S3 bucket via de browser en voeg een screenshot van de webpagina toe aan je oplossingbestand. De url kan je achterhalen a.d.h.v. de volgende syntax:
```
http://cloud-pe-studentennummer.s3-website-<AWS_REGION>.amazonaws.com
```

Bij het bezoeken van deze pagina zou er in het midden een hash moeten verschijnen. Indien er een titel "Cloud serivces rocks!" verschijnt, wil dit zeggen dat de communicatie tussen S3 bucket en je loadbalancer niet werkt.