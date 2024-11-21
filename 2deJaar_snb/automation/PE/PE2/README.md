[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/jn-1pxQS)
# Automation PE2

## Basis opdracht (50%)

### Doel
Installeer een AWS VM als bastion host en zorg dat die ssh accessible is vanop het Internet als `my_user` met public key-based authentication.

### Manuele Voorbereiding: AWS Bastion VM
- Maak een AWS VM aan, gebaseerd op RHEL, in je AWS Academy Automation I module. Deze dient als **Bastion Host**.
- Vergeet niet in AWS de juiste poort te openen voor ssh access.

### Ansible deployment
- Het paswoord van het private-public keypair voor `my_user` is leeg.
- Overal waar je een paswoord nodig hebt (vault, user password) gebruik je het paswoord `pxl`.
- Je gebruikt ansible vault om de hashed sha-512 versie van het user password (`pxl`) voor de nieuwe user `my_user` te bewaren in `secrets.yaml`. Zie *Extra Informatie* hieronder voor eventuele tips.
- Na het uitvoeren van het playbook is de VM accessible vanop je machine via het Internet via ssh als `my_user` met passwordless public key-based authentication.
- Gebruik een Ansible playbook `playbook.yaml` en bijbehorende Ansible inventory `aws_hosts.ini` om de AWS Bastion VM te configureren:
  - Installeer SSH en Firewalld op de Bastion VM, ga er niet van uit dat die daar al opstaan.
  - Maak de user `my_user` aan met de gehashte versie van het user password uit je vault file.
  - Configureer SSH public key authentication voor `my_user`.
  - Configureer Firewalld om de ssh-poort open te zetten.
  - Maak een bestand `/home/my_user/info.txt` aan met de inhoud "Bastion host configured."

## Extra's (50%)
**De extra's worden *enkel* beoordeeld als de Basis Opdracht succesvol is volbracht.**

Extra's hoeven niet in volgorde worden opgelost.

### Doel

Deploy een webserver die naast de Bastion host staat, waarvoor HTTP vanop het Internet beschikbaar is.

Zorg dat die webserver ssh accessible is **vanop de bastion host** met diezelfde, bestaande `my_user`, met public key-based authentication.

### Manuele Voorbereiding: AWS Webserver VM
- Maak een AWS VM aan, gebaseerd op RHEL, in je AWS Academy Automation I module. Deze dient als **Webserver host**.
- Vergeet niet in AWS de juiste poort te openen voor *ssh access* naar het Internet. Dat is een beetje raar, maar anders kan je niet makkelijk configureren met ansible via ssh vanaf je laptop.

### Extra 1 - Ansible: installeer apache (20%)
- Installeer apache op de webserver.
- Zorg dat die accessible is via het internet, op poort `80`.
- Apache toont files uit `/var/www/html/`.
- Je gebruikt de file in de repo `./files/index.html` als website
- Je gebruikt een *host variable* `apache_index_src` die de file system *locatie* van deze index file bevat.
- Het gebruik van de command en shell modules is niet toegestaan.

### Extra 2 - Ansible: maak een user `my_user` aan die vanaf de bastion host aan de webserver kan met public key-based authentication (20%)
- Vul het Ansible playbook `playbook.yaml` en bijbehorende Ansible inventory `aws_hosts.ini` aan om de AWS Webserver VM te configureren.
- Je maakt een nieuwe user op de webserver aan `my_user` met de hashed version van het paswoord uit je vault file.
- Je zorgt ervoor dat je vanaf de Bastion VM naar de Webserver VM, met diezelfde ssh key die je al gemaakt hebt, kan inloggen als `my_user` (dus zonder paswoord).

### Extra 3 - Ansible: $PATH (10%)
- je toont de `$PATH` environment variable van de webserver host tijdens het uitvoeren van je playbook, *als laatste actie in het playbook.*

## Oplevering
- Maak de nodige aanpassingen in de juiste files en commit deze files.
- Check voor deze ene keer **beide keyfiles** van de user `my_user` in, zodat wij alles kunnen controleren.
- Als je extra files gebruikt voeg die dan toe aan de repo en commit.
- screenshot:
  - installeer `curl` op je laptop (bv met `sudo apt install curl`)
  - voer uit in je shell, maar gebruik je echte Bastion Host IP address ipv `EC2_IP_AWS_BASTIONHOST`, het echte Webserver IP address ipv `EC2_IP_AWS_WEBSERVER` als je die webserver extra's hebt opgelost, en vervang `KEYFILE` door de locatie van de **private key** van `my_user` op je laptop:
    ```
    export aws_bastion=EC2_IP_AWS_BASTIONHOST
    export aws_webserver=EC2_IP_AWS_WEBSERVER
    export my_user_key=KEYFILE
    ```
  - run je ansible playbook nog eens
  - voer uit in je shell, je kan eventueel het `curl` commando weglaten als je extra 2 niet hebt opgelost:
    ```
    ssh -i ./$my_user_key my_user@$aws_bastion 'cat /home/my_user/info.txt'
    curl $aws_webserver
    ssh -i ./$my_user_key my_user@$aws_webserver 'hostname'
    ```
  - neem een screenshot van de console **zodanig dat je de de output van de laatste task ziet** (als je extra 3 hebt opgelost), samen met de output van de 3 commando's hierboven, als **`screenshot.png`** en check deze in in de repo.

### Pas op!
- Manuele aanpassingen aan de VM zijn **niet** toegestaan, tenzij om manueel iets te testen. Vergeet deze manuele acties niet ongedaan te maken.
- Het gebruik van de command en shell modules is **niet**  toegestaan.
- Het gebruik van scripts is **niet**  toegestaan.
- Enkel files die gecommit zijn voor de deadline worden beoordeeld.

## Extra Informatie voor de PE

### Interessante commando's
`mkpasswd --help`

`mkpasswd -m help`

### Ansible multi-line string variables
Use a pipe symbol `|` and don't forget the YAML indentation.
```
my_var: |
  With his own sword,
  Which he did wave against my throat, I have ta’en
  His head from him.
```

### Ansible Jinja2 `lookup()` function
Lookup plugins retrieve data from outside sources such as files, databases, key/value stores, APIs, and other services. Like all templating, lookups execute and are evaluated on the Ansible control machine. Ansible makes the data returned by a lookup plugin available using the standard templating system. 
You can use lookup plugins anywhere you can use templating in Ansible: in a play, in variables file, or in a Jinja2 template.
```
vars:
  file_contents: "{{ lookup('file', 'path/to/file.txt') }}"
```

### Packages on RHEL
- Apache: `httpd`
- ssh server: `openssh-server`
- firewalld: `firewalld`
