[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ii-Sm7GH)
# Kubernetes PE - PSCFSA II

## Beschrijving

PSCFSA II is een fullstack web app die met een frontend woorden in een database kan steken of verwijderen.

De volgende technologieën worden gebruikt:

- React frontend
- Python/Flask backend
- Postgres database

## Opdracht

- Zorg ervoor dat deze web app in Kubernetes kan draaien.
- Gebruik een k3d cluster en zorg ervoor dat de app beschikbaar is in je browser op http://localhost:8088.

### Evaluatie

#### Minimum requirements om te slagen (10p)

- **Op regelmatige tijdstippen is er gecommit naar github (minstens 1x per elk 1 uur werk).**
- **Verander geen source files, met optionele uitzondering van de frontend Dockerfile (zie Extra's).**
- De app werkt in Kubernetes.
- Je kan woorden toevoegen en verwijderen.
- De app is beschikbaar in je browser via http://localhost:8088.
- De yaml files zijn beschikbaar in de oplossing directory.
- De k3d cluster startup file(s) is beschikbaar in de oplossing directory.

### Extra 1 (2p)

- De database username is 'myname'.
- Het database password is 'mypassword'.

### Extra 2 (2p)

- Het database password is een Kubernetes Secret.

### Extra 3 (3p)

- Alle gebruikte environment variables komen uit Kubernetes ConfigMaps.

### Extra 4 (3p)

- De frontend Dockerfile gebruikt een build stage om een efficiënter container image te bekomen.

# Belangrijk

- Plagiaat is niet toegestaan. (zie PXL examenregelement)
- Hier staan strenge straffen op, tot zelfs uitsluiting van alle examens.
- De persoon die eventuele oplossingen doorgeeft is eveneens schuldig aan plagiaat.
- Er wordt enkel individueel gewerkt.
- Op regelmatige tijdstippen wordt er gecommit naar github (minstens 1x per elk 1 uur werk). Het falen van deze verplichting heeft een '0' tot gevolg.
- Er wordt NIET gecommuniceerd over de PE met andere studenten! Dat wordt beschouwd als plagiaat.
